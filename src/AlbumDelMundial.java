import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AlbumDelMundial {

	private Fabrica fabrica;
	private Album album;
	private Participante participante;
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
			participante = new Participante(dni, nombre, album);
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
		if (estaRegistrado(dni) && participantes.get(dni).album.nombre() == "Web") {
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
		
		for (int i = 0; i < participantes.get(dni).figus.size(); i++) {
			if (participantes.get(dni).album.pegarFigurita2(participantes.get(dni).figus.get(i))) {

				pegadas.add(" $ " + participantes.get(dni).figus.get(i).mostrarPais() + " -$ "
						+ participantes.get(dni).figus.get(i).codigo() + " $ ");

				participantes.get(dni).quitar(participantes.get(dni).figus.get(i));
			}
		}
		return pegadas;
	}


	public boolean llenoAlbum(int dni) {
		return participantes.get(dni).album.albumLleno();
	}

	public String aplicarSorteoInstantaneo(int dni) {
		String sorteo[] = fabrica.generarPremiosParaSorteoInstantaneo();
		Random aleatorio = new Random(System.currentTimeMillis());

		if (!estaRegistrado(dni) || participantes.get(dni).tipoAlbum() != "Tradicional") {
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

		if (estaRegistrado(dni) && existeFigurita(dni, codFigurita)) {
			for (Map.Entry<Integer, Participante> participante : participantes.entrySet()) {
				if (participantes.get(dni).tipoAlbum() == participante.getValue().tipoAlbum()
						&& participantes.get(dni).dni != dni) {
					List<Figurita> figuRepetidaParticipante2 = participante.getValue().figuritasRepetida();
					participantes.get(dni).figus.add(
							recorrerLaListaDeFigusRepetidasYVerificaSiEsta(figuRepetidaParticipante2, codFigurita));
					if (recorrerLaListaDeFigusRepetidasYVerificaSiEsta(figuRepetidaParticipante2,
							codFigurita) != null) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		List<Figurita> repetidas = participantes.get(dni).figuritasRepetida();
		for (int i = 0; i < repetidas.size(); i++) {
			if (intercambiar(dni, repetidas.get(i).codigo())) {
				return true;
			}
		}
		return false;
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
		if (estaRegistrado(dni) && participante.album.albumLleno()) {
			premio = participante.album.darPremio();
		} else {
			throw new RuntimeException("Participante no registrado o album incompleto");
		}
		return premio;
	}

	public String listadoDeGanadores() {
		String listaDeGanadores = "";
		for (Map.Entry<Integer, Participante> participante : participantes.entrySet()) {
			if (participante.getValue().album.albumLleno()) {
				listaDeGanadores += "-" + "(" + "$" + participante.getValue().dni + ")" + " $"
						+ participante.getValue().darNombre() + ":" + " $" + participante.getValue().tipoAlbum();
			}
		}
		return listaDeGanadores;

	}

	public List<String> participantesQueCompletaronElPais(String pais) {
		List<String> participantesQueCompletaronElPais = new ArrayList<>();
		for (Map.Entry<Integer, Participante> participante : participantes.entrySet()) {
			if (participante.getValue().album.argentinaLleno(pais)) {
				participantesQueCompletaronElPais.add(participante.getValue().darNombre());
			}
		}
		return participantesQueCompletaronElPais;
	}

	////////////////////////////////////////// METODOS AUXILIARES //////////////////////////////////////////

	private boolean estaRegistrado(int dni) {
		return participantes.containsKey(dni);
	}

	private boolean codigoUsado(int dni) {
		return participantes.get(dni).album.codigoUsado();
	}

	private void usarCodigo(int dni) {
		participantes.get(dni).album.usarCodigo();
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
			// participante.agregarFigurita2(sobre.get(i).mostrarPais(), sobre.get(i));
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

	public Figurita[] figuritasAsociadas3(int dni) {
		return participantes.get(dni).figuritas2();
	}

	public List<String> figuritasAsociadas2(int dni) {
		return participantes.get(dni).figuritas(dni);
	}

	private Figurita recorrerLaListaDeFigusRepetidasYVerificaSiEsta(List<Figurita> figusRepetida, int codFigurita) {
		Figurita figu = null;
		for (int i = 0; i < figusRepetida.size(); i++) {
			if (figusRepetida.get(i).codigo() == codFigurita) {
				figu = figusRepetida.get(i);
			}
		}
		return figu;

	}

	private boolean existeFigurita(int dni, int codFigurita) {
		List<Figurita> figus = figuritasAsociadas(dni);
		for (int i = 0; i < figus.size(); i++) {
			if (figus.get(i).codigo() == codFigurita) {
				return true;
			}
		}
		return false;
	}

	public String mostrarAlbum(int dni) {
		return participantes.get(dni).album.toString(participantes.get(dni).tipoAlbum());
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
			if (estaRegistrado(participante.getValue().dni)) {
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

	String mostrarEstadoAlbumParticipante(int dni) {
		StringBuilder AlbumParticipante = new StringBuilder();

		// Cabecera
		if (participantes.containsKey(dni)) {
			AlbumParticipante.append("***************************************");
			AlbumParticipante.append("\n");
			AlbumParticipante.append(
					"Tipo Album: " + participantes.get(dni).tipoAlbum());
			AlbumParticipante.append("\n");
			AlbumParticipante.append("Nombre: " + participantes.get(dni).darNombre());
			AlbumParticipante.append("\n");
			AlbumParticipante.append("DNI: " + participantes.get(dni).dni());
			AlbumParticipante.append("\n");
			AlbumParticipante.append("Lleno album: "+ llenoAlbum(dni));
			AlbumParticipante.append("\n");
			AlbumParticipante.append("***************************************");
			AlbumParticipante.append("\n");
		
			// Estructuras
			List<Figurita> figuritas = participantes.get(dni).figus;
			List<Figurita> repetidas = participantes.get(dni).figuritasRepetida();
			List<Figurita> pegadas = participantes.get(dni).album.mostrarPegadas();

			// Mostrar coleccion figuritas por pais
			String[] pais = participantes.get(dni).album.mostrarPaisesParticpantes();
			for (int j = 0; j < pais.length; j++) {

				AlbumParticipante.append(pais[j].toString());
				AlbumParticipante.append("[ ");

				for (int i = 0; i < figuritas.size(); i++) {

					if (pais[j].toString() == figuritas.get(i).mostrarPais()) {

						AlbumParticipante.append(figuritas.get(i).codigo()).append(", ");
					}
				}

				// Mostrar coleccion de figuritas repetidas
				AlbumParticipante.append("]");
				AlbumParticipante.append("\n");
				AlbumParticipante.append("   Repetidas [ ");
				for (int i = 0; i < repetidas.size(); i++) {

					if (pais[j].toString() == repetidas.get(i).mostrarPais()) {
						AlbumParticipante.append(repetidas.get(i).codigo()).append(", ");
					}
				}
				AlbumParticipante.append("]");
				AlbumParticipante.append("\n");

				AlbumParticipante.append("   Pegadas [ ");
				for (int i = 0; i < pegadas.size(); i++) {
					if (pais[j].toString() == pegadas.get(i).mostrarPais()) {
							AlbumParticipante.append(pegadas.get(i).codigo()).append(", ");
					}
				}
				AlbumParticipante.append("]");
				AlbumParticipante.append("\n");
			}

		}

		return AlbumParticipante.toString();
	}
}
