import java.util.Map;

public class FiguritaTOP10 extends Figurita{
	String[] listadoDeMundiales;
	Map<String, String[]> balonYPaisPorMundialTop10;

	FiguritaTOP10(int numeroIdentificador, String pais, String[] listadoDeMundiales, Map<String, String[]> balonYPaisPorMundialTop10) {
		super(numeroIdentificador, pais);
		this.listadoDeMundiales=listadoDeMundiales;
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

	

}
