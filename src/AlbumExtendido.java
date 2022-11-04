import java.util.HashMap;
import java.util.Map;

public class AlbumExtendido extends Album{
	private String[] listadoDeMundialesTop10;
	private Map<String, String[]> balonYPaisPorMundialTop10;
	
	public AlbumExtendido(Integer lugaresPorPais, String[] paisesParticipantes, String[] listadoDeMundialesTop10, Map<String, String[]> balonYPaisPorMundialTop102,int  codigo) {
		super(lugaresPorPais, paisesParticipantes, codigo);
		this.listadoDeMundialesTop10=listadoDeMundialesTop10;
		this.balonYPaisPorMundialTop10 = new HashMap<>();
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
