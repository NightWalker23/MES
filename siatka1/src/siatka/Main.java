package siatka;

import java.text.DecimalFormat;

public class Main {

    public static void main ( String[] args ) {
        DecimalFormat dec = new DecimalFormat( "#0.0000" );

        Grid grid = new Grid();
        grid.showNodes();

        System.out.println();
        grid.showElements();

        double[][] jak = Jakobian( grid.getElement( 0 ), new double[] { - 1, 1, 1, - 1 }, new double[] { - 1, - 1, 1, 1 } );
        for ( int i = 0; i < 2; i++ ) {
            for ( int j = 0; j < 2; j++ )
                System.out.print( dec.format( jak[i][j] ) + "\t" );
            System.out.println();
        }
    }

    //zamiast 0123 wstawić dla którego punktu całkowania
    public static double[][] Jakobian ( Element el, double[] x, double[] y ) {
        double[][] J = new double[2][2];

        //                          tutaj
        //                          ||
        //                          \/
        J[0][0] = el.getdN_po_ksi()[0][0] * x[0] + el.getdN_po_ksi()[0][1] * x[1] + el.getdN_po_ksi()[0][2] * x[2] + el.getdN_po_ksi()[0][3] * x[3];
        J[1][0] = el.getdN_po_ksi()[1][0] * y[0] + el.getdN_po_ksi()[1][1] * y[1] + el.getdN_po_ksi()[1][2] * y[2] + el.getdN_po_ksi()[1][3] * y[3];
        J[0][1] = el.getdN_po_eta()[2][0] * x[0] + el.getdN_po_eta()[2][1] * x[1] + el.getdN_po_eta()[2][2] * x[2] + el.getdN_po_eta()[2][3] * x[3];
        J[1][1] = el.getdN_po_eta()[3][0] * y[0] + el.getdN_po_eta()[3][1] * y[1] + el.getdN_po_eta()[3][2] * y[2] + el.getdN_po_eta()[3][3] * y[3];

//        J[0][0] = el.getdN_po_ksi()[0][0] * x[0] + el.getdN_po_ksi()[0][1] * x[1] + el.getdN_po_ksi()[0][2] * x[2] + el.getdN_po_ksi()[0][3] * x[3];
//        J[1][0] = el.getdN_po_ksi()[0][0] * y[0] + el.getdN_po_ksi()[0][1] * y[1] + el.getdN_po_ksi()[0][2] * y[2] + el.getdN_po_ksi()[0][3] * y[3];
//        J[0][1] = el.getdN_po_eta()[0][0] * x[0] + el.getdN_po_eta()[0][1] * x[1] + el.getdN_po_eta()[0][2] * x[2] + el.getdN_po_eta()[0][3] * x[3];
//        J[1][1] = el.getdN_po_eta()[0][0] * y[0] + el.getdN_po_eta()[0][1] * y[1] + el.getdN_po_eta()[0][2] * y[2] + el.getdN_po_eta()[0][3] * y[3];

        //to powinno zwrócić jakobian odwrotny
        //czyli 1/(detJ) * J^-1

        //trzeba też mieć dostęp do samego wyznacznika Jakobiana
        //czyli najlepiej chyba będzie zrobić klasę Jakobian i ona będzie liczyć Jakobian
        //i będzie też mieć wyznacznik
        return J;
    }
}
