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

	
	//Codigo Limpiado
			void comprarFiguritas(int dni) {
				if(estaRegistrado(dni)) {
					List<Figurita> sobre =fabrica.generarSobre(4);
					AgregarFiguritasAlSobre(sobre,dni);
				}else {
					throw new RuntimeException("Participante No esta registrado o Album invalido");
				}
			}
		
		//Codigo Limpiado
		void comprarFiguritasTop10(int dni) {
			if(estaRegistrado(dni)) {
				List<FiguritaTOP10> sobre =fabrica.generarSobreTop10(4);
				AgregarFiguritasAlSobreTOP10(sobre,dni);
			}else {
				throw new RuntimeException("Participante No esta registrado o Album invalido");
			}
		}
		//Metodo Auxiliar
				private void AgregarFiguritasAlSobre(List<Figurita> sobre, int dni) {
					for (Map.Entry<Integer, Participante> p : participantes.entrySet()) {
					    if(dniParticipante(p.getValue(),dni)) {
						    recorreSobreyAgregaFigurita(p.getValue(),sobre);
					    }
					}
					
				}
		private boolean dniParticipante(Participante value, int dni) {
			return value.dni==dni;
			
		}

		//Metodo Auxiliar
		private void AgregarFiguritasAlSobreTOP10(List<FiguritaTOP10> sobre, int dni) {
			for (Map.Entry<Integer, Participante> p : participantes.entrySet()) {
			    if(dniParticipanteYTieneAlbumExtendido(p.getValue(),p.getKey(),dni)) {
				    recorreSobreyAgregaFiguritaTOP10(p.getValue(),sobre);
			    }
			}
			
		}
		//Metodo Auxiliar
		private void recorreSobreyAgregaFigurita(Participante value, List<Figurita> sobre) {
					for(int i =0; i < sobre.size(); i++) {
				    	value.agregarFigus2(sobre.get(i));
				    }
				}
		//Metodo Auxiliar
		private void recorreSobreyAgregaFiguritaTOP10(Participante value, List<FiguritaTOP10> sobre) {
			for(int i =0; i < sobre.size(); i++) {
		    	value.agregarFigus2(sobre.get(i));
		    }
		}

		//Metodo Auxiliar
		private boolean dniParticipanteYTieneAlbumExtendido(Participante value, Integer key,Integer dni) {
			return key== dni && value.album.nombre()=="Extendido";
			
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
		
	//Nuevo metodo devuelve el Participante 
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
		nombre= nombreParticipante(dni);
		return "Nombre participante: "+nombre;
	}

	//Metodo auxiliar devuelve el nombre del Participante.
	private String nombreParticipante(int dni) {
		String nombre="";
		for (Map.Entry<Integer, Participante> p : participantes.entrySet()) {
		    if(p.getKey()== dni)
		    	nombre=p.getValue().darNombre();
		}
		return nombre;
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
	
	//Intentando Resolver esto.
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
