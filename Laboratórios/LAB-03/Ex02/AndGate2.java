public class AndGate2 {
	private boolean gateA;
	private boolean gateB;

	public AndGate2() {
		gateA = false;
		gateB = false;
	}
	public AndGate2(boolean b) {
		this.gateA = b;
		this.gateB = b;
	}
	public AndGate2(boolean b1, boolean b2) {
		this.gateA = b1;
		this.gateB = b2;
	}

	public boolean getGateA() {
		return this.gateA;
	}

	public boolean getGateB() {
		return this.gateB;
	}

	public void setGateA(boolean b) {
		this.gateA = b;
	}
	public void setGateB(boolean b) {
		this.gateB = b;
	}
	public void setGates(boolean b) {
		this.gateA = b;
		this.gateB = b;
	}
	public void setGates(boolean b1, boolean b2) {
		this.gateA = b1;
		this.gateB = b2;
	}

	public boolean getOutput() {
		return this.gateA && this.gateB;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AndGate2) {
			AndGate2 other = (AndGate2) obj;
			return this.gateA == other.getGateA() && this.gateB == other.getGateB();
		}
		return false;
	}

	@Override
	public String toString() {
		return "A: " + this.gateA + " B: " + this.gateB;
	}
}