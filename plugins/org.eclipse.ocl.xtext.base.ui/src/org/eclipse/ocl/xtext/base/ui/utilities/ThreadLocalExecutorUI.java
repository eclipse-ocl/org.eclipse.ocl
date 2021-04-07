package org.eclipse.ocl.xtext.base.ui.utilities;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * The ThreadLocalExecutorUI enhances ThreadLocalExecutor to maintain a distinct ThreadLocal contxt for each
 * WorkbenchPart.
 *
 * @since 1.14
 */
public class ThreadLocalExecutorUI extends ThreadLocalExecutor implements IPartListener
{
	private @Nullable IWorkbenchPart activePart = null;;
//	private @Nullable IWorkbenchPart openPart = null;;

	/**
	 * An IWorkbenchPart to EnvironmentFactoryInternal binding is present for every OCL-using IWorkbenchPart.
	 * The binding is explicitly established for OCL-aware IWorkbenchParts by calls of initPart and destroyed by killPart from creation/dispose.
	 * The binding is implicitly established for OCL-blind IWorkbenchParts by calls of initPart from lazy OCL creation and destroyed by a killPart from partClosed.
	 */
	protected final @NonNull Map<@NonNull IWorkbenchPart, @NonNull EnvironmentFactoryInternal> part2environmentFactory = new HashMap<>();

	public ThreadLocalExecutorUI() {}

/*	protected void activate(@NonNull IWorkbenchPart newActivePart) {
		check();
		assert activePart == null;


		EnvironmentFactoryInternal newEnvironmentFactory = part2environmentFactory.remove(newActivePart);
		if (newEnvironmentFactory == null) {
			newEnvironmentFactory = localBasicGetEnvironmentFactory();
		}
		if (newEnvironmentFactory != null) {
			localAttachEnvironmentFactory(newEnvironmentFactory);
		}





	//	EnvironmentFactoryInternal newEnvironmentFactory = part2environmentFactory.get(newActivePart);
	//	assert oldEnvironmentFactory == null;
	//	EnvironmentFactoryInternal environmentFactory = localBasicGetEnvironmentFactory();
	//	if (environmentFactory != null) {
	//		part2environmentFactory.put(oldActivePart, environmentFactory);
	//		localDetachEnvironmentFactory(environmentFactory);
	//	}
		activePart = newActivePart;
		check();
	} */

/*	protected void check() {
		EnvironmentFactoryInternal environmentFactory = localBasicGetEnvironmentFactory();
		IWorkbenchPart activePart2 = activePart;
		if (activePart2 != null) {
			assert !part2environmentFactory.containsKey(activePart2);
			EnvironmentFactory partEnvironmentFactory = activePart2.getAdapter(EnvironmentFactory.class);
		//	assert partEnvironmentFactory != null;
			assert partEnvironmentFactory == environmentFactory;		// FIXME not true once we use EMF apps
		}
		else {
	//		assert environmentFactory == null;
		}
	} */

	@Override
	protected @NonNull ThreadLocalExecutor createInstance() {
		if (Display.getCurrent() == null) {
			return super.createInstance();
		}
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
	//	IPartService service = workbench.getService(IPartService.class);
		if (activeWorkbenchWindow != null) {
//
			IPartService partService = activeWorkbenchWindow.getPartService();
			if (partService != null) {
			//	partActivated(partService.getActivePart());
				partService.addPartListener(this);
			}
		//	IWorkbenchPart activePart = partService.getActivePart();

		//	IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
		//	IEditorPart activeEditor = activePage.getActiveEditor();
		//	if (activeEditor != null) {
		//		IWorkbenchPartSite site = activeEditor.getSite();
		//		site.getPage().addPartListener(this);
		//	}
		}
		return this;
	}

/*	protected void deactivate(@NonNull IWorkbenchPart oldActivePart) {
		check();
		assert activePart == oldActivePart;
		EnvironmentFactoryInternal oldEnvironmentFactory = part2environmentFactory.get(oldActivePart);
		assert oldEnvironmentFactory == null;
		EnvironmentFactoryInternal environmentFactory = localBasicGetEnvironmentFactory();
		if (environmentFactory != null) {
			part2environmentFactory.put(oldActivePart, environmentFactory);
			localDetachEnvironmentFactory(environmentFactory);
		}
	} */

	@Override
	protected @NonNull String getThreadName() {
		return "[" + Thread.currentThread().getName() + ":" + NameUtil.debugSimpleName(activePart) + /*":" + NameUtil.debugSimpleName(openPart) +*/ "]";
	}

	public void initPart(@Nullable IWorkbenchPart initActivePart, @NonNull EnvironmentFactoryInternal initEnvironmentfactory) {
		if (initActivePart == null) {			// If implicit OCL-bland init
			initActivePart = this.activePart;
			assert initActivePart != null;
		}
		EnvironmentFactoryInternal oldEnvironmentFactory = part2environmentFactory.put(initActivePart, initEnvironmentfactory);
		assert oldEnvironmentFactory == null;
	}

	public void killPart(@NonNull IWorkbenchPart killActivePart) {
		EnvironmentFactoryInternal oldEnvironmentFactory = part2environmentFactory.remove(killActivePart);
		assert oldEnvironmentFactory != null;
	}

	@Override
	protected void localReset() {
		IWorkbenchPart activePart2 = activePart;
		if (activePart2 != null) {
			EnvironmentFactoryInternal environmentFactory = localBasicGetEnvironmentFactory();
			if (environmentFactory != null) {			// Opening a new editorr
				EnvironmentFactoryInternal oldEnvironmentFactory = part2environmentFactory.put(activePart2, environmentFactory);
				assert (oldEnvironmentFactory == null) || (oldEnvironmentFactory == environmentFactory);
			}
		}
		super.localReset();
	}

	@Override
	public void partActivated(IWorkbenchPart newActivePart) {
		assert newActivePart != null;
//		check();
		activePart = newActivePart;
		setEnvironmentFactory(part2environmentFactory.get(activePart));
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " partActivated " + toString());
		}
//		check();
	}

	@Override
	public void partBroughtToTop(IWorkbenchPart p) {
//		check();
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " partBroughtToTop " + toString());
		}
	}

	@Override
	public void partClosed(IWorkbenchPart oldOpenPart) {
		assert oldOpenPart != null;
		assert oldOpenPart != activePart;
		killPart(oldOpenPart);
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " partClosed " + toString());
		}
	//	openPart = null;
	}

	@Override
	public void partDeactivated(IWorkbenchPart oldActivePart) {
		assert oldActivePart != null;
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " partDeactivated " + toString());
		}
		activePart = null;
		setEnvironmentFactory(null);
//		check();
	}

	@Override
	public void partOpened(IWorkbenchPart newOpenPart) {
	//	check();
	//	if (activePart == null) {
	//		partActivated(newOpenPart);
	//	}
	//	if (openPart == null) {
	//		openPart = newOpenPart;
	//	}
	//	else {
		//	assert openPart == newOpenPart;
	//	}
	//	EnvironmentFactoryInternal environmentFactory = localBasicGetEnvironmentFactory();
	//	EnvironmentFactoryInternal newEnvironmentFactory = part2environmentFactory.get(newOpenPart);
	//	assert newEnvironmentFactory == null;
	//	assert environmentFactory == newEnvironmentFactory;
		if (THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
			THREAD_LOCAL_ENVIRONMENT_FACTORY.println(getThreadName() + " partOpened " + toString());
		}
	//	check();
	}
}