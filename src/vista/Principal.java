package vista;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import com.mongodb.Mongo;

import controlador.Controlador;
import modelo.MongoDBManager;
import modelo.Vuelos;

public class Principal {
	public static void main(String[] args) {
		Controlador miControlador = new Controlador();
		Principal miVista = new Principal();
		MongoDBManager mongo = new MongoDBManager();
		miControlador.setMiVista(miVista);
		miControlador.setMongo(mongo);
		System.out.println("¡Bienvenido a nuestra Aerolínea!");
		while (miControlador.ejecucion())
			;

	}

	public String respuestas(String pregunta, boolean respuesta) {
		String miRespuesta = "";

		System.out.println(pregunta);
		Scanner in = new Scanner(System.in);
		if (respuesta) {
			miRespuesta = in.next();
		}
		
		return miRespuesta;

	}

	public void convertirHashAstring(HashMap<String, Vuelos> miHash) {

		Iterator it = miHash.entrySet().iterator();

		String salida = "";
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();

			Vuelos miVuelo = (Vuelos) e.getValue();

			salida += convertirVueloEnString(miVuelo);
			
			

		}
		System.out.println(salida);

	}

	public String convertirVueloEnString(Vuelos miVuelo) {

		String salida = "id: " + miVuelo.getId_vuelo() + ", origen: " + miVuelo.getOrigen() + ", destino: "
				+ miVuelo.getDestino() + ", codigo de vuelo: " + miVuelo.getCodigo_vuelo() + ", fecha: "
				+ miVuelo.getFecha() + ", hora: " + miVuelo.getHora() + ", plazas totales: "
				+ miVuelo.getPlazas_totales() + ", plazas disponibles: " + miVuelo.getPlazas_disponibles() + "\n";

		return salida;

	}

	public String[] pedirDatosPasajero() {
		Scanner in = new Scanner(System.in);

		String [] datos = new String [5];
		
		System.out.println("Introduce los datos que se le solicitan a continuación:");
		System.out.println("DNI del pasajero:");
		datos[0] = in.next();
		System.out.println("Primer Apellido del pasajero:");
		datos[1] = in.next();
		System.out.println("Nombre del pasajero:");
		datos[2] = in.next();
		System.out.println("DNI del pagador:");
		datos[3] = in.next();
		System.out.println("número de la tarjeta de crédito:");
		datos[4] = in.next();
		
		
		return datos;
	}

}
