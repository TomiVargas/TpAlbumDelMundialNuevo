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
		figus.add(figurita);
	}

	/*
	 * //Metodos nuevo pegar boolean pegarFigurita(Figurita figurita) {
	 * if(!album.estaPegada(figurita)) { album.pegarFigurita2(figurita); return
	 * true; } return false; }
	 */

	boolean estaAsociadaFigurita(Figurita figu) {
		for (Figurita figurita : figus) {
			if (figurita.equals(figu)) {
				return true;
			}
		}
		return false;
	}

	protected boolean quitaFigurita(Figurita figurita) {
		for (int i = 0; i < figus.size(); i++) {
			if (figurita != null) {
				figus.remove(figurita);
				figus.add(i, null);
				return true;
			}
		}

		return false;
	}

	protected boolean quitar(Figurita figurita) {
		return figus.remove(figurita);
	}

	// Nuevo metodo al estar en el particpante no es necesario el dni
	List<Figurita> figuritas() {
		return this.figus;
	}

	// Nuevo metodo al estar en el particpante no es necesario el dni
	List<String> figuritasString() {
		List<String> figuritas = new ArrayList<>();
		for (int i = 0; i < figus.size(); i++) {
			figuritas.add(figus.get(i).codigo().toString());
		}
		return figuritas;
	}

	Figurita[] figuritas2() {
		Figurita[] figu = new Figurita[figus.size()];
		for (int i = 0; i < figus.size(); i++) {
			figu[i] = figus.get(i);
		}
		return figu;
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

		for (int i = 0; i < figus.size(); i++) {
			for (int j = i + 1; j < figus.size(); j++) {
				if (sonRepetidas(figus.get(i), figus.get(j))) {
					repetidas.add(figus.get(j));
				}
			}
		}
		return repetidas;
	}

	// metodo auxiliar simplifica verificacion repetida
	private boolean sonRepetidas(Figurita figurita, Figurita figurita2) {
		return (figurita.codigo() == figurita2.codigo() && figurita.mostrarPais() == figurita2.mostrarPais()
				&& (figurita != null || figurita2 != null) && !album.estaPegada(figurita)) ? true : false;
	}
	
	
	public List<Figurita> mostrarRepetidas(){
		return this.repetidas;
	}

	public List<String> figuritas(int dni2) {
		List<String> f = new ArrayList<String>();
		for (int i = 0; i < figus.size(); i++) {
			f.add(figus.get(i).codigo().toString() + figus.get(i).mostrarPais());
		}
		return f;
	}

	boolean albumCompleto() {
		return this.album.albumLleno();
	}

	boolean tieneAlbumExtendido() {
		return album.nombre() == "Extendido";
	}

}