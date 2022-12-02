import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumExtendido extends AlbumTradicional {
	private Map<String, Figurita[]> seccionTop10;
	private List<String> balonOro;
	private Figurita[] lugaresTOP10;

	// Se modifica Contructor para que contenga los lugares top10 <Pais ,
	// PaisesConBalon(2)>
	public AlbumExtendido(Integer lugaresPorPais, String[] paisesParticipantes, String[] generarListadoDeMundiales,
			Map<String, String[]> balonYPaisPorMundialTop10, int codigo) {

		super(lugaresPorPais, paisesParticipantes, codigo);
		this.seccionTop10 = new HashMap<>();
		this.balonOro= new ArrayList<String>();


		for (int i = 0; i < generarListadoDeMundiales.length; i++) {
			this.lugaresTOP10 = new FiguritaTOP10[2];
			// Crea el el Pais Sede con 2 lugares
			this.seccionTop10.put(generarListadoDeMundiales[i], lugaresTOP10);

			if (balonYPaisPorMundialTop10.containsKey(generarListadoDeMundiales[i])) {
				// 0 --> ORO
				// 1 --> PLATA
				String[] paises = balonYPaisPorMundialTop10.get(generarListadoDeMundiales[i]);
				this.balonOro.add(paises[0]);
					}
				}
			}
	
	@Override //instanceOf o getClass
	protected boolean pegarFigurita(Figurita figus) {
		if (figus == null) {
			return false;
		}
		for (Map.Entry<String, Figurita[]> mundial : seccionTop10.entrySet()) {
			if (mundial.getKey()==figus.mostrarSede()) {
				pegarFigu(figus,mundial.getKey());
				agregarAPegadas(figus);
				return true;	
				}
			}
		return false;
	}

	private void pegarFigu(Figurita figus,String sede) {
		for(int i=0; i<balonOro.size();i++) {
			if(balonOro.get(i)==figus.mostrarPais()) {
				seccionTop10.get(sede)[0]=figus;
			}else {
				seccionTop10.get(sede)[1]=figus;
			}
		}
	}
	
	@Override
	public boolean albumLleno() {
		boolean lleno =true;
		for(Figurita[] figuritas: seccionTop10.values()) {
			lleno = lleno && estaLleno(figuritas);
		}
		return lleno;
	}
	

	private boolean estaLleno(Figurita[] figuritas) {
		boolean lleno=true;
		for(int i=0;i<figuritas.length;i++) {
			lleno=lleno && figuritas[i]!=null;
		}
		return lleno;

	}

	@Override
	public String nombre() {
		return "Extendido";
	}
	
	@Override
	public int codigo() {
		return super.codigo();
	}

}
