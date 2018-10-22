package com.syssolu.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.syssolu.rabbitmq.Model.SampleMessage;

@SpringBootApplication
public class RabbitmqApplication implements CommandLineRunner{

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/**
		 * Simplest way to the publish the message from rabbit template.
		 * convertAndSend support many form of the data in Object like simple text message, image 
		 * As we do not defined any Exchange and binding it will go to default AMQP
		 *
		 * Default exchange :The default exchange is implicitly bound to every queue, with a routing key equal to the queue name. It it not possible to explicitly bind to, 
		 * or unbind from the default exchange. It also cannot be deleted.
 		 */

		rabbitTemplate.convertAndSend("Hello from the rabbit MQ project");

		/**
		 * As we define Exchange and routing key it will go to exchange(rabbitmq.demo.exchange) and will be visible under the queue(rabbitmq.demo.queue)
		 */
		rabbitTemplate.convertAndSend("rabbitmq.demo.exchange", "rabbitmq.routing.key", "Hello from the rabbit MQ project with binding");

		/**
		 * As we define Exchange and routing key it will go to exchange(rabbitmq.demo.exchange) and will be visible under the queue(rabbitmq.demo.queue)
		 * Use the Model to send the Object to MQ
		 */

		SampleMessage sampleMessage = new SampleMessage();
		sampleMessage.setName("Sample Message Name");
		sampleMessage.setDescription("Sample Hello from the rabbit MQ project with binding description ");
		rabbitTemplate.convertAndSend("rabbitmq.demo.exchange", "rabbitmq.routing.key", sampleMessage);
	}
}
