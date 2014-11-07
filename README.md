Mylyn Connector for Targetprocess
---------------------------------

Supported Eclipse versions: 3.7.2 - 4.4

How to generate connector for new version of Targetprocess:

- Download Axis2 1.5.6 from http://axis.apache.org/axis2/java/core/download.cgi
- Unpack downloaded Axis2 archive to some directory, e.g. `C:\bin\axis2-1.5.6`
- Create environment variable `AXIS2_HOME` and set its value to that directory `C:\bin\axis2-1.5.6`
- Add its `\bin` subdirectory (e.g. `C:\bin\axis2-1.5.6\bin`) to the `PATH` environment variable
- Go to `org.eclipse.mylyn.targetprocess.modules` directory and launch `generateService.bat`
- Launch Eclipse
- Refresh project, make sure project builds without errors
- Create zip archive with help of Plug-in Export Wizard
