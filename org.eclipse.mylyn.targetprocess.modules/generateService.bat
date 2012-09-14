set host=http://localhost/targetprocess
if not "%1" == "" (
   set host=%1
  )

call wsdl2java.bat -uri %host%/services/AuthenticationService.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
call wsdl2java.bat -uri %host%/services/MyAssignmentsService.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
call wsdl2java.bat -uri %host%/services/BugService.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
call wsdl2java.bat -uri %host%/services/GeneralUserService.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
call wsdl2java.bat -uri %host%/services/AssignableService.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
call wsdl2java.bat -uri %host%/services/ProjectService.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
call wsdl2java.bat -uri %host%/services/UserStoryService.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
call wsdl2java.bat -uri %host%/services/TaskService.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
call wsdl2java.bat -uri %host%/services/PriorityService.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
call wsdl2java.bat -uri %host%/services/RequestService.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
call wsdl2java.bat -uri %host%/services/EntityStateService.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
call wsdl2java.bat -uri %host%/services/GeneralService.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
call wsdl2java.bat -uri %host%/services/FileService.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
call wsdl2java.bat -uri %host%/services/CommentService.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
