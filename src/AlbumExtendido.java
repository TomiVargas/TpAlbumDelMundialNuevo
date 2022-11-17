import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumExtendido extends Album {
	private String[] generarListadoDeMundiales;
	private Map<String, Figurita[]> seccionTop10;
	// Verificar estructura //
	private Map<String, String> paisesBalones;
	// OPCION2
	private List<String> balonOro;
	private List<String> balonPlata;

	private Figurita[] lugaresTOP10;

	// Se modifica Contructor para que contenga los lugares top10 <Pais ,
	// PaisesConBalon(2)>
	public AlbumExtendido(Integer lugaresPorPais, String[] paisesParticipantes, String[] generarListadoDeMundiales,
			Map<String, String[]> balonYPaisPorMundialTop10, int codigo) {

		super(lugaresPorPais, paisesParticipantes, codigo);
		this.generarListadoDeMundiales = generarListadoDeMundiales; // listado paises Argentina 86
		// this.paisesBalones=balonYPaisPorMundialTop102;
		this.paisesBalones = new HashMap<>();
		this.seccionTop10 = new HashMap<>();
		// this.lugaresTOP10 = new Figurita[2];
		for (Map.Entry<String, String[]> albumSeccionTop10 : balonYPaisPorMundialTop10.entrySet()) {
			for (int i = 0; i < generarListadoDeMundiales.length; i++) {
				this.lugaresTOP10 = new Figurita[2];
				if (albumSeccionTop10.getKey() == generarListadoDeMundiales[i]) {

					seccionTop10.put(albumSeccionTop10.getKey(), lugaresTOP10);

				}
			}

			paisesBalones.put(albumSeccionTop10.getKey(), albumSeccionTop10.getValue()[0]);
			paisesBalones.put(albumSeccionTop10.getKey(), albumSeccionTop10.getValue()[1]);
		}

		// OPCION 2
		for (int i = 0; i < generarListadoDeMundiales.length; i++) {
			this.lugaresTOP10 = new Figurita[2];
			// Crea el el Pais Sede con 2 lugares
			this.seccionTop10.put(generarListadoDeMundiales[i], lugaresTOP10);

			if (balonYPaisPorMundialTop10.containsKey(generarListadoDeMundiales[i])) {
				// 0 --> ORO
				// 1 --> PLATA
				String[] paises = balonYPaisPorMundialTop10.get(generarListadoDeMundiales[i]);
				for (int pais = 0; pais < paises.length; pais++) {
					if (pais == 0) {
						// Agrega a la coleccion de balonOro
						this.balonOro.add(paises[pais]);
					} else {
						// Agrega a la coleccion de balonPlata
						this.balonPlata.add(paises[pais]);
					}
				}
			}
		}
	}

	// Metodo a revisar
	@Override
	protected boolean pegarFigurita2(Figurita figus) {
		boolean sePego = true;

		if (figus == null) {
			return false;
		} else {
			for (Map.Entry<String, Figurita[]> mundial : seccionTop10.entrySet()) {
				if (buscarPaisDeLaFigurita(figus) != -1) {
					mundial.getValue()[buscarPaisDeLaFigurita(figus)] = figus;
					sePego = sePego && true;
				}

			}
		}
		return sePego;
	}

	private int buscarPaisDeLaFigurita(Figurita figus) {
		int contador = 0;
		for (Map.Entry<String, String> paisConBalon : paisesBalones.entrySet()) {
			if (paisConBalon.getValue() == figus.mostrarPais()) {
				return contador;
			} else {
				contador++;
			}
			if (contador == 2) {
				contador = 0;
			}
		}
		return -1;
	}

	@Override
	public String nombre() {
		return "Extendido";
	}

	@Override
	public String toString(String tipoAlbum) {
		StringBuilder resultado = new StringBuilder();
		resultado.append("*********************").append("\n");
		resultado.append("* ALBUM ").append(tipoAlbum.toUpperCase()).append(" *").append("\n");
		resultado.append("*********************").append("\n").append("\n");
		// Recorremos en Album
		for (Map.Entry<String, Figurita[]> entry : seccionTop10.entrySet()) {

			// Mostramos la clave (Nombre del pais participante)

			resultado.append(entry.getKey());
			resultado.append("\n");

			resultado.append("   Figuritas : ").append("\n");

			for (int i = 0; i < entry.getValue().length; i++) {
				// Mostramos valor (Lugares de cada pais)
				resultado.append(i + " = ").append(entry.getValue()[i]).append("; ").append("\n");
			}
		}
		resultado.append("\n").append("\n").append(
				"***************************************************************************************************************************")
				.append("\n");

		return resultado.toString();
	}

}
