package controlador;

import java.util.HashMap;

import modelo.MongoDBManager;
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
				.respuestas("¿Qué quiere hacer? COMPRAR VUELO (1) / CANCELAR VUELO (2) / MODIFICAR VUELO (3)", true);
		switch (opcionElegida) {
		case "1":
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
			break;

		case "2":
			String dniPasajero = miVista.respuestas("Introduzca su dni", true);
			// miVista.convertirHashAstring(miMongo.mostrarTodosLosVuelos());
			HashMap<String, Reserva> misReservas = miMongo.mostrarVuelosDelCliente(dniPasajero);
			miVista.HashAstringReserva(misReservas);
			String codigoVenta = miVista.pedirDatosCancelarModificar(true);
			miMongo.cancelarVuelo(codigoVenta, misReservas);
			miVista.respuestas("Borrado!", false);

			break;
		case "3":
			
			String dni = miVista.respuestas("Introduzca su DNI para continuar: ", true);
			HashMap<String, Reserva> misReservasAmodificar = miMongo.mostrarVuelosDelCliente(dni);

			if (misReservasAmodificar.size() == 0) {
				miVista.respuestas("Actualmente no tiene ningún vuelo comprado.", false);
			} else {
				miVista.HashAstringReserva(misReservasAmodificar);
				String codigoVenta2 = miVista.pedirDatosCancelarModificar(false);

				if (misReservasAmodificar.get(codigoVenta2) == null) {
					miVista.respuestas("No tiene ninguna reserva con ese código de venta.", false);
				} else {
					miMongo.modificarVuelo(misReservasAmodificar, codigoVenta2, miVista.pedirDatosModificar());
					miVista.respuestas("Modificado!", false);
				}
			}

			break;
		default:
			miVista.respuestas("Opción incorrecta!", false);
		}

		String continuar = miVista.respuestas("¿Desea realizar alguna otra operación? 1) si 2) no", true);
		if (continuar.equals("2")) {
			repetimos = false;
		}
		return repetimos;
	}

	public void setMongo(MongoDBManager mongo) {
		this.miMongo = mongo;

	}

}
