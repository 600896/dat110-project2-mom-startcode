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

		TemperatureSensor sn = new TemperatureSensor();
		Client client = new Client("sensor", "temperature", 8080);

		Storage storage = new Storage();
		Dispatcher dispatcher = new Dispatcher(storage);

		client.connect();
		try {
			if(client.connect()) {
	            for (int i = 0; i < COUNT; i++) {
	                int temperature = sn.read();
	                PublishMsg publishMsg = new PublishMsg("Temperature", "sensor", Integer.toString(temperature));
	                dispatcher.onPublish(publishMsg);
	                Thread.sleep(1000);
	            }

	            DisconnectMsg disconnectMsg = new DisconnectMsg("sensor");
	            dispatcher.onDisconnect(disconnectMsg);

			} else {
				System.out.println("Kunne ikke koble til brokeren");
			}

		} catch(Exception e) {
			e.fillInStackTrace();
		}

		System.out.println("Temperature device stopping ... ");


	}
}
