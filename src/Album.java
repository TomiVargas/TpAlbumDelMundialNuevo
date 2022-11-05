import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Album {
	private int codigo;
	private Map<String, Figurita[]> album;
	protected String[] paisesParticipantes;

	public Album(Integer lugaresPorPais, String[] paisesParticipantes, int codigo) {
		this.codigo = codigo;
		this.paisesParticipantes = paisesParticipantes;
		Figurita[] lugares = new Figurita[12];
		this.album = new HashMap<String, Figurita[]>();

		for (int pais = 0; pais < paisesParticipantes.length; pais++) {
			album.put(paisesParticipantes[pais], lugares);
		}
	}

	public int codigo() {
		return this.codigo;
	}

	public void pegarFigurita(Figurita figu) {
		for (Map.Entry<String, Figurita[]> album : album.entrySet()) {
			if (album.getKey().equals(figu.mostrarPais())) {
				for (int i = 0; i < album.getValue().length; i++) {
					if (figu.codigo() == i) {
						if (album.getValue() == null) {
							album.getValue()[i] = figu;
						}
					}
				}

			}
		}
	}

	Figurita pegarFigurita2(Figurita figu) {
		for (Map.Entry<String, Figurita[]> a : album.entrySet()) {
			if (a.getKey().equals(figu.mostrarPais())) {
				for (int i = 0; i < a.getValue().length; i++) {
					if (figu.codigo() == i) {
						a.getValue()[i] = figu;
						return figu;
					}
				}

			}
		} return null;
	}

	/*
	 * public void pegarFigurita2(Figurita figu) { Figurita[]
	 * figus=album.get(figu.mostrarPais()); for (int i = 0; i < figus.length; i++) {
	 * if(!figu.equals(figus[i])) {
	 * 
	 * } } }
	 */

	protected boolean estaPegada(Figurita figu) {
		for (Map.Entry<String, Figurita[]> album : album.entrySet()) {
			for (int i = 0; i < album.getValue().length; i++) {
				if (album.getValue()[i] == figu) {
					return true;
				}
			}

		}
		return false;
	}

	public List<Figurita> figuritas() {
		List<Figurita> figus = new ArrayList<Figurita>();
		for (Map.Entry<String, Figurita[]> album : album.entrySet()) {
			// album.getValue().addAll(figus);
			for (int i = 0; i < album.getValue().length; i++) {
				figus.add(album.getValue()[i]);
			}
		}
		return figus;
	}

	/*
	 * public boolean albumLleno() { boolean estaLleno=true; for(Map.Entry<String,
	 * Figurita[]> album : album.entrySet()) { for(int
	 * i=0;i<paisesParticipantes.length;i++) {
	 * if(paisesParticipantes[i]==album.getKey()) { estaLleno=estaLleno &&
	 * paisCompleto(album.getValue()[i].mostrarPais()); } } } return estaLleno; }
	 */

	/*
	 * public boolean albumLleno2() { boolean lleno=true; for(Map.Entry<String,
	 * List<Figurita>> album : album.entrySet()) { lleno=lleno &&
	 * album.getValue().isEmpty(); } return lleno; }
	 * 
	 * 
	 * public boolean paisLleno(List<Figurita> figurita) { boolean lleno= true; for
	 * (int i = 0; i < figurita.size(); i++) { lleno= lleno &&
	 * figurita.get(i)!=null; } return lleno; }
	 */

	/*
	 * public boolean paisCompleto(String pais) { boolean estaLleno=true;
	 * for(Map.Entry<String, Figurita[]> album : album.entrySet()) {
	 * if(album.getKey()==pais) { for(int i=0;album.getValue().length;i++) {
	 * estaLleno=estaLleno && album.getValue()[i]!=null; } }
	 * 
	 * } return estaLleno; }
	 */

	/*
	 * protected List<Figurita> tamañoDeLaListaDeFiguritas(Figurita[] figu){
	 * List<Figurita> figus = new ArrayList<Figurita>();
	 * //album.getValue().addAll(figus); for(int i=0;i<figu.size();i++) {
	 * figus.add(figu.get(i)); } return figus; }
	 */

	// Este metodo esta implementado en los 3 album, que devuelve el nombre de cada
	// uno.
	protected abstract String nombre();

	protected void usarCodigo() {
	}

	protected boolean codigoUsado() {
		return false;
	}

	protected abstract String darPremio();

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
				resultado.append(i + " = " );
				if(estaPegada(entry.getValue()[i])) {
					resultado.append(i + "; ");
				}
			}
		}
		resultado.append("\n").append("\n").append(
				"***************************************************************************************************************************")
				.append("\n");

		return resultado.toString();
	}
}
