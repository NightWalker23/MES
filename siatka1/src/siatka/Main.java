package siatka;

import java.text.DecimalFormat;

public class Main {

    public static void main ( String[] args ) {
        DecimalFormat dec = new DecimalFormat( "#0.0000" );

        Grid grid = new Grid();
        grid.showNodes();

        System.out.println();
        grid.showElements();

        Jakobian J = new Jakobian( new ElementLokal(), new double[] { - 1, 1, 1, - 1 }, new double[] { - 1, - 1, 1, 1 }, 0 );
        double[][] Jak = J.getJ_odw();
        for ( int i = 0; i < 2; i++ ) {
            for ( int j = 0; j < 2; j++ )
                System.out.print( dec.format( Jak[i][j] ) + "\t" );
            System.out.println();
        }
    }

}
