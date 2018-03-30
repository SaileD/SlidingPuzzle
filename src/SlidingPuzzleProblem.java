import java.util.*;

public class SlidingPuzzleProblem {
	private static PriorityQueue<Node> open;
	private static ArrayList<Node> visited;

	public static void main(String[] args) {
		State s = new State(args);
		// String[] tester = { "W", "B", "E", "W", "B", "W", "B" };

		System.out.println(s.calculateHeuristic());

		Node start = new Node(s);

		open = new PriorityQueue<Node>();
		visited = new ArrayList<Node>();

		open.add(start);

		while (!open.isEmpty()) {
			// take top most node
			Node n = open.poll();

			// check if we're out of paths
			if (n == null) {
				System.out.println("Out of paths");
				break;
			}

			// check if we've found solution
			if (n.getHeuristicCost() == 0) {
				finish(n);
				return;
			}

			// Not out of paths, not at solution, add n to visited
			visited.add(n);

			// Let's look at all possible children nodes
			int num = n.numberChildren();
			int index = n.spaceIndex();
			Node temp;

			if (num == 3) { // space on an edge
				if (index < 3) { // left side
					temp = n.swap(0, 1); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(0, 2); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(0, 3); // cost 2
					checkAndAdd(n, temp, 2);
				} else {// right side
					temp = n.swap(6, 5);// cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(6, 4);// cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(6, 3);// cost 2
					checkAndAdd(n, temp, 2);
				}
			} else if (num == 4) {// space one in
				if (index < 3) { // left side
					temp = n.swap(1, 0); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(1, 2); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(1, 3); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(1, 4); // cost 2
					checkAndAdd(n, temp, 2);
				} else {// right side
					temp = n.swap(5, 6); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(5, 4); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(5, 3); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(5, 2); // cost 2
					checkAndAdd(n, temp, 2);
				}
			} else if (num == 5) {// space two in
				if (index < 3) { // left side
					temp = n.swap(2, 0); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(2, 1); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(2, 3); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(2, 4); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(2, 5); // cost 2
					checkAndAdd(n, temp, 2);
				} else {// right side
					temp = n.swap(4, 6); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(4, 5); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(4, 3); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(4, 2); // cost 1
					checkAndAdd(n, temp, 1);

					temp = n.swap(4, 1); // cost 2
					checkAndAdd(n, temp, 2);
				}
			} else if (num == 6) { // space in the middle
				temp = n.swap(3, 0); // cost 2
				checkAndAdd(n, temp, 2);

				temp = n.swap(3, 1); // cost 1
				checkAndAdd(n, temp, 1);

				temp = n.swap(3, 2); // cost 1
				checkAndAdd(n, temp, 1);

				temp = n.swap(3, 4); // cost 1
				checkAndAdd(n, temp, 1);

				temp = n.swap(3, 5); // cost 1
				checkAndAdd(n, temp, 1);

				temp = n.swap(3, 6); // cost 2
				checkAndAdd(n, temp, 2);
			}
		}
	}

	static void checkAndAdd(Node current, Node t, int cost) {
		int t_cost = t.getHeuristicCost() + cost;

		boolean v = visited.contains(t);
		if (!v) {
			t = new Node(t.getVal(), current, cost);
			open.add(t);
		}
	}

	public static boolean isDuplicate(ArrayList<Node> a, Node node) {
		for (Node n : a) {
			if (n.equals(node))
				return true;
		}
		return false;
	}

	public static void finish(Node n) {
		Node curr = n;
		Stack<Node> path = new Stack<Node>();
		while (curr != null) {
			path.add(curr);
			curr = curr.getParent();
		}

		while (!path.isEmpty()) {
			Node p = path.pop();
			System.out.println(p.toString() + " : G(n) = " + p.getRealCost() + " : h(n) = " + p.getHeuristicCost());
		}
		System.out.println("Total Cost = " + n.getTotalCost());
	}

}
