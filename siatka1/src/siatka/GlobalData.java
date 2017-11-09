package siatka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GlobalData {
    private double H;
    private double B;
    private int nH;
    private int nB;
    private double dB;
    private double dH;
    private int nh;
    private int ne;

    public GlobalData ( String name ) {
        try {
            Scanner file = new Scanner( new File( name ) );
            H = file.nextDouble();
            B = file.nextDouble();
            nH = file.nextInt();
            nB = file.nextInt();

            dB = B / ( nB - 1 );
            dH = H / ( nH - 1 );

            ne = ( nH - 1 ) * ( nB - 1 );
            nh = nH * nB;
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
    }

    public double getH () {
        return H;
    }

    public double getB () {
        return B;
    }

    public int getnH () {
        return nH;
    }

    public int getnB () {
        return nB;
    }

    public double getdB () {
        return dB;
    }

    public double getdH () {
        return dH;
    }

    public int getNh () {
        return nh;
    }

    public int getNe () {
        return ne;
    }
}
