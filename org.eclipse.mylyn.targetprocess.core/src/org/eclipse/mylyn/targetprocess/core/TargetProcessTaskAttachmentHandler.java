package org.eclipse.mylyn.targetprocess.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskAttachmentHandler;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskAttachmentSource;
import org.eclipse.mylyn.tasks.core.data.TaskAttachmentMapper;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;

public class TargetProcessTaskAttachmentHandler extends AbstractTaskAttachmentHandler {

	private TargetProcessRepositoryConnector connector;

	public TargetProcessTaskAttachmentHandler(TargetProcessRepositoryConnector connector) {
		this.connector = connector;
	}

	@Override
	public boolean canGetContent(TaskRepository repository, ITask task) {
		return true;
	}

	@Override
	public boolean canPostContent(TaskRepository repository, ITask task) {
		return true;
	}

	@Override
	public InputStream getContent(TaskRepository repository, ITask task, TaskAttribute attachmentAttribute,
			IProgressMonitor monitor) throws CoreException {
		try {
			monitor.beginTask(Messages.TargetProcessTaskAttachmentHandler_Getting_attachment, IProgressMonitor.UNKNOWN);
			TaskAttachmentMapper attachment = TaskAttachmentMapper.createFrom(attachmentAttribute);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			downloadAttachment(repository, task, attachment.getAttachmentId(), attachment.getLength(), out, monitor);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (Exception e) {
			throw new  CoreException(new Status(IStatus.ERROR, TargetProcessCorePlugin.ID_PLUGIN,
					Messages.TargetProcessTaskAttachmentHandler_unable_to_retrieve_attachment, e));
		} finally {
			monitor.done();
		}
	}

	@Override
	public void postContent(TaskRepository repository, ITask task, AbstractTaskAttachmentSource source, String comment,
			TaskAttribute attachmentAttribute, IProgressMonitor monitor) throws CoreException {
		try {
			monitor.beginTask(Messages.TargetProcessTaskAttachmentHandler_Sending_attachment, IProgressMonitor.UNKNOWN);
			TargetProcessClient client = connector.getClientManager().getClient(repository,
					new SubProgressMonitor(monitor, IProgressMonitor.UNKNOWN));

			client.postAttachment(task.getTaskId(), comment, source, attachmentAttribute, monitor);
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, TargetProcessCorePlugin.ID_PLUGIN,
					Messages.TargetProcessTaskAttachmentHandler_unable_to_submit_attachment, e));
		} finally {
			monitor.done();
		}
	}
	
	private void downloadAttachment(TaskRepository repository, ITask task, String attachmentId, Long attachmentLength, OutputStream out,
			IProgressMonitor monitor) throws Exception {
		TargetProcessClient client;
		try {
			client = connector.getClientManager().getClient(repository,
					new SubProgressMonitor(monitor, IProgressMonitor.UNKNOWN));
			client.getAttachmentData(repository, attachmentId, attachmentLength, out, monitor);
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, TargetProcessCorePlugin.ID_PLUGIN,
					Messages.TargetProcessTaskAttachmentHandler_unable_to_retrieve_attachment, e)); //$NON-NLS-1$
		}
		catch(Exception e)
		{
			throw e;
		}
	}

}
