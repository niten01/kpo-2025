package hse.kpo.rmq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMQConfig {
    @Value("${app.queues.payment-requests}")
    private String paymentRequestsQueue;

    @Value("${app.queues.payment-outcomes}")
    private String paymentOutcomesQueue;

    @Bean
    public Queue paymetRequestsQueue() {
        return new Queue(paymentRequestsQueue, true);
    }
}
