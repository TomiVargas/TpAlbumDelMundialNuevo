

public abstract class  Figurita {
	private int numeroIdentificador;
	private String pais;
	private int codigo;
	
	Figurita(int numeroIdentificador, String pais, String[] paisesParticipantes){
		this.numeroIdentificador=numeroIdentificador;
		this.pais=pais;
		for(int i=0;i<paisesParticipantes.length;i++) {
			if(paisesParticipantes[i]==pais && i==0) {
				this.setCodigo(numeroIdentificador);
			}else if(paisesParticipantes[i]==pais && i==1) {
				this.setCodigo(numeroIdentificador+12);
			}else if(paisesParticipantes[i]==pais){
				this.setCodigo((12*(i+1))+numeroIdentificador);
			}
		}
		
	}
	
	


	protected String mostrarPais() {
		return this.pais;
	}
	
	protected Integer codigo() {
		return this.numeroIdentificador;
	}




	public int getCodigo() {
		return codigo;
	}




	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	
	
	
	
}
