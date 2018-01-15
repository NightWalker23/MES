package element;

import node.NodeLocal;
import powierzchnia.PowierzchniaLokal;

public class ElementLokal {

	private double[][] dN_po_ksi;						//macierz pochodnych funkcji kształtu po ksi
	private double[][] dN_po_eta;						//macierz pochodnych funkcji kształtu po eta
	private double[][] N;								//macierz składająca się z wartości funkcji kształtu
	private PowierzchniaLokal[] powierzchnieLokalne;	//cztery powierzchnie elementu
	private NodeLocal[] punktyCalkowania;				//cztery punkty calkowania
	public static ElementLokal el_lok = null;			//obiekt tej  klasy jako singleton


	private ElementLokal () {

		punktyCalkowania = new NodeLocal[4];
		punktyCalkowania[0] = new NodeLocal(-1.0 / Math.sqrt(3.0), -1.0 / Math.sqrt(3.0));
		punktyCalkowania[1] = new NodeLocal(1.0 / Math.sqrt(3.0), -1.0 / Math.sqrt(3.0));
		punktyCalkowania[2] = new NodeLocal(-1.0 / Math.sqrt(3.0), 1.0 / Math.sqrt(3.0));
		punktyCalkowania[3] = new NodeLocal(1.0 / Math.sqrt(3.0), 1.0 / Math.sqrt(3.0));

		dN_po_ksi = new double[4][4];
		dN_po_eta = new double[4][4];
		N = new double[4][4];

		for ( int i = 0; i < 4; i++ ) {
			dN_po_ksi[i][0] = N1_ksi( punktyCalkowania[i].getKsi() );
			dN_po_ksi[i][1] = N2_ksi( punktyCalkowania[i].getKsi() );
			dN_po_ksi[i][2] = N3_ksi( punktyCalkowania[i].getKsi() );
			dN_po_ksi[i][3] = N4_ksi( punktyCalkowania[i].getKsi() );

			dN_po_eta[i][0] = N1_eta( punktyCalkowania[i].getEta() );
			dN_po_eta[i][1] = N2_eta( punktyCalkowania[i].getEta() );
			dN_po_eta[i][2] = N3_eta( punktyCalkowania[i].getEta() );
			dN_po_eta[i][3] = N4_eta( punktyCalkowania[i].getEta() );

			N[i][0] = N1( punktyCalkowania[i].getKsi(), punktyCalkowania[i].getEta() );
			N[i][1] = N2( punktyCalkowania[i].getKsi(), punktyCalkowania[i].getEta() );
			N[i][2] = N3( punktyCalkowania[i].getKsi(), punktyCalkowania[i].getEta() );
			N[i][3] = N4( punktyCalkowania[i].getKsi(), punktyCalkowania[i].getEta() );
		}

		powierzchnieLokalne = new PowierzchniaLokal[4];
		powierzchnieLokalne[0] = new PowierzchniaLokal( new NodeLocal( - 1.0, 1.0 / Math.sqrt( 3 ) ), new NodeLocal( - 1.0, - 1.0 / Math.sqrt( 3.0 ) ) );
		powierzchnieLokalne[1] = new PowierzchniaLokal( new NodeLocal( - 1.0 / Math.sqrt( 3 ), - 1.0 ), new NodeLocal( 1.0 / Math.sqrt( 3.0 ), - 1.0 ) );
		powierzchnieLokalne[2] = new PowierzchniaLokal( new NodeLocal( 1.0, - 1.0 / Math.sqrt( 3 ) ), new NodeLocal( 1.0, 1.0 / Math.sqrt( 3.0 ) ) );
		powierzchnieLokalne[3] = new PowierzchniaLokal( new NodeLocal( 1.0 / Math.sqrt( 3 ), 1.0 ), new NodeLocal( - 1.0 / Math.sqrt( 3.0 ), 1.0 ) );

		for ( int i = 0; i < 4; i++ ) {
			for ( int j = 0; j < 2; j++ ) {
				powierzchnieLokalne[i].N[j][0] = N1( powierzchnieLokalne[i].nodes[j].getKsi(), powierzchnieLokalne[i].nodes[j].getEta() );
				powierzchnieLokalne[i].N[j][1] = N2( powierzchnieLokalne[i].nodes[j].getKsi(), powierzchnieLokalne[i].nodes[j].getEta() );
				powierzchnieLokalne[i].N[j][2] = N3( powierzchnieLokalne[i].nodes[j].getKsi(), powierzchnieLokalne[i].nodes[j].getEta() );
				powierzchnieLokalne[i].N[j][3] = N4( powierzchnieLokalne[i].nodes[j].getKsi(), powierzchnieLokalne[i].nodes[j].getEta() );
			}
		}

	}

	public static ElementLokal getInstance(){
		if ( el_lok == null )
			el_lok = new ElementLokal();

		return el_lok;
	}

	//
	private double N1_ksi ( double eta ) {
		return ( - ( 1.0 / 4.0 ) * ( 1 - eta ) );
	}

	private double N1_eta ( double ksi ) {
		return ( - ( 1.0 / 4.0 ) * ( 1 - ksi ) );
	}

	//
	private double N2_ksi ( double eta ) {
		return ( ( 1.0 / 4.0 ) * ( 1 - eta ) );
	}

	private double N2_eta ( double ksi ) {
		return ( - ( 1.0 / 4.0 ) * ( 1 + ksi ) );
	}

	//
	private double N3_ksi ( double eta ) {
		return ( ( 1.0 / 4.0 ) * ( 1 + eta ) );
	}

	private double N3_eta ( double ksi ) {
		return ( ( 1.0 / 4.0 ) * ( 1 + ksi ) );
	}

	//
	private double N4_ksi ( double eta ) {
		return ( - ( 1.0 / 4.0 ) * ( 1 + eta ) );
	}

	private double N4_eta ( double ksi ) {
		return ( ( 1.0 / 4.0 ) * ( 1 - ksi ) );
	}


	//
	private double N1 ( double ksi, double eta ) {
		return 0.25 * ( 1.0 - ksi ) * ( 1.0 - eta );
	}

	private double N2 ( double ksi, double eta ) {
		return 0.25 * ( 1.0 + ksi ) * ( 1.0 - eta );
	}

	private double N3 ( double ksi, double eta ) {
		return 0.25 * ( 1.0 + ksi ) * ( 1.0 + eta );
	}

	private double N4 ( double ksi, double eta ) {
		return 0.25 * ( 1.0 - ksi ) * ( 1.0 + eta );
	}


	//
	public double[][] getdN_po_ksi () {
		return dN_po_ksi;
	}

	public double[][] getdN_po_eta () {
		return dN_po_eta;
	}

	public double[][] getN () {
		return N;
	}

	public PowierzchniaLokal[] getPowLok () {
		return powierzchnieLokalne;
	}
}
