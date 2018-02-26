import java.util.Random;

import LiveMarketData.LiveMarketData;
import OrderManager.Order;
import Ref.Instrument;

import static java.lang.StrictMath.floor;

//TODO this should really be in its own thread
public class SampleLiveMarketData implements LiveMarketData{
	private static final Random RANDOM_NUM_GENERATOR=new Random();
	public void setPrice(Order o){
		o.initialMarketPrice=1+RANDOM_NUM_GENERATOR.nextInt(2);
	}
}
