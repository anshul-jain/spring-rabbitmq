package com.syssolu.rabbitmq.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Data
public class SampleMessage implements Serializable{
	private String name;
	private String description;
}
