import java.util.Map;

public class FiguritaTOP10 extends Figurita{
	Map<String, String[]> balonYPaisPorMundialTop10;

	// Se modifica constructor
	FiguritaTOP10(int numeroIdentificador, String pais, Map<String, String[]> balonYPaisPorMundialTop10) {
		
		super(numeroIdentificador, pais);
		
		this.balonYPaisPorMundialTop10=balonYPaisPorMundialTop10;
		
	}
	
	@Override
	public String mostrarPais() {
		// TODO Auto-generated method stub
		return super.mostrarPais();
	}
	
	@Override
	public Integer codigo() {
		// TODO Auto-generated method stub
		return super.codigo();
	}
	
	// Se agrega verificacion pais top10
	public boolean pais(String pais) {
		return balonYPaisPorMundialTop10.containsKey(pais);
	}

	

}
