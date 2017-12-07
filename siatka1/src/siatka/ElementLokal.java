package siatka;

public class ElementLokal {

	private double[][] dN_po_ksi;
	private double[][] dN_po_eta;
	private double[][] N;
	private PowierzchniaLokal[] powierzchnieL;


	public ElementLokal () {
		double[] ksi = { ( - 1 / Math.pow( 3, 0.5 ) ), ( 1 / Math.pow( 3, 0.5 ) ), ( - 1 / Math.pow( 3, 0.5 ) ), ( 1 / Math.pow( 3, 0.5 ) ) };
		double[] eta = { ( - 1 / Math.pow( 3, 0.5 ) ), ( - 1 / Math.pow( 3, 0.5 ) ), ( 1 / Math.pow( 3, 0.5 ) ), ( 1 / Math.pow( 3, 0.5 ) ) };

		dN_po_ksi = new double[4][4];
		dN_po_eta = new double[4][4];
		N = new double[4][4];
		for ( int i = 0; i < 4; i++ ) {
			dN_po_ksi[i][0] = N1_ksi( ksi[i] );
			dN_po_ksi[i][1] = N2_ksi( ksi[i] );
			dN_po_ksi[i][2] = N3_ksi( ksi[i] );
			dN_po_ksi[i][3] = N4_ksi( ksi[i] );

			dN_po_eta[i][0] = N1_eta( eta[i] );
			dN_po_eta[i][1] = N2_eta( eta[i] );
			dN_po_eta[i][2] = N3_eta( eta[i] );
			dN_po_eta[i][3] = N4_eta( eta[i] );

			N[i][0] = N1( ksi[i], eta[i] );
			N[i][1] = N2( ksi[i], eta[i] );
			N[i][2] = N3( ksi[i], eta[i] );
			N[i][3] = N4( ksi[i], eta[i] );
		}

		powierzchnieL = new PowierzchniaLokal[4];
		powierzchnieL[0] = new PowierzchniaLokal( new NodeLocal( - 1.0, 1.0 / Math.sqrt( 3 ) ), new NodeLocal( - 1.0, - 1.0 / Math.sqrt( 3.0 ) ) );
		powierzchnieL[1] = new PowierzchniaLokal( new NodeLocal( - 1.0 / Math.sqrt( 3 ), - 1.0 ), new NodeLocal( 1.0 / Math.sqrt( 3.0 ), - 1.0 ) );
		powierzchnieL[2] = new PowierzchniaLokal( new NodeLocal( 1.0, - 1.0 / Math.sqrt( 3 ) ), new NodeLocal( 1.0, 1.0 / Math.sqrt( 3.0 ) ) );
		powierzchnieL[3] = new PowierzchniaLokal( new NodeLocal( 1.0 / Math.sqrt( 3 ), 1.0 ), new NodeLocal( - 1.0 / Math.sqrt( 3.0 ), 1.0 ) );

		for ( int i = 0; i < 4; i++ ) {
			for ( int j = 0; j < 2; j++ ) {
				powierzchnieL[i].N[j][0] = N1( ksi[j], eta[j] );
				powierzchnieL[i].N[j][1] = N2( ksi[j], eta[j] );
				powierzchnieL[i].N[j][2] = N3( ksi[j], eta[j] );
				powierzchnieL[i].N[j][3] = N4( ksi[j], eta[j] );
			}
		}

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


	public double[][] getdN_po_ksi () {
		return dN_po_ksi;
	}

	public double[][] getdN_po_eta () {
		return dN_po_eta;
	}

	public double[][] getN () {
		return N;
	}

	public PowierzchniaLokal[] getPowierzchnieL () {
		return powierzchnieL;
	}
}
