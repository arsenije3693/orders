@echo off
echo Enter your Azure Web App name:
set /p WEBAPP_NAME=
echo.
echo Fetching logs...
az webapp log tail --name %WEBAPP_NAME% --resource-group <your-resource-group>
