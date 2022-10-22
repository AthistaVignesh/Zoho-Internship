#!/bin/bash

javac -cp ".:WEB-INF/lib/*" $(find java -name *.java) -d WEB-INF/classes/ 

echo "successfull"
