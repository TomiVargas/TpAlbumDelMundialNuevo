
public class AlbumTradicional extends Album{
	

	public AlbumTradicional(Integer lugaresPorPais, String[] paisesParticipantes, int codigo) {
		super(lugaresPorPais, paisesParticipantes, codigo);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String nombre() {
		return "Tradicional";
	}

	

}
