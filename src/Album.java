import java.util.HashMap;
import java.util.Map;

public abstract class Album  {
	private int codigo;
	private String tipoAlbum;
	private Integer lugaresPorPais;
	private String[] paisesParticipantes;
	private Map<String, Integer[]> paisParticipante; // Album
	

	public Album(Integer lugaresPorPais, String[] paisesParticipantes, int codigo) {
		this.codigo=codigo;
		this.lugaresPorPais=lugaresPorPais;
		this.paisesParticipantes=paisesParticipantes;
		this.paisParticipante = new HashMap<>(); // crea el album
		
		Integer[] cantidadLugares= new Integer[lugaresPorPais];	
		
		
		for(int i = 0; i < paisesParticipantes.length; i++) {			
			paisParticipante.put(paisesParticipantes[i], cantidadLugares);
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

	protected String nombre() {
		return this.tipoAlbum;
	};
} 	





















