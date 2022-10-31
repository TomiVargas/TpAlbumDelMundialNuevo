import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participante {
	protected Integer dni;
	private String nombreUsuario;
	protected Album album;
	
	//Nueva estructura
	protected List<Figurita> figus;
	
	
	public Participante(Integer dni,String nombreUsuario,Album album) {
			this.dni=dni;
			this.nombreUsuario=nombreUsuario;
			this.album=album;
			
			//Nueva estructura
			this.figus=new ArrayList<Figurita>();
		}
	
	
	//Nuevo metodo
	void agregarFigus(Figurita figurita) {
		if(estaAsociadaFigu(figurita.codigo()))
			throw new RuntimeException("Figurita ya pegada!");
		figus.add(figurita);
	}
	
	
	//Nuevo metodo
	boolean estaAsociadaFigu(int codigo) {
		for (Figurita figurita : figus) {
			if(figurita.codigo()==codigo) {
				return true;
			}
		}
		return false;
	}
	
	//Nuevo metodo
	boolean quitaFigu(Figurita figurita) {
		if(estaAsociadaFigu(figurita.codigo()))
			for (Figurita figurit : figus) {
				if(figurit.codigo().equals(figurita.codigo())) {
					figus.remove(figurita);
				}
			}
		return false;
	}
	
	List<Figurita> figuritas(int dni){
		return this.figus;
	}
	
	String darNombre() {
		return this.nombreUsuario;
	}
	
	String tipoAlbum() {
		return album.nombre();
	}
	 
}
