import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class IAlbumDelMundial {

	private Fabrica fabrica;
	private Map<Integer, Participante> participantes;
	private int cantDeVecesCodigoPromo;
	private int cantDeSorteosEfectuados;
	private int cantDeFiguritasCompradas;

	public IAlbumDelMundial() {
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
			pegadas.add(figuAPegar.get(i).mostrarPais() + "-"
				+ figuAPegar.get(i).getCodigo());
			participantes.get(dni).quitar(figuAPegar.get(i));
		}
		return pegadas;
	}
	
	public boolean llenoAlbum(int dni) {
		if(estaRegistrado(dni)) {
		return participantes.get(dni).albumCompleto();
		}else {
			throw new RuntimeException("Participante no registrado");
		}
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
		if(!estaRegistrado(dni)) {
			throw new RuntimeException("Participante no registrado");
		}
		else if (estaRegistrado(dni) && participantes.get(dni).figuritasRepetida().size() >= 1) {
			return participantes.get(dni).figuritasRepetida().get(0).getCodigo();
		}else {
			return -1;	
		}
	}

	public boolean intercambiar(int dni, int codFigurita) {
		if(!estaRegistrado(dni) || participantes.get(dni).existeFigurita(codFigurita)) {
			throw new RuntimeException("Participante no registrado o no tiene figurita");
		}
			else if (estaRegistrado(dni)) {
			for (Map.Entry<Integer, Participante> participante : participantes.entrySet()) {
				if (verificaSiSePuedeIntercambiar(dni,participante.getValue(),codFigurita)) {
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

	public String listadoDeGanadores() {
		String listadoDeGanadores = "";
		for (Map.Entry<Integer, Participante> participante : participantes.entrySet()) {
			if (participante.getValue().albumCompleto()) {
				listadoDeGanadores+=("("+participante.getValue().saberDni()+") "+participante.getValue().darNombre()
					+	":"	+participante.getValue().darPremio()+"\n");
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
	public int saberCodigo(int dni) {
		return participantes.get(dni).saberCodigo();
	}
	

	////////////////////////////////////////// METODOS AUXILIARES //////////////////////////////////////////
	
	private boolean verificaSiSePuedeIntercambiar(int dni, Participante participante,int codFigurita) {
		return participantes.get(dni).tipoAlbum() == participante.tipoAlbum()
				&& participantes.get(dni).saberDni() != dni && participantes.get(dni).existeFigurita(codFigurita);
	}

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
			AlbumParticipante.append("DNI: " + participantes.get(dni).saberDni());
			AlbumParticipante.append("\n");
			AlbumParticipante.append("Lleno album: "+ llenoAlbum(dni));
			AlbumParticipante.append("\n");
			AlbumParticipante.append("***************************************");
			AlbumParticipante.append("\n");
		
			// Estructuras
			List<Figurita> figuritas = participantes.get(dni).figuritas();
			List<Figurita> repetidas = participantes.get(dni).figuritasRepetida();
			List<Figurita> pegadas = participantes.get(dni).saberAlbum().mostrarPegadas();

			// Mostrar coleccion figuritas por pais
			String[] pais = participantes.get(dni).saberAlbum().mostrarPaisesParticpantes();
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
