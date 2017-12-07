package siatka;

public class Jakobian {

    private double detJ;
    private double[][] J;
    private double[][] J_odw;

    public Jakobian ( ElementLokal el, double[] x, double[] y, int k ) {
        J = new double[2][2];
        J_odw = new double[2][2];

        J( el, x, y, k );
        J_odw( el, x, y, k );
        setDetJ( J );
    }

    private void J ( ElementLokal el, double[] x, double[] y, int k ) {
        J[0][0] = el.getdN_po_ksi()[k][0] * x[0] + el.getdN_po_ksi()[k][1] * x[1] + el.getdN_po_ksi()[k][2] * x[2] + el.getdN_po_ksi()[k][3] * x[3];
        J[1][0] = el.getdN_po_ksi()[k][0] * y[0] + el.getdN_po_ksi()[k][1] * y[1] + el.getdN_po_ksi()[k][2] * y[2] + el.getdN_po_ksi()[k][3] * y[3];
        J[0][1] = el.getdN_po_eta()[k][0] * x[0] + el.getdN_po_eta()[k][1] * x[1] + el.getdN_po_eta()[k][2] * x[2] + el.getdN_po_eta()[k][3] * x[3];
        J[1][1] = el.getdN_po_eta()[k][0] * y[0] + el.getdN_po_eta()[k][1] * y[1] + el.getdN_po_eta()[k][2] * y[2] + el.getdN_po_eta()[k][3] * y[3];
    }

    private void J_odw ( ElementLokal el, double[] x, double[] y, int k ) {
        J_odw[0][0] = J[1][1];
        J_odw[1][0] = - 1 * J[1][0];
        J_odw[0][1] = - 1 * J[0][1];
        J_odw[1][1] = J[0][0];
    }

    private void setDetJ ( double[][] J ) {
        detJ = J[0][0] * J[1][1] - J[0][1] * J[1][0];
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
