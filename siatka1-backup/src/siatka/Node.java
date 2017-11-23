package siatka;

public class Node {
    double x, y, t;
    int status;

    public Node ( double x, double y, double t ) {
        GlobalData gd = new GlobalData( "data.txt" );
        double B = gd.getB();
        double H = gd.getH();

        this.x = x;
        this.y = y;
        this.t = t;

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

    public void setT ( int t ) {
        this.t = t;
    }
}
