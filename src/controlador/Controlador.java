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

//					while (miMongo.comprobarPasajeroEnElVuelo(arrDatosPasajero[0],
//							vueloSeleccioando.getCodigo_vuelo())) {
//						arrDatosPasajero[0] = miVista.respuestas(
//								"Ya existe ese dni en nuestro vuelo, no puede haber dos pasajeros con el mismo dni. Introduzca un dni valido:",
//								true);
//					}

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

		case "2":
			String dniPasajero = miVista.respuestas("Introduzca su DNI (dni del pagador)", true);
			// miVista.convertirHashAstring(miMongo.mostrarTodosLosVuelos());
			HashMap<String, Reserva> misReservas = miMongo.mostrarVuelosDelCliente(dniPasajero);

			if (misReservas.size() == 0) {
				miVista.respuestas("No tiene reservas asociadas al DNI: " + dniPasajero, false);
			} else {
				miVista.HashAstringReserva(misReservas);

				String codigoVenta = miVista.pedirDatosCancelarModificar(true);
				while (misReservas.get(codigoVenta) == null) {
					miVista.respuestas("No hay ninguna reserva asociada a ese codigo", false);

					codigoVenta = miVista.pedirDatosCancelarModificar(true);
				}
				miMongo.cancelarVuelo(codigoVenta, misReservas);
				miVista.respuestas("Borrado!", false);
			}

			// en cancelar:
			// pedirle el dni y enseñarle los vuelos asociados a su dni
			// borrar el que elija

			break;
		case "3":

			// en modificar:
			// pedirle el dni y enseñarle los vuelos asociados a su dni
			// recoger los cambios
			// aplicar los cambios y guardarlos

			String dni = miVista.respuestas("Introduzca su DNI (dni del pagador) para continuar: ", true);
			HashMap<String, Reserva> misReservasAmodificar = miMongo.mostrarVuelosDelCliente(dni);

			if (misReservasAmodificar.size() == 0) {
				miVista.respuestas("Actualmente no tiene ningún vuelo comprado.", false);
			} else {
				miVista.HashAstringReserva(misReservasAmodificar);

				String codigoVenta2 = miVista.pedirDatosCancelarModificar(false);

				while (misReservasAmodificar.get(codigoVenta2) == null) {
					miVista.respuestas("No hay ninguna reserva asociada a ese codigo", false);

					codigoVenta2 = miVista.pedirDatosCancelarModificar(false);
				}
				miMongo.modificarVuelo(misReservasAmodificar, codigoVenta2, miVista.pedirDatosModificar());
				miVista.respuestas("Modificado!", false);
			}
			break;

		default:
			miVista.respuestas("Opción incorrecta!", false);

		}

		String continuar = miVista.respuestas("¿Desea realizar alguna otra operación? SI (1) / NO (2)", true);

		if (continuar.equals("2")) {
			repetimos = false;
		}

		return repetimos;

	}

	public void setMongo(MongoDBManager mongo) {
		this.miMongo = mongo;

	}

}
