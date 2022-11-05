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
	public void comprarFiguritasConCodigoPromocional(int dni) {
		if (estaRegistrado(dni) && participantes.get(dni).album.nombre()=="Web") {
			List<Figurita> sobre = fabrica.generarSobre(4);
			if(!codigoUsado(dni)) {
				agregarSobre(sobre,dni);
				usarCodigo(dni);
			}
		} else {
			throw new RuntimeException("Participante No esta registrado o Album invalido o Codigo Usado");
		}
	}
	private boolean codigoUsado(int dni) {
		return participantes.get(dni).album.codigoUsado();
	}
	private void usarCodigo(int dni) {
		participantes.get(dni).album.usarCodigo();
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
	public List<String> figuritasAsociadas2(int dni){
		return participantes.get(dni).figuritas(dni);
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
		//List<Figurita> figusPegadasQuitar = new ArrayList<Figurita>();
		Participante participante=participantes.get(dni);
				List<Figurita> figusParticipante = figuritasAsociadas(dni);
				for (int i = 0; i < figusParticipante.size(); i++) {
					participante.album.pegarFigurita(figusParticipante.get(i));
					figusPegadas.add(" $ " + figusParticipante.get(i).mostrarPais() + " -$ "
							+ figusParticipante.get(i).codigo() + " $ ");
					
				}
		return figusPegadas;
	}

	

	public int buscarFiguritaRepetida(int dni) {
		if(estaRegistrado(dni) && participantes.get(dni).figuritasRepetida().size()>=1) {
			return participantes.get(dni).figuritasRepetida().get(0).codigo();
			
		}
		return -1;
	};

	public boolean intercambiar(int dni, int codFigurita) {
		 if(estaRegistrado(dni) && existeFigurita(dni,codFigurita)) {
			 for(Map.Entry<Integer, Participante> participante : participantes.entrySet()) {
				 if(participantes.get(dni).tipoAlbum()
					== participante.getValue().tipoAlbum()) {
				 List<Figurita> figuRepetidaParticipante2=participante.getValue().figuritasRepetida(); 
				 	participantes.get(dni).figus
				 	.add(recorrerLaListaDeFigusRepetidasYVerificaSiEsta(figuRepetidaParticipante2,codFigurita));
				 	if(recorrerLaListaDeFigusRepetidasYVerificaSiEsta(figuRepetidaParticipante2,codFigurita)!=null) {
				 		return true;
				 	}
				 }
			 
			 
			 }
			 
		 }
		 return false;

	}

	private Figurita recorrerLaListaDeFigusRepetidasYVerificaSiEsta(List<Figurita> figusRepetida, int codFigurita) {
		Figurita figu=null;		
		for(int i=0;i<figusRepetida.size();i++) {
					if(figusRepetida.get(i).codigo()==codFigurita) {
						figu=figusRepetida.get(i);
					}
				}
				return figu;
		
	}

	private boolean existeFigurita(int dni, int codFigurita) {
		List<Figurita> figus= figuritasAsociadas(dni);
		for(int i=0;i<figus.size();i++) {
			if(figus.get(i).codigo()== codFigurita) {
				return true;
			}
		}
		return false;
	}
	public boolean llenoAlbum(int dni) {
		return participantes.get(dni).album.albumLleno();
	}
	
	public boolean llenoAlbum2(int dni) {
		return participantes.get(dni).album.albumLleno2();
	}

	public String darPremio(int dni) {
			String premio="";
			Participante participante= participantes.get(dni);
			//FALTA RESOLVER LO DE ALBUM LLENO esto en realidad iria 
			// participante.album.albumLleno() sin negar.
			if (estaRegistrado(dni) && participante.album.albumLleno()) {
				premio = participante.album.darPremio();
			} else {
				throw new RuntimeException("Participante no registrado o album incompleto");
			}
			return premio;
	}

	public String listadoDeGanadores() {
		String listaDeGanadores="";
		for (Map.Entry<Integer, Participante> participante : participantes.entrySet()) {
			if(participante.getValue().album.albumLleno()) {
				listaDeGanadores+="-"+"("+"$"+participante.getValue().dni+")"+" $"+
				participante.getValue().darNombre()+":"+ " $"+participante.getValue().tipoAlbum();
			}
		}
		return listaDeGanadores;
		
	}

	public List<String> participantesQueCompletaronElPais(String pais) {
		List<String> participantesQueCompletaronElPais= new ArrayList<>();
		for(Map.Entry<Integer, Participante> participante : participantes.entrySet()) {
			if(participante.getValue().album.paisCompleto(pais)) {
				participantesQueCompletaronElPais.add(participante.getValue().darNombre());
			}
		}
		return participantesQueCompletaronElPais;
	}

	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		List<Figurita> repetidas=participantes.get(dni).figuritasRepetida();
		for(int i=0;i<repetidas.size();i++) {
			if(intercambiar(dni,repetidas.get(i).codigo())) {
				return true;
			}
		}
		return false;
	}
}
