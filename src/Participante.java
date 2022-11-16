import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Participante {
	private Integer dni;
	private String nombreUsuario;
	private Album album;
	private List<Figurita> figus;
	private List<Figurita> repetidas;

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
	void usarCodigo() {
		album.usarCodigo();
	}
	boolean codigoUsado() {
		return album.codigoUsado();
	}

	String tipoAlbum() {
		return album.nombre();
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
	public boolean intercambiarFiguritaRepetida(Participante participante) {
		List<Figurita> repetida = repetidas;
		for (int i = 0; i < repetida.size(); i++) {
			if (intercambiar(repetida.get(i).getCodigo(),participante)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean intercambiar(int codFigurita,Participante participante) {
		List<Figurita> figuRepetidaParticipante2 = participante.repetidas;
				if (recorrerLaListaDeFigusRepetidasYVerificaSiEsta(figuRepetidaParticipante2,
						codFigurita) != null) {
						figus.add(recorrerLaListaDeFigusRepetidasYVerificaSiEsta(figuRepetidaParticipante2, codFigurita));
						return true;
				}
		return false;
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
	protected boolean existeFigurita(int codFigurita) {
		for (int i = 0; i < figus.size(); i++) {
			if (figus.get(i).getCodigo() == codFigurita) {
				return true;
			}
		}
		return false;
	}
	private Figurita recorrerLaListaDeFigusRepetidasYVerificaSiEsta(List<Figurita> figusRepetida, int codFigurita) {
		Figurita figu = null;
		for (int i = 0; i < figusRepetida.size(); i++) {
			if (figusRepetida.get(i).getCodigo() == codFigurita) {
				figu = figusRepetida.get(i);
			}
		}
		return figu;

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

	// metodo auxiliar simplifica verificacion repetida
	private boolean sonRepetidas(Figurita figurita, Figurita figurita2) {
		return (figurita.getCodigo() == figurita2.getCodigo() && figurita.mostrarPais() == figurita2.mostrarPais()) ? true : false;
	}

	// Nuevo metodo
	public List<Figurita> mostrarRepetidas() {
		return this.repetidas;
	}
	public int saberCodigo() {
		return album.codigo();
	}
	
}