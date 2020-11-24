package controlador;

import java.util.HashMap;

import modelo.MongoDBManager;
import modelo.Pasajero;
import modelo.Reserva;
import modelo.Vuelos;
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

	public boolean ejecucion() {
		boolean repetimos = true;
		String opcionElegida = miVista
				.respuestas("¿Qué quiere hacer? COMPRAR VUELO (A) / CANCELAR VUELO (B) / MODIFICAR VUELO (C)", true);
		switch (opcionElegida) {
		case "A":
			miVista.convertirHashAstring(miMongo.mostrarTodosLosVuelos());
			String eleccionVuelo = miVista.respuestas(
					"Estos son todos nuestros vuelos disponibles, introduce el codigo de vuelo del que desea comprar:",
					true);
			Vuelos vueloSeleccioando = miMongo.seleccionarUno(eleccionVuelo);

			if (vueloSeleccioando != null) {
				if (vueloSeleccioando.getPlazas_disponibles() > 0) {
					String[] arrDatosPasajero = miVista.pedirDatosPasajero();

					if (miMongo.comprarVuelo(arrDatosPasajero, vueloSeleccioando)) {
						miVista.respuestas("¡Gracias por su compra!", false);
					} else {
						miVista.respuestas("¡Error en la compra!", false);
					}
				} else {
					miVista.respuestas("Lo sentimos pero actualmente no hay plazas disponibles para ese vuelo", false);
				}

			}
			// En comprar:
			// enseñar todos darle a elegir
			// recoger los datos
			// meter los datos en el vuelo elegido

			break;

		case "B":
			String dniPasajero = miVista.respuestas("Introduzca su dni", true);
			// miVista.convertirHashAstring(miMongo.mostrarTodosLosVuelos());
			HashMap<String, Reserva> misReservas = miMongo.mostrarVuelosDelCliente(dniPasajero);
			miVista.HashAstringReserva(misReservas);
			String codigoVenta = miVista.pedirDatosCancelarVuelo();
			miMongo.cancelarVuelo(codigoVenta, misReservas);
			miVista.respuestas("Borrado!", false);
			

//			miVista.HashAstringReserva(miMongo.mostrarVuelosDelCliente(dniPasajero));
//			String[] arrDatosVueloAborrar = miVista.pedirDatosCancelarVuelo();
			// miMongo.cancelarVuelo(arrDatosVueloAborrar);

			// en cancelar:
			// pedirle el dni y enseñarle los vuelos asociados a su dni
			// borrar el que elija

			break;
		case "C":
			miMongo.modificarVuelo();
			// en modificar:
			// pedirle el dni y enseñarle los vuelos asociados a su dni
			// recoger los cambios
			// aplicar los cambios y guardarlos
			
			String dni = miVista.respuestas("Introduzca su DNI para continuar: ", true);
			miMongo.mostrarVuelosDelCliente(dni);
			break;

		default:
			// si lo mete mal

		}

		// un desea continuar haciendo otra cosa si desea true si no false:
		/*
		 * if(no desea continuar) { repetimos= false; }
		 */

		return repetimos;

	}

	public void setMongo(MongoDBManager mongo) {
		this.miMongo = mongo;

	}

}
