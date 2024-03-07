package no.hvl.dat110.messages;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class CreateTopicMsg extends Message{
	
	// message sent from client to create topic on the broker
	private Message message;
	private String topic;


	public CreateTopicMsg(String user, String topic) {
		super(MessageType.CREATETOPIC, user);
		this.topic = topic;

	}
	@Override
	public String toString() {
		return "CreateTopicMsg [message=" + message + ", topic=" + topic + "]";
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}


	// TODO: 
	// Implement object variables - a topic is required
	
	// Complete the constructor, get/set-methods, and toString method
    // as described in the project text	
}
