import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Album  {
	private int codigo;
	private String tipoAlbum;
	private Integer lugaresPorPais;
	private String[] paisesParticipantes;
	private Map<String, Integer[]> paisParticipante; // Album
	
	//Nueva estructura
	private Map<String, List<Figurita>> album;
	

	public Album(Integer lugaresPorPais, String[] paisesParticipantes, int codigo) {
		this.codigo=codigo;
		this.lugaresPorPais=lugaresPorPais;
		this.paisesParticipantes=paisesParticipantes;
		this.paisParticipante = new HashMap<>(); // crea el album
		
		Integer[] cantidadLugares= new Integer[lugaresPorPais];	
		for(int i = 0; i < paisesParticipantes.length; i++) {			
			paisParticipante.put(paisesParticipantes[i], cantidadLugares);
		}
		
		//Nueva estructura 
		album = new HashMap<String, List<Figurita>>();
		List<Figurita> lugares = new ArrayList<Figurita>(11);
		for(int pais = 0; pais < paisesParticipantes.length; pais++) {	
			album.put(paisesParticipantes[pais], lugares);
		}
	}
	
	public int largo() {
		return paisParticipante.size();
	}
	
	public int codigo() {
		return this.codigo;
	}

	
	public void pegarFigurita(int figurita) { 
		for (Map.Entry<String, Integer[]> entry : paisParticipante.entrySet()) {
			for(int i=0; i < entry.getValue().length ; i++) { 
					if(figurita==i)
						entry.getValue()[i]=figurita;
			}
		}
	}
	

	public String toString() {
		StringBuilder resultado = new StringBuilder();
		resultado.append("*********************").append("\n");
		resultado.append("* ALBUM TRADICIONAL *").append("\n");
		resultado.append("*********************").append("\n").append("\n");
		// Recorremos en Album
		for (Map.Entry<String, Integer[]> entry : paisParticipante.entrySet()) {
		    
			//Mostramos la clave (Nombre del pais participante)
			resultado.append(entry.getKey());
			resultado.append("\n");
			resultado.append("   Figuritas : "); 
			
			for(int i=0; i < entry.getValue().length ; i++) {
				// Mostramos valor (Lugares de cada pais)
				resultado.append(i + " = " + entry.getValue()[i] + ", ");
			}
			resultado.append("\n").append("\n").append("***************************************************************************************************************************").append("\n");
		}
		return resultado.toString();
	}
	//Este metodo esta implementado en los 3 album, que devuelve el nombre de cada uno. 
	protected abstract String nombre();
} 	





















