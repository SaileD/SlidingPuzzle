
public class Node implements Comparable<Node> {
	private State val;
	private Node parent;
	private int f; // total cost = g + h
	private int g; // Cost to get here so far
	private int h; // Heuristic val

	public Node(State s) {
		val = s;
		parent = null;
		h = s.calculateHeuristic();
		g = 0;
		f = g + h;
	}

	public Node(State s, Node n, int cost) {
		val = s;
		parent = n;
		h = s.calculateHeuristic();
		g = n.getRealCost() + cost;
		f = g + h;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node n) {
		parent = n;
	}

	public int getRealCost() {
		return g;
	}

	public void setRealCost(int i) {
		g = i;
	}

	public int getHeuristicCost() {
		return h;
	}

	public void setHeuristicCost(int i) {
		h = i;
	}

	public int getTotalCost() {
		return f;
	}

	public void setTotalCost(int i) {
		f = i;
	}

	public State getVal() {
		return val;
	}

	public int numberChildren() {
		int retVal = 0;
		int i = 0;
		for (String s : val.getVals()) {
			if (s.equals("E")) {
				break;
			}
			i++;
		}
		if (i == 0 || i == 6) {
			return 3;
		} else if (i == 1 || i == 5) {
			return 4;
		} else if (i == 2 || i == 4) {
			return 5;
		} else if (i == 3) {
			return 6;
		}

		return retVal;
	}

	public int spaceIndex() {
		int i = 0;
		for (String s : val.getVals()) {
			if (s.equals("E")) {
				break;
			}
			i++;
		}
		return i;
	}

	@Override
	public boolean equals(Object o) {
		Node n = (Node) o;
		if (val.equals(n.getVal())) {
			return true;
		}
		return false;
	}

	public String toString() {
		String retVal = "";
		for (String s : val.getVals()) {
			retVal += s;
		}

		return retVal;
	}

	public Node swap(int i, int j) {
		return new Node(val.swap(i, j));
	}

	@Override
	public int compareTo(Node o) {
		if (o.getTotalCost() < f)
			return 1;
		else if (o.getTotalCost() > f)
			return -1;
		else
			return 0;
	}
}
