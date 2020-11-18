package modelo;

import controlador.Controlador;

public class Pasajero {

	Controlador miControlador;

	private int asiento;
	private String dni_pasajero;
	private String apellido;
	private String nombre;
	private String dni_pagador;
	private int tarjeta;
	private String codigo_venta;
	
	public Pasajero() {}
	
	public Pasajero(Controlador miControlador, int asiento, String dni_pasajero, String apellido, String nombre,
			String dni_pagador, int tarjeta, String codigo_venta) {
		this.miControlador = miControlador;
		this.asiento = asiento;
		this.dni_pasajero = dni_pasajero;
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni_pagador = dni_pagador;
		this.tarjeta = tarjeta;
		this.codigo_venta = codigo_venta;
	}

	public int getAsiento() {
		return asiento;
	}

	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}

	public String getDni_pasajero() {
		return dni_pasajero;
	}

	public void setDni_pasajero(String dni_pasajero) {
		this.dni_pasajero = dni_pasajero;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni_pagador() {
		return dni_pagador;
	}

	public void setDni_pagador(String dni_pagador) {
		this.dni_pagador = dni_pagador;
	}

	public int getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(int tarjeta) {
		this.tarjeta = tarjeta;
	}

	public String getCodigo_venta() {
		return codigo_venta;
	}

	public void setCodigo_venta(String codigo_venta) {
		this.codigo_venta = codigo_venta;
	}
	
	
	
	

}
