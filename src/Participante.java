import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Participante {
	
	private Integer dni;
	private String nombreUsuario;
	private Album album;
	private List<Figurita> figus;
	private List<Figurita> repetidas;
	private boolean albumLleno;
	
	
	public Participante(Integer dni, String nombreUsuario, Album album) {
		this.dni = dni;
		this.nombreUsuario = nombreUsuario;
		this.figus = new ArrayList<Figurita>();
		this.repetidas = new ArrayList<Figurita>();
		this.album = album;
	}
	
	public Integer saberDni() {
		return this.dni;
	}
	
	public Album saberAlbum() {
		return this.album;
	}

	void agregarFigurita(Figurita figurita) {
			figus.add(figurita);
	}
		
	protected void quitar(Figurita figurita) {
		figus.remove(figurita);
	}
	
	public List<String> pegarFiguritas() {
		List<String> figuritasPegadas = buscarFiguritasPegadas();
		this.albumLleno=album.albumLleno();
		return figuritasPegadas;
	}

	private List<String> buscarFiguritasPegadas() {
		List<String> figuritasPegadas= new ArrayList<>();
		Iterator<Figurita> it= figus.iterator();
		while(it.hasNext()) {
		Figurita figu=it.next();
			if(album.pegarFigurita(figu)) {
				figuritasPegadas.add(figu.mostrarPais() + "-"
				+ figu.getCodigo());
				it.remove();
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
	
	void usarCodigo() {
		album.usarCodigo();
	}
	
	boolean codigoUsado() {
		return album.codigoUsado();
	}

	String tipoAlbum() {
		return album.nombre();
	}

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
	
	public boolean intercambiarFiguritaRepetida(Participante participante) {
		List<Figurita> repetida = repetidas;
		for (int i = 0; i < repetida.size(); i++) {
			if (intercambiar(repetida.get(i).getCodigo(),participante)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean intercambiar(int codFigurita,Participante participante) { //arreglar
		List<Figurita> figuRepetidaParticipante2 = participante.repetidas;
		if(this.dni==participante.saberDni()) {
			return false;
		}
		Figurita figu= buscarFiguritaRepetida(figuRepetidaParticipante2,codFigurita);
		if (figu != null) {
			figus.add(figu);
			return true;
		}
		return false;
	}
	
	boolean albumCompleto() {
		return this.albumLleno;
	}
	
	public boolean paisLleno(String pais) {
		return album.paisLleno(pais);
	}

	boolean tieneAlbumExtendido() {
		return album.nombre() == "Extendido";
	}
	String darPremio() {
		if(tipoAlbum()=="Extendido") {
			return "Una pelota y un viaje";
		}
		if(tipoAlbum()=="Web") {
			return "Camiseta oficial de la seleccion";

		}
		if(tipoAlbum()=="Tradicional") {
			return "Pelota";
		}
		return "";
	}

	
	
///////////////////// METODOS AUXILIARES ///////////////////////	
	private Figurita buscarFiguritaRepetida(List<Figurita> figusRepetida, int codFigurita) {
		for (int i = 0; i < figusRepetida.size(); i++) {
			if (figusRepetida.get(i).getCodigo() == codFigurita) {
				return figusRepetida.get(i);
			}
		}
		return null;

	}
	
	private boolean EstaEnRepetida(Figurita figurita) {
		for(int i=0; i< repetidas.size();i++) {
			if(repetidas.get(i).getCodigo()==figurita.getCodigo() &&
				repetidas.get(i).mostrarPais()==figurita.mostrarPais()) {
				return true;
			}
		}
		return false;
	}

	private boolean sonRepetidas(Figurita figurita, Figurita figurita2) {
		return (figurita.getCodigo() == figurita2.getCodigo() && figurita.mostrarPais() == figurita2.mostrarPais()) ? true : false;
	}
	
	public List<Figurita> mostrarRepetidas() {
		return this.repetidas;
	}
	
	public void agregarSobre(List<Figurita> sobre) {
			figus.addAll(sobre);
	}
	
	public boolean tieneAlbumLleno() {
		return album.albumLleno();
	}
	
}