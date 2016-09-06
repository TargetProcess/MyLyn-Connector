Mylyn Connector for Targetprocess
=================================

Supported Eclipse versions: 3.7.2 - 4.6

How to generate connector for new version of Targetprocess
----------------------------------------------------------

- Download Axis2 1.5.6 from http://axis.apache.org/axis2/java/core/download.cgi
- Unpack downloaded Axis2 archive to some directory, e.g. `C:\bin\axis2-1.5.6`
- Create environment variable `AXIS2_HOME` and set its value to that directory `C:\bin\axis2-1.5.6`
- Add its `\bin` subdirectory (e.g. `C:\bin\axis2-1.5.6\bin`) to the `PATH` environment variable
- Go to `org.eclipse.mylyn.targetprocess.modules` directory
- If URL to the Targetprocess differs from default "http://localhost/targetprocess", fix it
  in the file `generateService.bat` at the line `set host=http://localhost/targetprocess`
- Launch `generateService.bat`, wait while it finishes.
  Fix errors if any and relaunch `generateService.bat` until it finishes without any errors/exceptions.
  Warning `[WARN] Type {http://targetprocess.com}anyType missing!` could be ignored.
- Launch Eclipse 4.4
- Refresh project `org.eclipse.mylyn.targetprocess.modules`, make sure all projects are rebuilt without errors
- Open file `feature.xml` (it is in `TargetProcess` project), click `Export Wizard`, create zip archive

How to test connector in Eclipse
--------------------------------

- Create Run Configuration: Run - Run Configurations... - Choose "Eclipse Application" - New launch configuration,
  Run a product: `org.eclipse.platform.ide`
- Run
- Open View named `Task Repositories` (Window -> Show View), then choose `Add Task Repository`
- Fill in connection settings: URL "http://localhost/targetprocess", Repository Name "TP", Login, Password
- Press "Check connection" button, you should see "Authentication credentials are valid"
- Press OK, then agree to create new task query
- Open View `Task List`, there you should see your task query with list of loaded tasks
