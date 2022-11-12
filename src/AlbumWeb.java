import java.util.List;

public class AlbumWeb extends Album{
	int codigoPromocional;

	public AlbumWeb(Integer lugaresPorPais, String[] paisesParticipantes, int codigo) {
		super(lugaresPorPais, paisesParticipantes, codigo);
		this.codigoPromocional=codigo;
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public String nombre() {
		return "Web";
	}
	@Override 
	public void usarCodigo() {
		this.codigoPromocional=0;
	}
	@Override
	public boolean codigoUsado() {
		return codigoPromocional==0;
	}
	@Override
	protected String darPremio() {
		return "Camiseta oficial de la seleccion";
	}
	@Override
	protected List<Figurita> pegadas() {
		// TODO Auto-generated method stub
		return null;
	}
		
	
}
