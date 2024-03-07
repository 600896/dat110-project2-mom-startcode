package no.hvl.dat110.broker;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.common.TODO;
import no.hvl.dat110.messages.CreateTopicMsg;
import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messagetransport.Connection;

public class Storage {

	// data structure for managing subscriptions
	// maps from a topic to set of subscribed users
	protected ConcurrentHashMap<String, Set<String>> subscriptions;
	
	// data structure for managing currently connected clients
	// maps from user to corresponding client session object
	
	protected ConcurrentHashMap<String, ClientSession> clients;

	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
	}

	public Collection<ClientSession> getSessions() {
		return clients.values();
	}

	public Set<String> getTopics() {

		return subscriptions.keySet();

	}

	// get the session object for a given user
	// session object can be used to send a message to the user
	
	public ClientSession getSession(String user) {
//		ConcurrentHashMap.newKeySet();
		
		ClientSession session = clients.get(user);

		return session;
	}

	public Set<String> getSubscribers(String topic) {

		return subscriptions.get(topic);

	}

	public void addClientSession(String user, Connection connection) {

		// TODO: add corresponding client session to the storage
		// See ClientSession class
		//ConcurrentHashMap.newKeySet();
		ClientSession cs = new ClientSession(user, connection);
		clients.put(user, cs);
	
		
	}


	public void removeClientSession(String user) {
		ConcurrentHashMap.newKeySet();
		ClientSession cs = getSession(user);

		clients.remove(user, cs);
		
		
	}

	public void createTopic(String topic) {
		
	//	CreateTopicMsg ct = new CreateTopicMsg(topi );
		subscriptions.put(topic, ConcurrentHashMap.newKeySet());

		// TODO: create topic in the storage
	
	}

	public void deleteTopic(String topic) {
		
		Set<String> sub = subscriptions.get(topic);
		
		subscriptions.remove(topic, sub);

	}

	public void addSubscriber(String user, String topic) {
		
		
		subscriptions.get(topic).add(user);

		// TODO: add the user as subscriber to the topic
		
	}

	public void removeSubscriber(String user, String topic) {

		// Remove the user as a subscriber from the topic
		subscriptions.get(topic).remove(user);
		   
	}
}
