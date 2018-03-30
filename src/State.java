import java.util.ArrayList;

public class State {
	private ArrayList<String> vals;
	private String[] valsArray;

	public State(String[] args) {
		vals = new ArrayList<String>();
		valsArray = args;
		for (int i = 0; i < args.length; i++) {
			vals.add(args[i]);
		}
	}

	public ArrayList<String> getVals() {
		return vals;
	}

	public void setVals(ArrayList<String> newVals) {
		vals = newVals;
	}

	public void setVals(String[] newVals) {
		vals = new ArrayList<String>();
		for (int i = 0; i < newVals.length; i++) {
			vals.add(newVals[i]);
		}
	}

	/**
	 * calculates h(n) for this state
	 */
	public int calculateHeuristic() {
		int retVal = 0;
		int b = 0;
		for (int i = 0; i < vals.size(); i++) {
			if (vals.get(i).equals("W")) {
				retVal += b;
			} else if (vals.get(i).equals("B")) {
				b++;
			}
		}

		return retVal;
	}

	public State swap(int i, int j) {
		State ret = new State(valsArray);
		String temp = ret.vals.get(i);
		ret.vals.set(i, ret.vals.get(j));
		ret.vals.set(j, temp);
		return ret;
	}
	
	@Override
	public boolean equals(Object o) {
		State s = (State) o;
		ArrayList<String> compStr = s.getVals();
		int i = 0;

		for (String str : vals) {
			if (str.equals(compStr.get(i))) {
				i++;
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

}
