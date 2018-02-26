package Database;

import OrderClient.NewOrderSingle;
import OrderManager.Order;
import org.apache.log4j.PropertyConfigurator;

import java.util.Arrays;

//TODO figure out how to make this abstract or an interface, but want the method to be static
public class Database {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Database.class.getName());

    public static void write(Object o) {
        System.out.println(o.toString());
    }

    public static void writeSentOrder(NewOrderSingle o) {
        initialiseLogging();
        logger.info("Order sent = " + " Instrument: " + o.instrument + ", Price: " + o.price + ", Size " + o.size + ", Position: " + o.pos);
    }

    public static void writeAcceptedOrder(Order o) {
        initialiseLogging();
        logger.info("Accepted Order = " + o.OrdStatus + ", Order ID: " + o.id + ", Client ID: " + o.clientid + ", Client order Id: " + o.clientOrderID + ", Instrument: " + o.instrument + ", Position: " + o.pos);
    }

    public static void writeSlicedOrder(Order o) {
        initialiseLogging();
        logger.info("Sliced Order = " + o.OrdStatus + ", Order ID: " + o.id + ", Client ID: " + o.clientid + ", Client order Id: " + o.clientOrderID + ", Instrument: " + o.instrument + ", Slice size: " + o.size + ", Position: " + o.pos);

    }

    public static void writePricedOrder(Order o) {
        initialiseLogging();
        logger.info("Priced Order = " + o.OrdStatus + ", Order ID: " + o.id + ", Client ID: " + o.clientid + ", Client order Id: " + o.clientOrderID + ", Instrument: " + o.instrument + " Order price: " + Arrays.toString(o.bestPrices));
    }

    public static void writeFilledOrder(Order o) {
        initialiseLogging();
        logger.info("Filled Order = " + o.OrdStatus + ", Order ID: " + o.id + ", Client ID: " + o.clientid + ", Client order Id: " + o.clientOrderID + ", Instrument: " + o.instrument + ", Order size filled: " + o.sizeFilled());
    }

    public static void writeFullyFilledOrder(Order o) {
        initialiseLogging();
        //   System.out.println(o.toString());
        logger.info("Fully Filled Order " + o.OrdStatus + ", Order ID: " + o.id + ", Client ID: " + o.clientid + ", Client order Id: " + o.clientOrderID + ", Instrument: " + o.instrument + ", Position: " + o.pos + ", Order size filled: " + o.sizeFilled());

    }

    public static void writeCancelledOrder(Order o) {
        initialiseLogging();
        logger.info("Cancelled Order = " + o.OrdStatus + ", Order ID: " + o.id + ", Client ID: " + o.clientid + ", Client order Id: " + o.clientOrderID + ", Instrument: " + o.instrument);

        //	logger.info("Client ID: " + o.clientid + ", Order Cancelled = " + o.OrdStatus + ", Instrument: " + o.instrument + ", Order ID: " + o.orderId);

    }

    public static void writeRoutedOrder(Order o) {
        initialiseLogging();
        logger.info("Routed Order = " + o.OrdStatus + ", Order ID: " + o.id + ", Client ID: " + o.clientid + ", Client order Id: " + o.clientOrderID + ", Instrument: " + o.instrument + ", Size " + o.size + ", Position: " + o.pos);
    }

    public static void memoryUsage() {
        initialiseLogging();
        Runtime runtime = Runtime.getRuntime();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        logger.info("Memory usage = " + memory / (1024L * 1024L) + " megabytes");
    }

    public static void initialiseLogging() {
        PropertyConfigurator.configure("LoggingData/log4j.properties");
    }


}