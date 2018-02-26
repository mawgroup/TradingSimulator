package OrderClient;

import java.io.Serializable;

import Ref.Instrument;

public class NewOrderSingle implements Serializable{
	public int size;
	public float price;
	public Instrument instrument;
	public String pos;
	public NewOrderSingle(int size,float price,Instrument instrument, String pos){
		this.size=size;
		this.price=price;
		this.instrument=instrument;
		this.pos = pos;
	}
}