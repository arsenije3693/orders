@echo off
echo Building project...
mvnw.cmd clean package -DskipTests
echo.
echo Build complete! JAR file is in target folder.
pause
