package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class MongoDBManager {
	private MongoClient mongo;
	private MongoCollection<Document> coleccion;

	public MongoDBManager() {

		this.mongo = new MongoClient("localhost", 27017);
		MongoDatabase db = mongo.getDatabase("vuelos2_0");
		this.coleccion = db.getCollection("vuelos");
	}

	public boolean comprarVuelo(String[] arrDatosPasajero, Vuelos vueloSeleccioando) {

		int asientoGenerado = generarAsiento(vueloSeleccioando);

		String codigoVentaGenerado = GenerarCodigo();

		String[] nombresCampos = { "asiento", "dni", "apellido", "nombre", "dniPagador", "tarjeta", "codigoVenta" };

		Document quienCambio = new Document("codigo", vueloSeleccioando.getCodigo_vuelo());

		Document camposPasajero = new Document();

		int contadorDatos = 0;
		for (int i = 0; i < nombresCampos.length; i++) {
			if (i == 0) {
				camposPasajero.append(nombresCampos[i], asientoGenerado);
			} else if (i == nombresCampos.length - 1) {
				camposPasajero.append(nombresCampos[i], codigoVentaGenerado);
			} else {
				camposPasajero.append(nombresCampos[i], arrDatosPasajero[contadorDatos]);
				contadorDatos++;
			}
		}

		Document vendidos = new Document("vendidos", camposPasajero);
		Document auxSet = new Document("$addToSet", vendidos);

		coleccion.updateOne(quienCambio, auxSet);

		return true;

	}

	public boolean cancelarVuelo(Pasajero miPasajero, Vuelos vuelo) {
		return true;

	}

	public boolean modificarVuelo(Pasajero miPasajero, Vuelos vuelo) {
		return true;

	}

	public HashMap<String, Vuelos> mostrarTodosLosVuelos() {
		HashMap<String, Vuelos> todosVuelos = new HashMap<String, Vuelos>();
		FindIterable<Document> fi = coleccion.find();
		MongoCursor<Document> cursor = fi.cursor();

		while (cursor.hasNext()) {
			Document doc = cursor.next();

//			ArrayList<Double> arrList = (ArrayList<Double>) (doc.get("asientos_libres"));
//			System.out.println(arrList.get(0).intValue());
//			System.out.println((doc.get("asientos_libres")).getClass().getSimpleName());
//			System.out.println((doc.get("asientos_libres")));

			Vuelos esteVuelo = new Vuelos(doc.getInteger("id"), doc.getString("codigo"), doc.getString("origen"),
					doc.getString("destino"), doc.getString("fecha"), doc.getString("hora"),
					doc.getInteger("plazas_totales"), doc.getInteger("plazas_disponibles"));

			todosVuelos.put(doc.getString("codigo"), esteVuelo);
		}
		return todosVuelos;
	}

	public HashMap<String, Vuelos> mostrarVuelosDelCliente() {
		HashMap<String, Vuelos> miVueloBBDD = new HashMap<String, Vuelos>();

		return miVueloBBDD;
	}

	public Vuelos seleccionarUno(String codigo) {
		Vuelos vueloEncontrado = null;

		FindIterable<Document> fi = coleccion.find();
		MongoCursor<Document> cursor = fi.cursor();

		while (cursor.hasNext()) {
			Document doc = cursor.next();
			if (doc.getString("codigo").equals(codigo)) {
				vueloEncontrado = new Vuelos(doc.getInteger("id"), doc.getString("codigo"), doc.getString("origen"),
						doc.getString("destino"), doc.getString("fecha"), doc.getString("hora"),
						doc.getInteger("plazas_totales"), doc.getInteger("plazas_disponibles"));

				if (doc.get("asientos_libres") != null) {
					vueloEncontrado.setAsientos((ArrayList<Integer>) (doc.get("asientos_libres")));
				}
			}

			// ArrayList<Double> arrList = (ArrayList<Double>) (doc.get("asientos_libres"))

		}

		return vueloEncontrado;

	}

	public String GenerarCodigo() {
		String codigo = "";
		int caracteres = (int) Math.floor(Math.random() * (5 - 3 + 1) + 3);
		for (int i = 0; i < caracteres; i++) {
			int codigoAscii = (int) Math.floor(Math.random() * (90 - 65 + 1) + 65);
			codigo = codigo + (char) codigoAscii;
		}

		int numeros = (int) Math.floor(Math.random() * (999999 - 10 + 1) + 10);
		codigo = codigo.substring(0, 2) + Integer.toString(numeros) + codigo.substring(2, codigo.length() - 1);

		return codigo;
	}

	public int generarAsiento(Vuelos vueloSeleccioando) {
		Document quienCambio = new Document("codigo", vueloSeleccioando.getCodigo_vuelo());
		int primerAsientoDisponible = 1;

		if (vueloSeleccioando.getAsientos() != null) {
			ArrayList<Integer> asientosDisponibles = vueloSeleccioando.getAsientos();

			System.out.println(asientosDisponibles);
			primerAsientoDisponible = asientosDisponibles.get(0).intValue();

			Document asientoAquitar = new Document("asientos_libres", primerAsientoDisponible);
			Document auxPull = new Document("$pull", asientoAquitar);

			coleccion.updateOne(quienCambio, auxPull);
			asientosDisponibles.remove(0);

		} else {
			// no se ha comprado ningun vuelo aún, y no existen asientosLibres.

			List<Integer> plazas = new ArrayList<Integer>();
			for (int i = 1; i < vueloSeleccioando.getPlazas_totales(); i++) {
				plazas.add(i + 1);
			}
			coleccion.findOneAndUpdate(Filters.eq("codigo", vueloSeleccioando.getCodigo_vuelo()),
					Updates.pushEach("asientos_libres", plazas));
		}

		Document plazas_disponibles = new Document("plazas_disponibles", vueloSeleccioando.getPlazas_disponibles() - 1);
		Document auxSet = new Document("$set", plazas_disponibles);
		coleccion.updateOne(quienCambio, auxSet);

		return primerAsientoDisponible;

	}

}
