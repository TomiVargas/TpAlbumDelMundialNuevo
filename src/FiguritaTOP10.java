import java.util.Map;

public class FiguritaTOP10 extends Figurita{
	Map<String, String[]> balonYPaisPorMundialTop10;
	protected boolean balonOro;
	protected boolean balonPlata;
	private String sede;
	// Modificar este para que tenga si fue balon de oro o plata y tenga la sede?
	FiguritaTOP10(int numeroIdentificador, String pais,String sede, Map<String, String[]> balonYPaisPorMundialTop10) {
			super(numeroIdentificador,pais);
			this.balonYPaisPorMundialTop10=balonYPaisPorMundialTop10;
			this.sede=sede;
			
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
