package Aufgabe_15;


import java.util.Stack;
import java.util.StringTokenizer;

public class StackRechner {

	public StackRechner() {
		//
	}

	// Methode fuer die Umwandlung eines in Infix-Notation gegebenen
	// String in die Postfix-Schreibweise
	private String infixToPostfix(String input) {
		int parseValue;
		for (int i = 0; i < input.length(); i++) {
			switch (input.charAt(i)) {
			case ']':
			case '}':
				input = input.subSequence(0, i-1) + ")" + input.subSequence(i+1, input.length());
				
				break;
			case '{':
			case '[':
				input = input.subSequence(0, i-1) + "(" + input.subSequence(i+1, input.length());
				
				break;

			
			default:
				break;
			}
			
		}
		String postfix = "";
		String token;
		// Ein StringTokenizer nimmt einen Eingabestring und
		// filtert (sozusagen) jedes einzelne Wort innerhalb
		// des Strings anhand der vorkommenden Leerzeichen
		StringTokenizer allToken = new StringTokenizer(input);
		// Hilfstack fuer die Umwandlung in die Postfix-Notation
		Stack<String> postfixStack = new Stack<String>();
		
		
		
		// Solange noch nicht alle Woerter des Eingabestring abgearbeitet wurden...
		while (allToken.hasMoreElements()) {
			// ...hole das naechste Wort und...
			token = allToken.nextToken();
			// ...ueberpruefe, ob es sich dabei um einen Operator handelt.
			switch(token.charAt(0)) {
			// Falls es sich um ein '+' oder '-' handelt...
			case ')':
				postfixStack.push(token);
			case '(':
				
			case '+':
			case '-':
				// ...fuege saemtliche Operatoren, die auf dem Stack liegen
				// und ueber eine gleiche bzw. hoehere Prioritaet verfuegen
				// ("Punkt-Rechnung geht vor Strichrechnung") 
				// dem Ausgabestring und...
				// BEMERKUNG: Eigentlich muesste man an dieser Stelle noch pruefen,
				// ob die Operatoren, welche eine gleiche Prioritaet aufweisen, wie
				// der aktuelle Operator ueberhaupt vom Stack geholt werden duerfen.
				// Diese Ueberpruefung muesste erfolgen, wenn der Eingabestring mit 
				// Klammern versehen wird. Da dies laut Aufgabenstellung jedoch
				// vernachlaessigt werden darf, ist die Ausfuehrung von Operationen
				// gleicher Prioritaet "Auslegungssache", da diese bei nicht
				// vorhandener Klammerung in unterschiedlicher Reihenfolge 
				// durchgefuehrt werden duerfen, ohne das Ergebnis zu verfaelschen.
				// Aus diesem Grund werden hier die Operatoren gleicher Prioritaet
				// einfach ausgegeben und bei der Multiplikation bzw. Division nicht.
				// Dies fuehrt zu einer einfacheren Umsetzung.
				while (!postfixStack.isEmpty())
					postfix += postfixStack.pop() + " ";
				// ...setze den aktuell betrachteten Operator auf den Stack. 
				postfixStack.push(token);
				break;
				// Falls es sich bei dem aktuellen Operator um ein '*' oder '/' handelt...
			case '*':
			case '/':
				// ...lege diesen einfach auf den Stack ab (beachte die Bermerkung
				// in der oberen Fallentscheidung).
				postfixStack.push(token);
				break;
				// Falls das aktuell betrachtete Element jedoch kein Operator ist...
			default:
				// ...ueberpruefe ob es sich um eine gueltige Zahlendarstellung handelt.
				try { parseValue = Integer.parseInt(token); }
				// Falls dem nicht so ist, setze den Wert auf '0'...
				catch(NumberFormatException ex) { parseValue = 0; }
				// ...andernfalls fuege den Zahlenwert dem Ausgabe string hinzu.
				postfix += parseValue + " ";
				// BEMERKUNG: Die Ueberpruefung auf einen gueltigen Zahlenwert, wird
				// an dieser Stelle ueber das Abfangen einer moeglichen Exception 
				// erreicht. Der Term 'b3' wuerde beim parsen eine Exception ausloesen,
				// da dieser Ausdruck nicht als Integer-Wert geparst werden kann.
				// In der Ausnahmebehandlung der Exception, wird der Wert fuer die
				// Ausgabe dann entsprechend auf '0' gesetzt.
				break;
			}
		}
		// Zum Schluss muessen noch alle Operatoren, die sich noch auf dem Stack befinden
		// koennen, dem Ausgabestring hinzugefuegt werden.
		while (!postfixStack.isEmpty())
			postfix += postfixStack.pop() + " ";

		return postfix;
	}

	// Methode fuer die Auswertung eines Postfix-Strings und fuer die
	// Berechnung des entsprechenden Wertes
	private int calculateValue(String postfix) {
		String token;
		int value, operant01, operant02, result = 0;
		// Aehnlich wie bei der vorherigen Methode, werden zunaechst alle
		// Woerter/Terme der Eingabe separat ueber den StringTokenizer verwaltet.
		StringTokenizer allToken = new StringTokenizer(postfix);
		// Hilfstack fuer die eigentliche Berechnung (haelt die Zahlenwerte).
		Stack<Integer> calcValue = new Stack<Integer>();

		// Solange noch Werte im StringTokenizer enthalten sind...
		while (allToken.hasMoreTokens()) {
			// ...hole den Naechsten und...
			token = allToken.nextToken();
			// ...ueberpruefe, ob es sich dabei um eine Operation handelt.
			if ((token.equals("+")) || (token.equals("-")) || (token.equals("*")) || (token.equals("/"))) {
				// Falls dem so ist, hole die Operanden vom Stack und...
				operant02 = calcValue.pop();
				operant01 = calcValue.pop();
				// ...fuehre die jeweilige Operation durch.
				switch(token.charAt(0)) {
				case '+': result = operant01 + operant02; break;
				case '-': result = operant01 - operant02; break;
				case '*': result = operant01 * operant02; break;
				case '/': result = operant01 / operant02; break;
				default: break;
				}
				// Lege das Ergebnis dieser Berechnung wieder auf den Stack.
				calcValue.push(result);
			} else {
				// Falls es sich bei dem aktuellen Ausdruck um keinen Operator handelt,
				// ueberpruefe ob es sich dabei um einen Zahlenwert handelt und...
				try { value = Integer.parseInt(token); }
				catch(NumberFormatException ex) { value = 0; }
				// ...lege den Wert auf den Hilfsstack ab. 
				calcValue.push(value);
			}
		}

		return result;
	}

	public int getValueOf(String input) {
		System.out.println("Eingabe (Infix):\t"+input);
		String postfix = infixToPostfix(input);
		System.out.println("Stack (Postfix): \t"+postfix);
		int result = calculateValue(postfix);
		System.out.println("Berechnungsergebnis:\t"+result);
		return result;
	}

}
