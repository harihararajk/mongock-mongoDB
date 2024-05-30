package com.tw.mongock.migration;

import com.tw.mongock.MongockApplication;
import com.tw.mongock.domain.Order;
import com.tw.mongock.repository.OrderRepository;
import io.mongock.api.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ChangeUnit(id="client-initializer", order = "1", author = "mongock-execution")
@Slf4j
public class InitialChangeUnit {

    public final static int INITIAL_CLIENTS = 10;

    @BeforeExecution
    public void beforeExecution(MongoTemplate mongoTemplate) {
        log.info("creating collection");
        mongoTemplate.createCollection(MongockApplication.ORDER_COLLECTION_NAME);
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution(MongoTemplate mongoTemplate) {
        log.info("rollback of collection creation");
        mongoTemplate.dropCollection(MongockApplication.ORDER_COLLECTION_NAME);
    }

    @Execution
    public void execution(OrderRepository orderRepository) {

        orderRepository.saveAll(
                IntStream.range(0, INITIAL_CLIENTS)
                        .mapToObj(InitialChangeUnit::getRandomOrder)
                        .collect(Collectors.toList())
        );
    }

    @RollbackExecution
    public void rollbackExecution(OrderRepository orderRepository) {
        log.info("rollback of execution");

        orderRepository.deleteAll();
    }

    private static Order getRandomOrder(int i) {
        Random random = new Random();
        return  Order.builder().id(String.valueOf(i)).name("name-"+i).quantity(random.nextInt()).cost(random.nextDouble()).build();
    }
}
