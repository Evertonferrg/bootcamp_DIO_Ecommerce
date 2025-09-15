package br.com.dio.storefront.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

    public static final String EXCHANGE_NAME = "product.change.availability.exchange";
    public static final String QUEUE_NAME = "product.change.availability.queue";
    public static final String ROUTING_KEY = "product.change.availability.routing.key";

    @Bean
    Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory,
                                  final Jackson2JsonMessageConverter converter) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

    // ---- Declarações para criar a infra no RabbitMQ ----
    @Bean
    public Queue productChangeQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public TopicExchange productChangeExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    @Bean
    public Binding productChangeBinding(Queue productChangeQueue, TopicExchange productChangeExchange) {
        return BindingBuilder.bind(productChangeQueue).to(productChangeExchange).with(ROUTING_KEY);
    }

    // Garante que o RabbitAdmin exista para declarar exchange/queue/binding
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        var admin = new RabbitAdmin(connectionFactory);
        admin.setAutoStartup(true);
        return admin;
    }


    @Bean
    public ApplicationRunner declareOnStartup(RabbitAdmin admin, Queue queue, TopicExchange exchange, Binding binding) {
        return args -> {
            admin.declareExchange(exchange);
            admin.declareQueue(queue);
            admin.declareBinding(binding);
        };
    }
}
