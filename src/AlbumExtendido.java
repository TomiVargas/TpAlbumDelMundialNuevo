import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumExtendido extends Album{
	private String[] generarListadoDeMundiales;
	private Map<String, Figurita[]> seccionTop10;
	private Map<String,String> paisesBalones;
	private Figurita[] lugaresTOP10;
	
	// Se modifica Contructor para que contenga los lugares top10 <Pais , PaisesConBalon(2)>
	public AlbumExtendido(Integer lugaresPorPais, String[] paisesParticipantes, String[] generarListadoDeMundiales,
			Map<String, String[]> balonYPaisPorMundialTop102,int  codigo) {
		
		super(lugaresPorPais, paisesParticipantes, codigo);
		this.generarListadoDeMundiales=generarListadoDeMundiales;
		//this.paisesBalones=balonYPaisPorMundialTop102;
		this.paisesBalones= new HashMap<>();
		this.seccionTop10 = new HashMap<>();
		//this.lugaresTOP10 = new Figurita[2];
		for(Map.Entry<String , String[]> albumSeccionTop10: balonYPaisPorMundialTop102.entrySet()) {
			for (int i = 0; i < generarListadoDeMundiales.length; i++) {
				this.lugaresTOP10 = new Figurita[2];
				if(albumSeccionTop10.getKey()==generarListadoDeMundiales[i]) {
					
					seccionTop10.put(albumSeccionTop10.getKey(), lugaresTOP10);
					
				}
			}

			paisesBalones.put(albumSeccionTop10.getKey(), albumSeccionTop10.getValue()[0]);
			paisesBalones.put(albumSeccionTop10.getKey(), albumSeccionTop10.getValue()[1]);
		}
	}
	
	//Metodo a revisar
	@Override  
	protected boolean pegarFigurita2(Figurita figus) {
		boolean sePego=true;
		
		if(figus==null) {
				sePego=sePego&&false;
		}else {
			for(Map.Entry<String, Figurita[]> mundial : seccionTop10.entrySet()) {
				if( buscarPaisDeLaFigurita(figus)!=-1){
					mundial.getValue()[buscarPaisDeLaFigurita(figus)]=figus;
					sePego=sePego&&true;
				}		
				
			}
		}
		return sePego;
	}
		private int buscarPaisDeLaFigurita(Figurita figus) {
			int contador=0;
			for(Map.Entry<String, String> paisConBalon : paisesBalones.entrySet()) {
				if(paisConBalon.getValue()==figus.mostrarPais()) {
					return contador;
				}else {
					contador++;
				}
				if(contador==2) {
					contador=0;
				}
			}
			return -1;
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
		for (Map.Entry<String, Figurita[]> entry : seccionTop10.entrySet()) {

			// Mostramos la clave (Nombre del pais participante)
				
			resultado.append(entry.getKey());
				resultado.append("\n");
			
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
