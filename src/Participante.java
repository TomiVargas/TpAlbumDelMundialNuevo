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
			
			//Nueva estructura
			this.figus=new ArrayList<Figurita>();
			this.album=album;
		}

	
	//Nuevo metodo el participitante puede tener figuritas repetidas en su coleccion
		void agregarFigus2(Figurita figurita) {
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
	
	//Nuevo metodo al estar en el particpante no es necesario el dni
	List<Figurita> figuritas2(){
		return this.figus;
	}
	
	String darNombre() {
		return this.nombreUsuario;
	}
	
	String tipoAlbum() {
		return album.nombre();
	}
	 
}
