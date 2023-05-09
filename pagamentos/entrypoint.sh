#!/bin/bash
/usr/local/bin/wait-for-it.sh mysql-pagamentos:3306 -t 120 && java -jar /usr/local/bin/app.jar