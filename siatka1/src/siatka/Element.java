package siatka;

public class Element {
    private Node[] ID;

    public Element ( double x, double y ) {
        GlobalData gd = new GlobalData( "data.txt" );
        ID = new Node[4];
        x = x * gd.getdB();
        y = y * gd.getdH();

        ID[0] = new Node( x, y, 0.0 );
        ID[1] = new Node( x + gd.getdB(), y, 0.0 );
        ID[2] = new Node( x + gd.getdB(), y + gd.getdH(), 0.0 );
        ID[3] = new Node( x, y + gd.getdH(), 0.0 );
    }

    public Node[] getID () {
        return ID;
    }
}
