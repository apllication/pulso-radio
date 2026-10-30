@echo off
setlocal
cd /d "%~dp0"
where node >nul 2>&1
if errorlevel 1 (
 echo Instale o Node.js 22 ou superior antes de continuar.
 pause
 exit /b 1
)
echo Preparando o Pulso para o Android Studio...
call npm ci
if errorlevel 1 goto erro
call npm run android:sync
if errorlevel 1 goto erro
if exist "%ProgramFiles%\Android\Android Studio1\product-info.json" set "CAPACITOR_ANDROID_STUDIO_PATH=%ProgramFiles%\Android\Android Studio1\bin\studio64.exe"
call npm run android:open
if errorlevel 1 goto erro
exit /b 0
:erro
echo Nao foi possivel concluir. Confira a mensagem acima e o LEIA-ME.md.
pause
exit /b 1

