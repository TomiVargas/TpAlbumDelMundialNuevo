import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AlbumDelMundial implements  IAlbumDelMundial  {

	private Fabrica fabrica;
	private Map<Integer, Participante> participantes;
	private int cantDeVecesCodigoPromo;
	private int cantDeSorteosEfectuados;
	private int cantDeFiguritasCompradas;

	public AlbumDelMundial() {
		this.participantes = new HashMap<>();
		this.fabrica = new Fabrica();
		this.cantDeVecesCodigoPromo = 0;
		this.cantDeSorteosEfectuados = 0;
		this.cantDeFiguritasCompradas = 0;
	}

	////////////////////////////////////////// INTERFAZ PUBLICA //////////////////////////////////////////

	public int registrarParticipante(int dni, String nombre, String tipoAlbum) {
		if (estaRegistrado(dni)) {
			throw new RuntimeException("Participante ya registrado!");
		} 
			
			Album album = crearAlbum(tipoAlbum);
			// Crea un nuevo Participante
			Participante participante = new Participante(dni, nombre, album);
			// Agrega Participante al Sistema
			participantes.put(dni, participante);
		 
		return album.codigo();
	}

	private Album crearAlbum(String tipoAlbum) {
		if (tipoAlbum == "Tradicional") {
			return fabrica.crearAlbumTradicional();
			
		}else if (tipoAlbum == "Web") {
			return  fabrica.crearAlbumWeb();
			
		}else if (tipoAlbum == "Extendido") {
			 return  fabrica.crearAlbumExtendido();
			
		}else {
			throw new RuntimeException("Tipo de Album invalido");
		}
	}

	public void comprarFiguritas(int dni) {
		Participante participante = buscarParticipante(dni);
		agregarSobreAParticipante(participante);
	}

	private void agregarSobreAParticipante(Participante participante) {
		List<Figurita> sobre = fabrica.generarSobre(4);
		participante.agregarSobre(sobre);
		cantDeFiguritasCompradas += 4;
	}

	private Participante buscarParticipante(int dni) {
		if (!estaRegistrado(dni)) {
			throw new RuntimeException("Participante No esta registrado");
		}
		Participante participante= participantes.get(dni);
		return participante;
	}

	public void comprarFiguritasTop10(int dni) {
		Participante participante = buscarParticipante(dni);
		if(participante.tipoAlbum()!="Extendido"){
			throw new RuntimeException("Album invalido");
		}
		List<Figurita> sobre = fabrica.generarSobreTop10(4);
		cantDeFiguritasCompradas += 4;
		participante.agregarSobre(sobre);
	}

	public void comprarFiguritasConCodigoPromocional(int dni) {
		Participante participante = buscarParticipante(dni);
		if(participante.tipoAlbum()!="Web") {
			throw new RuntimeException("Album invalido");
		}
		if (!codigoUsado(dni)) {
			agregarSobreAParticipante(participante);
			usarCodigo(dni);
			cantDeVecesCodigoPromo++;
			}
		}

	public List<String> pegarFiguritas(int dni) {
		Participante participante = buscarParticipante(dni);
		return participante.pegarFiguritas();
	}
	
	public boolean llenoAlbum(int dni) {
		Participante participante = buscarParticipante(dni);
		return participante.albumCompleto();
	}

	public String aplicarSorteoInstantaneo(int dni) {
		String sorteo[] = fabrica.generarPremiosParaSorteoInstantaneo();
		Random aleatorio = new Random(System.currentTimeMillis());
		Participante participante= buscarParticipante(dni);
			if (participante.tipoAlbum() == "Web" ) {
				throw new RuntimeException("Codigo inexistente");
			}
		int intAletorio = aleatorio.nextInt(3);
		cantDeSorteosEfectuados++;
		return sorteo[intAletorio];
	}

	public int buscarFiguritaRepetida(int dni) {
		Participante participante=buscarParticipante(dni);
		if(participante.figuritasRepetida().size() >= 1) {
			return participante.figuritasRepetida().get(0).getCodigo();
		}else {
			return -1;	
		}
	}

	public boolean intercambiar(int dni, int codFigurita) {
		Participante participante=buscarParticipante(dni);
		for (Participante participantes : participantes.values()) { 
			if(participante.intercambiar(codFigurita,participantes)) {
				return true;
			}
		}
		return false;
	}

	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		Participante participante = buscarParticipante(dni);
		return participante.intercambiarFiguritaRepetida(participante);
	}

	public String darNombre(int dni) {
		return buscarParticipante(dni).darNombre();
	}

	public String darPremio(int dni) {
		String premio = "";
		Participante participante = buscarParticipante(dni);
		if (estaRegistrado(dni) && participante.albumCompleto()) {
			premio = participante.darPremio();
		} else {
			throw new RuntimeException("Participante no registrado o album incompleto");
		}
		return premio;
	}

	public String listadoDeGanadores() { 
		StringBuilder listadoDeGanadores = new StringBuilder() ;
		for (Participante participante : participantes.values()) {
			if (participante.albumCompleto()) {
				listadoDeGanadores.append("(").append(participante.saberDni()
				).append(") ").append(participante.darNombre()
				).append(": ").append(participante.darPremio()).append("\n");
			}
		}
		return listadoDeGanadores.toString();

	}

	public List<String> participantesQueCompletaronElPais(String nombrePais) {
		List<String> participantesQueCompletaronElPais = new ArrayList<>();
		for (Participante participante : participantes.values()) {
			if (participante.paisLleno(nombrePais)) {
				participantesQueCompletaronElPais.add("(" + participante.saberDni()+ ") " +
				participante.darNombre() + " : " + participante.tipoAlbum());
			}
		}
		return participantesQueCompletaronElPais;
	}
	
	
	

	////////////////////////////////////////// METODOS AUXILIARES //////////////////////////////////////////
	

	private boolean estaRegistrado(int dni) {
		return participantes.containsKey(dni);
	}

	private boolean codigoUsado(int dni) {
		return buscarParticipante(dni).codigoUsado();
	}

	private void usarCodigo(int dni) {
		buscarParticipante(dni).usarCodigo();
	}

	public List<Figurita> figuritasAsociadas(int dni) {
		return buscarParticipante(dni).figuritas();

	}

	public String toString() {
		StringBuilder resultado = new StringBuilder();
		int cantidadDeParticipantes = 0;
		int cantidadDeTipoDeAlbumExtendido = 0;
		int cantidadDeTipoDeAlbumWeb = 0;
		int cantidadDeTipoDeAlbumTradicional = 0;
		resultado.append("         Sistema Del Album Del Mundial ").append("\n");
		resultado.append("=================================================").append("\n");
		// Recorremos en Album
		for (Participante participante : participantes.values()) {
			if (participante.tipoAlbum() == "Extendido") {
				cantidadDeTipoDeAlbumExtendido++;
			}
			if (participante.tipoAlbum() == "Tradicional") {
				cantidadDeTipoDeAlbumTradicional++;
			}
			if (participante.tipoAlbum() == "Web") {
				cantidadDeTipoDeAlbumWeb++;
			}
			if (estaRegistrado(participante.saberDni())) {
				cantidadDeParticipantes++;
			}
		}
		resultado.append("Cantidad de participantes: ");
		resultado.append(cantidadDeParticipantes).append("\n");
		resultado.append("Cantidad de tipo de album Tradicional: ");
		resultado.append(cantidadDeTipoDeAlbumTradicional).append("\n");
		resultado.append("Cantidad de tipo de album Extendido: ");
		resultado.append(cantidadDeTipoDeAlbumExtendido).append("\n");
		resultado.append("Cantidad de tipo de album Web: ");
		resultado.append(cantidadDeTipoDeAlbumWeb).append("\n");
		resultado.append("Cantidad de codigo promocional canjeado: ");
		resultado.append(cantDeVecesCodigoPromo).append("\n");
		resultado.append("Cantidad de sorteos efectuados: ");
		resultado.append(cantDeSorteosEfectuados).append("\n");
		resultado.append("Cantidad de figuritas Compradas: ");
		resultado.append(cantDeFiguritasCompradas);
		resultado.append("\n").append("\n").append("\n");
		return resultado.toString();

	}

	
	
}
