//package com.tw.mongock.migration;
//
//import com.couchbase.client.java.Cluster;
//import io.mongock.api.annotations.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.couchbase.CouchbaseClientFactory;
//
//@Slf4j
//@ChangeUnit(id = "client-initializer", order = "1", author = "mongock")
//public class MigrationChangeUnit {
//
//
//    @BeforeExecution
//    public void beforeExecution(CouchbaseClientFactory clientFactory) {
//        log.debug("beforeExecution with bucket {}", clientFactory.getBucket().name() );
//    }
//
//    @RollbackBeforeExecution
//    public void rollbackBeforeExecution(CouchbaseClientFactory clientFactory) {
//        log.debug("rollbackBeforeExecution with bucket {}", clientFactory.getBucket().name() );
//    }
//    @Execution
//    public void execution(Cluster cluster) {
//        cluster.queryIndexes().createPrimaryIndex("orders");
////        cluster.queryIndexes().createIndex("orders", "idx_example_index", Arrays.asList("field1, field2"));
//    }
//
//    @RollbackExecution
//    public void rollbackExecution(Cluster cluster) {
//        cluster.queryIndexes().dropPrimaryIndex("orders");
////        cluster.queryIndexes().dropIndex("orders", "idx_example_index", DropQueryIndexOptions.dropQueryIndexOptions().ignoreIfNotExists(true));
//    }
//
//}
