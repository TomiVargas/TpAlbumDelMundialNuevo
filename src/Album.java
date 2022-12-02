
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Album {
	private static int codigo;
	private Map<String, Figurita[]> album;
	private List<Figurita> pegadas; //Deberia ser un conjunto.
	private Figurita[] lugares;
	

	public Album(Integer lugaresPorPais, String[] paisesParticipantes, int codigo) {
		codigo++;
		this.pegadas = new ArrayList<>();
		this.album = new HashMap<String, Figurita[]>();
		for (int pais = 0; pais < paisesParticipantes.length; pais++) {
			this.lugares = new Figurita[lugaresPorPais];
			album.put(paisesParticipantes[pais], lugares);
		}
		
	}
	
	

	public int codigo() {
		return codigo;
	}
	protected boolean pegarFigurita(Figurita figus) {
		if(figus == null ) {
			return false;
		}
		if (pegada(figus)) {
			return false;
		}
		agregarAPegadas(figus);
		album.get(figus.mostrarPais())[figus.numeroIdentificador()] = figus;
		return true;
		}
	
	boolean pegada(Figurita figu) {
		for (int i = 0; i < pegadas.size(); i++) {
			if(pegadas.get(i).mostrarPais() == figu.mostrarPais() && 
					pegadas.get(i).numeroIdentificador()== figu.numeroIdentificador()) {
					return true;
				}
			}
		return false;
	} 	
	
	protected void agregarAPegadas(Figurita figurita) {
		pegadas.add(figurita);
	}
	
	
	protected List<Figurita> mostrarPegadas() {
		return this.pegadas;
	}

	public boolean albumLleno() {
		boolean lleno = true;
		for (Figurita[] figuritas : album.values()) {
			lleno = lleno && paisEstaLleno(figuritas);
			
		}
		return lleno;
	}

	protected boolean paisEstaLleno(Figurita[] figurita) {
		boolean lleno = true;
		for (int posicion = 0; posicion < figurita.length; posicion++) {
			lleno = lleno && figurita[posicion] != null;
		}
		return lleno;
	}

	public boolean paisLleno(String pais) {
		return paisEstaLleno(album.get(pais));
	}


	// Este metodo esta implementado en los 3 album, que devuelve el nombre de cada
	// uno.
	protected abstract String nombre();

	protected void usarCodigo() {
	}
	protected boolean codigoUsado() {
		return false;
	}
	


}
