Starts the single-node cluster and exposes the node¡Çs port (9042) on the host
machine
```
docker network create cassandra-net
docker run --name my-cassandra \
    --network cassandra-net \
    -p 9042:9042 \
    -d cassandra:latest
```

Start the CQL shell
``` shell
docker run -it --network cassandra-net --rm cassandra cqlsh my-cassandra
```

Create keyspace
``` shell
cqlsh> create keyspace tacocloud
    with replication={'class':'SimpleStrategy', 'replication_factor':1}
    and durable_writes=true;
```

``` shell
select id, name, createdAt, ingredients from tacos;
```