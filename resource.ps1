@echo off
setlocal enabledelayedexpansion

REM Check if a directory argument is provided
if "%~1"=="" (
    echo Please provide the project root directory as an argument.
    echo Usage: %0 ^<project_root_directory^>
    exit /b 1
)

REM Set the base directory
set BASE_DIR=%~1\src\main\resources

REM Create main directories
mkdir "%BASE_DIR%\static\css" 2>nul
mkdir "%BASE_DIR%\static\js" 2>nul
mkdir "%BASE_DIR%\templates\contactInfo" 2>nul
mkdir "%BASE_DIR%\templates\education" 2>nul
mkdir "%BASE_DIR%\templates\skills" 2>nul
mkdir "%BASE_DIR%\templates\workHistory" 2>nul

REM Create empty CSS and JS files
echo. > "%BASE_DIR%\static\css\styles.css"
echo. > "%BASE_DIR%\static\js\script.js"

REM Function to create HTML files
:create_html
(
echo ^<!DOCTYPE html^>
echo ^<html xmlns:th="http://www.thymeleaf.org"^>
echo ^<head^>
echo     ^<meta charset="UTF-8"^>
echo     ^<meta name="viewport" content="width=device-width, initial-scale=1.0"^>
echo     ^<title^>%~1^</title^>
echo     ^<link rel="stylesheet" th:href="@{/css/styles.css}"^>
echo ^</head^>
echo ^<body^>
echo     ^<h1^>%~1^</h1^>
echo     ^<!-- Add your content here --^>
echo     ^<script th:src="@{/js/script.js}"^>^</script^>
echo ^</body^>
echo ^</html^>
) > "%~2"
exit /b 0

REM Create HTML files
call :create_html "DevFolio Home" "%BASE_DIR%\templates\home.html"
call :create_html "Add Contact Info" "%BASE_DIR%\templates\contactInfo\add.html"
call :create_html "Edit Contact Info" "%BASE_DIR%\templates\contactInfo\edit.html"
call :create_html "Contact Info List" "%BASE_DIR%\templates\contactInfo\list.html"
call :create_html "Add Education" "%BASE_DIR%\templates\education\add.html"
call :create_html "Edit Education" "%BASE_DIR%\templates\education\edit.html"
call :create_html "Education List" "%BASE_DIR%\templates\education\list.html"
call :create_html "Add Skills" "%BASE_DIR%\templates\skills\add.html"
call :create_html "Edit Skills" "%BASE_DIR%\templates\skills\edit.html"
call :create_html "Skills List" "%BASE_DIR%\templates\skills\list.html"
call :create_html "Add Work History" "%BASE_DIR%\templates\workHistory\add.html"
call :create_html "Edit Work History" "%BASE_DIR%\templates\workHistory\edit.html"
call :create_html "Work History List" "%BASE_DIR%\templates\workHistory\list.html"

echo Directory structure and files created successfully in %BASE_DIR%