
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public abstract class  Figurita {
	private int numeroIdentificador;
	private String nombreJugador;
	private String pais;
	protected Map<Integer, String> figuritas=new HashMap();
	protected List<Figurita> sobre;

	
	Figurita(int numeroIdentificador, String nombrejugador, String pais){
		this.numeroIdentificador=numeroIdentificador;
		this.nombreJugador=nombrejugador;
		this.pais=pais;
		this.figuritas.put(numeroIdentificador, nombrejugador);
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
