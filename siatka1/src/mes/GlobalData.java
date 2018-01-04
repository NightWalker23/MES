package mes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GlobalData {

	private final double H;		//wysokość
	private final double B;		//szerokość
	private final int nH;		//liczba węzłów po wysokości
	private final int nB;		//liczba węzłów po szerokości
	private final double dB;	//liczba elementów po szerokości
	private final double dH;	//liczba elementów po wysokości
	private final int nh;		//liczba węzłów
	private final int ne;		//liczba elementów

	private final double initialTemperature;			//temperatura początkowa
	private final double simulationTime;				//czas procesu
	private final double simulationStepTime;			//początkowa wartość przyrostu czasu
	private final double ambientTemperature;			//temperatura otoczenia
	private final double alfa;							//wspołczynnik wymiany ciepła
	private final double specificHeat;					//ciepło właściwe
	private final double conductivity;					//współczynnik przewodzenia ciepła
	private final double density;						//gęstość

	private static GlobalData gd = null;

	private GlobalData ( String name ) throws FileNotFoundException {

		Scanner input = new Scanner( new File( name ) );
		this.H = input.nextDouble();
		input.nextLine();
		this.B = input.nextDouble();
		input.nextLine();
		this.nH = input.nextInt();
		input.nextLine();
		this.nB = input.nextInt();
		input.nextLine();
		this.initialTemperature = input.nextDouble();
		input.nextLine();
		this.simulationTime = input.nextDouble();
		input.nextLine();
		this.simulationStepTime = input.nextDouble();
		input.nextLine();
		this.ambientTemperature = input.nextDouble();
		input.nextLine();
		this.alfa = input.nextDouble();
		input.nextLine();
		this.specificHeat = input.nextDouble();
		input.nextLine();
		this.conductivity = input.nextDouble();
		input.nextLine();
		this.density = input.nextDouble();
		input.close();

		dB = B / ( nB - 1 );
		dH = H / ( nH - 1 );

		ne = ( nH - 1 ) * ( nB - 1 );
		nh = nH * nB;
	}

	public static GlobalData getInstance () throws FileNotFoundException {
		if ( gd == null )
			gd = new GlobalData( "data.txt" );
		return gd;
	}


	public double getH () {
		return H;
	}

	public double getB () {
		return B;
	}

	public int getnH () {
		return nH;
	}

	public int getnB () {
		return nB;
	}

	public double getdB () {
		return dB;
	}

	public double getdH () {
		return dH;
	}

	public int getNh () {
		return nh;
	}

	public int getNe () {
		return ne;
	}

	public double getInitialTemperature () {
		return initialTemperature;
	}

	public double getSimulationTime () {
		return simulationTime;
	}

	public double getSimulationStepTime () {
		return simulationStepTime;
	}

	public double getAmbientTemperature () {
		return ambientTemperature;
	}

	public double getAlfa () {
		return alfa;
	}

	public double getSpecificHeat () {
		return specificHeat;
	}

	public double getConductivity () {
		return conductivity;
	}

	public double getDensity () {
		return density;
	}

}
