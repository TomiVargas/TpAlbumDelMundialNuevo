import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AlbumDelMundial  {
	
	private Fabrica  fabrica;
	private Album album;
	private Participante participante;
	private Map <Integer, Participante> participantes;
	
	public AlbumDelMundial() {
				this.participantes=new HashMap<>();
				this.fabrica=new Fabrica();
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
			//Crea un nuevo Participante
			participante=new Participante(dni,nombre,album);
			
			//Agrega Participante al Sistema
			participantes.put(dni,participante);
		}
		
		return album.codigo();
	}

	private boolean estaRegistrado(int dni) {
		return participantes.containsKey(dni);
	}

	
	//Nuevo metodo
		void  comprarFiguritas2(int dni) {
			if(estaRegistrado(dni)) {
				List<Figurita> sobre =fabrica.generarSobre(4);
				for (Map.Entry<Integer, Participante> p : participantes.entrySet()) {
				    if(p.getKey()== dni)
				    for(int i =0; i < sobre.size(); i++) {
				    	p.getValue().agregarFigus2(sobre.get(i));
				    }
				}
			}else {
				throw new RuntimeException("Participante No esta registrado");
			}
			
		}
		void comprarFiguritasTop10(int dni) {
			if(estaRegistrado(dni)) {
				List<FiguritaTOP10> sobre =fabrica.generarSobreTop10(4);
				for (Map.Entry<Integer, Participante> p : participantes.entrySet()) {
				    if(p.getKey()== dni && p.getValue().album.nombre()=="Extendido") {
				    for(int i =0; i < sobre.size(); i++) {
				    	p.getValue().agregarFigus2(sobre.get(i));
				    }
				    }
				}
			}else {
				throw new RuntimeException("Participante No esta registrado o Album invalido");
			}
		}
		
		//Metodo test
		public List<Figurita> testComprarFigurita(int dni){
			List<Figurita> sobre =fabrica.generarSobre(4);
			Participante participante = null;
			//Figurita figurita = new FiguritaTradicional(111, "test", "test");
			for (Map.Entry<Integer, Participante> p : participantes.entrySet()) {
			    if(p.getKey()== dni) {
			    	for(int i =0; i < sobre.size(); i++) {
				    	p.getValue().agregarFigus2(sobre.get(i));
				    	
				    }
			    	participante = p.getValue();
			    	
			    }
			}
			return participante.figus;
		}
	//Nuevo metodo auxiliar devuelve el el objeto Participante 
	protected Participante participante(int dni) {
		if(estaRegistrado(dni))
		for (Map.Entry<Integer, Participante> p : participantes.entrySet()) {
		    if(p.getKey()== dni)
		    	return p.getValue();
		}
		throw new RuntimeException("No esta Registrado");
	}
	
	//Devuelve la coleccion de Figuritas del Participante
	public List<Figurita> figuritasAsociadas(int dni){
		return participante(dni).figuritas2();
		
	}

	public void comprarFiguritasConCodigoPromocional(int dni) {
		
	}

	//Devuelve nombre Participante
	public String darNombre(int dni) {
		String nombre="";
		if(!estaRegistrado(dni)) {
			throw new RuntimeException("No esta registrado!");
		}
		for (Map.Entry<Integer, Participante> p : participantes.entrySet()) {
		    if(p.getKey()== dni)
		    	nombre= p.getValue().darNombre();
		}
		return "Nombre participante: "+nombre;
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
	
	public List<String> pegarFiguritas(int dni) {
		List<String> figusPegadas = new ArrayList<String>();
		for (Map.Entry<Integer, Participante> p : participantes.entrySet()) {
			if(p.getKey()==dni) {
				List<Figurita>	figusAlbum= p.getValue().album.figuritas();
				List<Figurita> figusParticipante=figuritasAsociadas(dni);
				for(int i=0;i<figusParticipante.size();i++) {
					
						figusPegadas.add("$"+figusParticipante.get(i).mostrarPais()+"-$"+figusParticipante.get(i).codigo()+"$");
					
				}
				
			}
		}
		
		return figusPegadas;
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
