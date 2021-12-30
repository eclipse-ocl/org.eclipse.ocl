/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bug 237205
 *   Patrick Könemann - Bug 294200 (history)
 *   E.D.Willink - rework of LPG OCL Console for Xtext
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.console;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ocl.common.internal.preferences.BooleanPreference;
import org.eclipse.ocl.examples.debug.vm.core.VMVariable;
import org.eclipse.ocl.examples.debug.vm.data.VMVariableData;
import org.eclipse.ocl.examples.xtext.console.actions.CloseAction;
import org.eclipse.ocl.examples.xtext.console.actions.DebugAction;
import org.eclipse.ocl.examples.xtext.console.actions.LoadExpressionAction;
import org.eclipse.ocl.examples.xtext.console.actions.SaveExpressionAction;
import org.eclipse.ocl.examples.xtext.console.messages.ConsoleMessages;
import org.eclipse.ocl.examples.xtext.console.xtfo.EmbeddedXtextEditor;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.AbstractLogger;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.EvaluationHaltedException;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.context.ClassContext;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal;
import org.eclipse.ocl.pivot.internal.resource.EnvironmentFactoryAdapter;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.options.PivotConsoleOptions;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory.EnvironmentFactoryExtension;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.OCLHelper;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.Value;
import org.eclipse.ocl.xtext.base.ui.model.BaseDocument;
import org.eclipse.ocl.xtext.base.ui.utilities.BaseUIUtil;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLCSResource;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.actions.ClearOutputAction;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.contentassist.DefaultContentAssistantFactory;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.ui.editor.outline.impl.OutlinePage;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Injector;

/**
 * The page implementing the Interactive OCL console.
 */
public class OCLConsolePage extends Page //implements MetamodelManagerListener
{
	public static enum ColorChoices
	{
		DEFAULT,
		ERROR
	}

	public static class InterrogatableContentAssistantFactory extends DefaultContentAssistantFactory
	{
		@Override
		protected ContentAssistant createAssistant() {
			return new InterrogatableContentAssistant();
		}
	}

	public static final class InterrogatableContentAssistant extends ContentAssistant
	{
		@Override
		public boolean isProposalPopupActive() {
			return super.isProposalPopupActive();
		}
	}

	private class EvaluationRunnable implements IRunnableWithProgress
	{
		private final @NonNull CSResource resource;
		private final @NonNull String expression;
		private Object value = null;

		public EvaluationRunnable(@NonNull CSResource resource, @NonNull String expression) {
			this.resource = resource;
			this.expression = expression;
		}

		public Object getValue() {
			return value;
		}

		@Override
		public void run(final IProgressMonitor monitor) {
			monitor.beginTask(NLS.bind(ConsoleMessages.Progress_Title, expression), 10);
			monitor.subTask(ConsoleMessages.Progress_Synchronising);
			monitor.worked(1);
			//			CS2ASResourceAdapter csAdapter = CS2ASResourceAdapter.getAdapter((BaseCSResource)resource, metamodelManager);
			EnvironmentFactoryInternal environmentFactory = (EnvironmentFactoryInternal) editor.getEnvironmentFactory();
			ThreadLocalExecutor.attachEnvironmentFactory(environmentFactory);
			try {
				//			monitor.subTask(ConsoleMessages.Progress_CST);
				//			try {
				//				csAdapter.refreshPivotMappings();
				//			} catch (Exception e) {
				//				value = new ExceptionValue(valueFactory, ConsoleMessages.Result_MappingFailure, e);
				//				return;
				//			}
				//			monitor.worked(2);
				//			monitor.subTask(ConsoleMessages.Progress_AST);
				ExpressionInOCL expressionInOCL;
				try {
					PivotUtil.checkResourceErrors("", resource); //$NON-NLS-1$
					expressionInOCL = parserContext.getExpression(resource);
				} catch (ParserException e) {
					value = new InvalidValueException(e, ConsoleMessages.Result_ParsingFailure);
					return;
				}
				if (expressionInOCL != null) {
					//			monitor.worked(2);
					monitor.subTask(ConsoleMessages.Progress_Extent);
					ModelManager modelManager = environmentFactory.createModelManager(contextObject);
					//				EvaluationEnvironment evaluationEnvironment = environmentFactory.createEvaluationEnvironment(expressionInOCL, modelManager);
					assert ThreadLocalExecutor.basicGetExecutor() == null;
					ExecutorInternal executor = ((EnvironmentFactoryExtension)environmentFactory).createExecutor(modelManager);
					ThreadLocalExecutor.setExecutor(executor);
					executor.initializeEvaluationEnvironment(expressionInOCL);
					EvaluationEnvironment evaluationEnvironment = executor.getRootEvaluationEnvironment();
					Object contextValue = executor.getIdResolver().boxedValueOf(contextObject);
					evaluationEnvironment.add(ClassUtil.nonNullModel(expressionInOCL.getOwnedContext()), contextValue);
					monitor.worked(2);
					monitor.subTask(ConsoleMessages.Progress_Evaluating);
					try {
						//				metamodelManager.setMonitor(monitor);
						EvaluationVisitor evaluationVisitor = executor.getEvaluationVisitor();
						evaluationVisitor.setMonitor(BasicMonitor.toMonitor(monitor));
						executor.setLogger(new AbstractLogger()
						{
							@Override
							public void print(final @NonNull String message) {
								OCLConsolePage.this.getControl().getDisplay().asyncExec(new Runnable()
								{
									@Override
									public void run() {
										OCLConsolePage.this.append(message, ColorManager.DEFAULT, false);
									}
								});
							}
						});
						value = evaluationVisitor.visitExpressionInOCL(expressionInOCL);
					} catch (EvaluationHaltedException e) {
						value = new InvalidValueException(ConsoleMessages.Result_EvaluationTerminated);
					} catch (InvalidValueException e) {
						value = e;
					} catch (Exception e) {
						value = new InvalidValueException(e, ConsoleMessages.Result_EvaluationFailure);
					} finally {
						//				metamodelManager.setMonitor(null);
						ThreadLocalExecutor.setExecutor(null);;
					}
				}
				monitor.worked(4);
			}
			finally {
				ThreadLocalExecutor.detachEnvironmentFactory(environmentFactory);
			}
		}
	}

	/**
	 * A key listener that listens for the Enter key to evaluate the OCL
	 * expression.
	 */
	private class InputKeyListener implements KeyListener {
		private boolean evaluationSuccess = false;
		private List<String> history = new ArrayList<String>();
		private int currentHistoryPointer = 0;

		@Override
		public void keyPressed(KeyEvent e) {
			IContentAssistant contentAssistant = editor.getViewer().getContentAssistant();
			if ((contentAssistant instanceof InterrogatableContentAssistant)
					&& ((InterrogatableContentAssistant)contentAssistant).isProposalPopupActive()) {
				return;
			}
			switch (e.keyCode) {
				case SWT.CR :
					if ((e.stateMask & (SWT.CTRL | SWT.SHIFT)) == 0) {
						String text = getEditorDocument().get();
						evaluationSuccess = evaluate(text.trim());
					}
					break;
				case SWT.PAGE_UP :
					if ((e.stateMask & (SWT.CTRL | SWT.SHIFT)) == 0) {
						// history
						if (currentHistoryPointer == 0 && history.size() > 0) {
							if (history.size() > 0 && history.get(0).length() == 0) {
								history.remove(0);
							}
							history.add(0, getEditorDocument().get().trim());
							currentHistoryPointer = 1;
							setTextFromHistory();
						} else if (currentHistoryPointer < history.size() - 1) {
							currentHistoryPointer++;
							setTextFromHistory();
						}
					}
					break;
				case SWT.PAGE_DOWN :
					if ((e.stateMask & (SWT.CTRL | SWT.SHIFT)) == 0) {
						// history
						if (currentHistoryPointer > 0) {
							currentHistoryPointer--;
							setTextFromHistory();
						}
					}
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.keyCode) {
				case SWT.CR :
					if ((e.stateMask & (SWT.CTRL | SWT.SHIFT)) == 0) {
						if (evaluationSuccess) {
							getEditorDocument().set(""); //$NON-NLS-1$
							// history
							if (history.size() > 0 && history.get(0).trim().length() == 0) {
								history.remove(0);
							}
							if (history.size() == 0 || !history.get(0).equals(lastOCLExpression.trim())) {
								history.add(0, lastOCLExpression.trim());
							}
							currentHistoryPointer = 0;
						}
						evaluationSuccess = false;
					}
					break;
					//			case ' ':
					//			    if ((e.stateMask & SWT.CTRL) == SWT.CTRL) {
					//			        input.getContentAssistant().showPossibleCompletions();
					//			    }
			}
		}

		protected void setTextFromHistory() {
			String newText = history.get(currentHistoryPointer);
			getEditorDocument().set(newText);
			input.setSelectedRange(newText.length(), 0);
		}
	}

	private final OCLConsole console;
	private Composite page;

	private ITextViewer output;
	private ColorManager colorManager;

	private SourceViewer input;
	private EmbeddedXtextEditor editor;
	private String lastOCLExpression;
	private DebugAction debugAction;

	private ISelectionService selectionService;
	private ISelectionListener selectionListener;
	private @Nullable EObject contextObject = null;
	private @Nullable Iterable<org.eclipse.ocl.pivot.@NonNull Class> contextModelClasses = null;
	private ParserContext parserContext;

	/**
	 * Initializes me.
	 * @param console
	 */
	protected OCLConsolePage(OCLConsole console) {
		super();
		//		this.metamodelManager = new CancelableMetamodelManager();
		this.console = console;
	//	System.out.println("Create " + NameUtil.debugSimpleName(this));
	}

	/**
	 * Appends the specified text to the output viewer.
	 *
	 * @param text the text to append
	 * @param rgb the color to print the text with
	 * @param bold whether to print the text bold
	 */
	protected void append(String text, RGB rgb, boolean bold) {

		IDocument doc = getDocument();
		try {
			int offset = doc.getLength();
			text = (text != null ? text : "") + '\n';
			int length = text.length();
			if (offset > 0) {
				doc.replace(offset, 0, text);
			} else {
				doc.set(text);
			}
			StyleRange style = new StyleRange();
			style.start = offset;
			style.length = length;
			style.foreground = colorManager.getColor(rgb);

			if (bold) {
				style.fontStyle = SWT.BOLD;
			}

			output.getTextWidget().setStyleRange(style);
		} catch (BadLocationException e) {
			IStatus status = new Status(IStatus.ERROR, XtextConsolePlugin.getPluginId(),
				1, ConsoleMessages.Output_Exception, e);
			XtextConsolePlugin.getInstance().getLog().log(status);
		}
	}

	public void cancelValidation() {
		BaseDocument editorDocument = getEditorDocument();
		Job validationJob = editorDocument.getValidationJob();
		if (validationJob != null) {
			validationJob.cancel();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
			while (validationJob.getState() == Job.RUNNING) {
				validationJob.cancel();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			};
		}
	}

	@Override
	public void createControl(Composite parent) {
		// force left-to-right text direction in the console, because it
		//    works with OCL text and the OCL language is based on English
		page = new SashForm(parent, SWT.VERTICAL | SWT.LEFT_TO_RIGHT);

		output = new TextViewer(page, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		output.getTextWidget().setLayoutData(new GridData(GridData.FILL_BOTH));
		output.getTextWidget().setFont(JFaceResources.getFont(JFaceResources.TEXT_FONT));
		output.setEditable(false);
		output.setDocument(new Document());

		colorManager = new ColorManager();
		//		document.setOCLFactory(oclFactory);
		//		document.setModelingLevel(modelingLevel);

		createEditor(page);
		input = editor.getViewer();
		input.getTextWidget().addKeyListener(new InputKeyListener());
		input.getTextWidget().setFont(JFaceResources.getFont(JFaceResources.TEXT_FONT));

		selectionListener = new ISelectionListener() {
			@Override
			public void selectionChanged(IWorkbenchPart part, final ISelection selection) {
				//				System.out.println("selectionChanged: ");
				if (part instanceof IConsoleView) {
					IConsole console = ((IConsoleView)part).getConsole();
					if (console instanceof OCLConsole) {
						return;
					}
				}
				if (part instanceof ContentOutline) {
					ContentOutline contentOutline = (ContentOutline)part;
					IPage currentPage = contentOutline.getCurrentPage();
					if (currentPage instanceof OutlinePage) {
						OutlinePage outlinePage = (OutlinePage)currentPage;
						IXtextDocument xtextDocument = outlinePage.getXtextDocument();
						Element pivotElement = xtextDocument.readOnly(new IUnitOfWork<Element, XtextResource>()
						{
							@Override
							public Element exec(@Nullable XtextResource state) throws Exception {
								if ((state != null) && (selection instanceof IStructuredSelection)) {
									IStructuredSelection structuredSelection = (IStructuredSelection) selection;
									if (structuredSelection.size() == 1) {
										Object selectedObject = structuredSelection.getFirstElement();
										if (selectedObject instanceof EObjectNode) {
											EObjectNode eObjectNode = (EObjectNode)selectedObject;
											URI uri = eObjectNode.getEObjectURI();
											if (uri != null) {
												EObject csObject = state.getEObject(uri.fragment());
												if (csObject instanceof Pivotable) {
													Element pivotObject = ((Pivotable) csObject).getPivot();
													if (pivotObject != null) {
														return pivotObject;
													}
												}
											}
										}
									}
								}
								return null;
							}
						});
						if (pivotElement != null) {
							OCLConsolePage.this.selectionChanged(new StructuredSelection(pivotElement));
							return;
						}
					}
				}
				OCLConsolePage.this.selectionChanged(selection);
			}};
			selectionService = getSite().getWorkbenchWindow().getSelectionService();
			selectionService.addPostSelectionListener(selectionListener);

			// get current selection
			//		ISelection selection = selectionService.getSelection();			// Doesn't have a value preceding console start
			ISelection selection = BaseUIUtil.getActiveSelection(getSite());
			selectionChanged(selection);

			((SashForm) page).setWeights(new int[] {2, 1});

			ClearOutputAction clear = new ClearOutputAction(output);
			CloseAction close = new CloseAction();
			SaveExpressionAction saveExpression = new SaveExpressionAction(this);
			LoadExpressionAction loadExpression = new LoadExpressionAction(this);
			debugAction = new DebugAction(this);

			IMenuManager menu = getSite().getActionBars().getMenuManager();
			menu.add(loadExpression);
			menu.add(saveExpression);
			menu.add(clear);
			menu.add(close);
			menu.add(debugAction);

			IToolBarManager toolbar = getSite().getActionBars().getToolBarManager();
			toolbar.appendToGroup(IConsoleConstants.OUTPUT_GROUP, loadExpression);
			toolbar.appendToGroup(IConsoleConstants.OUTPUT_GROUP, saveExpression);
			toolbar.appendToGroup(IConsoleConstants.OUTPUT_GROUP, clear);
			toolbar.appendToGroup(IConsoleConstants.OUTPUT_GROUP, close);
			toolbar.appendToGroup(IConsoleConstants.OUTPUT_GROUP, debugAction);
	}

	private int convertHeightInCharsToPixels(int i) {
		// Create a GC to calculate font's dimensions
		GC gc = new GC(Display.getDefault());
		gc.setFont(editor.getViewer().getTextWidget().getFont());

		// Determine string's dimensions
		FontMetrics fontMetrics = gc.getFontMetrics();

		int ret = (fontMetrics.getHeight() + fontMetrics.getAscent() + fontMetrics.getDescent() + fontMetrics.getLeading()) * i;

		// Dispose that gc
		gc.dispose();

		return ret;
	}

	protected @Nullable EnvironmentFactoryAdapter createEditor(Composite s1) {
		Composite client = s1; //new Composite(s1, SWT.NULL);
		Injector injector = XtextConsolePlugin.getInstance().getInjector(EssentialOCLPlugin.LANGUAGE_ID);
		Composite editorComposite = client; //new Composite(client, SWT.NULL);
		EnvironmentFactoryInternal currentEnvironmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
		editor = new EmbeddedXtextEditor(editorComposite, injector, /*SWT.BORDER |*/ SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL, currentEnvironmentFactory);
		//		MetamodelManagerResourceSetAdapter.getAdapter(editor.getResourceSet(), metamodelManager);

		/*		editor.getViewer().getTextWidget().addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String text = editor.getViewer().getTextWidget().getText();
				System.out.println("modifyText: " + text);
//				if (!text.equals(getEditedEObject().getAsString())) {
//					getEditor().setDirty(true);
//					getEditor().firePropertyChange(IEditorPart.PROP_DIRTY);
//				} else {
//					getEditor().setDirty(false);
//					getEditor().firePropertyChange(IEditorPart.PROP_DIRTY);
//				}
			}
		}); */
		/*		editor.getDocument().addModelListener(new IXtextModelListener() {
			public void modelChanged(XtextResource resource) {
				System.out.println("modelChanged: " + resource);
//				reconcileChangedModel();
			}
		}); */

		editor.getViewer().getTextWidget().addVerifyKeyListener(new VerifyKeyListener() {
			@Override
			public void verifyKey(VerifyEvent e) {
				//				System.out.println("verifyKey: " + e.keyCode);
				if (e.keyCode == SWT.KEYPAD_CR || e.keyCode == SWT.CR) {
					if ((e.stateMask & (SWT.CTRL | SWT.SHIFT)) == 0) {
						e.doit = false;
					}
				}
			}
		});

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint= convertHeightInCharsToPixels(1);
		editorComposite.setLayoutData(data);

		ResourceSet resourceSet = editor.getResourceSet();
		return resourceSet != null ? EnvironmentFactoryAdapter.find(resourceSet) : null;
	}

	/**
	 * Extends the inherited method to dispose of additional colour resources.
	 */
	@Override
	public void dispose() {
	//	System.out.println("Dispose " + NameUtil.debugSimpleName(this));
		if (editor != null) {
			editor.dispose();
			editor = null;
		}
		colorManager.dispose();
		selectionService.removePostSelectionListener(selectionListener);
		reset();
		super.dispose();
	}

	/**
	 * Prints an error message to the output viewer, in red text.
	 *
	 * @param message the error message to print
	 */
	private void error(String message) {
		append(message, ColorManager.OUTPUT_ERROR, false);
		scrollText();
	}

	/**
	 * Evaluates an OCL expression using the OCL Interpreter's {@link OCLHelper}
	 * API.
	 *
	 * @param expression an OCL expression
	 *
	 * @return <code>true</code> on successful evaluation; <code>false</code>
	 *    if the expression failed to parse or evaluate
	 */
	protected boolean evaluate(final String expression) {
		//		if (contextObject == null) {
		//			error(OCLInterpreterMessages.console_noContext);
		//			return false;
		//		}
		if ((expression == null) || (expression.trim().length() <= 0)) {
			error(ConsoleMessages.Result_NoExpression);
			return false;
		}
		//		editorDocument.getResource();
		// create an OCL helper to do our parsing and evaluating
		//      ocl = oclFactory.createOCL(modelingLevel);
		//      OCLHelper helper = ocl.createOCLHelper();
		boolean result = true;
		try {
			// set our helper's context classifier to parse against it
			//	        ConstraintKind kind = modelingLevel.setContext(helper, context, oclFactory);

			IDocument doc = getDocument();

			if (doc.getLength() > 0) {
				// separate previous output by a blank line
				append("", ColorManager.DEFAULT, false); //$NON-NLS-1$
			}

			append(ConsoleMessages.Heading_Evaluating, ColorManager.DEFAULT, true);
			append(expression, ColorManager.DEFAULT, false);
			append(ConsoleMessages.Heading_Results, ColorManager.DEFAULT, true);

			final BaseDocument editorDocument = getEditorDocument();
			Object value = null;
			try {
				value = editorDocument.readOnly(new IUnitOfWork<Object, XtextResource>() {

					@Override
					public Object exec(@Nullable XtextResource state) throws Exception {
						if (state != null) {
							IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
							EvaluationRunnable runnable = new EvaluationRunnable((CSResource) state, expression);
							progressService.busyCursorWhile(runnable);
							return runnable.getValue();
						}
						else {
							return null;
						}
					}});
			}
			catch (Throwable e) {
				while (e.getCause() != null) {
					e = e.getCause();
				}
				append(e.toString(), ColorManager.OUTPUT_ERROR, false);
			}
			if (value instanceof InvalidValueException) {
				InvalidValueException exception = (InvalidValueException)value;
				append(exception.getMessage(), ColorManager.OUTPUT_ERROR, true);
				Throwable cause = exception.getCause();
				if ((cause != null) && (cause != exception)) {
					if (cause instanceof ParserException) {
						append(cause.getMessage(), ColorManager.OUTPUT_ERROR, false);
					}
					else {
						StringWriter s = new StringWriter();
						PrintWriter pw = new PrintWriter(s);
						cause.printStackTrace(pw);
						append(s.toString(), ColorManager.OUTPUT_ERROR, false);
					}
				}
			}
			else if (value != null) {
				CollectionValue collectionValue = ValueUtil.isCollectionValue(value);
				if (collectionValue != null) {
					for (Object elementValue : collectionValue.iterable()) {
						append(ValueUtil.stringValueOf(elementValue), ColorManager.OUTPUT_RESULTS, false);
					}
				}
				else {
					append(ValueUtil.stringValueOf(value), ColorManager.OUTPUT_RESULTS, false);
				}
			}
			else {
				append(ValueUtil.stringValueOf(value), ColorManager.OUTPUT_ERROR, false);
			}
			scrollText();

			// store the successfully parsed expression
			lastOCLExpression = expression;
		} catch (Exception e) {
			result = false;
			error((e.getLocalizedMessage() == null) ? e.getClass().getName()
				: e.getLocalizedMessage());
		}

		return result;
	}

	protected void flushEvents() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		while (workbench.getDisplay().readAndDispatch());
	}

	public @Nullable EObject getContextObject() {
		return contextObject;
	}

	@Override
	public Control getControl() {
		return page;
	}

	/**
	 * Obtains the document in the output viewer.
	 *
	 * @return the output document
	 */
	private IDocument getDocument() {
		return output.getDocument();
	}

	public IXtextDocument getDocument(URI trimFragment) {
		return getEditorDocument();
	}

	public BaseDocument getEditorDocument() {
		return (BaseDocument) editor.getDocument();
	}

	public OCL getEditorOCL() {
		return editor.getOCL();
	}

	public String getLastOCLExpression() {
		return lastOCLExpression;
	}

	public @NonNull EnvironmentFactory getEnvironmentFactory(@Nullable EObject contextObject) {
		ResourceSet contextResourceSet = null;
		if (contextObject != null) {
			Resource contextResource = contextObject.eResource();
			if (contextResource != null) {
				contextResourceSet = contextResource.getResourceSet();
			}
		}
		editor.setContext(contextResourceSet);
		return editor.getEnvironmentFactory();
	}

	protected ILaunch internalLaunchDebugger() {
		return debugAction.launch();
	}

	protected void popUpModelTypesUsageInformation() {
		Shell shell = getControl().getShell();
		if (!shell.isDisposed()) {
			shell.getDisplay().asyncExec(new Runnable()
			{
				@Override
				public void run() {
					MessageDialog.openInformation(shell, ConsoleMessages.ModelTypesUsage_Title, ConsoleMessages.ModelTypesUsage_Message);
					//						MessageDialogWithToggle openInformation = MessageDialogWithToggle.openInformation(getControl().getShell(), ConsoleMessages.ModelTypesUsage_Title, ConsoleMessages.ModelTypesUsage_Message, ConsoleMessages.ModelTypesUsage_Question, false, null, null);
					//						if (openInformation.getToggleState()) {		// Don't show again
					//							option.setDefaultValue(false);			// Don't know how to persist this so don't offer toggle
					//						}
				}
			});
		}
	}

	protected void refreshSelection(final Object selected) {
		if (getControl().isDisposed()) {
			return;
		}
		final BaseDocument editorDocument = getEditorDocument();
		try {
			editorDocument.modify(new IUnitOfWork<Object, XtextResource>()
			{
				@Override
				public Value exec(@Nullable XtextResource resource) throws Exception {
					Object selectedObject = selected;
					if (selectedObject instanceof IAdaptable) {
						Object adapted = ((IAdaptable) selectedObject).getAdapter(EObject.class);
						boolean isNonNull = adapted != null;			// BUG 485093 fixed in Neon reported that adapted was nonNull and so the test was redundant.
						if (isNonNull) {
							selectedObject = adapted;
						}
					}
					if (selectedObject instanceof EObject) {
						contextObject = (EObject) selectedObject;
					}
					else {		// FIXME else Value in particular CollectionValue
						contextObject = null;
					}
					if (resource instanceof BaseCSResource) {
						((BaseCSResource)resource).dispose();
					}

					EnvironmentFactory environmentFactory = ThreadLocalExecutor.getEnvironmentFactory();
					IdResolver.IdResolverExtension idResolver = (IdResolver.IdResolverExtension)environmentFactory.getIdResolver();
					//				DomainType staticType = idResolver.getStaticTypeOfValue(null, selectedObject);
					org.eclipse.ocl.pivot.Class staticType = idResolver.getStaticTypeOfValue(null, contextObject);
					org.eclipse.ocl.pivot.Class contextType = environmentFactory.getMetamodelManager().getPrimaryClass(staticType);
					Iterable<org.eclipse.ocl.pivot.@NonNull Class> savedContextModelClasses = contextModelClasses;
					contextModelClasses = contextObject != null ? idResolver.getModelClassesOf(contextObject) : null;
					BooleanPreference option = PivotConsoleOptions.ConsoleModeltypesInformation;
					if ((contextModelClasses != null) && !contextModelClasses.equals(savedContextModelClasses) && option.getPreferredValue()) {
						popUpModelTypesUsageInformation();
					}
					EObject instanceContext = contextObject;
					if ((instanceContext != null) && !(instanceContext instanceof Element)) {
						instanceContext = ((EnvironmentFactoryInternalExtension)environmentFactory).getASOf(Element.class, instanceContext);
					}
					parserContext = new ClassContext(environmentFactory, null, contextType, (instanceContext instanceof Type) && !(instanceContext instanceof ElementExtension) ? (Type)instanceContext : null);
					EssentialOCLCSResource csResource = (EssentialOCLCSResource) resource;
					if (csResource != null) {
						if (contextObject != null) {
							csResource.getCS2AS();
						}
						ResourceSet resourceSet = editor.getResourceSet();
						if (resourceSet != null) {
							environmentFactory.adapt(resourceSet);
						}
						csResource.setParserContext(parserContext);
					}
					console.setSelection(contextObject, contextType);
					return null;
				}
			});
		}
		catch (WrappedException e) {
			Shell shell = getControl().getShell();
			if (!shell.isDisposed()) {
				Throwable t = e.getCause();
				IStatus status = new Status(IStatus.ERROR, XtextConsolePlugin.PLUGIN_ID, t.getLocalizedMessage(), t);
				ErrorDialog.openError(shell, ConsoleMessages.SelectionError_Title, ConsoleMessages.SelectionError_Message, status);
				console.setSelection(null, null);
			}
		}
		catch (Exception e) {
			Shell shell = getControl().getShell();
			if (!shell.isDisposed()) {
				IStatus status = new Status(IStatus.ERROR, XtextConsolePlugin.PLUGIN_ID, e.getLocalizedMessage(), e);
				ErrorDialog.openError(shell, ConsoleMessages.SelectionError_Title, ConsoleMessages.SelectionError_Message, status);
				console.setSelection(null, null);
			}
		}
	}

	public void reset() {
		if (editor != null) {
			flushEvents();
		}
		parserContext = null;
		contextObject = null;
	}

	protected void resetDocument() {
		IDocument doc = getDocument();
		if (doc != null) {
			doc.set(""); //$NON-NLS-1$
		}
	}

	/**
	 * Ensures that the last text printed to the output viewer is shown.
	 */
	private void scrollText() {
		output.revealRange(getDocument().getLength(), 0);
	}

	private void selectionChanged(ISelection sel) {
		Object selectedObject = BaseUIUtil.getSelectedObject(sel, getSite());
		if (selectedObject instanceof VMVariable) {					// FIXME move to BaseUIUtil once additional dependency acceptable
			VMVariableData vmVar = ((VMVariable)selectedObject).getVmVar();
			if (vmVar != null) {
				selectedObject = vmVar.valueObject;
			}
		}
		refreshSelection(selectedObject);
	}

	@Override
	public void setFocus() {
		input.getTextWidget().setFocus();
	}
}
