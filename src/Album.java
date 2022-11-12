
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Album {
	private int codigo;
	private Map<String, Figurita[]> album;
	protected String[] paisesParticipantes;
	
	private List<Figurita> pegadas;

	private Figurita[] lugares;
	public Album(Integer lugaresPorPais, String[] paisesParticipantes, int codigo) {
		this.codigo = codigo;
		this.paisesParticipantes = paisesParticipantes;
		
		this.pegadas= new ArrayList<>();
		this.album = new HashMap<String, Figurita[]>();

		this.lugares = new Figurita[lugaresPorPais];

		for (int pais = 0; pais < paisesParticipantes.length; pais++) {
			album.put(paisesParticipantes[pais], lugares);
		}
	}

	public int codigo() {
		return this.codigo;
	}


	protected boolean pegarFigurita2(Figurita figus) {
		if(estaPegada(figus) || figus==null) {
				return false;
		}else {
				album.get(figus.mostrarPais())[figus.codigo()]=figus;
				return true;
		}
	}
	
	protected boolean pegar(Figurita figurita) {
		if(album.containsKey(figurita.mostrarPais()))
			return pegarEnPais(album.get(figurita.mostrarPais()), figurita);
		return false;
	}
	
	protected boolean pegarEnPais(Figurita[] figuritas, Figurita figurita) {
		for (int posicion = 0; posicion < figuritas.length; posicion++) {
			if(figuritas[posicion]==null) {
				figuritas[posicion]=figurita;
				agregarAPegadas(figuritas[posicion]);
				return true;
			}
		} return false;
	}
	
	protected void agregarAPegadas(Figurita figurita) {
		this.pegadas.add(figurita);
	}
	
	protected List<Figurita> mostrarPegadas(){
		return this.pegadas;
	}

	private boolean existeFigurita(Figurita figu) {
		for(Map.Entry<String, Figurita[]> figus : album.entrySet()) {
			for(int i=0;i<figus.getValue().length;i++) {
				if(figus.getValue()[i]!=null) {
					if(figus.getValue()[i].equals(figu)) {
						return true;
				}
				}
			}
		}
		return false;
	}

	protected boolean estaPegada(Figurita figu) {
		if(figu!=null) {
			for(Map.Entry<String, Figurita[]> figus : album.entrySet()) {
				for(int i=0;i<figus.getValue().length;i++) {
					if(figus.getValue()[i]!=null) {
						if(figus.getValue()[i].equals(figu)) {
							return true;
						}
					}
				
			
				}
			}
		}
		return false;
		
	}

	
	public boolean albumLleno() {
		boolean lleno = true;
		for (Map.Entry<String, Figurita[]> a : album.entrySet()) {
			lleno = lleno && paisLleno(a.getValue());
		}
		return lleno;
	}

	private boolean paisLleno(Figurita[] figurita) {
		boolean lleno = true;
		for (int i = 0; i < figurita.length; i++) {
			lleno = lleno && figurita[i] != null;
		}
		return lleno;
	}

	public boolean argentinaLleno(String pais) {
		boolean lleno = true;
		for (Map.Entry<String, Figurita[]> a : album.entrySet()) {
			if (a.getKey().equals(pais)) {
				lleno = lleno && paisLleno(a.getValue());
			}
		}
		return lleno;
	}
	public String toString(String tipoAlbum) {
		StringBuilder resultado = new StringBuilder();
		resultado.append("*********************").append("\n");
		resultado.append("* ALBUM ").append(tipoAlbum.toUpperCase()).append(" *").append("\n");
		resultado.append("*********************").append("\n").append("\n");
		// Recorremos en Album
		for (Map.Entry<String, Figurita[]> entry : album.entrySet()) {

			// Mostramos la clave (Nombre del pais participante)
			resultado.append(entry.getKey());
			resultado.append("\n");
			resultado.append("   Figuritas : ").append("\n");

			for (int i = 0; i < entry.getValue().length; i++) {
				// Mostramos valor (Lugares de cada pais)
				resultado.append(i + " = ").append(entry.getValue()[i]).append( "; ");
				}
		}
		resultado.append("\n").append("\n").append(
				"***************************************************************************************************************************")
				.append("\n");

	
	

		return resultado.toString();
	}
	
	String[] mostrarPaisesParticpantes() {
		return this.paisesParticipantes;
	}


	// Este metodo esta implementado en los 3 album, que devuelve el nombre de cada
	// uno.
	protected abstract String nombre();

	protected void usarCodigo() {
	}


	protected boolean codigoUsado() {
		return false;
	}

	protected abstract String darPremio();

	protected abstract List<Figurita> pegadas();
}
