package org.eclipse.mylyn.targetprocess.core.tests.mockservices;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.core.tests.TestContext;
import org.eclipse.mylyn.targetprocess.modules.services.CommentServiceStub.Create;
import org.eclipse.mylyn.targetprocess.modules.services.CommentServiceStub.CreateResponse;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.ICommentServiceStub;

public class CommentServiceStubMock extends ServiceStubMockBase<ICommentServiceStub> implements ICommentServiceStub{

	public CommentServiceStubMock(TestContext context) {
		super(context);
	}

	@Override
	public CreateResponse create(Create create14) throws RemoteException {
		if(this.mock != null)
		{
			this.mock.create(create14);
		}
		
		return new CreateResponse();
	}

}
