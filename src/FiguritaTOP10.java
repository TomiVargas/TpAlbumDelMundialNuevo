import java.util.Map;

public class FiguritaTOP10 extends Figurita {
	private boolean balonOro;
	private String sede;

	FiguritaTOP10(int numeroIdentificador, String pais, String sede,
			Map<String, String[]> balonYPaisPorMundialTop10,int valorBase) {
		
		super(numeroIdentificador, pais,valorBase);
		this.sede = sede;

		// El indice 0 --> ORO
		// El indice 1 --> PLATA
		if (balonYPaisPorMundialTop10.containsKey(sede)) {
			String[] paises = balonYPaisPorMundialTop10.get(sede);
			for (int i = 0; i < paises.length; i++) {
				if (this.mostrarPais()==paises[0]) {
					this.setBalonOro(true);
				}
			}
		}
	}
	@Override
	public int getCodigo() {
		return super.getCodigo();
	}
	@Override
	public String mostrarSede() {
		return this.sede;
	}

	@Override
	public String mostrarPais() {
		return super.mostrarPais();
	}

	@Override
	public Integer numeroIdentificador() {
		return super.numeroIdentificador();
	}

	public boolean esBalonOro() {
		return balonOro;
	}

	public void setBalonOro(boolean balonOro) {
		this.balonOro = balonOro;
	}


}
