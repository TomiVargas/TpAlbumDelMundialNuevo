import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AlbumDelMundial  {
	
	private Fabrica  fabrica;
	private Album album;
	private Participante participante;

	private Map <Integer, Participante> participantes;
	
	public AlbumDelMundial() {
				participantes=new HashMap<>();
				fabrica=new Fabrica();
				
		
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
				album =fabrica.crearAlbumWeb();			}
			if(tipoAlbum=="Extendido") {
				album =fabrica.crearAlbumExtendido();
			}
			participante=new Participante(dni,nombre,album);
			participantes.put(dni,participante);
		}
		
		return album.codigo();
	}

	private boolean estaRegistrado(int dni) {
		return participantes.containsKey(dni);
	}

	
	//Nuevo metodo
		public void comprarFiguritas2(int dni) {
			if(estaRegistrado(dni)) {
				List<Figurita> sobre =fabrica.generarSobre(4);
				for (Map.Entry<Integer, Participante> p : participantes.entrySet()) {
				    if(p.getKey()== dni)
				    for(int i =0; i < sobre.size(); i++) {
				    	p.getValue().agregarFigurita(sobre.get(i));
				    }
				}
			}else {
				throw new RuntimeException("Participante No esta registrado");
			}
			
		}
	//Nuevo metodo auxiliar devuelve el participante del sistema
	private Participante participante(int dni) {
		if(estaRegistrado(dni))
		for (Map.Entry<Integer, Participante> p : participantes.entrySet()) {
		    if(p.getKey()== dni)
		    	return p.getValue();
		}
		throw new RuntimeException("No esta Registrado");
	}
	
	//Nuevo metodo devuelve la coleccion de un participante
	public List<Figurita> figuritasAsociadas(int dni){
		return participante(dni).figus;
	}

	public void comprarFiguritasConCodigoPromocional(int i) {
		// TODO Auto-generated method stub
		
	}

	public String darNombre(int dni) {
		String nombre="";
		if(!estaRegistrado(dni)) {
			throw new RuntimeException("No esta registrado!");
		}
		for (Map.Entry<Integer, Participante> p : participantes.entrySet()) {
		    if(p.getKey()== dni)
		    	nombre= p.getValue().darNombre();
		}
		return "Nombre: "+nombre;
	}


	public String aplicarSorteoInstantaneo(int dni) {
		String sorteo[] = fabrica.generarPremiosParaSorteoInstantaneo();
		Random aleatorio = new Random(System.currentTimeMillis());
		
		if(!estaRegistrado(dni)|| participante(dni).album.nombre() != "Tradicional") {
			throw new RuntimeException("Participante no registrado o Codigo inexistente");
		}
		
		
		int intAletorio = aleatorio.nextInt(3);
		return sorteo[intAletorio];	
	}
	
	public List<String> pegarFiguritas(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object buscarFiguritaRepetida(int i) {
		
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
