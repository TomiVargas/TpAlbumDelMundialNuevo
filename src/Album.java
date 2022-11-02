import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Album  {
	private int codigo;
	private String tipoAlbum;	
	//Nueva estructura
	private Map<String, List<Figurita>> album;
	private List<Figurita> lugares;
	

	public Album(Integer lugaresPorPais, String[] paisesParticipantes, int codigo) {
		this.codigo=codigo;
		this.album = new HashMap<String, List<Figurita>>();
		
		List<Figurita> lugares = new ArrayList<Figurita>(11);
		for(int pais = 0; pais < paisesParticipantes.length; pais++) {	
			album.put(paisesParticipantes[pais], lugares);
		}
	}
	
	
	
	public int codigo() {
		return this.codigo;
	}

	
	public void pegarFigurita(Figurita figu) { 
		for (Map.Entry<String, List<Figurita>> album : album.entrySet()) {
			for(int i=0; i < album.getValue().size() ; i++) {
					if(album.getValue().get(i).codigo()==figu.codigo() && 
						album.getValue().get(i).mostrarPais()== figu.mostrarPais() && !estaPegada(figu)) {
						lugares.add(figu);
					}
				}
					
			}
		}
	private boolean estaPegada(Figurita figu) {
		for (Map.Entry<String, List<Figurita>> album : album.entrySet()) {
			for(int i=0; i < album.getValue().size() ; i++) {
					if(album.getValue().get(i).codigo()==figu.codigo() && 
						album.getValue().get(i).mostrarPais()== figu.mostrarPais()) {
						return true;
					}
				}
					
			}
		return false;
	}
	
	public List<Figurita> figuritas(){
		List<Figurita> figus = new ArrayList<Figurita>();
		for (Map.Entry<String, List<Figurita>> album : album.entrySet()) {
			//album.getValue().addAll(figus);
			for(int i=0;i<album.getValue().size();i++) {
				figus.add(album.getValue().get(i));
			}
		}
		return figus;
	}
	

	/*public String toString() {
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
	}*/
	
	//Este metodo esta implementado en los 3 album, que devuelve el nombre de cada uno. 
	protected abstract String nombre();
} 	





















