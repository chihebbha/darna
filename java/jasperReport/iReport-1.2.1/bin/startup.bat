@echo off
set TMP_CLASSPATH=%CLASSPATH%

for %%i in (%0) do cd /d %%~dpi\..


set CLASSPATH=%CLASSPATH%;.\classes\;.\fonts\;
rem Add all jars....
for %%i in (".\lib\*.jar") do call ".\bin\cpappend.bat" %%i
for %%i in (".\lib\*.zip") do call ".\bin\cpappend.bat" %%i

set IREPORT_CLASSPATH=%CLASSPATH%
set CLASSPATH=%TMP_CLASSPATH%

java -cp "%IREPORT_CLASSPATH%" -Xms24m -Xmx512m it.businesslogic.ireport.gui.MainFrame %*

