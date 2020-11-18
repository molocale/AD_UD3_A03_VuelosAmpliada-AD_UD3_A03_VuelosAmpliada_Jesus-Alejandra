package controlador;

import modelo.MongoDBManager;
import modelo.Pasajero;
import vista.Principal;

public class Controlador {

	private Principal miVista;
	private MongoDBManager miMongo;

	public Principal getMiVista() {
		return miVista;
	}

	public void setMiVista(Principal miVista) {
		this.miVista = miVista;
	}

	public void ejecucion() {
		int opcionElegida = miVista
				.respuestas("¿Que quieres hacer? COMPRAR VUELO (1) / CANCELAR VUELO (2) / MODIFICAR VUELO (3)");
		switch (opcionElegida) {
		case 1:
			Pasajero miPasajero = new Pasajero();
			miVista.pintar(miMongo.comprarVuelo());
			break;

		case 2:
			miVista.pintar(miMongo.cancelarVuelo(null));
			break;
		case 3:
			miVista.pintar(miMongo.modificarVuelo(null));
			break;

		default:
			miVista.pintar("Valor no valido");
			ejecucion();

		}

	
	}

}
