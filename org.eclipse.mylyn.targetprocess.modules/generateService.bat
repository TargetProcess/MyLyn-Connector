@echo off

set host=http://localhost/targetprocess
if not "%1" == "" (
  set host=%1
)

for %%s in (AuthenticationService MyAssignmentsService BugService GeneralUserService AssignableService ProjectService UserStoryService TaskService PriorityService RequestService EntityStateService GeneralService FileService CommentService) do (
  call wsdl2java.bat -uri %host%/services/%%s.asmx?wsdl -p org.eclipse.mylyn.targetprocess.modules.services -or
)
