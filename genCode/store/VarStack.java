package genCode.store;

import java.util.HashMap;

public class VarStack {
	private int currentPc;
	private HashMap<String, Integer> var_indexMap;

	public VarStack() {
		currentPc = 0;
		var_indexMap = new HashMap<String, Integer>();
	}

	public void addCurrenPc() {
		this.currentPc++;
	}

	public int getCurrentPc() {
		return currentPc;
	}

	public void setCurrentPc(int currentPc) {
		this.currentPc = currentPc;
	}

	public HashMap<String, Integer> getVar_indexMap() {
		return var_indexMap;
	}
}
