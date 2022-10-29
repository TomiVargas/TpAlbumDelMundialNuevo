
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public abstract class  Figurita {
	private int numeroIdentificador;
	private String nombreJugador;
	protected Map<Integer, String> figuritas=new HashMap();
	protected List<Figurita> sobre;

	
	Figurita(int numeroIdentificador, String nombrejugador){
		this.numeroIdentificador=numeroIdentificador;
		this.nombreJugador=nombrejugador;
		this.figuritas.put(numeroIdentificador, nombrejugador);
	}
	
	
}
