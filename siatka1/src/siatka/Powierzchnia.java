package siatka;

public class Powierzchnia {
	Node nodes[];

	public Powierzchnia ( Node n1, Node n2 ) {
		nodes = new Node[2];
		nodes[0] = n1;
		nodes[1] = n2;
	}

	public Node[] getNodes () {
		return nodes;
	}
}
