package org.eclipse.mylyn.targetprocess.ui.editors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.internal.tasks.ui.editors.RichTextAttributeEditor;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskDataModel;
import org.eclipse.mylyn.tasks.ui.editors.AbstractRenderingEngine;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorExtension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.forms.widgets.FormToolkit;

@SuppressWarnings("restriction")
public class DescriptionEditor extends RichTextAttributeEditor {

	private boolean isInEditMode;

	public DescriptionEditor(TaskDataModel manager, TaskRepository taskRepository, TaskAttribute taskAttribute,
			IContextService contextService, AbstractTaskEditorExtension extension) {
		super(manager, taskRepository, taskAttribute, SWT.MULTI, contextService, extension);
		//this.taskRepository = taskRepository;
		//this.manager = manager;
		//this.taskAttribute = taskAttribute;
		super.setRenderingEngine(new AbstractRenderingEngine() {
			@Override
			public String renderAsHtml(TaskRepository repository, String text, IProgressMonitor monitor)
					throws CoreException {
				return text;
			}
		});
	}

	@Override
	public void createControl(Composite parent, FormToolkit toolkit) {
		super.createControl(parent, toolkit);
		showBrowser();
		isInEditMode = false;
	}

	public String getValue() {
		return getAttributeMapper().getValue(getTaskAttribute());
	}

	public boolean isInEditMode() {
		return isInEditMode;
	}

	public void showPreview() {
		isInEditMode = false;
		showBrowser();
	}

	public void showEditor() {

		super.showEditor();
		isInEditMode = true;
	}

/*
	private TaskDataModel manager;
	private TaskAttribute taskAttribute;
	private FormToolkit toolkit;
	private RichTextAttributeEditor rich;

	private TaskRepository taskRepository;
	private Browser viewer;
	private Control editor;
	
	private SourceViewer editorViewer;
	
	private Composite parent;
	private AbstractTaskEditorExtension extension;
	private Mode mode;
	

	public DescriptionEditor(TaskDataModel manager, TaskAttribute taskAttribute, TaskRepository taskRepository, AbstractTaskEditorExtension extension) {
		super(manager, taskAttribute);
		this.taskRepository = taskRepository;
		this.extension = extension;
		setMode(Mode.DEFAULT);
	}

	private void setMode(Mode mode) {
		this.mode = mode;		
	}
	
	private Mode getMode() {
		return this.mode;		
	}

	@Override
	public void createControl(Composite parent, FormToolkit toolkit) {
		int style = SWT.NONE;
		
		this.parent = parent;
		setViewer(new Browser(parent, style));		
		getViewer().setText( getValue());
		setControl(getViewer());
	}
	
	public String getValue() {
		return getAttributeMapper().getValue(getTaskAttribute());
	}

	public void setValue(String text) {
		getAttributeMapper().setValue(getTaskAttribute(), text);
		attributeChanged();
	}
	
	public void refresh() {
		
	}

	public void setViewer(Browser viewer) {
		this.viewer = viewer;
	}

	public Browser getViewer() {
		return viewer;
	}

	public void showEditor() {
		viewer.dispose();
		viewer = null;
		
		editorViewer  = extension.createEditor(taskRepository, parent, SWT.V_SCROLL);
		configure(editorViewer, new Document(getValue()), false);
		editor = editorViewer.getControl();
		setControl(editor);
		this.parent.redraw();		
	}

	public static RepositoryTextViewerConfiguration installHyperlinkPresenter(ISourceViewer viewer,
			TaskRepository repository, Mode mode) {
		RepositoryTextViewerConfiguration configuration = new RepositoryTextViewerConfiguration(repository, false);
		configuration.setMode(mode);

		// do not configure viewer, this has already been done in extension

		AbstractHyperlinkTextPresentationManager manager;
		if (mode == Mode.DEFAULT) {
			manager = new HighlightingHyperlinkTextPresentationManager();
			manager.setHyperlinkDetectors(configuration.getDefaultHyperlinkDetectors(viewer, null));
			manager.install(viewer);

			manager = new TaskHyperlinkTextPresentationManager();
			manager.setHyperlinkDetectors(configuration.getDefaultHyperlinkDetectors(viewer, Mode.TASK));
			manager.install(viewer);
		} else if (mode == Mode.TASK_RELATION) {
			manager = new TaskHyperlinkTextPresentationManager();
			manager.setHyperlinkDetectors(configuration.getDefaultHyperlinkDetectors(viewer, Mode.TASK_RELATION));
			manager.install(viewer);
		}

		return configuration;
	}
	
	private SourceViewer configure(final SourceViewer viewer, Document document, boolean readOnly) {
		// do this before setting the document to not require invalidating the presentation
		installHyperlinkPresenter(viewer, taskRepository, getMode());

		if (readOnly) {
			viewer.setDocument(document);
			if (extension != null) {
				// setting view source action
				viewer.getControl().setData(ViewSourceHandler.VIEW_SOURCE_ACTION, viewSourceAction);
				viewer.getControl().addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						ViewSourceHandler.setChecked(getViewer() == defaultViewer);
					}
				});
			}
		} else {
			configureAsEditor(viewer, document);
			installListeners(viewer);
			viewer.getControl().setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		}

		// enable cut/copy/paste
		CommonTextSupport.setTextViewer(viewer.getTextWidget(), viewer);
		viewer.setEditable(!readOnly);
		viewer.getTextWidget().setFont(getFont());
		if (toolkit != null) {
			toolkit.adapt(viewer.getControl(), false, false);
		}

		return viewer;
	}
*/
	/** Configures annotation model for spell checking. */
	/*
	private void configureAsEditor(SourceViewer viewer, Document document) {
		AnnotationModel annotationModel = new AnnotationModel();
		viewer.showAnnotations(false);
		viewer.showAnnotationsOverview(false);
		IAnnotationAccess annotationAccess = new DefaultMarkerAnnotationAccess();
		final SourceViewerDecorationSupport support = new SourceViewerDecorationSupport(viewer, null, annotationAccess,
				EditorsUI.getSharedTextColors());
		Iterator<?> e = new MarkerAnnotationPreferences().getAnnotationPreferences().iterator();
		while (e.hasNext()) {
			support.setAnnotationPreference((AnnotationPreference) e.next());
		}
		support.install(EditorsUI.getPreferenceStore());
		viewer.getTextWidget().addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				support.uninstall();
			}
		});
		//viewer.getTextWidget().setIndent(2);
		viewer.setDocument(document, annotationModel);
	}
	public boolean isInEditMode() { 
		return viewer == null && editor != null;
	}

	public void showViewer() {
		editor.dispose();
		editor = null;
		setViewer(new Browser(parent, SWT.NONE));		
		getViewer().setText( getValue());
		setControl(getViewer());
		this.parent.redraw();
	}
*/
/*
	public DescriptionEditor(TaskDataModel model, TaskAttribute taskAttribute, TaskRepository taskRepository,
			AbstractTaskEditorExtension extension) {
		super(model,taskRepository, taskAttribute,SWT.MULTI,extension);
	}
	*/
}
