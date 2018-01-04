package mes;

import element.ElementLokal;
import java.io.FileNotFoundException;

public class Fourier {

	private double[][] H_current;	//obecna macierz H
	private double[] P_current;		//obecny wektor P
	private double[][] H_global;	//globalna macierz H
	private double[] P_global;		//globalny wektor P

	private GlobalData gd;
	private ElementLokal el;

	public Fourier () throws FileNotFoundException {
		gd = GlobalData.getInstance();
		el = ElementLokal.getInstance();

		H_current = new double[4][4];
		P_current = new double[4];
		H_global = new double[gd.getNh()][gd.getNh()];
		P_global = new double[gd.getNh()];
	}

	public void compute () throws FileNotFoundException {

		//wyzerowanie
		for ( int i = 0; i < gd.getNh(); i++ ) {
			for ( int j = 0; j < gd.getNh(); j++ )
				H_global[i][j] = 0;

			P_global[i] = 0;
		}

		//pobieramy saitkę i jakobian
		Grid grid = Grid.getInstance();
		Jakobian jakobian;

		double[] dNdx = new double[4];		//wektor pochodnych funkcji kształtu po x
		double[] dNdy = new double[4];		//wektor pochodnych funkcji kształtu po y
		double[] x = new double[4];			//współrzędne x-ksowe węzłów z elementu
		double[] y = new double[4];			//współrzędne y-kowe węzłów z elementu
		double[] temp_el = new double[4];	//wektor temperatur z węzłów elementu
		double temp_interpol;				//temperatura zinterpolowana z rogu elementu do punktu całkowania
		double cellFromC;					//wartość aktualnie potrzebnej wartości z macierzy C
		int id; 							//wyciągamy id noda globalnego
		double detj = 0; 					//zerujemy wyznacznik

		//lecimy po wszystkich elementach w siatce
		for ( int el_nr = 0; el_nr < gd.getNe(); el_nr++ ) {

			//zerowanie macierzy dla obecnego elementu
			for ( int i = 0; i < 4; i++ ) {
				for ( int j = 0; j < 4; j++ )
					H_current[i][j] = 0;

				P_current[i] = 0;
			}

			//wyciągamy dane z elementu z siatki
			for ( int i = 0; i < 4; i++ ) {
				id = grid.elements[el_nr].globalNodeID[i];
				x[i] = grid.nodes[id].getX();
				y[i] = grid.nodes[id].getY();
				temp_el[i] = grid.nodes[id].getT();
			}

			//dla sposobu dwupunktowego
			for ( int pc = 0; pc < 4; pc++ ) { // 4 - liczba punktow calkowania po powierzchni w elemencie
				jakobian = new Jakobian( x, y, pc ); //jakobian dla danego punktu całkowania
				temp_interpol = 0;

				//pętla po węzłach
				for ( int j = 0; j < 4; j++ ) { // 4 - liczba wezlow w wykorzystywanym elemencie skonczonym
					dNdx[j] = 1.0 / jakobian.getDetJ() * ( jakobian.getJ_odw()[0][0] * el.getdN_po_ksi()[pc][j] + jakobian.getJ_odw()[0][1] * el.getdN_po_eta()[pc][j] );
					dNdy[j] = 1.0 / jakobian.getDetJ() * ( jakobian.getJ_odw()[1][0] * el.getdN_po_ksi()[pc][j] + jakobian.getJ_odw()[1][1] * el.getdN_po_eta()[pc][j] );

					temp_interpol += temp_el[j] * el.getN()[pc][j]; //interpolacja temperatury z rogu elementu do punktu całkowania
				}

				detj = Math.abs( jakobian.getDetJ() ); //wartość bezwzględna z wyznacznika - z książki pana profesora Milenina

				//bo tworzymy macierz 4x4 z przemnożenia przez siebie wektorów 4-elementowych
				for ( int i = 0; i < 4; i++ ) {
					for ( int j = 0; j < 4; j++ ) {
						cellFromC = gd.getSpecificHeat() * gd.getDensity() * el.getN()[pc][i] * el.getN()[pc][j] * detj;
						H_current[i][j] += gd.getConductivity() * ( dNdx[i] * dNdx[j] + dNdy[i] * dNdy[j] ) * detj + cellFromC / gd.getSimulationStepTime();
						P_current[i] += cellFromC / gd.getSimulationStepTime() * temp_interpol;
					}
				}
			}

			//warunki brzegowe
			for ( int ipow = 0; ipow < grid.elements[el_nr].getLiczbaPowierzchni(); ipow++ ) { //lecimy po tych powierzchniach kontaktowych z otoczeniem
				id = grid.elements[el_nr].getLokalLiczPow()[ipow]; //z elementu wyciągamy id powierzchni lokalnej (0|1|2|3)
				//zależnie od tego która to jest powierzchnia
				//to jest obliczenie tego delta x / 2
				switch ( id ) {
					case 0:
						detj = Math.sqrt( Math.pow( grid.elements[el_nr].nodeID[3].getX() - grid.elements[el_nr].nodeID[0].getX(), 2 ) + Math.pow( grid.elements[el_nr].nodeID[3].getY() - grid.elements[el_nr].nodeID[0].getY(), 2 ) ) / 2.0;
						break;
					case 1:
						detj = Math.sqrt( Math.pow( grid.elements[el_nr].nodeID[0].getX() - grid.elements[el_nr].nodeID[1].getX(), 2 ) + Math.pow( grid.elements[el_nr].nodeID[0].getY() - grid.elements[el_nr].nodeID[1].getY(), 2 ) ) / 2.0;
						break;
					case 2:
						detj = Math.sqrt( Math.pow( grid.elements[el_nr].nodeID[1].getX() - grid.elements[el_nr].nodeID[2].getX(), 2 ) + Math.pow( grid.elements[el_nr].nodeID[1].getY() - grid.elements[el_nr].nodeID[2].getY(), 2 ) ) / 2.0;
						break;
					case 3:
						detj = Math.sqrt( Math.pow( grid.elements[el_nr].nodeID[2].getX() - grid.elements[el_nr].nodeID[3].getX(), 2 ) + Math.pow( grid.elements[el_nr].nodeID[2].getY() - grid.elements[el_nr].nodeID[3].getY(), 2 ) ) / 2.0;
						break;
				}

				//bo dwa punkty całkowania na powierzchni
				for ( int p = 0; p < 2; p++ ) {

					//to samo co wyżej z tą macierzą 4x4
					for ( int n = 0; n < 4; n++ ) {
						for ( int i = 0; i < 4; i++ )
							H_current[n][i] += gd.getAlfa() * el.getPowLok()[id].N[p][n] * el.getPowLok()[id].N[p][i] * detj; //dodajemy warunek brzegowy na powierzchni

						P_current[n] += gd.getAlfa() * gd.getAmbientTemperature() * el.getPowLok()[id].N[p][n] * detj;
					}
				}
			}

			//agregacja
			for ( int i = 0; i < 4; i++ ) {
				for ( int j = 0; j < 4; j++ )
					H_global[grid.elements[el_nr].globalNodeID[i]][grid.elements[el_nr].globalNodeID[j]] += H_current[i][j];

				P_global[grid.elements[el_nr].globalNodeID[i]] += P_current[i];
			}
		}
	}

	public double[][] getH_global () {
		return H_global;
	}

	public double[] getP_global () {
		return P_global;
	}
}
