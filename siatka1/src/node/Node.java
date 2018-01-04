package node;

import mes.GlobalData;
import java.io.FileNotFoundException;

public class Node {

    private double x, y, t;
    private int status;
    private GlobalData gd;

    public Node ( double x, double y ) throws FileNotFoundException {
        gd = GlobalData.getInstance();
        double B = gd.getB();
        double H = gd.getH();

        this.x = x;
        this.y = y;
        this.t = gd.getInitialTemperature();

        if ( this.x == 0.0 || this.x >= B || this.y == 0.0 || this.y >= H ) status = 1;
            else status = 0;
    }

    public double getX () {
        return x;
    }

    public double getY () {
        return y;
    }

    public double getT () {
        return t;
    }

    public int getStatus () {
        return status;
    }

    public void setT ( double t ) {
        this.t = t;
    }
}
