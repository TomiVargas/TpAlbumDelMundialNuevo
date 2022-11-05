import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participante {
	protected Integer dni;
	private String nombreUsuario;
	protected Album album;
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
		
		//Metodos nuevo pegar
		void pegarFigurita(Figurita figurita) {
			if(!album.estaPegada(figurita))
				album.pegarFigurita2(figurita);
		}
		
	
	
	boolean estaAsociadaFigurita(Figurita figu) {
		for (Figurita figurita : figus) {
			if(figurita.equals(figu)) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean quitaFigurita(Figurita figurita) {
			for (int i=0;i<figus.size();i++) {
				if(figus.get(i).codigo() == figurita.codigo() && figus.get(i).mostrarPais()== figurita.mostrarPais()) {
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
	
	String dni() {
		return this.dni.toString();
	}
	
	List<Figurita> figuritasRepetida() {
		List<Figurita> repetidas = new 	ArrayList<Figurita>();
		for (int i = 0; i < figus.size(); i++) {
			for(int j=0;j<figus.size();j++) {
				if( figus.get(j).equals(figus.get(i)) 
					&& !album.estaPegada(figus.get(j))) {
					repetidas.add(figus.get(j));
				}
			}
			
		}
		return repetidas;
	}


	public List<String> figuritas(int dni2) {
		List<String> f= new ArrayList<String>();
		for(int i=0;i<figus.size();i++) {
			f.add(figus.get(i).codigo().toString());
		}
		return f;
	}


	
}