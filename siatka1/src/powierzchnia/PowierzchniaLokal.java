package powierzchnia;

import node.NodeLocal;

public class PowierzchniaLokal {
	public double[][] N;
	public NodeLocal nodes[];

	public PowierzchniaLokal ( NodeLocal n1, NodeLocal n2 ) {
		N = new double[2][4];
		nodes = new NodeLocal[2];
		nodes[0] = n1;
		nodes[1] = n2;
	}
}