package modelo;

public class Reserva {

	private int asiento;
	private String dni;
	private String apellido;
	private String nombre;
	private String dniPagador;
	private int tarjeta;
	private String codigoVenta;
	private Vuelos vuelo;

	public Reserva(int asiento, String dni, String apellido, String nombre, String dniPagador, int tarjeta,
			String codigoVenta, Vuelos vuelo) {
		this.asiento = asiento;
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		this.dniPagador = dniPagador;
		this.tarjeta = tarjeta;
		this.codigoVenta = codigoVenta;
		this.vuelo = vuelo;
	}

	public Vuelos getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelos vuelo) {
		this.vuelo = vuelo;
	}

	public int getAsiento() {
		return asiento;
	}

	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public String getDniPagador() {
		return dniPagador;
	}

	public void setDniPagador(String dniPagador) {
		this.dniPagador = dniPagador;
	}

	public int getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(int tarjeta) {
		this.tarjeta = tarjeta;
	}

	public String getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

}
