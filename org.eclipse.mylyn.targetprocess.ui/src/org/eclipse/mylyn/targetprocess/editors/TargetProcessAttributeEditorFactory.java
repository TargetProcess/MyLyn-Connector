package org.eclipse.mylyn.targetprocess.editors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.mylyn.internal.tasks.ui.editors.TaskEditorExtensions;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttribute;
import org.eclipse.mylyn.targetprocess.ui.editors.DescriptionEditor;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskDataModel;
import org.eclipse.mylyn.tasks.ui.editors.AbstractAttributeEditor;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorExtension;
import org.eclipse.mylyn.tasks.ui.editors.AttributeEditorFactory;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.services.IServiceLocator;


public class TargetProcessAttributeEditorFactory extends AttributeEditorFactory {

	private TaskRepository taskRepository;
	private TaskDataModel model;
	private IServiceLocator serviceLocator;

	public TargetProcessAttributeEditorFactory(TaskDataModel model, TaskRepository taskRepository, IServiceLocator serviceLocator) {
		super(model,taskRepository,serviceLocator);
		Assert.isNotNull(model);
		Assert.isNotNull(taskRepository);
		this.model = model;
		this.taskRepository = taskRepository;
		this.serviceLocator = serviceLocator;
	}

	public AbstractAttributeEditor createEditor(String type, TaskAttribute taskAttribute) {
		if (TargetProcessAttribute.Description.getType().equals(type ))
		{
			IContextService contextService = (IContextService) serviceLocator.getService(IContextService.class);
			
			AbstractTaskEditorExtension extension = TaskEditorExtensions.getTaskEditorExtension(model.getTaskRepository());
			return new DescriptionEditor(model, taskRepository, taskAttribute,contextService,extension);
		}
		
		return super.createEditor(type, taskAttribute);
	}

}
