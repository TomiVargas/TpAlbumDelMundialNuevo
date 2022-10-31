

public abstract class  Figurita {
	private int numeroIdentificador;
	private String nombreJugador;
	private String pais;

	
	Figurita(int numeroIdentificador, String nombrejugador, String pais){
		this.numeroIdentificador=numeroIdentificador;
		this.nombreJugador=nombrejugador;
		this.pais=pais;
	}


	protected String mostrarPais() {
		return this.pais;
	}
	
	protected String mostrarNombreJugador() {
		return this.nombreJugador;
	}
	
	protected Integer codigo() {
		return this.numeroIdentificador;
	};
	
	
	
	
}
