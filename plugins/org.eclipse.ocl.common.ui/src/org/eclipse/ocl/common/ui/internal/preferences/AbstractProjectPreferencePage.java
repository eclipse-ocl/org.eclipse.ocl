/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.common.ui.internal.preferences;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ocl.common.preferences.PreferenceableOption;
import org.eclipse.ocl.common.ui.internal.messages.CommonUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * An abstract Project/Property preference page providing support for use as a global
 * preference page or as a project-specific property page.
 */
public abstract class AbstractProjectPreferencePage extends PreferencePage
	implements IPropertyChangeListener, IWorkbenchPreferencePage, IWorkbenchPropertyPage
{
	protected static interface IFieldEditor
	{
		void adjustForNumColumns(int numColumns);
		int getNumberOfControls();
		String getPreferenceName();
		boolean isValid();
		void load();
		void loadDefault();
		void setEnabled(boolean enabled, Composite fieldEditorParent);
		void setFocus();
		void setPage(DialogPage dialogPage);
		void setPreferenceStore(IPreferenceStore store);
		void setPresentsDefaultValue(boolean booleanValue);
		void setPropertyChangeListener(IPropertyChangeListener listener);
		void store();
	}

	protected static class MyComboFieldEditor extends ComboFieldEditor implements IFieldEditor
	{
		public MyComboFieldEditor(PreferenceableOption<?> preference, String labelText,
				String[][] entryNamesAndValues, Composite parent) {
			this(preference, labelText, entryNamesAndValues, parent, null);
		}

		/**
		 * @since 1.16
		 */
		public MyComboFieldEditor(PreferenceableOption<?> preference, String labelText,
				String[][] entryNamesAndValues, Composite parent, String toolTipText) {
			super(preference.getKey(), labelText, entryNamesAndValues, parent);
			if (toolTipText != null) {
				getLabelControl().setToolTipText(toolTipText);
			}
			else {
				getLabelControl().setToolTipText("toolTipText");
			}
		}

		@Override
		public void adjustForNumColumns(int numColumns) {
			super.adjustForNumColumns(numColumns);
		}

		@Override
		public void setPresentsDefaultValue(boolean booleanValue) {
			super.setPresentsDefaultValue(booleanValue);
		}
	}

	// Name of the preferences store: /project/.setting/STORE_ID.prefs
	protected static final String[][] BOOLEANS = new String[][] {
		{ CommonUIMessages.Preference_False, Boolean.FALSE.toString() },
		{ CommonUIMessages.Preference_True, Boolean.TRUE.toString() }
	};

	protected static final String[][] ANY_LESS_VALUES = new String[][] {
		{ CommonUIMessages.Preference_Null, Boolean.FALSE.toString() },
		{ CommonUIMessages.Preference_Invalid, Boolean.TRUE.toString() }
	};

	@SuppressWarnings("deprecation")
	private static final InstanceScope INSTANCE_SCOPE_INSTANCE = new InstanceScope();	// InstanceScope.INSTANCE not available for Galileo

	private String pluginId;

    /**
     * The field editors, or <code>null</code> if not created yet.
     */
    private List<IFieldEditor> fields = new ArrayList<IFieldEditor>();

    /**
     * The first invalid field editor, or <code>null</code>
     * if all field editors are valid.
     */
    private IFieldEditor invalidFieldEditor = null;

    /**
     * The parent composite for field editors
     */
    private Composite fieldEditorParent;

	private IProject project;							// Non-null for a project page.
	private IPersistentPreferenceStore projectStore;	// Non-null store for a project page

	private Button projectSpecificSettingsButton;
	private Link configureLink;


	private boolean initialized = false;	// Set true once field editors initialized

    public AbstractProjectPreferencePage(String pluginId, String pageTitle) {
		this.pluginId = pluginId;
		if (pluginId != null) {
			setPreferenceStore(new ScopedPreferenceStore(INSTANCE_SCOPE_INSTANCE, pluginId));
		}
		setDescription(pageTitle);
	}

	/**
     * Adjust the layout of the field editors so that
     * they are properly aligned.
     */
    protected void adjustGridLayout() {
        int numColumns = calcNumberOfColumns();
        ((GridLayout) fieldEditorParent.getLayout()).numColumns = numColumns;
		for (IFieldEditor field : fields) {
             field.adjustForNumColumns(numColumns);
        }
    }

    /**
     * Calculates the number of columns needed to host all field editors.
     *
     * @return the number of columns
     */
    private int calcNumberOfColumns() {
        int result = 0;
		for (IFieldEditor field : fields) {
            result = Math.max(result, field.getNumberOfControls());
        }
        return result;
    }

    /**
     * Recomputes the page's error state by calling <code>isValid</code> for
     * every field editor.
     */
    protected void checkState() {
        boolean valid = true;
        invalidFieldEditor = null;
        // The state can only be set to true if all
        // field editors contain a valid value. So we must check them all
		for (IFieldEditor field : fields) {
            valid = valid && field.isValid();
            if (!valid) {
                invalidFieldEditor = field;
                break;
            }
        }
        setValid(valid);
    }

	protected abstract AbstractProjectPreferencePage createClonePage();

	/**
	 * Insert the project-specific button and link on project-specific pages.
	 */
	@Override
	protected Control createContents(Composite parent) {
		if (project != null) {
			Composite comp = new Composite(parent, SWT.NONE);
			GridLayout layout = new GridLayout(2, false);
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			comp.setLayout(layout);
			comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			projectSpecificSettingsButton = new Button(comp, SWT.CHECK);
			projectSpecificSettingsButton.setText(CommonUIMessages.EnableProjectSpecificSettings);
			projectSpecificSettingsButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					updateFieldEditors();
				}
			});
			projectSpecificSettingsButton.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
			if (true) {
				configureLink = createLink(comp, CommonUIMessages.ConfigureWorkspaceSettings);
				configureLink.setLayoutData(new GridData(SWT.END, SWT.CENTER, false, false));
			}
		}
        fieldEditorParent = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        fieldEditorParent.setLayout(layout);
        fieldEditorParent.setFont(parent.getFont());
        createFieldEditors(fieldEditorParent);
        adjustGridLayout();
        initialize();
        checkState();
        return fieldEditorParent;
    }

	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors(Composite fieldEditorParent) {
		createFieldEditors(fieldEditorParent, fields);
	}

	protected abstract void createFieldEditors(Composite fieldEditorParent, List<IFieldEditor> fields);

	private Link createLink(Composite composite, String text) {
		Link link= new Link(composite, SWT.NONE);
		link.setFont(composite.getFont());
		link.setText("<A>" + text + "</A>");  //$NON-NLS-1$//$NON-NLS-2$
		link.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doLinkActivated((Link) e.widget);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				doLinkActivated((Link) e.widget);
			}
		});
		return link;
	}

    /**
     * The field editor preference page implementation of an <code>IDialogPage</code>
     * method disposes of this page's controls and images.
     * Subclasses may override to release their own allocated SWT
     * resources, but must call <code>super.dispose</code>.
     */
    @Override
	public void dispose() {
        super.dispose();
		for (IFieldEditor field : fields) {
			field.setPage(null);
			field.setPropertyChangeListener(null);
			field.setPreferenceStore(null);
        }
     }

	/**
	 * When the project-specific link is activated, install the project-specific property page.
	 */
	final void doLinkActivated(Link link) {
		IPreferencePage page = createClonePage();
		page.setTitle(getTitle());
		final IPreferenceNode targetNode = new PreferenceNode(pluginId, page);
		PreferenceManager manager = new PreferenceManager();
		manager.addToRoot(targetNode);
		final PreferenceDialog dialog = new PreferenceDialog(getControl().getShell(), manager);
		BusyIndicator.showWhile(getControl().getDisplay(), new Runnable() {
			@Override
			public void run() {
				dialog.create();
				dialog.setMessage(targetNode.getLabelText());
				dialog.open();
			}
		});
	}

	/**
	 * Return the object that owns the properties shown in this property page, which is
	 * a non-null IProject for a project Property page and null for a global preference page.
	 */
	@Override
	public final IProject getElement() {
		return project;
	}

	/**
	 * Returns the prevailing project or workspace preference store.
	 */
	@Override
	public IPreferenceStore getPreferenceStore() {
		if (project == null) {
			return getWorkspaceStore();
		}
		if (!initialized) {
			return projectStore;
		}
		if (!projectSpecificSettingsButton.getSelection()) {
			return getWorkspaceStore();
		}
		return projectStore;
	}

	public IPreferenceStore getWorkspaceStore() {
		return super.getPreferenceStore();
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	protected void initialize() {
		if (project != null) {
			IPreferenceStore workspaceStore = getWorkspaceStore();
			IEclipsePreferences preferenceNode = new ProjectScope(project).getNode(pluginId);
			boolean hasProjectSpecificSettings = false;
			for (IFieldEditor field : fields) {
				String preferenceName = field.getPreferenceName();
				String prefValue = preferenceNode.get(preferenceName, null);
				if (prefValue != null) {
					hasProjectSpecificSettings = true;
				}
				else {	// Missing preference has null-default to force write
					projectStore.setValue(preferenceName, workspaceStore.getString(preferenceName));
				}
			}
			projectSpecificSettingsButton.setSelection(hasProjectSpecificSettings);
		}
//		printPreferences("super.initialize");
//		super.initialize();
		for (IFieldEditor field : fields) {
			field.setPage(this);
			field.setPropertyChangeListener(this);
			field.setPreferenceStore(getPreferenceStore());
			field.load();
        }
		initialized  = true;
		if (project != null) {
			updateFieldEditors();
		}
	}

	/**
	 * Performing defaults reverts workspace settings to built-in defaults or
	 * project settings to workspace settings.
	 */
	@Override
	protected void performDefaults() {
		IPreferenceStore workspaceStore = getWorkspaceStore();
		if (project == null) {
			for (IFieldEditor field : fields) {
				String preferenceName = field.getPreferenceName();
				workspaceStore.setValue(preferenceName, workspaceStore.getDefaultString(preferenceName));
			}
		}
		else {
			for (IFieldEditor field : fields) {
				String preferenceName = field.getPreferenceName();
				projectStore.setValue(preferenceName, workspaceStore.getString(preferenceName));
			}
		}
		for (IFieldEditor field : fields) {
			field.loadDefault();
        }
        // Force a recalculation of my error state.
        checkState();
		super.performDefaults();
//		printPreferences("super.performDefaults()");
	}

	/**
	 *
	 */
	@Override
	public boolean performOk() {
		if ((project != null) && !projectSpecificSettingsButton.getSelection()) {
			for (IFieldEditor field : fields) {
				String preferenceName = field.getPreferenceName();
				projectStore.setValue(preferenceName, projectStore.getDefaultString(preferenceName));
				field.loadDefault();
			}
		}
//		printPreferences("pre super.performOk");
//		boolean result = super.performOk();
		for (IFieldEditor field : fields) {
			field.store();
			field.setPresentsDefaultValue(false);
        }
//		printPreferences("post super.performOk");
		if (project != null) {
			try {
				if (projectStore.needsSaving()) {
					projectStore.save();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

/*	public void printPreferences(String title) {
		System.out.println("--------" + title + "--------");
		IPreferenceStore workspaceStore = getWorkspaceStore();
		List<String> names = new ArrayList<String>();
		for (FieldEditor editor : editors) {
			names.add(editor.getPreferenceName());
		}
		Collections.sort(names);
		for (String preferenceName : names) {
			String workValue = workspaceStore.getString(preferenceName);
			String workDefaultValue = workspaceStore.getDefaultString(preferenceName);
			if (projectStore == null) {
				System.out.println(preferenceName + " " + workValue + "[" + workDefaultValue + "]");
			}
			else {
				String projValue = projectStore.getString(preferenceName);
				String projDefaultValue = projectStore.getDefaultString(preferenceName);
				System.out.println(preferenceName + " " + projValue + "[" + projDefaultValue + "]" + " [" + workValue + "[" + workDefaultValue + "]]");
			}
		}
	} */

    /**
     * The field editor preference page implementation of this <code>IPreferencePage</code>
     * (and <code>IPropertyChangeListener</code>) method intercepts <code>IS_VALID</code>
     * events but passes other events on to its superclass.
     */
    @Override
	public void propertyChange(PropertyChangeEvent event) {

        if (event.getProperty().equals(FieldEditor.IS_VALID)) {
            boolean newValue = ((Boolean) event.getNewValue()).booleanValue();
            // If the new value is true then we must check all field editors.
            // If it is false, then the page is invalid in any case.
            if (newValue) {
                checkState();
            } else {
                invalidFieldEditor = (IFieldEditor) event.getSource();
                setValid(newValue);
            }
        }
    }

	/**
     * Receives the object that owns the properties shown in this property page.
	 * @see org.eclipse.ui.IWorkbenchPropertyPage#setElement(org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	public void setElement(IAdaptable element) {
		IProject adapter = element.getAdapter(IProject.class);
		this.project = adapter;
		if (project != null) {
			projectStore = new ScopedPreferenceStore(new ProjectScope(project), pluginId);

		}
	}

    @Override
	public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible && invalidFieldEditor != null) {
            invalidFieldEditor.setFocus();
        }
    }

	/*
	 * Enables or disables the editors and buttons depending on whether
	 * the project-specific settings are applicable.
	 */
	protected void updateFieldEditors() {
		boolean enabled = projectSpecificSettingsButton.getSelection();
		for (IFieldEditor field : fields) {
			field.setEnabled(enabled, fieldEditorParent);
		}
		configureLink.setEnabled(enabled);
//		printPreferences("updateFieldEditors");
	}
}