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
		void agregarFigurita(Figurita figurita) {
			figus.add(figurita);
		}
	
	
	//Nuevo metodo
	boolean estaAsociadaFigurita(int codigo) {
		for (Figurita figurita : figus) {
			if(figurita.codigo()==codigo) {
				return true;
			}
		}
		return false;
	}
	
	//Nuevo metodo
	boolean quitaFigurita(Figurita figurita) {
		if(estaAsociadaFigurita(figurita.codigo()))
			for (Figurita figurit : figus) {
				if(figurit.codigo().equals(figurita.codigo())) {
					figus.remove(figurita);
				}
			}
		return false;
	}
	
	//Nuevo metodo al estar en el particpante no es necesario el dni
	List<Figurita> figuritas(){
		return this.figus;
	}
	
	String darNombre() {
		return this.nombreUsuario;
	}
	
	String tipoAlbum() {
		return album.nombre();
	}
	
	List<Figurita> figuritasRepetida() {
		List<Figurita> repetidas = new 	ArrayList<Figurita>();
		for (int i = 0; i < figus.size(); i++) {
			repetidas.add(repetida(figus.get(i), figus));
		}
		return repetidas;
	}


	private Figurita repetida(Figurita figurita, List<Figurita> figus2) {
		Figurita figu=null;
		for (int i = 0; i < figus2.size(); i++) {
			if(figurita.equals(figus2.get(i))) {
				figu=figurita;
			}
	}
		return figu;
	 
}
}