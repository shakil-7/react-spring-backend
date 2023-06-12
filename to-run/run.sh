#!/bin/bash

clear

# aws configuration
chmod +x aws-configure.sh
./aws-configure.sh

# to run the docker
docker-compose up -d
sleep 3
doc

# to visualize the dynamodb table in dynamodb-admin
DYNAMO_ENDPOINT=http://localhost:4566 dynamodb-admin --open


# create a new table
chmod +x user-table-create.sh
./user-table-create.sh
