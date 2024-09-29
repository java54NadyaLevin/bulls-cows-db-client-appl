package telran.net.games;

import telran.net.TcpClient;
import telran.view.*;
import telran.net.games.service.*;

import java.util.*;
public class BullsCowsClientAppl {

	private static final String DEFAULT_HOST = "localhost";
	private static final int DEFAULT_PORT = 5000;
	
	public static void main(String[] args) {
		
		String hostIp = args.length > 0 ? args[0] : DEFAULT_HOST ;
		int port = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_PORT;
		TcpClient tcpClient = new TcpClient(hostIp, port);
		BullsCowsService bullsCows = new BullsCowsProxy(tcpClient);
		List<Item> items = BullsCowsApplItems.getItems(bullsCows);
		items.add(Item.of("Exit & Close connection", io -> tcpClient.close(), true));
		Menu menu = new Menu("Bulls and Cows Game",
				items.toArray(Item[]::new));
		menu.perform(new SystemInputOutput());

	}

}

