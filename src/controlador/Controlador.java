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

	public boolean ejecucion() {
		boolean repetimos = true;
		int opcionElegida = miVista
				.respuestas("¿Qué quiere hacer? COMPRAR VUELO (1) / CANCELAR VUELO (2) / MODIFICAR VUELO (3)", true);
		switch (opcionElegida) {
		case 1:
			//En comprar:
			//enseñar todos darle a elegir 
			//recoger los datos 
			//meter los datos en el vuelo elegido
			
			break;

		case 2:
			
			//en cancelar:
			//pedirle el dni y enseñarle los vuelos asociados a su dni
			//borrar el que elija
			
			break;
		case 3:
			//en modificar:
			//pedirle el dni y enseñarle los vuelos asociados a su dni
			//recoger los cambios
			//aplicar los cambios y guardarlos
			break;

		default:
			//si lo mete mal

		}
		
		
		
		//un desea continuar haciendo otra cosa si desea true si no false:
		/*if(no desea continuar) {
			repetimos= false;
		}*/
		
		return repetimos;

	
	}

}
