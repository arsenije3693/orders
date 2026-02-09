@echo off
echo Running Spring Boot with full error output...
echo.
mvnw.cmd clean spring-boot:run 2>&1 | findstr /V "Downloading Downloaded"
pause
