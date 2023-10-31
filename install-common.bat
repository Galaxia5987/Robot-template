@echo off
setlocal

set repository_url=https://github.com/Galaxia5987/Common.git

set destination_directory=src\main\java\frc\robot

if not exist "%destination_directory%" (
    mkdir "%destination_directory%"
)

cd "%destination_directory%"

if not exist "Common" (
    git clone %repository_url%
)

endlocal