package mes;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {

	public static void main ( String[] args ) throws FileNotFoundException {

		GlobalData gd = GlobalData.getInstance();
		Grid grid = Grid.getInstance();
		Fourier fourier = new Fourier();
		double[] t;																			//wektor temperatur, który mamy policzyć
		int timeStop = ( int ) ( gd.getSimulationTime() / gd.getSimulationStepTime() );		//ilość iteracji - czyli czas procesu dzielimy przez krok czasowy

		System.out.println( "Time\tMinTemp\t\tMaxTemp\n" );
		for ( int i = 0; i < timeStop; i++ ) {
			fourier.compute();
			t = Gauss.gaussElimination( gd.getNh(), fourier.getH_global(), fourier.getP_global() );

			for ( int j = 0; j < gd.getNh(); j++ )
				grid.nodes[j].setT( t[j] );	//jak już policzymy wektor tremperatur to do każdego wezla wpisujemy odpowiednią temperaturę

			System.out.print( (i+1)*gd.getSimulationStepTime() + "\t" );
			System.out.printf( "%.3f\t\t", getMin( t ) );
			System.out.printf( "%.3f", getMax( t ) );
			System.out.println();
		}
		System.out.println("\n");

		//wyświetlenie temperatur
		int iterator = 0;
		for ( int i = 0; i < gd.getnB(); i++ ) {
			for ( int j = 0; j < gd.getnH(); j++ )
				System.out.printf( "%.3f\t\t", grid.nodes[iterator++].getT() );
			System.out.println();
		}
	}

	public static double getMin ( double[] t ){
		return Arrays.stream( t ).min().getAsDouble();
	}

	public static double getMax ( double[] t ){
		return Arrays.stream( t ).max().getAsDouble();
	}

}
