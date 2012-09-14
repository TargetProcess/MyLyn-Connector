package org.eclipse.mylyn.targetprocess.core.tests.mockservices;

import java.rmi.RemoteException;

import org.eclipse.mylyn.targetprocess.core.tests.TestContext;
import org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub.DownloadChunk;
import org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub.DownloadChunkResponse;
import org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub.GetFileSize;
import org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub.GetFileSizeResponse;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IFileServiceStub;

public class FileServiceStubMock extends ServiceStubMockBase<IFileServiceStub> implements IFileServiceStub{

	public FileServiceStubMock(TestContext context) {
		super(context);
	}

	@Override
	public GetFileSizeResponse getFileSize(GetFileSize getFileSize4) throws RemoteException {
		if(this.mock != null)
		{
			this.mock.getFileSize(getFileSize4);
		}
		GetFileSizeResponse response = new GetFileSizeResponse();
		response.setGetFileSizeResult(this.context.getFileSize(getFileSize4.getFileName()));
		return response;
	}

	@Override
	public DownloadChunkResponse downloadChunk(DownloadChunk downloadChunk6) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
