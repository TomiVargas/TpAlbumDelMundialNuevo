import java.util.HashMap;
import java.util.Map;

public class AlbumExtendido extends Album{
	private String[] generarListadoDeMundiales;
	private Map<String, String[]> balonYPaisPorMundialTop10;
	
	// Se modifica Contructor para que contenga los lugares top10 <Pais , PaisesConBalon(2)>
	public AlbumExtendido(Integer lugaresPorPais, String[] paisesParticipantes, String[] generarListadoDeMundiales,
			Map<String, String[]> balonYPaisPorMundialTop102,int  codigo) {
		
		super(lugaresPorPais, paisesParticipantes, codigo);
		this.generarListadoDeMundiales=generarListadoDeMundiales;
		this.balonYPaisPorMundialTop10 = new HashMap<>();
		String[]lugaresTOP10 = new String[2];
		for (int i = 0; i < generarListadoDeMundiales.length; i++) {
			balonYPaisPorMundialTop10.put(generarListadoDeMundiales[i], lugaresTOP10 );
		}
	}
	
	
	
	@Override
	public String nombre() {
		return "Extendido";
	}

	@Override
	protected String darPremio() {
		return "Una pelota y un viaje";
	}
}
