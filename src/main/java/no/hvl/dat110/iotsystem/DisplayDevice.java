package no.hvl.dat110.iotsystem;

import no.hvl.dat110.broker.Broker;
import no.hvl.dat110.broker.ClientSession;
import no.hvl.dat110.broker.Dispatcher;
import no.hvl.dat110.broker.Storage;
import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.*;
import no.hvl.dat110.common.TODO;
import org.apache.maven.surefire.api.event.StandardStreamOutEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DisplayDevice {
	
	private static final int COUNT = 10;
	private ClientSession clientSession;
		
	public static void main (String[] args) {
		
		System.out.println("Display starting ...");
				
		// create a client object and use it to
        Client client = new Client("display", "localhost", 8080);
		client.connect();
		try {
			if(client.connect()) {
				Storage storage = new Storage();
				Dispatcher dispatcher = new Dispatcher(storage);
				Broker broker = new Broker(dispatcher, 8080);

				// - connect to the broker - use "display" as the username
				// - create the temperature topic on the broker
				// - subscribe to the topic
				SubscribeMsg msg = new SubscribeMsg("display", "Temperature");
				dispatcher.onSubscribe(msg);
				// - receive messages on the topic
				client.receive();
				// - unsubscribe from the topic
				UnsubscribeMsg unsubmsg = new UnsubscribeMsg("display", "Temperature");
				dispatcher.onUnsubscribe(unsubmsg);
				// - disconnect from the broker
				DisconnectMsg dmsg = new DisconnectMsg("display");
				dispatcher.onDisconnect(dmsg);

				// TODO - END
			} else{
				System.out.println("kunne ikke koble til brokeren");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println("Display stopping ... ");
		
	}
}
