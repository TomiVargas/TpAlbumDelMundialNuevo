import java.util.ArrayList;
import java.util.List;

public class Participante {
	protected Integer dni;
	private String nombreUsuario;
	protected Album album;
	protected List<Figurita> figus;
	protected List<Figurita> repetidas;

	public Participante(Integer dni, String nombreUsuario, Album album) {
		this.dni = dni;
		this.nombreUsuario = nombreUsuario;
		this.figus = new ArrayList<Figurita>();
		this.repetidas = new ArrayList<Figurita>();
		this.album = album;
	}

	// Nuevo metodo el participitante puede tener figuritas repetidas en su
	// coleccion
	void agregarFigurita(Figurita figurita) {
		if (figus.contains(figurita)) {
			repetidas.add(figurita);
		} else {
			figus.add(figurita);
		}
	}
	
	// Nuevo metodo
	protected void quitar(Figurita figurita) {
		figus.remove(figurita);
	}
	
	public List<Figurita> pegarFiguritas() {
		List<Figurita> figuritasPegadas = new ArrayList<>();
		for (int i = 0; i < figus.size(); i++) {
			if (album.pegarFigurita2(figus.get(i))) {
				figuritasPegadas.add(figus.get(i));
			}
		}
		return figuritasPegadas;
	}


	// Nuevo metodo al estar en el particpante no es necesario el dni
	List<Figurita> figuritas() {
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

	// Nnuevo metodo
	List<Figurita> figuritasRepetida() {

		for (int i = 0; i < figus.size(); i++) {
			if(!EstaEnRepetida(figus.get(i))) {
			for (int j = i + 1; j < figus.size();j++) { 
					if (sonRepetidas(figus.get(i), figus.get(j))) {
						repetidas.add(figus.get(j));
					}
				
			}
			}
		}
		return repetidas;
	}
	
	boolean albumCompleto() {
		return this.album.albumLleno();
	}
	
	public boolean paisLleno(String pais) {
		return album.paisLleno(pais);
	}

	boolean tieneAlbumExtendido() {
		return album.nombre() == "Extendido";
	}

	
	
///////////////////// METODOS AUXILIARES ///////////////////////	

	private boolean EstaEnRepetida(Figurita figurita) {
		for(int i=0; i< repetidas.size();i++) {
			if(repetidas.get(i).codigo()==figurita.codigo() &&
				repetidas.get(i).mostrarPais()==figurita.mostrarPais()) {
				return true;
			}
		}
		return false;
	}

	// metodo auxiliar simplifica verificacion repetida
	private boolean sonRepetidas(Figurita figurita, Figurita figurita2) {
		return (figurita.codigo() == figurita2.codigo() && figurita.mostrarPais() == figurita2.mostrarPais()) ? true : false;
	}

	// Nuevo metodo
	public List<Figurita> mostrarRepetidas() {
		return this.repetidas;
	}
}