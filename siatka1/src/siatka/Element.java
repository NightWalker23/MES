package siatka;

public class Element {
    private Node[] ID;
    private double[][] dN_po_ksi;
    private double[][] dN_po_eta;

    public Element ( double x, double y ) {
        GlobalData gd = new GlobalData( "data.txt" );
        ID = new Node[4];
        x = x * gd.getdB();
        y = y * gd.getdH();

        ID[0] = new Node( x, y, 0.0 );
        ID[1] = new Node( x + gd.getdB(), y, 0.0 );
        ID[2] = new Node( x + gd.getdB(), y + gd.getdH(), 0.0 );
        ID[3] = new Node( x, y + gd.getdH(), 0.0 );

        //te jakobiany stąd wyrzucić do osobnej klasy global_element
        //bo to jest teraz to samo liczone dla wszystkich
        double[] ksi = {(-1/Math.pow( 3, -1 )),(1/Math.pow( 3, -1 )),(-1/Math.pow( 3, -1 )),(1/Math.pow( 3, -1 ))};
        double[] eta = {(-1/Math.pow( 3, -1 )),(-1/Math.pow( 3, -1 )),(1/Math.pow( 3, -1 )),(1/Math.pow( 3, -1 ))};

        dN_po_ksi = new double[4][4];
        dN_po_eta = new double[4][4];
        for ( int i = 0; i < 4; i++ ) {
            dN_po_ksi[i][0] = N1_ksi( ksi[i] );
            dN_po_ksi[i][1] = N2_ksi( ksi[i] );
            dN_po_ksi[i][2] = N3_ksi( ksi[i] );
            dN_po_ksi[i][3] = N4_ksi( ksi[i] );

            dN_po_eta[i][0] = N1_eta( eta[i] );
            dN_po_eta[i][1] = N2_eta( eta[i] );
            dN_po_eta[i][2] = N3_eta( eta[i] );
            dN_po_eta[i][3] = N4_eta( eta[i] );
        }
        //
    }

    public Node[] getID () {
        return ID;
    }

    //
    public double N1_ksi ( double eta ) {
        return ( - ( 1.0 / 4.0 ) * ( 1 - eta ) );
    }

    public double N1_eta ( double ksi ) {
        return ( - ( 1.0 / 4.0 ) * ( 1 - ksi ) );
    }

    //
    public double N2_ksi ( double eta ) {
        return ( ( 1.0 / 4.0 ) * ( 1 - eta ) );
    }

    public double N2_eta ( double ksi ) {
        return ( - ( 1.0 / 4.0 ) * ( 1 + ksi ) );
    }

    //
    public double N3_ksi ( double eta ) {
        return ( ( 1.0 / 4.0 ) * ( 1 + eta ) );
    }

    public double N3_eta ( double ksi ) {
        return ( ( 1.0 / 4.0 ) * ( 1 + ksi ) );
    }

    //
    public double N4_ksi ( double eta ) {
        return ( - ( 1.0 / 4.0 ) * ( 1 + eta ) );
    }

    public double N4_eta ( double ksi ) {
        return ( ( 1.0 / 4.0 ) * ( 1 - ksi ) );
    }


    public double[][] getdN_po_ksi () {
        return dN_po_ksi;
    }

    public double[][] getdN_po_eta () {
        return dN_po_eta;
    }
}
