import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participante {
	protected Integer dni;
	private String nombreUsuario;
	protected Album album;
	protected Map<Integer,List<Figurita>> figuritas;
	
	public Participante(Integer dni,String nombreUsuario,Album album) {
			this.dni=dni;
			this.nombreUsuario=nombreUsuario;
			this.album=album;
			this.figuritas=new HashMap<>();
			
	
	}
	 
}
