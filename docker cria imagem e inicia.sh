#!/bin/sh
docker rm -f bovespa-fii 
docker build -t fvslistas/bovespa-fii:0.0.2 .
docker run -d -p 8081:8081 --network bovespa --rm  --env MYSQL_HOST=bovespa-fii-mysql  --name bovespa-fii fvslistas/bovespa-fii:0.0.2 
