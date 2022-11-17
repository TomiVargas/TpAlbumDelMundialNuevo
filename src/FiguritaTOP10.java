import java.util.Map;

public class FiguritaTOP10 extends Figurita {
	private Map<String, String[]> balonYPaisPorMundialTop10;
	protected String balonOro;
	protected String balonPlata;
	private String sede;

	FiguritaTOP10(int numeroIdentificador, String pais, String[] paisesParticipantes, String sede,
			Map<String, String[]> balonYPaisPorMundialTop10) {
		
		super(numeroIdentificador, pais, paisesParticipantes);
		this.balonYPaisPorMundialTop10 = balonYPaisPorMundialTop10;
		this.sede = sede;

		// El indice 0 --> ORO
		// El indice 1 --> PLATA
		if (balonYPaisPorMundialTop10.containsKey(sede)) {
			String[] paises = balonYPaisPorMundialTop10.get(sede);
			for (int i = 0; i < paises.length; i++) {
				if (i == 0) {
					this.balonOro = paises[i];
				} else {
					this.balonPlata = paises[i];
				}
			}
		}
	}

	boolean balonOro(String pais) {
		return pais == balonOro ? true : false;
	}

	boolean balonPlata(String pais) {
		return pais == balonPlata ? true : false;
	}

	@Override
	public String mostrarPais() {
		return super.mostrarPais();
	}

	@Override
	public Integer codigo() {
		return super.codigo();
	}

	// Se agrega verificacion pais top10
	public boolean pais(String pais) {
		return balonYPaisPorMundialTop10.containsKey(pais);
	}

}
