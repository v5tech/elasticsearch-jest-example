@echo off

set DIR=%~dp0
set LIB="%DIR%\..\lib\*"
set BIN="%DIR%\..\bin\*"

REM ???
echo {^
    "type" : "jdbc",^
    "jdbc" : {^
        "url" : "jdbc:mysql://localhost:3306/news",^
        "user" : "root",^
        "password" : "root",^
	"schedule" : "0 0/15 * ? * *",^
        "sql" :  [^
             {"statement":"SELECT title,content,url,source,author,pubdate FROM news"},^
             {^
                "statement":"SELECT title,content,url,source,author,pubdate FROM news where pubdate > ?",^
                "parameter" : [ "$metrics.lastexecutionstart" ]^
             }^
	],^
	"autocommit" : true,^
        "treat_binary_as_string" : true,^
        "elasticsearch" : {^
             "cluster" : "elasticsearch",^
             "host" : "localhost",^
             "port" : 9300^
        },^
        "index" : "news",^
        "type" : "article"^
      }^
}^ | "%JAVA_HOME%\bin\java" -cp "%LIB%" -Dlog4j.configurationFile="file://%DIR%\log4j2.xml" "org.xbib.tools.Runner" "org.xbib.tools.JDBCImporter"