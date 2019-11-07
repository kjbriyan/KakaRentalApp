package id.kakarental.store.Model.raja_ongkir;


import com.google.gson.annotations.SerializedName;


public class ResponseRajaOngkir{

	@SerializedName("rajaongkir")
	private Rajaongkir rajaongkir;

	public Rajaongkir getRajaongkir(){
		return rajaongkir;
	}
}