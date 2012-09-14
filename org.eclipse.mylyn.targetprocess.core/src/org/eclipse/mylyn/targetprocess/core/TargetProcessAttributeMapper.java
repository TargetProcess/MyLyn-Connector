package org.eclipse.mylyn.targetprocess.core;

import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;

public class TargetProcessAttributeMapper extends TaskAttributeMapper {

	public TargetProcessAttributeMapper(TaskRepository taskRepository) {
		super(taskRepository);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getLabel(TaskAttribute taskAttribute) {
		String label = super.getLabel(taskAttribute);
		if (label == null || label.equals(""))
			return label;
		return label + ":"; //$NON-NLS-1$
	}
	
	@Override
	public String mapToRepositoryKey(TaskAttribute parent, String key) {
		if (key.equals(TaskAttribute.SUMMARY)) {
			return TargetProcessAttribute.Name.getKey();
		} else if (key.equals(TaskAttribute.DESCRIPTION)) {
			return TargetProcessAttribute.Description.getKey();
		} else if (key.equals(TaskAttribute.STATUS)) {
			return TargetProcessAttribute.State.getKey();
		}
		return super.mapToRepositoryKey(parent, key);
	}

}
