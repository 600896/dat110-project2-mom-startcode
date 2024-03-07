package no.hvl.dat110.iotsystem;

import no.hvl.dat110.broker.Broker;
import no.hvl.dat110.broker.Dispatcher;
import no.hvl.dat110.broker.Storage;
import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;
import no.hvl.dat110.messages.ConnectMsg;
import no.hvl.dat110.messages.DisconnectMsg;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.messagetransport.Connection;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();

		// TODO - start

		// create a client object and use it to
		Client client = new Client("sensor", "Tema", 8080);
		Storage storage = new Storage();
		Dispatcher dispatcher = new Dispatcher(storage);
		Broker broker = new Broker(dispatcher, 8080);
		// - connect to the broker - user "sensor" as the user name
		// - publish the temperature(s)
		try {
			
			if(client.connect()) {
				ConnectMsg connectMsg = new ConnectMsg("sensor");
	          //  dispatcher.onConnect(connectMsg, ((Object) client).getConnection());
	            for (int i = 0; i < COUNT; i++) {
	                // Simulate getting a temperature reading
	             //   int temperature = sn.readTemperature();

	                // Create a PublishMsg with the temperature reading
	                PublishMsg publishMsg = new PublishMsg("temperature", "sensor", Integer.toString(0));

	                // Publish the temperature reading
	                dispatcher.onPublish(publishMsg);

	                // Sleep for a while before publishing the next reading
	                Thread.sleep(1000);
	            }

	            // Disconnect from the broker
	            DisconnectMsg disconnectMsg = new DisconnectMsg("sensor");
	            dispatcher.onDisconnect(disconnectMsg);

			}
//			ConnectMsg connect = new ConnectMsg("Sensor");
//			Connection connection = client.connect();
//            dispatcher.onConnect(connect, connection);
//			broker.doProcess();
//			
//			// - disconnect from the broker
//			DisconnectMsg disconnect = new DisconnectMsg("Disconeccting");
//			
//			dispatcher.onDisconnect(disconnect);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		

		// TODO - end

		System.out.println("Temperature device stopping ... ");

		throw new UnsupportedOperationException(TODO.method());

	}
}
