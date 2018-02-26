package Database;

import OrderManager.Order;
import org.apache.log4j.PropertyConfigurator;

import java.util.Arrays;

//TODO figure out how to make this abstract or an interface, but want the method to be static
public class Database{

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Database.class.getName());

	public static void write(Object o){
		System.out.println(o.toString());
	}

	//to log accepted orders

	public static void writeAcceptedOrder(Order o){
		initialiseLogging();
		System.out.println(o.toString());
		//logger.info("Accepted Order = " + o.OrdStatus + "Instrument: " + o.instrument + " Order ID: " + o.id + " Order size filled: " + o.slices);
	}

	public static void writeSlicedOrder(Order o){
		initialiseLogging();
		System.out.println(o.toString());
		logger.info("Sliced Order = " + "Instrument: " + o.instrument + " Order ID: " + o.id + " Order sliced: " + o.sliceSizes());
	}

	public static void writePricedOrder(Order o){
		initialiseLogging();
		System.out.println(o.toString());
		//logger.info("Order Priced = " + "Instrument: " + o.instrument + " Order ID: " + o.id + " Order price: " + Arrays.toString(o.bestPrices));
	}

	public static void initialiseLogging()
	{
		PropertyConfigurator.configure("LoggingData/log4j.properties");
	}

}