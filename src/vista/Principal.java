package vista;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mongodb.Mongo;

import controlador.Controlador;
import modelo.MongoDBManager;
import modelo.Reserva;
import modelo.Vuelos;

public class Principal {
	public static void main(String[] args) {
		Controlador miControlador = new Controlador();
		Principal miVista = new Principal();
		MongoDBManager mongo = new MongoDBManager();
		miControlador.setMiVista(miVista);
		miControlador.setMongo(mongo);
		System.out.println("�Bienvenido a nuestra Aerol�nea!");
		while (miControlador.ejecucion())
			;
		System.out.println("�Hasta pronto!");

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

	public void HashAstringReserva(HashMap<String, Reserva> miHash) {

		Iterator it = miHash.entrySet().iterator();

		System.out.println("SUS RESERVAS:");
		String salida = "";
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();

			Reserva miReserva = (Reserva) e.getValue();

			System.out.println("Vuelo con codigo: " + miReserva.getVuelo().getCodigo_vuelo() + " "
					+ miReserva.getVuelo().getOrigen() + " - " + miReserva.getVuelo().getDestino() + ":");

			salida = convertirReservaEnString(miReserva);
			System.out.println(salida);

		}
		// System.out.println(salida);

	}

	public String convertirVueloEnString(Vuelos miVuelo) {

		String salida = "id: " + miVuelo.getId_vuelo() + ", origen: " + miVuelo.getOrigen() + ", destino: "
				+ miVuelo.getDestino() + ", codigo de vuelo: " + miVuelo.getCodigo_vuelo() + ", fecha: "
				+ miVuelo.getFecha() + ", hora: " + miVuelo.getHora() + ", plazas totales: "
				+ miVuelo.getPlazas_totales() + ", plazas disponibles: " + miVuelo.getPlazas_disponibles() + "\n";

		return salida;

	}

	public String convertirReservaEnString(Reserva miReserva) {

		String salida = "Detalles de la reserva --> Asiento: " + miReserva.getAsiento() + ", dni: " + miReserva.getDni()
				+ ", apellido: " + miReserva.getApellido() + ", nombre: " + miReserva.getNombre() + ", codigoVenta: "
				+ miReserva.getCodigoVenta() + "\n";

//		String salida = "Detalles de la reserva --> Asiento: " + miReserva.getAsiento() + ", dni: " + miReserva.getDni() + ", apellido: "
//				+ miReserva.getApellido() + ", nombre: " + miReserva.getNombre() + ", dniPagador: "
//				+ miReserva.getDniPagador() + ", tarjeta: " + miReserva.getTarjeta() + ", codigoVenta: "
//				+ miReserva.getCodigoVenta() + "\n";

		return salida;

	}

	public String pedirDatosCancelarModificar(boolean isBorrar) {

		// String[] misDatos = new String[3];

		Scanner in = new Scanner(System.in);
//		System.out.println("Introduzca los siguiente datos del vuelo que desee borrar:");
//		System.out.println("C�digo del vuelo: ");
//		misDatos[0] = in.next();
//		System.out.println("Dni ");
//		misDatos[1] = in.next();
//		System.out.println("C�digo de venta: ");
//		misDatos[2] = in.next();
		String accion = "borrar";
		if (!isBorrar) {
			accion = "modificar";
		}

		System.out.println("Introduce el codigo de venta del vuelo que quiera " + accion + ":");
		String codigo = in.next();

		return codigo;

	}

	public String[] pedirDatosModificar() {
		Scanner in = new Scanner(System.in);

		String[] datos = new String[3];

		System.out.println("Introduce los nuevos datos");
		System.out.println("DNI del pasajero:");
		datos[0] = in.next();
		validarDni(datos[0]);
		System.out.println("Primer Apellido del pasajero:");
		datos[1] = in.next();
		revisarStrings(datos[1]);
		System.out.println("Nombre del pasajero:");
		datos[2] = in.next();
		revisarStrings(datos[2]);

		return datos;
	}

	public String[] pedirDatosPasajero() {
		Scanner in = new Scanner(System.in);

		String[] datos = new String[5];

		System.out.println("Introduce los datos que se le solicitan a continuaci�n:");
		System.out.println("DNI del pasajero:");
		datos[0] = in.next();
		validarDni(datos[0]);
		System.out.println("Primer Apellido del pasajero:");
		datos[1] = in.next();
		revisarStrings(datos[1]);
		System.out.println("Nombre del pasajero:");
		// ;
		datos[2] = in.next();
		revisarStrings(datos[2]);
		System.out.println("DNI del pagador:");

		datos[3] = in.next();
		validarDni(datos[3]);
		System.out.println("n�mero de la tarjeta de cr�dito:");
		datos[4] = in.next();
		revisarMisInt(datos[4]);

		return datos;
	}

	public String revisarStrings(String midato) {
		Scanner in = new Scanner(System.in);

		while (!palabraCorrect(midato)) {

			System.out.println("Formato no valido (solo puede contener letras):");
			midato = in.next();

		}
		return midato;

	}

	public boolean palabraCorrect(String midato) {
		boolean onlyLetras = true;
		int i = 0;
		while (i < midato.length() && onlyLetras) {
			if (!Character.isLetter(midato.charAt(i))) {
				onlyLetras = false;
			}
			i++;
		}
		return onlyLetras;
	}

	public void validarDni(String miDni) {

		Scanner miScanner = new Scanner(System.in);

		Pattern pat = Pattern.compile("[0-9]{7,8}[A-Z a-z]");

		Matcher mat = pat.matcher(miDni);

		while (!mat.matches()) {

			System.out.println("DNI INCORRECTO, VUELVA A INTRODUCIR EL DNI");
			miDni = miScanner.nextLine();

			mat = pat.matcher(miDni);

		}

	}

	public String revisarMisInt(String midatoNumerico) {
		Scanner ins = new Scanner(System.in);

		while (midatoNumerico.length() != 12) {
			System.out.println("Formato no valido, la tarjeta debe tener 12 n�meros (todos seguidos)");
			midatoNumerico = ins.next();

		}

		while (!midatoNumerico.matches("[0-9]*")) {

			System.out.println("Formato no valido, por favor, introduzca valores numericos:");
			midatoNumerico = ins.next();

		}
		return midatoNumerico;
	}

}
