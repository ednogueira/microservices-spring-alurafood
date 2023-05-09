#!/bin/bash
/usr/local/bin/wait-for-it.sh mysql-pedidos:3308 -t 120 && java -jar /usr/local/bin/app.jar