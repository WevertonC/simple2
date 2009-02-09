@echo off

REM FOR /R .\lib %%G IN (*.jar) DO set CLASSPATH=!CLASSPATH!;%%G

set JAVA=java -cp bin\; simple.main.Main


%JAVA% 