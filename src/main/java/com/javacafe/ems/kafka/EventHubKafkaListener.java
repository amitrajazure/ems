package com.javacafe.ems.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventHubKafkaListener {
	
	EventHubKafkaListener() {
		System.out.println("inside kafka constructor");
	}
	
	//@KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(String message) {
		System.out.println("Message consumed: " +message);
	}
}
