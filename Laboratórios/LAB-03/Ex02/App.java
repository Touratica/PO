public class App {
	public static void main(String[] args) {
		AndGate2[] gates = new AndGate2[4];
		
		gates[0] = new AndGate2();
		gates[1] = new AndGate2(true);
		gates[2] = new AndGate2(false, true);
		gates[3] = new AndGate2(true, false);

		System.out.println("BEFORE");
		for (int i = 0; i < gates.length; i++) {
			System.out.println(gates[i].toString());
		}

		System.out.println("Gate 1: " + gates[1].getOutput());
		System.out.println("Gate 2: " + gates[2].getOutput());

		gates[0].setGates(true);
		gates[1].setGateA(false);
		gates[2].setGateB(false);
		gates[3].setGates(false, true);


		System.out.println("Gate 1 == Gate 3 ? " + gates[1].equals(gates[3]));
		System.out.println("Gate 1 == Gate 2 ? " + gates[1].equals(gates[2]));

		System.out.println("AFTER");
		for (int i = 0; i < gates.length; i++) {
			System.out.println(gates[i].toString());
		}
	}
}