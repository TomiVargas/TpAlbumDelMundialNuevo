import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AlbumDelMundial  {
	
	private Fabrica fabrica;
	private Participante participante;
	private Album album;
	private Figurita figurita;
	private FiguritaTradicional figuritaTradicional;
	private Map <Integer, Album> participantes;
	private List <Figurita> figuritas;
	
	public AlbumDelMundial() {
		fabrica = new Fabrica();
		participantes=new HashMap<Integer,Album>();
		
	}
	
	int registrarParticipante(Integer dni, String nombre, String tipoAlbum) {
		if(tipoAlbum !="Tradicional" && tipoAlbum !="Web" && tipoAlbum !="Extendido" ) {
			throw new RuntimeException("Tipo de Album invalido");
		} 
		if(estaRegistrado(dni)) {
			throw new RuntimeException("Participante ya registrado!");
		}else {
			if(tipoAlbum=="Tradicional") {
				album=fabrica.crearAlbumTradicional();
			}
			if(tipoAlbum=="Web") {
				album =fabrica.crearAlbumWeb();
			}
			if(tipoAlbum=="Extendido") {
				album =fabrica.crearAlbumExtendido();
			}
			participante=new Participante(dni,nombre,album);
			participantes.put(participante.dni,participante.album);
		}
		
		return album.codigo();
	}

	private boolean estaRegistrado(int dni) {
		return participantes.containsKey(dni);
	}

	public void comprarFiguritas(int dni) {
		if(estaRegistrado(dni)) {
			participante.figuritas.put(dni,fabrica.generarSobre(4));
		}else {
			throw new RuntimeException("Participante No esta registrado");
		}
		
	}

	public void comprarFiguritasConCodigoPromocional(int i) {
		// TODO Auto-generated method stub
		
	}

	public String darNombre(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public String aplicarSorteoInstantaneo(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> pegarFiguritas(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object buscarFiguritaRepetida(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public void intercambiar(int i, Object buscarFiguritaRepetida) {
		// TODO Auto-generated method stub
		
	}

	public boolean llenoAlbum(int i) {
		// TODO Auto-generated method stub
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

	public String[] participantesQueCompletaronElPais(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
