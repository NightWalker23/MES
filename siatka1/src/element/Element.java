package element;

import node.Node;
import mes.GlobalData;
import powierzchnia.Powierzchnia;
import java.io.FileNotFoundException;

public class Element {

	public Node[] nodeID;
	private Powierzchnia[] powierzchnie;

	private int liczbaPowierzchni;	//liczba powierzchni które są na brzegach
	private int[] lokalLiczPow;		//lokalne numery powierzchni kontaktowych elementu

	public int[] globalNodeID;
	private GlobalData gd;

	public Element ( double x, double y ) throws FileNotFoundException {
		gd = GlobalData.getInstance();
		nodeID = new Node[4];

		globalNodeID = new int[4];
		int i = ( int ) x;
		int j = ( int ) y;

		//wyznaczamy globalne id wezlow w elemencie
		globalNodeID[0] = gd.getnH() * i + j;
		globalNodeID[1] = gd.getnH() * (i + 1) + j;
		globalNodeID[2] = gd.getnH() * (i + 1) + (j + 1);
		globalNodeID[3] = gd.getnH() * i + (j + 1);

		x = x * gd.getdB();
		y = y * gd.getdH();

		nodeID[0] = new Node( x, y );
		nodeID[1] = new Node( x + gd.getdB(), y );
		nodeID[2] = new Node( x + gd.getdB(), y + gd.getdH() );
		nodeID[3] = new Node( x, y + gd.getdH() );

		powierzchnie = new Powierzchnia[4];
		powierzchnie[0] = new Powierzchnia( nodeID[3], nodeID[0] );
		powierzchnie[1] = new Powierzchnia( nodeID[0], nodeID[1] );
		powierzchnie[2] = new Powierzchnia( nodeID[1], nodeID[2] );
		powierzchnie[3] = new Powierzchnia( nodeID[2], nodeID[3] );

		liczbaPowierzchni = 0;
		for (int k = 0; k < 4; k++)
			if ( powierzchnie[k].getNodes()[0].getStatus() == 1 && powierzchnie[k].getNodes()[1].getStatus() == 1)
				liczbaPowierzchni++;

		lokalLiczPow = new int[liczbaPowierzchni];
		int counter = 0;
		for (int k = 0; k < 4; k++)
			if ( powierzchnie[k].getNodes()[0].getStatus() == 1 && powierzchnie[k].getNodes()[1].getStatus() == 1)
				lokalLiczPow[counter++] = k;
	}

	public Node[] getNodeID () {
		return nodeID;
	}

	public int getLiczbaPowierzchni () {
		return liczbaPowierzchni;
	}

	public int[] getLokalLiczPow () {
		return lokalLiczPow;
	}
}
