//Abgabe von Ewald Bayer und Lukas Schmidtmann

public class Main {

	public static void main(String[] args) {
		String test = "(9 +3)*(4  -12)  / 6"; // Test mit einfachen und doppelten Leerzeichen dazwischen und jeder Operation einmal (sollte 19 ergeben)
		String test2 = "(9 +3*4  -12  / 6) - (9 +3*4  -12  / 6) *  (9 +3*4  -12  / 6)+(9 +3*4  -12  / 6)"; // "Härtere" Testrechnung (sollte 33 ergeben)
		StackRechner sr = new StackRechner();
		System.out.println(sr.getValueOf(test));
		System.out.println(sr.getValueOf(test2));
	}
}
