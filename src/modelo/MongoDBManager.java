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
	private MongoCollection coleccion;

	
	public MongoDBManager() {

		this.mongo = new MongoClient("localhost", 27017);
		 MongoDatabase db = mongo.getDatabase("vuelos2_0");
		this.coleccion = db.getCollection("vuelos");
	}
	
	public boolean comprarVuelo() {
		return true;
		
	}
	
	public boolean cancelarVuelo(Pasajero miPasajero, Vuelos vuelo) {
		return true;
		
	}
	
	public boolean modificarVuelo(Pasajero miPasajero, Vuelos vuelo) {
		return true;
		
	}
	
	public HashMap<Integer, Vuelos> mostrarTodosLosVuelos() {
		HashMap<Integer, Vuelos> miVueloBBDD = new HashMap<Integer, Vuelos>();

		return miVueloBBDD;
	}
	
	public HashMap<Integer, Vuelos> mostrarVuelosDelCliente() {
		HashMap<Integer, Vuelos> miVueloBBDD = new HashMap<Integer, Vuelos>();

		return miVueloBBDD;
	}
	

}
