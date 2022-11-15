import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AlbumDelMundial {

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

	int registrarParticipante(Integer dni, String nombre, String tipoAlbum) {
		Album album = null;
		if (tipoAlbum != "Tradicional" && tipoAlbum != "Web" && tipoAlbum != "Extendido") {
			throw new RuntimeException("Tipo de Album invalido");
		}
		if (estaRegistrado(dni)) {
			throw new RuntimeException("Participante ya registrado!");
		} else {
			
			if (tipoAlbum == "Tradicional") {
				album = fabrica.crearAlbumTradicional();
			}
			if (tipoAlbum == "Web") {
				album = fabrica.crearAlbumWeb();
			}
			if (tipoAlbum == "Extendido") {
				 album = fabrica.crearAlbumExtendido();
			}
			// Crea un nuevo Participante
			Participante participante = new Participante(dni, nombre, album);
			// Agrega Participante al Sistema
			participantes.put(dni, participante);
		}
		return album.codigo();
	}

	public void comprarFiguritas(int dni) {
		if (estaRegistrado(dni)) {
			List<Figurita> sobre = fabrica.generarSobre(4);
			cantDeFiguritasCompradas += 4;
			agregarSobre(sobre, dni);
		} else {
			throw new RuntimeException("Participante No esta registrado o Album invalido");
		}
	}

	public void comprarFiguritasTop10(int dni) {
		if (estaRegistrado(dni) && participantes.get(dni).tipoAlbum() == "Extendido") {
			List<FiguritaTOP10> sobre = fabrica.generarSobreTop10(4);
			cantDeFiguritasCompradas += 4;
			agregarSobreTOP10(sobre, dni);
		} else {
			throw new RuntimeException("Participante No esta registrado o Album invalido");
		}
	}

	public void comprarFiguritasConCodigoPromocional(int dni) {
		if (estaRegistrado(dni) && participantes.get(dni).tipoAlbum() == "Web") {
			List<Figurita> sobre = fabrica.generarSobre(4);
			if (!codigoUsado(dni)) {
				agregarSobre(sobre, dni);
				cantDeFiguritasCompradas += 4;
				usarCodigo(dni);
				cantDeVecesCodigoPromo++;
			}
		} else {
			throw new RuntimeException("Participante No esta registrado o Album invalido o Codigo Usado");
		}
	}

	public List<String> pegarFiguritas(int dni) {
		List<String> pegadas = new ArrayList<String>();
		List<Figurita> figuAPegar = participantes.get(dni).pegarFiguritas();
		for(int i=0;i<figuAPegar.size();i++) {
			pegadas.add(" $ " + figuAPegar.get(i).mostrarPais() + " -$ "
				+ figuAPegar.get(i).codigo() + " $ ");
			participantes.get(dni).quitar(figuAPegar.get(i));
		}
		return pegadas;
	}
	
	public boolean llenoAlbum(int dni) {
		return participantes.get(dni).albumCompleto();
	}

	public String aplicarSorteoInstantaneo(int dni) {
		String sorteo[] = fabrica.generarPremiosParaSorteoInstantaneo();
		Random aleatorio = new Random(System.currentTimeMillis());

		if (!estaRegistrado(dni) || participantes.get(dni).tipoAlbum() == "Web" ) {
			throw new RuntimeException("Participante no registrado o Codigo inexistente");
		}
		int intAletorio = aleatorio.nextInt(3);
		cantDeSorteosEfectuados++;
		return sorteo[intAletorio];
	}

	public int buscarFiguritaRepetida(int dni) {
		if (estaRegistrado(dni) && participantes.get(dni).figuritasRepetida().size() >= 1) {
			return participantes.get(dni).figuritasRepetida().get(0).codigo();
		}
		return -1;
	};

	public boolean intercambiar(int dni, int codFigurita) {

		if (estaRegistrado(dni)) {
			for (Map.Entry<Integer, Participante> participante : participantes.entrySet()) {
				if (participantes.get(dni).tipoAlbum() == participante.getValue().tipoAlbum()
						&& participantes.get(dni).saberDni() != dni) {
					if(participantes.get(dni).intercambiar(codFigurita,participante.getValue())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		Participante participante = participantes.get(dni);
		return participante.intercambiarFiguritaRepetida(participante);
	}

	public String darNombre(int dni) {
		if (estaRegistrado(dni)) {
			return participantes.get(dni).darNombre();
		}
		throw new RuntimeException("No registrado");
	}

	public String darPremio(int dni) {
		String premio = "";
		Participante participante = participantes.get(dni);
		if (estaRegistrado(dni) && participante.albumCompleto()) {
			premio = participante.darPremio();
		} else {
			throw new RuntimeException("Participante no registrado o album incompleto");
		}
		return premio;
	}

	public StringBuilder listadoDeGanadores() {
		StringBuilder listadoDeGanadores = new StringBuilder();
		for (Map.Entry<Integer, Participante> participante : participantes.entrySet()) {
			if (participante.getValue().albumCompleto()) {
				listadoDeGanadores.append("Listado de Ganadores :").append("\n");
				listadoDeGanadores.append("(");
				listadoDeGanadores.append(participante.getValue().saberDni());
				listadoDeGanadores.append(")");
				listadoDeGanadores.append(" ").append(participante.getValue().darNombre()).append(":");
				listadoDeGanadores.append(participante.getValue().darPremio()).append("\n");
			}
		}
		return listadoDeGanadores;

	}

	public List<String> participantesQueCompletaronElPais(String nombrePais) {
		List<String> participantesQueCompletaronElPais = new ArrayList<>();
		for (Map.Entry<Integer, Participante> participante : participantes.entrySet()) {
			if (participante.getValue().paisLleno(nombrePais)) {
				participantesQueCompletaronElPais.add("(" + participante.getValue().saberDni()+ ") " +
				participante.getValue().darNombre() + " : " + participante.getValue().tipoAlbum());
			}
		}
		return participantesQueCompletaronElPais;
	}

	////////////////////////////////////////// METODOS AUXILIARES //////////////////////////////////////////

	private boolean estaRegistrado(int dni) {
		return participantes.containsKey(dni);
	}

	private boolean codigoUsado(int dni) {
		return participantes.get(dni).codigoUsado();
	}

	private void usarCodigo(int dni) {
		participantes.get(dni).usarCodigo();
	}

	private void agregarSobre(List<Figurita> sobre, int dni) {
		agregarFiguritas(participantes.get(dni), sobre);
	}

	private void agregarSobreTOP10(List<FiguritaTOP10> sobre, int dni) {
		agregarFiguritasTOP10(participantes.get(dni), sobre);
	}

	private void agregarFiguritas(Participante participante, List<Figurita> sobre) {
		for (int i = 0; i < sobre.size(); i++) {
			participante.agregarFigurita(sobre.get(i));
		}
	}

	private void agregarFiguritasTOP10(Participante participante, List<FiguritaTOP10> sobre) {
		for (int i = 0; i < sobre.size(); i++) {
			participante.agregarFigurita(sobre.get(i));
		}
	}

	public List<Figurita> figuritasAsociadas(int dni) {
		return participantes.get(dni).figuritas();

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
		for (Map.Entry<Integer, Participante> participante : participantes.entrySet()) {
			if (participante.getValue().tipoAlbum() == "Extendido") {
				cantidadDeTipoDeAlbumExtendido++;
			}
			if (participante.getValue().tipoAlbum() == "Tradicional") {
				cantidadDeTipoDeAlbumTradicional++;
			}
			if (participante.getValue().tipoAlbum() == "Web") {
				cantidadDeTipoDeAlbumWeb++;
			}
			if (estaRegistrado(participante.getValue().saberDni())) {
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
