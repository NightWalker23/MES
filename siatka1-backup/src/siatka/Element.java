package siatka;

public class Element {
    private Node[] ID;
    private double x, y;

    public Element ( double x, double y ) {
        GlobalData gd = new GlobalData( "data.txt" );
        ID = new Node[4];
        this.x = x * gd.getdB();
        this.y = y * gd.getdH();

        ID[0] = new Node( this.x, this.y, 0.0 );
        ID[1] = new Node( this.x + gd.getdB(), this.y, 0.0 );
        ID[2] = new Node( this.x + gd.getdB(), this.y + gd.getdH(), 0.0 );
        ID[3] = new Node( this.x, this.y + gd.getdH(), 0.0 );
    }

    public Node[] getID () {
        return ID;
    }
}
