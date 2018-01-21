package mes;

import node.Node;
import element.Element;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;

public class Grid {

	public Node[] nodes;
	public Element[] elements;
	GlobalData gd;

	private static Grid grid = null;

	private Grid () throws FileNotFoundException {
		gd = GlobalData.getInstance();

		double alfa;            //wspołczynnik wymiany ciepła
		double specificHeat;    //ciepło właściwe
		double conductivity;    //współczynnik przewodzenia ciepła
		double density;         //gęstość

		//tworzenie elementów
		elements = new Element[gd.getNe()];
		int k = 0;
		for ( int i = 0; i < ( gd.getnB() - 1 ); i++ ) {
			for ( int j = 0; j < ( gd.getnH() - 1 ); j++ ) {

				//podział na 3 części szyba(5mm)-powietrze(10mm)-szyba(5mm)
				if ( ( i < 3 * ( gd.getnB() - 1 ) / 4.0 ) && ( i >= ( gd.getnB() - 1 ) / 4.0 ) ) {
					alfa = gd.getAlfa2();
					specificHeat = gd.getSpecificHeat2();
					conductivity = gd.getConductivity2();
					density = gd.getDensity2();
				} else {
					alfa = gd.getAlfa();
					specificHeat = gd.getSpecificHeat();
					conductivity = gd.getConductivity();
					density = gd.getDensity();
				}

				elements[k++] = new Element( i, j, alfa, specificHeat, conductivity, density );
			}
		}

		//tworzenie węzłów
		nodes = new Node[gd.getNh()];
		k = 0;
		for ( int i = 0; i < gd.getnB(); i++ )
			for ( int j = 0; j < gd.getnH(); j++ )
				nodes[k++] = new Node( i * gd.getdB(), j * gd.getdH() );
	}

	public static Grid getInstance () throws FileNotFoundException {
		if ( grid == null )
			grid = new Grid();
		return grid;
	}

	public void showElements () {
		System.out.println( "\nELEMENTY\n" );

		DecimalFormat dec = new DecimalFormat( "#0.0000" );
		int skok = gd.getNe() / ( gd.getnB() - 1 );
		Node[] n;

		n = elements[0].getNodeID();
		for ( int j = 0; j < 4; j++ )
			System.out.println( dec.format( n[j].getX() ) + "\t" + dec.format( n[j].getY() ) + "\t" + n[j].getStatus() );
		System.out.println();


		n = elements[elements.length - skok].getNodeID();
		for ( int j = 0; j < 4; j++ )
			System.out.println( dec.format( n[j].getX() ) + "\t" + dec.format( n[j].getY() ) + "\t" + n[j].getStatus() );
		System.out.println();


		n = elements[elements.length - 1].getNodeID();
		for ( int j = 0; j < 4; j++ )
			System.out.println( dec.format( n[j].getX() ) + "\t" + dec.format( n[j].getY() ) + "\t" + n[j].getStatus() );
		System.out.println();


		n = elements[skok - 1].getNodeID();
		for ( int j = 0; j < 4; j++ )
			System.out.println( dec.format( n[j].getX() ) + "\t" + dec.format( n[j].getY() ) + "\t" + n[j].getStatus() );
		System.out.println();
	}

	public void showAllElements () {
		System.out.println( "\nELEMENTY\n" );

		for ( int i = 0; i < elements.length; i++ ) {
			if ( i % (gd.getnH()-1) == 0 && i != 0 )
				System.out.println();
			System.out.print( elements[i].specificHeat + "\t" );
		}
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

	public Element getElement ( int i ) {
		return elements[i];
	}
}
