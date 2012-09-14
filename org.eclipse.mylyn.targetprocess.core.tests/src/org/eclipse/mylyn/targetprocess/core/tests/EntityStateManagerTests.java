package org.eclipse.mylyn.targetprocess.core.tests;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import junit.framework.Assert;

import org.eclipse.mylyn.targetprocess.core.EntityStateManager;
import org.eclipse.mylyn.targetprocess.modules.TargetProcessCredentials;
import org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.BugDTO;
import org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.EntityStateDTO;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IEntityStateServiceStub;
import org.junit.Before;
import org.junit.Test;

public class EntityStateManagerTests {

	private EntityStateManager entityStateManager;
	private IEntityStateServiceStub entityServiceStubMock;
	private MockServiceFactory factory;

	@Before
	public void setUp() throws RemoteException, MalformedURLException {
		factory = new MockServiceFactory();
		entityServiceStubMock = createMock(IEntityStateServiceStub.class);
		factory.setMock(entityServiceStubMock);

		expect(entityServiceStubMock.retrieveAll(isA(EntityStateServiceStub.RetrieveAll.class))).andReturn(null);
		replay(entityServiceStubMock);

		entityStateManager = new EntityStateManager(factory.getEntityStateServiceStub(), new TargetProcessCredentials(new URL("http://localhost/"), "userName", "password", false));
		factory.setMock(entityServiceStubMock);
		verify(entityServiceStubMock);
	}

	@Test
	public void testWillRetrieveBugEntityStates() {
		BugDTO bugDTO = factory.getContext().getBugList().get(0);
		EntityStateDTO[] entityStateDTOs = entityStateManager.retrieveEntityStatesFor(bugDTO.getEntityStateID());

		Assert.assertEquals(2, entityStateDTOs.length);
	}
}
