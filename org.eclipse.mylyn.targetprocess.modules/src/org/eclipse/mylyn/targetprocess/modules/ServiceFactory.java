package org.eclipse.mylyn.targetprocess.modules;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.Stub;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.rampart.handler.WSSHandlerConstants;
import org.apache.rampart.handler.config.OutflowConfiguration;
import org.eclipse.mylyn.targetprocess.modules.services.AuthenticationServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IAssignableServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IBugServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.ICommentServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IEntityStateServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IFileServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IGeneralServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IGeneralUserServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IMyAssignmentsServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IPriorityServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IRequestServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.ITaskServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.interfaces.IUserStoryServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.tpstubs.TPAssignableServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.tpstubs.TPBugServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.tpstubs.TPCommentServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.tpstubs.TPEntityStateServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.tpstubs.TPFileServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.tpstubs.TPGeneralServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.tpstubs.TPGeneralUserServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.tpstubs.TPMyAssignmentsServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.tpstubs.TPPriorityServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.tpstubs.TPRequestServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.tpstubs.TPTaskServiceStub;
import org.eclipse.mylyn.targetprocess.modules.services.tpstubs.TPUserStoryServiceStub;

public class ServiceFactory implements IServiceFactory {

	private HashMap<TargetProcessCredentials, HashMap<Class<? extends Stub>, Stub>> entityStates;

	public ServiceFactory() {
		entityStates = new HashMap<TargetProcessCredentials, HashMap<Class<? extends Stub>, Stub>>();
	}

	static void setSecurityHeader(Stub stub, String login, String password) {
		ServiceClient client = stub._getServiceClient();
		Options options = client.getOptions();

		OutflowConfiguration ofc = new OutflowConfiguration();
		ofc.setActionItems("UsernameToken");
		ofc.setUser(login);
		PWCBHandler.getPasswordHashMap().put(login, password);
		ofc.setPasswordCallbackClass("org.eclipse.mylyn.targetprocess.modules.PWCBHandler");
		ofc.setPasswordType("PasswordText");

		options.setProperty(WSSHandlerConstants.OUTFLOW_SECURITY, ofc.getProperty());
		options.setProperty(org.apache.axis2.transport.http.HTTPConstants.CHUNKED, Boolean.FALSE);
		options.setProperty(org.apache.axis2.Constants.Configuration.CHARACTER_SET_ENCODING, "UTF-8");
		
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(login);
		auth.setPassword(password);
		auth.setPreemptiveAuthentication(true);
		
		options.setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, auth);
		options.setManageSession(true);

		client.setOptions(options);
	}

	public AuthenticationServiceStub getAuthentificationServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		return getServiceStub(AuthenticationServiceStub.class, targetProcessCredentials,
				"/services/AuthenticationService.asmx");
	}

	public IMyAssignmentsServiceStub getMyAssignmentServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		return getServiceStub(TPMyAssignmentsServiceStub.class, targetProcessCredentials,
				"/services/MyAssignmentsService.asmx");
	}

	public IBugServiceStub getBugServiceStub(TargetProcessCredentials targetProcessCredentials) throws RemoteException {
		return getServiceStub(TPBugServiceStub.class, targetProcessCredentials, "/services/BugService.asmx");
	}

	public IGeneralServiceStub getGeneralServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		return getServiceStub(TPGeneralServiceStub.class, targetProcessCredentials, "/services/GeneralService.asmx");
	}

	@Override
	public ICommentServiceStub getCommentServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		return getServiceStub(TPCommentServiceStub.class, targetProcessCredentials, "/services/CommentService.asmx");
	}

	public IGeneralUserServiceStub getGeneralUserServiceStub(TargetProcessCredentials serviceCreationParams)
			throws RemoteException {
		return getServiceStub(TPGeneralUserServiceStub.class, serviceCreationParams,
				"/services/GeneralUserService.asmx");
	}

	public IAssignableServiceStub getAssignableServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		return getServiceStub(TPAssignableServiceStub.class, targetProcessCredentials,
				"/services/AssignableService.asmx");
	}

	public ProjectServiceStub getProjectServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		return getServiceStub(ProjectServiceStub.class, targetProcessCredentials, "/services/ProjectService.asmx");
	}

	@Override
	public IUserStoryServiceStub getUserStoryServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		return getServiceStub(TPUserStoryServiceStub.class, targetProcessCredentials, "/services/UserStoryService.asmx");
	}

	@Override
	public ITaskServiceStub getTaskServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		return getServiceStub(TPTaskServiceStub.class, targetProcessCredentials, "/services/TaskService.asmx");
	}

	@Override
	public IPriorityServiceStub getPriorityServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		return getServiceStub(TPPriorityServiceStub.class, targetProcessCredentials, "/services/PriorityService.asmx");
	}

	@Override
	public IRequestServiceStub getRequestServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		return getServiceStub(TPRequestServiceStub.class, targetProcessCredentials, "/services/RequestService.asmx");
	}

	@Override
	public IEntityStateServiceStub getEntityStateServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		return getServiceStub(TPEntityStateServiceStub.class, targetProcessCredentials,
				"/services/EntityStateService.asmx");
	}

	@Override
	public IFileServiceStub getFileServiceStub(TargetProcessCredentials targetProcessCredentials)
			throws RemoteException {
		return getServiceStub(TPFileServiceStub.class, targetProcessCredentials, "/services/FileService.asmx");
	}

	@SuppressWarnings("unchecked")
	public <T extends org.apache.axis2.client.Stub> T getServiceStub(Class<T> t,
			TargetProcessCredentials targetProcessCredentials, String serviceName) throws RemoteException {

		HashMap<Class<? extends Stub>, Stub> services = null;

		if (!entityStates.containsKey(targetProcessCredentials)) {
			services = new HashMap<Class<? extends Stub>, Stub>();
			entityStates.put(targetProcessCredentials, services);
		} else {
			services = entityStates.get(targetProcessCredentials);
		}

		T stub = null;

		if (services.containsKey(t)) {
			stub = (T) services.get(t);
		} else {

			Constructor<T> cnstr = null;
			try {
				cnstr = t.getConstructor(new Class[] { ConfigurationContext.class, String.class });
				stub = cnstr.newInstance(createContext(), targetProcessCredentials.getRepositoryUrl() + serviceName);
				services.put(t, stub);
			} catch (Exception e) {
				throw new RemoteException("Cannot create service stub for " + t.getSimpleName(), e);
				// return null;
			}
		}

		if (targetProcessCredentials.isWindowsAuthentication()) {
			NtlmJcifsCredentials.register(targetProcessCredentials.getUserName(),
					targetProcessCredentials.getPassword(), targetProcessCredentials.getDomain());
		} else {
			NtlmJcifsCredentials.unregister();
		}

		setSecurityHeader(stub, targetProcessCredentials.getOriginalUserName(), targetProcessCredentials.getPassword());

		return stub;
	}

	private ConfigurationContext createContext() throws AxisFault {
		URL axis2URL = this.getClass().getResource("/org/eclipse/mylyn/targetprocess/modules/resources/axis2.xml");
		URL repositoryURL = this.getClass()
				.getResource("/org/eclipse/mylyn/targetprocess/modules/resources/repository");

		ConfigurationContext ctx = null;
		ctx = ConfigurationContextFactory.createConfigurationContextFromURIs(axis2URL, repositoryURL);
		return ctx;
	}

}
