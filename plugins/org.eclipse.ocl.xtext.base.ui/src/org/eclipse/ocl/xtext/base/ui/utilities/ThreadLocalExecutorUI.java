/*******************************************************************************
 * Copyright (c) 2021, 2024 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.ui.utilities;

import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * The ThreadLocalExecutorUI enhances ThreadLocalExecutor to support the main/UI thread and delegate to a
 * a distinct ThreadLocalExecutor context for each WorkbenchPart. If there is no known WorkbenchPart
 * the inherited just-a-thread functionality is used.
 *
 * @since 1.14
 */
public class ThreadLocalExecutorUI extends ThreadLocalExecutor implements IPartListener
{
	/**
	 * The null part thread for use when no part has been opened.
	 */
	private static final @Nullable ThreadLocalExecutor NOT_A_PART_THREAD = null;

	/**
	 * Return the ThreadLocalExecutor for the activePart. Returns null if on a worker thread or if
	 * no IWorkbenchPart is active.
	 */
	public static @Nullable ThreadLocalExecutor basicGetPartThread() {
		ThreadLocalExecutor threadLocalExecutor = get();
		if (threadLocalExecutor instanceof ThreadLocalExecutorUI) {
			return ((ThreadLocalExecutorUI)threadLocalExecutor).activePartThread;
		}
		return null;
	}

	/**
	 * Close all editors using EnvironmentFactory instances. This method is intended solely for use at the end of tests
	 * for which an auto-editor created by the debugger may be hard to locate.
	 */
	public static void closeEditors() {
		ThreadLocalExecutor threadLocalExecutor = get();
		((ThreadLocalExecutorUI)threadLocalExecutor).localCloseEditors();
	}

	/**
	 * Initialize the partThread for initPart to ensure that an EnvironmentFactory created by the callBack is
	 * attached to the initPart rather than the activePart. This is typically invoked by an OCL-aware part such
	 * as the BaseEditor to configure its EnvironmentFactory while executing on behalf of whichever part provides
	 * the trigger such as an Open OCL Editor menu action.
	 */
	public static void init(@NonNull IWorkbenchPart initPart, @NonNull InitWrapperCallBack<?,?> callBack) {
		ThreadLocalExecutor threadLocalExecutor = get();
		((ThreadLocalExecutorUI)threadLocalExecutor).localInit(initPart, callBack);
	}
	/**
	 * Execute the callBack after establishing that the initPartThread is for the active thread. This is typically
	 * invoked by an OCL-aware part such as the BaseEditor to establish the relevant init EnvironmentFactory after
	 * construction of the initPartThread has commenced but before it is activated.
	 * @param needsInit
	 */
	public static void init(@NonNull ThreadLocalExecutor initPartThread, @NonNull InitWrapperCallBack<?,?> callBack, @NonNull NeedsInit needsInit) {
		ThreadLocalExecutor threadLocalExecutor = get();
		threadLocalExecutor.localInit(initPartThread, callBack, needsInit);
	}

	/**
	 * Return the IPartListener for test purposes.
	 */
	public static @NonNull IPartListener internalGetPartListener() {
		return (ThreadLocalExecutorUI)get();
	}

	/**
	 * Return true if OCL activity for partThread requires wrapping to enforce partThread
	 * as the prevailing context. If the current thread has no EnvironmentFactory, it is inferred
	 * from the partThread.
	 */
	public static @NonNull NeedsInit needsInit(@NonNull ThreadLocalExecutor partThread) {
		ThreadLocalExecutor threadLocalExecutor = ThreadLocalExecutorUI.get();
		if (!(threadLocalExecutor instanceof ThreadLocalExecutorUI)) {							// Ordinary worker thread
			return NeedsInit.ATTACH_FROM_PART_THREAD;
		}
		else if (((ThreadLocalExecutorUI)threadLocalExecutor).activePartThread == partThread) {	// Current part thread
			return NeedsInit.AS_IS;
		}
		else {
			return NeedsInit.WRAP_WITH_PART_THREAD;
		}
	}

	/**
	 * The overriding active part established by init() for the duration of an initialization such as
	 * openEditor before the relevant editor part is opened. null when no override in place.
	 */
	private @Nullable ThreadLocalExecutor initPartThread = null;

	/**
	 * The most recently partActivated(), null if partDeactivated.
	 */
	private @Nullable ThreadLocalExecutor activatedPartThread = NOT_A_PART_THREAD;

	/**
	 * The currently active part which is initPart else activatedPart.
	 */
	private @Nullable ThreadLocalExecutor activePartThread = NOT_A_PART_THREAD;

	/**
	 * The currently partClosed(), null if partDeactivated.
	 */
	private @Nullable ThreadLocalExecutor closingPartThread = NOT_A_PART_THREAD;

	/**
	 * An IWorkbenchPart to EnvironmentFactoryInternal binding is present for every OCL-using IWorkbenchPart.
	 * The binding is explicitly established for OCL-aware IWorkbenchParts by calls of initPart and destroyed by partClosed.
	 * The binding is implicitly established for OCL-blind IWorkbenchParts by calls of initPart from lazy OCL creation and destroyed by a partClosed.
	 */
	protected final @NonNull Map<@NonNull IWorkbenchPart, @NonNull ThreadLocalExecutor> part2partThread = new WeakHashMap<>();	// Weak needed in case part never opened

	@Override
	protected @NonNull ThreadLocalExecutor createInstance() {
		if (Display.getCurrent() == null) {
			return super.createInstance();
		}
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
		if (activeWorkbenchWindow != null) {
			IPartService partService = activeWorkbenchWindow.getPartService();
			if (partService != null) {
				partService.addPartListener(this);
				IWorkbenchPart activePart = partService.getActivePart();
				if (activePart != null) {
					partActivated(activePart);
				}
			}
		}
	//	debugState();
		return this;
	}

/*	@Override
	protected void debugState(@NonNull StringBuilder s) {
		super.debugState(s);
		s.append("\n\tinitPartThread = " + String.valueOf(initPartThread));
		s.append("\n\tactivatedPartThread = " + String.valueOf(activatedPartThread));
		s.append("\n\tactivePartThread = " + String.valueOf(activePartThread));
		for (Entry<@NonNull IWorkbenchPart, @NonNull ThreadLocalExecutorPart> entry : part2partThread.entrySet()) {
			IWorkbenchPart part = entry.getKey();
			ThreadLocalExecutorPart partThread = entry.getValue();
			s.append("\n\t" + NameUtil.debugSimpleName(part) + " => " + partThread.toString());
		}
	} */

	protected synchronized @NonNull ThreadLocalExecutor getPartThread(@NonNull IWorkbenchPart part) {
		ThreadLocalExecutor partThread = part2partThread.get(part);
		if (partThread == null) {
			String threadName = "[" + Thread.currentThread().getName() + ":" + NameUtil.debugSimpleName(part) + "]";
			partThread = new ThreadLocalExecutor(threadName) {
			/*	@Override
				public void debugState(@NonNull StringBuilder s) {
				//	super.debugState(s);
				} */
			};
			part2partThread.put(part, partThread);
		}
		return partThread;
	}

	@Override
	protected @NonNull String getThreadName() {
		StringBuilder s = new StringBuilder();
		if (activatedPartThread != null) {
			s.append(activatedPartThread.getName());
		}
		else {
			s.append("[");
			s.append(Thread.currentThread().getName());
			s.append("]");
		}
		return s.toString();
	}

	@Override
	public void localAttachEnvironmentFactory(@NonNull EnvironmentFactoryInternal newEnvironmentFactory) {
		assert closingPartThread == null;
		if (activePartThread != null) {
			activePartThread.localAttachEnvironmentFactory(newEnvironmentFactory);
		//	debugState();
		}
		else {
			super.localAttachEnvironmentFactory(newEnvironmentFactory);
		}
	}

	@Override
	public @Nullable EnvironmentFactoryInternal localBasicGetEnvironmentFactory() {
		if (closingPartThread != null) {
			return closingPartThread.localBasicGetEnvironmentFactory();
		}
		else if (activePartThread != null) {
			return activePartThread.localBasicGetEnvironmentFactory();
		}
		else {
			return super.localBasicGetEnvironmentFactory();
		}
	}

	@Override
	public @Nullable Executor localBasicGetExecutor() {
		assert closingPartThread == null;
		if (activePartThread != null) {
			return activePartThread.localBasicGetExecutor();
		}
		else {
			return super.localBasicGetExecutor();
		}
	}

	@Override
	public void localDetachEnvironmentFactory(@NonNull EnvironmentFactory environmentFactory) {
		if (closingPartThread != null) {
			closingPartThread.localDetachEnvironmentFactory(environmentFactory);
		}
		else if (activePartThread != null) {
			activePartThread.localDetachEnvironmentFactory(environmentFactory);
		}
		else {
			super.localDetachEnvironmentFactory(environmentFactory);
		}
	}

	private void localCloseEditors() {
		assert closingPartThread == null;
		assert initPartThread == null;
		for (@NonNull IWorkbenchPart part : new ArrayList<>(part2partThread.keySet())) {
			if (part instanceof IEditorPart) {
				IEditorPart editorPart = (IEditorPart)part;
				editorPart.getSite().getPage().closeEditor(editorPart, false);
			}
		}
	}

	/**
	 * Initialize the initPart after ensuring that an EnvironmentFactory created by the callBack is attached
	 * to the initPart rather than the activePart. THis is typically invoked by an OCL-aware part such as the
	 * BaseEditor to configure its EnvironmentFactory while executing on behalf of whichever part provides
	 * the trigger such as an Open OCL Editor menu action.
	 * @throws PartInitException
	 */
	public void localInit(@NonNull IWorkbenchPart initPart, @NonNull InitWrapperCallBack<?,?> callBack) {//throws PartInitException {
		ThreadLocalExecutor initPartThread = getPartThread(initPart);
		localInit(initPartThread, callBack, NeedsInit.WRAP_WITH_PART_THREAD);
	}
	@Override
	public void localInit(@NonNull ThreadLocalExecutor initPartThread, @NonNull InitWrapperCallBack<?,?> callBack, @NonNull NeedsInit needsInit) {
		assert closingPartThread == null;
	//	assert activePart != NOT_A_PART_THREAD;			// First init is from NOT_A_PART_THREAD
		if (needsInit == NeedsInit.WRAP_WITH_PART_THREAD) {
			assert this.activePartThread == this.activatedPartThread;
			ThreadLocalExecutor savedActivePartThread = this.activePartThread;
			this.activePartThread = this.initPartThread = initPartThread;
			//	System.out.println(getBracketedThreadName() + " activePartThread " + NameUtil.debugSimpleName(activePartThread));
			callBack.run();
			this.initPartThread = null;
			this.activePartThread = savedActivePartThread;
		//	System.out.println(getBracketedThreadName() + " activePartThread " + NameUtil.debugSimpleName(activePartThread));
		}
		else {
			super.localInit(initPartThread, callBack, needsInit);
		}
	}

	@Override
	public void localRemoveEnvironmentFactory() {
		assert closingPartThread == null;
		if (activePartThread != null) {
			activePartThread.localRemoveEnvironmentFactory();
		}
		else {
			super.localRemoveEnvironmentFactory();
		}
	}

	@Override
	public synchronized void localReset() {
		assert closingPartThread == null;
		if (activePartThread != null) {
			activePartThread.localReset();
		}
		else {
			super.localReset();
		}
	}

	@Override
	public void localSetExecutor(@Nullable Executor executor) {
		assert closingPartThread == null;
		if (activePartThread != null) {
			activePartThread.localSetExecutor(executor);
		}
		else {
			super.localSetExecutor(executor);
		}
	}

	@Override
	public void partActivated(IWorkbenchPart newActivePart) {
		assert closingPartThread == null;
		assert initPartThread == null;
		assert newActivePart != null;
		ThreadLocalExecutor newPartThread = getPartThread(newActivePart);
		assert newPartThread != null;
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " partActivated [" + Thread.currentThread().getName() + ":" + NameUtil.debugSimpleName(newActivePart) + "] " + toString());
		}
		this.activatedPartThread = newPartThread;
		this.activePartThread = newPartThread;
	//	debugState();
	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		assert closingPartThread == null;
		assert initPartThread == null;
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " partBroughtToTop [" + Thread.currentThread().getName() + ":" + NameUtil.debugSimpleName(part) + "] " + toString());
		}
	//	debugState();
	}

	@Override
	public void partClosed(IWorkbenchPart oldOpenPart) {
		assert closingPartThread == null;
		assert initPartThread == null;
		assert oldOpenPart != null;
		assert oldOpenPart != activatedPartThread;
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " partClosed [" + Thread.currentThread().getName() + ":" + NameUtil.debugSimpleName(oldOpenPart) + "] " + toString());
		}
		@SuppressWarnings("unused")
		ThreadLocalExecutor oldPartThread = part2partThread.remove(oldOpenPart);
	//	assert oldPartThread != null;				-- may not have been observed to open e.g. ProblemsView
		if (oldPartThread != null) {
			try {
				closingPartThread = oldPartThread;
				oldPartThread.setEnvironmentFactory(null);
			}
			finally {
				closingPartThread = NOT_A_PART_THREAD;
			}
		}
		activePartThread = activatedPartThread;
	//	debugState();
	}

	@Override
	public void partDeactivated(IWorkbenchPart oldActivePart) {
		assert closingPartThread == null;
		assert initPartThread == null;
		assert oldActivePart != null;
	//	assert activePartThread != NOT_A_PART_THREAD;
		if (activePartThread != NOT_A_PART_THREAD) {		// May never have been activated
			ThreadLocalExecutor oldPartThread = part2partThread.get(oldActivePart);		// XXX
			assert activePartThread == oldPartThread;
		}
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " partDeactivated [" + Thread.currentThread().getName() + ":" + NameUtil.debugSimpleName(oldActivePart) + "] " + toString());
		}
		this.activatedPartThread = NOT_A_PART_THREAD;
		this.activePartThread = NOT_A_PART_THREAD;
	//	debugState();
	}

	@Override
	public void partOpened(IWorkbenchPart newOpenPart) {
		assert closingPartThread == null;
		assert newOpenPart != null;
		assert initPartThread == null;
		getPartThread(newOpenPart);
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " partOpened [" + Thread.currentThread().getName() + ":" + NameUtil.debugSimpleName(newOpenPart) + "] " + toString());
		}
	//	debugState();
	}

	@Override
	public void setEnvironmentFactory(@Nullable EnvironmentFactoryInternal newEnvironmentFactory) {
		assert closingPartThread == null;
		assert initPartThread == null;
		if (activePartThread != null) {
			activePartThread.setEnvironmentFactory(newEnvironmentFactory);
		}
		else {
			super.setEnvironmentFactory(newEnvironmentFactory);
		}
	}
}