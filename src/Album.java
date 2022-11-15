
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Album {
	private int codigo;
	private Map<String, Figurita[]> album;
	private String[] paisesParticipantes;
	private List<Figurita> pegadas;
	private Figurita[] lugares;

	public Album(Integer lugaresPorPais, String[] paisesParticipantes, int codigo) {
		this.codigo = codigo;
		this.paisesParticipantes = paisesParticipantes;

		this.pegadas = new ArrayList<>();
		this.album = new HashMap<String, Figurita[]>();
		for (int pais = 0; pais < paisesParticipantes.length; pais++) {
			this.lugares = new Figurita[lugaresPorPais];
			album.put(paisesParticipantes[pais], lugares);
		}
	}

	public int codigo() {
		return this.codigo;
	}

	protected boolean pegarFigurita2(Figurita figus) {
		if (pegada(figus.mostrarPais(), figus.codigo()) || figus == null ) {
			return false;
		} else{
			agregarAPegadas(figus);
			album.get(figus.mostrarPais())[figus.codigo()] = figus;
		}
			return false;
		
	}
	boolean pegada(String mostrarPais, Integer codigo2) {
		for (int i = 0; i < pegadas.size(); i++) {
			if(pegadas.get(i).mostrarPais() == mostrarPais && 
					pegadas.get(i).codigo()== codigo2) {
					return true;
				}
			}
		return false;
	} 	// Nuevo metodo
	protected void agregarAPegadas(Figurita figurita) {
		this.pegadas.add(figurita);
	}
	// Nuevo metodo
	protected List<Figurita> mostrarPegadas() {
		return this.pegadas;
	}

	public boolean albumLleno() {
		boolean lleno = true;
		for (Map.Entry<String, Figurita[]> a : album.entrySet()) {
			lleno = lleno && paisEstaLleno(a.getValue());
		}
		return lleno;
	}

	private boolean paisEstaLleno(Figurita[] figurita) {
		boolean lleno = true;
		for (int posicion = 0; posicion < figurita.length; posicion++) {
			lleno = lleno && figurita[posicion] != null;
		}
		return lleno;
	}

	public boolean paisLleno(String pais) {
		boolean lleno = true;
		for (Map.Entry<String, Figurita[]> album : album.entrySet()) {
			if (album.getKey()==pais) {
				lleno = lleno && paisEstaLleno(album.getValue());
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
				if(entry.getValue()[i]!=null) {
					resultado.append(i + " = ").append(entry.getValue()[i].mostrarPais()).append("; ");
			}else {
				resultado.append(i + " = ").append(entry.getValue()[i]).append("; ");
			}
		}
		resultado.append("\n").append("\n").append(
				"***************************************************************************************************************************")
				.append("\n");

		
	}
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


	protected abstract List<Figurita> pegadas();
}
