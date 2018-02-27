import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import LiveMarketData.LiveMarketData;
import OrderManager.OrderManager;

import static Database.Database.memoryUsage;

public class Main{
	public static void main(String[] args) throws IOException{
		System.out.println("TEST: this program tests ordermanager");

		//start sample clients
		MockClient c1=new MockClient("Client 1",2000);
		MockClient c2=new MockClient("Client 2",2001);
		MockClient c3=new MockClient("Client 3",2002);
		MockClient c4=new MockClient("Client 4",2003);
		MockClient c5=new MockClient("Client 5",2004);
		c1.start();
		c2.start();
		c3.start();
		c4.start();
		c5.start();
		//(new MockClient("Client 2",2001)).start();
		//(new MockClient("Client 3",2002)).start();

		//start sample routers
		(new SampleRouter("Router LSE",2010)).start();
//		(new SampleRouter("Router BATE",2011)).start();
//		(new SampleRouter("Router AMY",2012)).start();
	
		(new Trader("Trader James",2020)).start();
		//(new Trader("Trader Amirus",2021)).start();

		//start order manager
		InetSocketAddress[] clients={new InetSocketAddress("localhost",2000), new InetSocketAddress("localhost",2001),new InetSocketAddress("localhost",2002),
				new InetSocketAddress("localhost",2003), new InetSocketAddress("localhost",2004)};
		InetSocketAddress[] routers={new InetSocketAddress("localhost",2010)};
//		                     new InetSocketAddress("localhost",2011), new InetSocketAddress("localhost",2012)};
		InetSocketAddress trader=new InetSocketAddress("localhost",2020);
		InetSocketAddress traderAmiru=new InetSocketAddress("localhost",2021);
		LiveMarketData liveMarketData=new SampleLiveMarketData();
		(new MockOM("Order Manager",routers,clients,trader,liveMarketData)).start();
//		(new MockOM("Order Manager",routers,clients,traderAmiru,liveMarketData)).start();

		memoryUsage();
	}
}
class MockClient extends Thread{
	int port;
	MockClient(String name,int port){
		this.port=port;
		this.setName(name);
	}
	public void run(){
		try {
			SampleClient client=new SampleClient(port);
			client.sendOrder(null);
			client.messageHandler();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class MockOM extends Thread{
	InetSocketAddress[] clients;
	InetSocketAddress[] routers;
	InetSocketAddress trader;
	LiveMarketData liveMarketData;
	MockOM(String name,InetSocketAddress[] routers,InetSocketAddress[] clients,InetSocketAddress trader,LiveMarketData liveMarketData){
		this.clients=clients;
		this.routers=routers;
		this.trader=trader;
		this.liveMarketData=liveMarketData;
		this.setName(name);
	}
	@Override
	public void run(){
		try{
			System.out.println("vermics");
			//In order to debug constructors you can do F5 F7 F5
			new OrderManager(routers,clients,trader,liveMarketData);
		}catch(IOException | ClassNotFoundException | InterruptedException ex){
			Logger.getLogger(MockOM.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
}