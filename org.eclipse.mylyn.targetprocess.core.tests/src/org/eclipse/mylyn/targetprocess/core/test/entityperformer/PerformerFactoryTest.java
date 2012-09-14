package org.eclipse.mylyn.targetprocess.core.test.entityperformer;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;

import junit.framework.Assert;

import org.eclipse.mylyn.targetprocess.core.entityperformer.BugPerformer;
import org.eclipse.mylyn.targetprocess.core.entityperformer.Comment;
import org.eclipse.mylyn.targetprocess.core.entityperformer.EntityPerformerFactory;
import org.eclipse.mylyn.targetprocess.core.entityperformer.IPerformer;
import org.eclipse.mylyn.targetprocess.core.entityperformer.NullPerformer;
import org.eclipse.mylyn.targetprocess.core.entityperformer.PerformerBase;
import org.eclipse.mylyn.targetprocess.core.entityperformer.RequestPerformer;
import org.eclipse.mylyn.targetprocess.core.entityperformer.TaskPerformer;
import org.eclipse.mylyn.targetprocess.core.entityperformer.UserStoryPerformer;
import org.eclipse.mylyn.targetprocess.core.tests.MockServiceFactory;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.junit.Before;
import org.junit.Test;

public class PerformerFactoryTest {

	private EntityPerformerFactory performerFactory;

	@Before
	public void setUp() throws MalformedURLException {
		TargetProcessCredentials targetProcessCredentials = new TargetProcessCredentials(new URL("http:\\localhost"),
				"userName", "password", false);
		performerFactory = new EntityPerformerFactory(targetProcessCredentials);
	}

	@Test
	public void getBugEntityPerformer() throws RemoteException {
		Assert.assertEquals(BugPerformer.class, performerFactory.createPerformer(new MockServiceFactory(),
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG).getClass());
	}

	@Test
	public void getUserStoryEntityPerformer() throws RemoteException {
		Assert.assertEquals(UserStoryPerformer.class, performerFactory.createPerformer(new MockServiceFactory(),
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_USER_STORY).getClass());
	}

	@Test
	public void getTaskEntityPerformer() throws RemoteException {
		Assert.assertEquals(TaskPerformer.class, performerFactory.createPerformer(new MockServiceFactory(),
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_TASK).getClass());
	}

	@Test
	public void getRequsetEntityPerformer() throws RemoteException {
		Assert.assertEquals(RequestPerformer.class, performerFactory.createPerformer(new MockServiceFactory(),
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_REQUEST).getClass());
	}

	@Test
	public void getNullEntityPerformer() throws RemoteException {
		Assert.assertEquals(NullPerformer.class, performerFactory.createPerformer(new MockServiceFactory(), "")
				.getClass());
	}

	@Test
	public void willTransfromHtmlToStringCorrectly() {
		Assert.assertEquals("1\n2\n2\n34\n\n56\n6\n", PerformerBase
				.removeHTML("1<br />\n2<br />\n2<br />\n34<br />\n<br />\n56<br />\n6<br />"));
		
		Assert
				.assertEquals(
						"This is a test for test removeHTML\nbold\nitalicunderline'\"><%#!@#$%^&*()_+*-+",
						PerformerBase
								.removeHTML("This is a test for test removeHTML<br />\n<strong>bold</strong><br />\n<em>italic</em>underline&#39;&quot;&gt;&lt;&#37;&#35;&#33;&#64;&#35;&#36;&#37;&#94;&#38;&#42;&#40;&#41;_&#43;&#42;-&#43;"));
	}
	
	@Test
	public void willReturnValidCommentsForTasks() throws RemoteException {
		IPerformer performer = performerFactory.createPerformer(new MockServiceFactory(),
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_TASK);
		
		ArrayList<Comment> comments = performer.getComments(1);
		Assert.assertEquals(2, comments.size());
		
		Assert.assertEquals("Task comment descr 1", comments.get(0).getDescription());
		Assert.assertEquals("Task comment descr 2", comments.get(1).getDescription());
	}
	
	
	@Test
	public void willReturnValidCommentsForBugs() throws RemoteException {
		IPerformer performer = performerFactory.createPerformer(new MockServiceFactory(),
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_BUG);
		
		ArrayList<Comment> comments = performer.getComments(1);
		Assert.assertEquals(2, comments.size());
		
		Assert.assertEquals("descr 1", comments.get(0).getDescription());
		Assert.assertEquals("descr 2", comments.get(1).getDescription());
	}
	
	
	@Test
	public void willReturnValidCommentsForUserStory() throws RemoteException {
		IPerformer performer = performerFactory.createPerformer(new MockServiceFactory(),
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_USER_STORY);
		
		ArrayList<Comment> comments = performer.getComments(1);
		Assert.assertEquals(2, comments.size());
		
		Assert.assertEquals("User story comment descr 1", comments.get(0).getDescription());
		Assert.assertEquals("User story comment descr 2", comments.get(1).getDescription());
	}
	
	@Test
	public void willReturnValidCommentsForRequest() throws RemoteException {
		IPerformer performer = performerFactory.createPerformer(new MockServiceFactory(),
				EntityPerformerFactory.TP_BUSINESS_OBJECTS_REQUEST);
		
		ArrayList<Comment> comments = performer.getComments(1);
		Assert.assertEquals(2, comments.size());
		
		Assert.assertEquals("Request comment descr 1", comments.get(0).getDescription());
		Assert.assertEquals("Request comment descr 2", comments.get(1).getDescription());
	}
}
