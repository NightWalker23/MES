package mes;

import java.io.*;
import java.util.Arrays;

public class Main {

	public static void main ( String[] args ) throws IOException {

		GlobalData gd = GlobalData.getInstance();
		Grid grid = Grid.getInstance();
		Fourier fourier = new Fourier();
		StringBuilder sb = new StringBuilder();

		double[] t;                                                                            //wektor temperatur, który mamy policzyć
		int timeStop = ( int ) ( gd.getSimulationTime() / gd.getSimulationStepTime() );        //ilość iteracji - czyli czas procesu dzielimy przez krok czasowy

		for ( int i = 0; i < timeStop; i++ ) {
			fourier.solve();
			t = Solver.solveGaussElimination( gd.getNh(), fourier.getH_global(), fourier.getP_global() );

			for ( int j = 0; j < gd.getNh(); j++ )
				grid.nodes[j].setT( t[j] );    //jak już policzymy wektor tremperatur to do każdego wezla wpisujemy odpowiednią temperaturę

			//uśrednienie temperatury powietrza między szybami po każdej iteracji
			double suma = 0.0, srednia;
			for ( int j = 20; j <62; j++ )
				suma += grid.nodes[j].getT();
			srednia = suma/42.0;

			for ( int j = 20; j < 62; j++ )
				grid.nodes[j].setT( srednia );

			if ( i%(timeStop/10) == 0 ) {
				int iterator = 0;
				for ( int k = 0; k < gd.getnB(); k++ ) {
					for ( int j = 0; j < gd.getnH(); j++ ) {
						sb.append( String.format( "%.3f", grid.nodes[iterator].getT() ) + "\t" );
						iterator++;
					}
					sb.append( "\n" );
				}
				sb.append( "\n\n" );
			}
		}



		//wyświetlenie temperatur
//		int iterator = 0;
//		for ( int i = 0; i < gd.getnB(); i++ ) {
//			for ( int j = 0; j < gd.getnH(); j++ ) {
//				System.out.printf( "%.3f\t", grid.nodes[iterator].getT() );
//				sb.append( String.format( "%.3f", grid.nodes[iterator].getT() ) + "\t" );
//				iterator++;
//			}
//			sb.append( "\n" );
//			System.out.println();
//		}

		File file = new File("result.txt");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(sb.toString());
		}

	}

}
