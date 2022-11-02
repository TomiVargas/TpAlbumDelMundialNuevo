

public abstract class  Figurita {
	private int numeroIdentificador;
	private String pais;

	
	Figurita(int numeroIdentificador, String pais){
		this.numeroIdentificador=numeroIdentificador;
		
		this.pais=pais;
	}


	protected String mostrarPais() {
		return this.pais;
	}
	

	
	protected Integer codigo() {
		return this.numeroIdentificador;
	};
	
	
	
	
}
