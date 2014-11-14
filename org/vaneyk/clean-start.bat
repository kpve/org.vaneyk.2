@echo on

CALL .\.bat\clean-and-build.bat %*

START CMD /C CALL .\.bat\start-rugby-data.bat %* ^& PAUSE
START CMD /C CALL .\.bat\start-rugby-web.bat %* ^& PAUSE

pause
