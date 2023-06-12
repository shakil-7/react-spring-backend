aws dynamodb --endpoint-url=http://localhost:4566 create-table \
    --table-name User \
    --attribute-definitions \
        AttributeName=id,AttributeType=N \
    --key-schema \
        AttributeName=id,KeyType=HASH \
--provisioned-throughput \
        ReadCapacityUnits=10,WriteCapacityUnits=5