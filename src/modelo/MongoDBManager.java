package modelo;

import java.util.HashMap;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBManager {
	private MongoClient mongo;
	private MongoDatabase db;
	private MongoCollection coleccion;

	
	public MongoDBManager() {

		this.mongo = new MongoClient("localhost", 27017);
		this.db = mongo.getDatabase("vuelos2_0");
		this.coleccion = db.getCollection("vuelos");
	}
	
	public String comprarVuelo() {
		return "Vuelo comprado con exito";
		
	}
	
	public String cancelarVuelo(Pasajero miPasajero) {
		return "Vuelo cancelado con exito";
		
	}
	
	public String modificarVuelo(Pasajero miPasajero) {
		return "Vuelo modificado con exito";
		
	}
	
	public HashMap<Integer, Vuelos> mostrarVuelos() {
		// TODO Auto-generated method stub
		HashMap<Integer, Vuelos> miVueloBBDD = new HashMap<Integer, Vuelos>();

		return miVueloBBDD;
	}
	

}
