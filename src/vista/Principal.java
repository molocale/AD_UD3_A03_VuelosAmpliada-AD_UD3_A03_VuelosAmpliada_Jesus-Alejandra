package vista;

import java.util.Scanner;

import controlador.Controlador;

public class Principal {
	public static void main(String[] args) {
		Controlador miControlador = new Controlador();
		Principal miVista = new Principal();
		miControlador.setMiVista(miVista);
		miControlador.ejecucion();
	}

	public int respuestas(String pregunta) {

		System.out.println(pregunta);
		Scanner in = new Scanner(System.in);
		int miRespuesta = in.nextInt();
		return miRespuesta;

	}

	public void pintar(String sysos) {

		System.out.println(sysos);

	}

}
