import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FiguritaTradicional extends Figurita {
	FiguritaTradicional figurita  ;
	Fabrica fabrica;

	FiguritaTradicional(int numeroIdentificador, String nombrejugador) {
		super(numeroIdentificador, nombrejugador);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String mostrarNombreJugador() {
		// TODO Auto-generated method stub
		return super.mostrarNombreJugador();
	}
	@Override
	public String mostrarPais() {
		// TODO Auto-generated method stub
		return super.mostrarPais();
	}
	
	
	List<Figurita> generarSobre(int cantidadFiguritas){
		cantidadFiguritas=4;
		this.sobre = new LinkedList<Figurita>();
		for(int cantidad =0 ; cantidad < cantidadFiguritas ; cantidad++) {
			sobre.add(conseguirFigurita());	
		}
		return sobre;
	}
	
	public FiguritaTradicional conseguirFigurita( ) {
		for (Map.Entry<Integer, String> f : figuritas.entrySet()) {
			for(int i=0; i < f.getValue().length() ; i++) { 
					figurita = new FiguritaTradicional(f.getKey(),f.getValue());
			}
		}
		return figurita;
	}
	@Override
	public Integer codigo() {
		// TODO Auto-generated method stub
		return super.codigo();
	}
	

	

}
