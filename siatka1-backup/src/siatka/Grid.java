package siatka;

import java.text.DecimalFormat;

public class Grid {

    private Node[] nodes;
    private Element[] elements;
    GlobalData gd;

    public Grid () {
        gd = new GlobalData( "data.txt" );

        //tworzenie elementów
        elements = new Element[gd.getNe()];
        int k = 0;
        for ( int i = 0; i < ( gd.getnB() - 1 ); i++ )
            for ( int j = 0; j < ( gd.getnH() - 1 ); j++ )
                elements[k++] = new Element( i, j );

        //tworzenie węzłów
        nodes = new Node[gd.getNh()];
        k = 0;
        for ( int i = 0; i < gd.getnB(); i++ )
            for ( int j = 0; j < gd.getnH(); j++ )
                nodes[k++] = new Node( i * gd.getdB(), j * gd.getdH(), 0.0 );
    }

    public void showElements () {
        System.out.println( "\nELEMENTY\n" );

        DecimalFormat dec = new DecimalFormat( "#0.0000" );
        int skok = gd.getNe() / ( gd.getnB() - 1 );
        Node[] n;

        n = elements[0].getID();
        for ( int j = 0; j < 4; j++ )
            System.out.println( dec.format( n[j].getX() ) + "\t" + dec.format( n[j].getY() ) + "\t" + n[j].getStatus() );
        System.out.println();


        n = elements[elements.length - skok].getID();
        for ( int j = 0; j < 4; j++ )
            System.out.println( dec.format( n[j].getX() ) + "\t" + dec.format( n[j].getY() ) + "\t" + n[j].getStatus() );
        System.out.println();


        n = elements[elements.length - 1].getID();
        for ( int j = 0; j < 4; j++ )
            System.out.println( dec.format( n[j].getX() ) + "\t" + dec.format( n[j].getY() ) + "\t" + n[j].getStatus() );
        System.out.println();


        n = elements[skok - 1].getID();
        for ( int j = 0; j < 4; j++ )
            System.out.println( dec.format( n[j].getX() ) + "\t" + dec.format( n[j].getY() ) + "\t" + n[j].getStatus() );
        System.out.println();
    }

    public void showNodes () {
        DecimalFormat dec = new DecimalFormat( "#0.0000" );
        int k = 0;

        System.out.println( "\nWEZLY\n" );
        for ( int i = 0; i < gd.getnB(); i++ ) {
            for ( int j = 0; j < gd.getnH(); j++ )
                System.out.print( "[" + dec.format( nodes[k].getX() ) + " | " + nodes[k].getStatus() + " | " + dec.format( nodes[k++].getY() ) + "]\t" );
            System.out.println( "\n" );
        }

    }

}
