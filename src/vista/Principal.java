package vista;

import java.util.Scanner;

import controlador.Controlador;

public class Principal {
	public static void main(String[] args) {
		Controlador miControlador = new Controlador();
		Principal miVista = new Principal();
		miControlador.setMiVista(miVista);
		System.out.println("¡Bienvenido a nuestra Aerolínea!");
		while (miControlador.ejecucion())
			;

	}

	public int respuestas(String pregunta, boolean respuesta) {
		int miRespuesta = -1;

		System.out.println(pregunta);
		Scanner in = new Scanner(System.in);
		if (respuesta) {
			miRespuesta = in.nextInt();
		}
		return miRespuesta;

	}

}
