package com.tw.mongock.migration;

import com.tw.mongock.MongockApplication;
import com.tw.mongock.domain.Order;
import com.tw.mongock.domain.OrderItem;
import com.tw.mongock.repository.OrderRepository;
import io.mongock.api.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ChangeUnit(id = "initial changeUnit", order = "1", author = "mongock-mongodb-demo")
@Slf4j
public class InitialChangeUnit {

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
                IntStream.range(0, 10)
                        .mapToObj(i -> getRandomOrder(String.valueOf(i)))
                        .collect(Collectors.toList())
        );
    }

    @RollbackExecution
    public void rollbackExecution(OrderRepository orderRepository) {
        log.info("rollback of execution");
        orderRepository.deleteAll();
    }

    private static Order getRandomOrder(String i) {
        Random random = new Random();
        var item = OrderItem.builder().id(i).totalCost(random.nextDouble()).quantity(1).build();
        return Order.builder().id(i).customerName("name-" + i).items(List.of(item)).build();
    }
}
