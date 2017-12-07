package siatka;

public class Element {
    private Node[] ID;
    private Powierzchnia[] POW;

    public Element ( double x, double y ) {
        GlobalData gd = new GlobalData( "data.txt" );
        ID = new Node[4];
        x = x * gd.getdB();
        y = y * gd.getdH();

        ID[0] = new Node( x, y, 0.0 );
        ID[1] = new Node( x + gd.getdB(), y, 0.0 );
        ID[2] = new Node( x + gd.getdB(), y + gd.getdH(), 0.0 );
        ID[3] = new Node( x, y + gd.getdH(), 0.0 );

        POW = new Powierzchnia[4];
        POW[0] = new Powierzchnia( ID[3], ID[0] );
		POW[1] = new Powierzchnia( ID[0], ID[1] );
		POW[2] = new Powierzchnia( ID[1], ID[2] );
		POW[3] = new Powierzchnia( ID[2], ID[3] );
    }

    public Node[] getID () {
        return ID;
    }
}
