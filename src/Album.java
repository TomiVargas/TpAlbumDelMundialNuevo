import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Album  {
	private int codigo;
	private Map<String, List<Figurita>> album;
	protected String[] paisesParticipantes;
	

	public Album(Integer lugaresPorPais, String[] paisesParticipantes, int codigo) {
		this.codigo=codigo;
		this.paisesParticipantes=paisesParticipantes;
		this.album = new HashMap<String, List<Figurita>>();
		
		List<Figurita> lugares = new ArrayList<Figurita>(lugaresPorPais);
		for(int pais = 0; pais < paisesParticipantes.length; pais++) {	
			album.put(paisesParticipantes[pais], lugares);
		}
	}
	
	
	
	public int codigo() {
		return this.codigo;
	}

	
	public void pegarFigurita(Figurita figu) { 
		for (Map.Entry<String, List<Figurita>> album : album.entrySet()) {
			if(album.getKey()==figu.mostrarPais()) {
				for(int i=0; i < album.getValue().size() ; i++) {
					if(figu.codigo()==i && !estaPegada(figu)) {
					album.getValue().add(i,figu);
					}
				}
					
		}
		}
	}
	



	protected boolean estaPegada(Figurita figu) {
		for (Map.Entry<String, List<Figurita>> album : album.entrySet()) {
			for(int i=0; i < album.getValue().size() ; i++) {
					if(album.getValue().get(i).equals(figu)) {
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
	//Intente resolverlo pero creo que quedo mal. No esta en los test para probarlo,
		//solo esta el que da error. 
		public boolean albumLleno() {
			boolean estaLleno=true;
			for(Map.Entry<String, List<Figurita>> album : album.entrySet()) {
				for(int i=0;i<paisesParticipantes.length;i++) {
					if(paisesParticipantes[i]==album.getKey()) {	
					estaLleno=estaLleno && album.getValue().size()==12;
					}
				}
			}
			return estaLleno;
		}
	
	public boolean albumLleno2() {
		boolean lleno=true;
		for (int i = 0; i < paisesParticipantes.length; i++) {
			lleno = lleno && paisLleno(album.get(paisesParticipantes[i]));
		}
		return lleno;
	}
	
	public boolean paisLleno(List<Figurita> figurita) {
		boolean lleno= true;
		for (int i = 0; i < figurita.size(); i++) {
			lleno= lleno && figurita.get(i)!=null;
		}
		return lleno;
	}
	
	
	public boolean paisCompleto(String pais) {
		boolean estaLleno=true;
		for(Map.Entry<String, List<Figurita>> album : album.entrySet()) {
				if(album.getKey()==pais) {
					estaLleno=estaLleno && album.getValue().size()==12;
				}	
		}
		return estaLleno;
	}
	protected List<Figurita> tamañoDeLaListaDeFiguritas(List<Figurita> figu){
		List<Figurita> figus = new ArrayList<Figurita>();
			//album.getValue().addAll(figus);
			for(int i=0;i<figu.size();i++) {
				figus.add(figu.get(i));
			}
			return figus;	
	}
	



	/*public String toString() {
		StringBuilder resultado = new StringBuilder();
		resultado.append("*********************").append("\n");
		resultado.append("* ALBUM TRADICIONAL *").append("\n");
		resultado.append("*********************").append("\n").append("\n");
		// Recorremos en Album
		for (Map.Entry<String, List<Figurita>> entry : album.entrySet()) {
		    
			//Mostramos la clave (Nombre del pais participante)
			resultado.append(entry.getKey());
			resultado.append("\n");
			resultado.append("   Figuritas : "); 
			
			for(int i=0; i < entry.getValue().size() ; i++) {
				// Mostramos valor (Lugares de cada pais)
				resultado.append(i + " = " + entry.getValue().get(i) + ", ");
			}
			resultado.append("\n").append("\n").append("***************************************************************************************************************************").append("\n");
		}
		return resultado.toString();
	}*/
	
	//Este metodo esta implementado en los 3 album, que devuelve el nombre de cada uno. 
	protected abstract String nombre();
	protected  void usarCodigo() {}
	protected boolean codigoUsado(){
		return false;}
	protected abstract String darPremio();
	//protected abstract boolean albumLleno();
} 	





















