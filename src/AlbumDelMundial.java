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

	public AlbumDelMundial() {
		this.participantes = new HashMap<>();
		this.fabrica = new Fabrica();
	}

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

	private boolean estaRegistrado(int dni) {
		return participantes.containsKey(dni);
	}

	void comprarFiguritas(int dni) {
		if (estaRegistrado(dni)) {
			List<Figurita> sobre = fabrica.generarSobre(4);
			agregarSobre(sobre, dni);
		} else {
			throw new RuntimeException("Participante No esta registrado o Album invalido");
		}
	}

	void comprarFiguritasTop10(int dni) {
		if (estaRegistrado(dni) && participantes.get(dni).tipoAlbum() == "Extendido") {
			List<FiguritaTOP10> sobre = fabrica.generarSobreTop10(4);
			agregarSobreTOP10(sobre, dni);
		} else {
			throw new RuntimeException("Participante No esta registrado o Album invalido");
		}
	}

	// Los metodos agregar buscan por tabla de Hash el objeto participante
	// por su dni(key), por lo que ya no tendriamos que recorrer el diccionario

	private void agregarSobre(List<Figurita> sobre, int dni) {
		agregarFiguritas(participantes.get(dni), sobre);
	}

	private void agregarSobreTOP10(List<FiguritaTOP10> sobre, int dni) {
		agregarFiguritasTOP10(participantes.get(dni), sobre);
	}

	private void agregarFiguritas(Participante participante, List<Figurita> sobre) {
		for (int i = 0; i < sobre.size(); i++) {
			participante.agregarFigus2(sobre.get(i));
		}
	}

	private void agregarFiguritasTOP10(Participante participante, List<FiguritaTOP10> sobre) {
		for (int i = 0; i < sobre.size(); i++) {
			participante.agregarFigus2(sobre.get(i));
		}
	}

	public List<Figurita> figuritasAsociadas(int dni) {
		return participantes.get(dni).figuritas2();

	}

	public String darNombre(int dni) {
		if (estaRegistrado(dni)) {
			return participantes.get(dni).darNombre();
		}
		throw new RuntimeException("No registrado");
	}

	public String aplicarSorteoInstantaneo(int dni) {
		String sorteo[] = fabrica.generarPremiosParaSorteoInstantaneo();
		Random aleatorio = new Random(System.currentTimeMillis());

		if (!estaRegistrado(dni) || participantes.get(dni).tipoAlbum() != "Tradicional") {
			throw new RuntimeException("Participante no registrado o Codigo inexistente");
		}

		int intAletorio = aleatorio.nextInt(3);
		return sorteo[intAletorio];
	}

	public List<String> pegarFiguritas(int dni) {
		List<String> figusPegadas = new ArrayList<String>();
		for (Map.Entry<Integer, Participante> p : participantes.entrySet()) {
			if (p.getKey() == dni) {
				List<Figurita> figusParticipante = figuritasAsociadas(dni);
				for (int i = 0; i < figusParticipante.size(); i++) {
					p.getValue().album.pegarFigurita(figusParticipante.get(i));
					figusPegadas.add("$" + figusParticipante.get(i).mostrarPais() + "-$"
							+ figusParticipante.get(i).codigo() + "$");
				}

			}
		}

		return figusPegadas;
	}

	public int buscarFiguritaRepetida(int dni) {
		return dni;
		// TODO Auto-generated method stub
	};

	public void intercambiar(int i, Object buscarFiguritaRepetida) {
		// TODO Auto-generated method stub

	}

	public boolean llenoAlbum(int dni) {

		return false;
	}

	public String darPremio(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public char[] listadoDeGanadores() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> participantesQueCompletaronElPais(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean intercambiarUnaFiguritaRepetida(int dniConAlbumTradicional) {
		// TODO Auto-generated method stub
		return false;
	}

	public void comprarFiguritasConCodigoPromocional(int dniConAlbumTradicional) {
		// TODO Auto-generated method stub

	}
}
