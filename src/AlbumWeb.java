import java.util.List;

public class AlbumWeb extends Album{
	private int codigoPromocional;

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
	
		
	
}
