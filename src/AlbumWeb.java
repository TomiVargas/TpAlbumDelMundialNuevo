
public class AlbumWeb extends Album{
	

	public AlbumWeb(Integer lugaresPorPais, String[] paisesParticipantes, int codigo) {
		super(lugaresPorPais, paisesParticipantes, codigo);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public String nombre() {
		return "Web";
	}
}
