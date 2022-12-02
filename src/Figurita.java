

public class  Figurita {
	private int numeroIdentificador;
	private String pais;
	private static int codigo; 
	private int valorBase;
	//codigo unico se tiene que definir internamente con static.
	
	Figurita(int numeroIdentificador, String pais,int valorBase){
		this.numeroIdentificador=numeroIdentificador;
		this.pais=pais;
		codigo++;
		this.valorBase=valorBase;
		
	}



	protected String mostrarPais() {
		return this.pais;
	}
	
	protected Integer numeroIdentificador() {
		return this.numeroIdentificador;
	}
	
	public String mostrarSede() {
		return null;
	}


	public int getCodigo() {
		return codigo;
	}
	

	public int getValorBase() {
		return valorBase;
	}


	
	
	
	
}
