#!/bin/bash

NAME=$1

echo curl --data name=${NAME} -X POST http://localhost:3000/api/series/
curl --data name=${NAME} -X POST http://localhost:3000/api/series/

