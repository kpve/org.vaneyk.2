@echo on

START CMD /C CALL .\.bat\run-rugby-data.bat %* ^& PAUSE
START CMD /C CALL .\.bat\run-rugby-web.bat %* ^& PAUSE

