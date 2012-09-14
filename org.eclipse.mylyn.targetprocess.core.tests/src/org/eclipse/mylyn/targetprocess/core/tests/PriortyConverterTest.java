package org.eclipse.mylyn.targetprocess.core.tests;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.eclipse.mylyn.targetprocess.core.PriorityConverter;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttribute;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttributeMapper;
import org.eclipse.mylyn.targetprocess.core.TargetProcessEntityKind;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.ITask.PriorityLevel;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.junit.Before;
import org.junit.Test;

public class PriortyConverterTest {

	private PriorityConverter converter;
	private MockServiceFactory serviceFactory;

	@Before
	public void setUp() throws AxisFault, RemoteException {
		serviceFactory = new MockServiceFactory();
		converter = new PriorityConverter(serviceFactory.getPriorityServiceStub(), "repositoryUrl");
	}

	@Test
	public void willCorrectlyConvertUserStoryPriorityFromTaskDataToMylyn() {
		TaskData taskData = new TaskData(new TargetProcessAttributeMapper(new TaskRepository("", "")), "", "", "");
		taskData.getRoot().createAttribute(TargetProcessAttribute.Priority.getKey()).setValue(new String("Some minor"));
		taskData.getRoot().createAttribute(TargetProcessAttribute.EntityKind.getKey()).setValue(
				TargetProcessEntityKind.USERSTORY.toString());

		PriorityLevel priorityLevel = converter.getMylynPriorityFromTaskData(taskData);
		assertEquals(PriorityLevel.P5, priorityLevel);

		taskData.getRoot().createAttribute(TargetProcessAttribute.Priority.getKey()).setValue(new String("Great"));
		priorityLevel = converter.getMylynPriorityFromTaskData(taskData);
		assertEquals(PriorityLevel.P2, priorityLevel);

		taskData.getRoot().createAttribute(TargetProcessAttribute.Priority.getKey()).setValue(
				new String("Nice To Have"));
		priorityLevel = converter.getMylynPriorityFromTaskData(taskData);
		assertEquals(PriorityLevel.P5, priorityLevel);
	}

	@Test
	public void willCorrectlyConvertBugPriorityFromTaskDataToMylyn() {
		TaskData taskData = new TaskData(new TargetProcessAttributeMapper(new TaskRepository("", "")), "", "", "");
		taskData.getRoot().createAttribute(TargetProcessAttribute.Priority.getKey())
				.setValue(new String("Fix If Time"));
		taskData.getRoot().createAttribute(TargetProcessAttribute.EntityKind.getKey()).setValue(
				TargetProcessEntityKind.BUG.toString());

		PriorityLevel priorityLevel = converter.getMylynPriorityFromTaskData(taskData);
		assertEquals(PriorityLevel.P3, priorityLevel);

		taskData.getRoot().createAttribute(TargetProcessAttribute.Priority.getKey()).setValue("Very Important");
		priorityLevel = converter.getMylynPriorityFromTaskData(taskData);
		assertEquals(PriorityLevel.P1, priorityLevel);
	}

	@Test
	public void willCorrectlyConvertRequestPriorityFromTaskDataToMylyn() {
		TaskData taskData = new TaskData(new TargetProcessAttributeMapper(new TaskRepository("", "")), "", "", "");
		taskData.getRoot().createAttribute(TargetProcessAttribute.Priority.getKey()).setValue(new String("Normal"));
		taskData.getRoot().createAttribute(TargetProcessAttribute.EntityKind.getKey()).setValue(
				TargetProcessEntityKind.REQUEST.toString());

		PriorityLevel priorityLevel = converter.getMylynPriorityFromTaskData(taskData);
		assertEquals(PriorityLevel.P2, priorityLevel);

		taskData.getRoot().createAttribute(TargetProcessAttribute.Priority.getKey()).setValue("Urgent");
		priorityLevel = converter.getMylynPriorityFromTaskData(taskData);
		assertEquals(PriorityLevel.P1, priorityLevel);
	}

}
