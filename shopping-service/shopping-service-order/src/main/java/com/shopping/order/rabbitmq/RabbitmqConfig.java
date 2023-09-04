package com.shopping.order.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitmqConfig {


//    /**
//     * 创建连接工厂
//     * @return
//     */
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
//        cachingConnectionFactory.setAddresses("192.168.2.7:5672");
//        cachingConnectionFactory.setVirtualHost("order");
//        cachingConnectionFactory.setUsername("dev");
//        cachingConnectionFactory.setPassword("123123");
//        cachingConnectionFactory.setConnectionTimeout(10000);
//        cachingConnectionFactory.setCloseTimeout(10000);
//        return cachingConnectionFactory;
//    }

//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
//        //spring容器启动加载该类
//        rabbitAdmin.setAutoStartup(true);
//        return rabbitAdmin;
//    }

//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
//        rabbitTemplate.setReceiveTimeout(50000);
//        return rabbitTemplate;
//    }

    //=====================================申明三个交换机====================================================================
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("order.direct.exchange", true, false);
    }

    @Bean
    public DirectExchange txDirectExchange() {
        return new DirectExchange("order.tx.direct.exchange", true, false);
    }

    @Bean
    public DirectExchange directExchange1() {
        return new DirectExchange("order.direct.exchange1", true, false);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("order.fanout.exchange", true, false);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("order.topic.exchange", true, false);
    }

    @Bean
    public DirectExchange dlxNormalExchange() { return new DirectExchange("order.dlx.normal.exchange", true, false);}

    @Bean
    public DirectExchange deadLetterExchange() { return new DirectExchange("order.dead.letter.exchange", true, false);}

    //=====================================申明队列====================================================================
    @Bean
    public Queue directQueue() {
        return new Queue("orderDirectQueue", true, false, false, null);
    }

    @Bean
    public Queue txDirectQueue() {
        return new Queue("txDirectQueue", true, false, false, null);
    }

    @Bean
    public Queue directQueue1() {
        return new Queue("orderDirectQueue1", true, false, false, null);
    }

    @Bean
    public Queue fanoutQueue() {
        return new Queue("orderFanoutQueue", true, false, false, null);
    }

    @Bean
    public Queue topicQueue() {
        return new Queue("orderTopicQueue", true, false, false, null);
    }

    @Bean
    public Queue dlxNormalQueue() {
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange
        args.put("x-dead-letter-exchange", "order.dead.letter.exchange");
        // x-dead-letter-routing-key
        args.put("x-dead-letter-routing-key", "order.dead.letter.key");
        //x-message-ttl
        args.put("x-message-ttl", 9000);
        //x-max-length
        args.put("x-max-length", 99);
        return QueueBuilder.durable("order.dxl.normal.queue").withArguments(args).build();
    }

    @Bean
    public Queue deadLetterQueue() { return QueueBuilder.durable("order.dead.letter.queue").build();}

    //=====================================申明绑定====================================================================
    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("order.direct.key");
    }

    @Bean
    public Binding txDirectBinding() {
        return BindingBuilder.bind(txDirectQueue()).to(txDirectExchange()).with("order.tx.direct.key");
    }

    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange1()).with("order.direct.key1");
    }

    @Bean
    public Binding fanoutBinding() {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding topicBinding() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("order.topic.key.#");
    }

    @Bean
    public Binding dlxNormalBinding() { return BindingBuilder.bind(dlxNormalQueue()).to(dlxNormalExchange()).with("order.dlx.normal.key");}

    @Bean
    public Binding deadLetterBinding() { return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("order.dead.letter.key");}

}
