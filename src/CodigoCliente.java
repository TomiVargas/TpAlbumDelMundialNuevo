import java.util.List;


public class CodigoCliente {

	public static void main(String[] args) {

		AlbumDelMundial sistema = new AlbumDelMundial();
		
		
		System.out.println("Codigo Album : " + sistema.registrarParticipante(222222, "Christian", "Tradicional"));
		System.out.println("Codigo Album : " + sistema.registrarParticipante(333333, "Mariana", "Extendido"));
		System.out.println(sistema.darNombre(333333));
		//sistema.registrarParticipante(333333, "Mariana", "Extendido");
		sistema.registrarParticipante(111111, "Jose", "Web");
		sistema.registrarParticipante(555555, "Miguel", "Web");
		sistema.registrarParticipante(666666, "Jazzmine", "Extendido");
		sistema.registrarParticipante(777777, "Dante", "Tradicional");
		
		//System.out.println("Figuritas :"+sistema.comprarFiguritas2(222222));
		// El participante 111111 tiene album Web entonces tiene un codigo 
		// promocional para solicitar 4 figuritas sin consto.
		sistema.comprarFiguritasConCodigoPromocional(111111);
		sistema.comprarFiguritas(222222);
		sistema.comprarFiguritas(333333);
		
		//Mostrar las figuritas test
		System.out.println("Figuritas :"+sistema.testComprarFigurita(222222));
		for(Figurita c: sistema.testComprarFigurita(222222)) {
		    System.out.println("Numero Del Jugador:"+ c.codigo() +" Pais: "+ c.mostrarPais());
		 }
		
		
		// El participante 333333 tiene un album tradicional y por eso puede 
		// participar en un sortepo por un premio instantaneo.
		//System.out.println(
			//	sistema.darNombre(222222) + 
			//	" recibio por sorteo instantaneo: " + 
			//	sistema.aplicarSorteoInstantaneo(222222)
		//	);
		//System.out.println(
			//	sistema.darNombre(333333) + 
			//	" recibio por sorteo instantaneo: " + 
			//	sistema.aplicarSorteoInstantaneo(333333)
		//	);
		
		
		List<String> pegadas = sistema.pegarFiguritas(222222);
		System.out.println(sistema.pegarFiguritas(222222));
		if(pegadas.isEmpty()) { //o sea... no pego ninguna
			sistema.comprarFiguritas(222222);
			sistema.intercambiar(
					222222,
					sistema.buscarFiguritaRepetida(222222)
				);
		}
		
		sistema.pegarFiguritas(333333);
		
		// Simulamos un uso prolongado del sistema.
		for (int i =0;i<2000;i++) {
			sistema.comprarFiguritas(222222);
			sistema.pegarFiguritas(222222);
			sistema.comprarFiguritas(555555);
			sistema.pegarFiguritas(555555);
		}
		for (int i =0;i<500;i++) {
			sistema.comprarFiguritas(666666);
			sistema.pegarFiguritas(666666);
			sistema.comprarFiguritas(777777);
			sistema.pegarFiguritas(777777);
		}
		
		if(sistema.llenoAlbum(222222)) {
			System.out.println(
					sistema.darNombre(222222) + 
					" recibio: " + 
					sistema.darPremio(222222)
				);
			System.out.println();
		}
		
		
		System.out.println("Llenaron album:");
		System.out.println(sistema.listadoDeGanadores());
		System.out.println();
		
		System.out.println("Participantes que Llenaron el Pais Argentina:");
		for (String item: sistema.participantesQueCompletaronElPais("Argentina"))
			System.out.println(item);
		
		
		System.out.println();
		System.out.println("=================================================");
		System.out.println(sistema);
	}

}
