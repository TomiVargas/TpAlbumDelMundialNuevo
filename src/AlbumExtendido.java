import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumExtendido extends Album{
	private String[] generarListadoDeMundiales;
	private Map<String[], Figurita[]> balonYPaisPorMundialTop10;
	
	// Se modifica Contructor para que contenga los lugares top10 <Pais , PaisesConBalon(2)>
	public AlbumExtendido(Integer lugaresPorPais, String[] paisesParticipantes, String[] generarListadoDeMundiales,
			Map<String, String[]> balonYPaisPorMundialTop102,int  codigo) {
		
		super(lugaresPorPais, paisesParticipantes, codigo);
		this.generarListadoDeMundiales=generarListadoDeMundiales;
		this.balonYPaisPorMundialTop10 = new HashMap<>();
		Figurita[]lugaresTOP10 = new Figurita[2];
		for(Map.Entry<String, String[]> mundial : balonYPaisPorMundialTop102.entrySet()) {
			for (int i = 0; i < 10; i++) {
				balonYPaisPorMundialTop10.put(mundial.getValue(), lugaresTOP10 );
			}
		}
		
	}
	
	//Se fija si existe el pais en el map y si existe, agrega la figu.
	@Override  
	protected boolean pegarFigurita2(Figurita figus) {
		boolean sePego=true;
		if(estaPegada(figus) || figus==null) {
				sePego=sePego&&false;
		}else {
			for(Map.Entry<String[], Figurita[]> mundial : balonYPaisPorMundialTop10.entrySet()) {
				for(int i=0;i<2;i++) {
					if(mundial.getKey()[i]==figus.mostrarPais()) {
						if(mundial.getValue()[i]==null) {
							mundial.getValue()[i]=figus;
						sePego=sePego&&true;
					}
				}
			}
			}
		}
		return sePego;
	}
	
	@Override
	public String nombre() {
		return "Extendido";
	}

	@Override
	protected String darPremio() {
		return "Una pelota y un viaje";
	}
	@Override 
	public String toString(String tipoAlbum) {
		StringBuilder resultado = new StringBuilder();
		resultado.append("*********************").append("\n");
		resultado.append("* ALBUM ").append(tipoAlbum.toUpperCase()).append(" *").append("\n");
		resultado.append("*********************").append("\n").append("\n");
		// Recorremos en Album
		for (Map.Entry<String[], Figurita[]> entry : balonYPaisPorMundialTop10.entrySet()) {

			// Mostramos la clave (Nombre del pais participante)
			for(int j=0; j<entry.getKey().length;j++) {
				resultado.append(entry.getKey()[j]);
				resultado.append("\n");
			}
			resultado.append("   Figuritas : ").append("\n");
			for (int i = 0; i < entry.getValue().length; i++) {
				// Mostramos valor (Lugares de cada pais)
				resultado.append(i + " = ").append(entry.getValue()[i]).append( "; ").append("\n");
				}
		}
		resultado.append("\n").append("\n").append(
				"***************************************************************************************************************************")
				.append("\n");

	
	

		return resultado.toString();
	}

	@Override
	protected List<Figurita> pegadas() {
		// TODO Auto-generated method stub
		return null;
	}
}
