package org.eclipse.mylyn.targetprocess.core.tests.mockservices;

import org.eclipse.mylyn.targetprocess.core.tests.TestContext;

public class ServiceStubMockBase<T> {

	protected TestContext context;
	public T mock;
	
	public ServiceStubMockBase(TestContext context) {
		this.context = context;
	}

	public void setMock(T mock) {
		this.mock = mock;		
	}
}
