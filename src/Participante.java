import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participante {
	protected Integer dni;
	private String nombreUsuario;
	protected Album album;
	protected Map<Integer,Figurita> figuritas;
	
	public Participante(Integer dni,String nombreUsuario,Album album) {
			this.dni=dni;
			this.nombreUsuario=nombreUsuario;
			this.album=album;
			this.figuritas=new HashMap<>();
		}
	
	void agregarFigurita(Figurita figurita) {
		figuritas.put(figurita.codigo(), figurita );
	}
	
	String darNombre() {
		return this.nombreUsuario;
	}
	
	String tipoAlbum() {
		return album.nombre();
	}
	 
}
