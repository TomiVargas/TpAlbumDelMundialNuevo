
import java.util.HashMap;
import java.util.Map;

public abstract class Album {
	private int codigo;
	private Map<String, Figurita[]> album;
	protected String[] paisesParticipantes;

	private Figurita[] lugares;
	public Album(Integer lugaresPorPais, String[] paisesParticipantes, int codigo) {
		this.codigo = codigo;
		this.paisesParticipantes = paisesParticipantes;
		this.album = new HashMap<String, Figurita[]>();

		this.lugares = new Figurita[lugaresPorPais];

		for (int pais = 0; pais < paisesParticipantes.length; pais++) {
			album.put(paisesParticipantes[pais], lugares);
		}
	}

	public int codigo() {
		return this.codigo;
	}


	boolean pegarFigurita2(Figurita figus) {
		if(!estaPegada(figus)) {
			album.get(figus.mostrarPais())[figus.codigo()]=figus;
				return true;
		}
			return false;
		}

	protected boolean estaPegada(Figurita figu) {
		if(album.get(figu.mostrarPais())[figu.codigo()]==figu){
					return true;
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
	public String toString() {
		StringBuilder resultado = new StringBuilder();
		resultado.append("*********************").append("\n");
		resultado.append("* ALBUM TRADICIONAL *").append("\n");
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
	


	// Este metodo esta implementado en los 3 album, que devuelve el nombre de cada
	// uno.
	protected abstract String nombre();

	protected void usarCodigo() {
	}


	protected boolean codigoUsado() {
		return false;
	}

	protected abstract String darPremio();
}
