package mes;

import element.ElementLokal;

public class Jakobian {

    private double detJ;
    private double[][] J;
    private double[][] J_odw;

    public static final ElementLokal el_lok = ElementLokal.getInstance();

    public Jakobian ( double[] x, double[] y, int punktCalkowania ) {
        J = new double[2][2];
        J_odw = new double[2][2];

        J( x, y, punktCalkowania );
        J_odw();
		detJ = J[0][0] * J[1][1] - J[0][1] * J[1][0];
    }

    private void J ( double[] x, double[] y, int punktCalkowania ) {
        J[0][0] = el_lok.getdN_po_ksi()[punktCalkowania][0] * x[0] + el_lok.getdN_po_ksi()[punktCalkowania][1] * x[1] + el_lok.getdN_po_ksi()[punktCalkowania][2] * x[2] + el_lok.getdN_po_ksi()[punktCalkowania][3] * x[3];
        J[1][0] = el_lok.getdN_po_ksi()[punktCalkowania][0] * y[0] + el_lok.getdN_po_ksi()[punktCalkowania][1] * y[1] + el_lok.getdN_po_ksi()[punktCalkowania][2] * y[2] + el_lok.getdN_po_ksi()[punktCalkowania][3] * y[3];
        J[0][1] = el_lok.getdN_po_eta()[punktCalkowania][0] * x[0] + el_lok.getdN_po_eta()[punktCalkowania][1] * x[1] + el_lok.getdN_po_eta()[punktCalkowania][2] * x[2] + el_lok.getdN_po_eta()[punktCalkowania][3] * x[3];
        J[1][1] = el_lok.getdN_po_eta()[punktCalkowania][0] * y[0] + el_lok.getdN_po_eta()[punktCalkowania][1] * y[1] + el_lok.getdN_po_eta()[punktCalkowania][2] * y[2] + el_lok.getdN_po_eta()[punktCalkowania][3] * y[3];
    }

    private void J_odw () {
        J_odw[0][0] = J[1][1];
        J_odw[1][0] = - 1 * J[1][0];
        J_odw[0][1] = - 1 * J[0][1];
        J_odw[1][1] = J[0][0];
    }

    public double getDetJ () {
        return detJ;
    }

    public double[][] getJ () {
        return J;
    }

    public double[][] getJ_odw () {
        return J_odw;
    }
}
