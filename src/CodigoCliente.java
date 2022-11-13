import java.util.List;


public class CodigoCliente {

	public static void main(String[] args) {

		AlbumDelMundial sistema = new AlbumDelMundial();
		
		
		sistema.registrarParticipante(222222, "Christian", "Tradicional");
		sistema.registrarParticipante(333333, "Mariana", "Extendido");
		sistema.registrarParticipante(111111, "Jose", "Web");
		sistema.registrarParticipante(555555, "Miguel", "Web");
		sistema.registrarParticipante(666666, "Jazzmine", "Extendido");
		sistema.registrarParticipante(777777, "Dante", "Tradicional");
		sistema.comprarFiguritas(111111);
		sistema.comprarFiguritasConCodigoPromocional(111111);
		sistema.comprarFiguritas(222222);
		sistema.comprarFiguritas(222222);
		sistema.comprarFiguritasConCodigoPromocional(555555);
		sistema.pegarFiguritas(111111);
		sistema.comprarFiguritasTop10(666666);
		sistema.pegarFiguritas(666666);
	
		
		
		// El participante 333333 tiene un album tradicional y por eso puede 
		// participar en un sortepo por un premio instantaneo.
		/*System.out.println(
				sistema.darNombre(222222) + 
				" recibio por sorteo instantaneo: " + 
			sistema.aplicarSorteoInstantaneo(222222)
			);
		System.out.println(
				sistema.darNombre(777777) + 
				" recibio por sorteo instantaneo: " + 
				sistema.aplicarSorteoInstantaneo(777777)
			);
		*/
		
		List<String> pegadas = sistema.pegarFiguritas(222222);

		System.out.println(pegadas);
		
		if(pegadas.isEmpty()) { //o sea... no pego ninguna
			sistema.comprarFiguritas(222222);
			sistema.intercambiar(
					222222,
					sistema.buscarFiguritaRepetida(222222)
				);
		}
		
		
		// Simulamos un uso prolongado del sistema.
		for (int i =0;i<100;i++) {
			sistema.comprarFiguritas(222222);
			sistema.pegarFiguritas(222222);
			sistema.comprarFiguritas(555555);
			sistema.pegarFiguritas(222222);
		}
	
	
		/*for (int i =0;i<1000;i++) {
			sistema.comprarFiguritas(666666);
			sistema.pegarFiguritas(222222);
			sistema.comprarFiguritas(777777);
			sistema.pegarFiguritas(777777);
		}*/
		
			

		
		if(sistema.llenoAlbum(333333)) {

			System.out.println(
					sistema.darNombre(333333) + 
					" recibio: " + 
					sistema.darPremio(333333)
				);
			System.out.println();
		}
		
		sistema.toString();
		System.out.println("Llenaron album:");
		System.out.println(sistema.listadoDeGanadores());
		System.out.println();
		System.out.println();
		System.out.println("Participantes que Llenaron el Pais Argentina:" +sistema.participantesQueCompletaronElPais("Argentina"));
		
		
		System.out.println();
		System.out.println("=================================================");
		System.out.println(sistema);
		System.out.println(sistema.mostrarEstadoAlbumParticipante(222222));
		
	}
	}


