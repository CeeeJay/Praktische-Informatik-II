//Abgabe von Ewald Bayer, Jan-Philipp Bamberger, Leon Peulings und Stephan Malzkorn

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

public class StackRechner {
	
	Tree tree;
	
	public boolean generateTree(String postfix){
		StringTokenizer st = new StringTokenizer(postfix);
		Stack<Tree> trees = new Stack<Tree>();
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			try{
				switch(token.charAt(0)){
				case '+':
				case '*':
					trees.push(new Tree(token, trees.pop(), trees.pop()));
					break;
				case '-':
				case '/':
				case '%':
					Tree tempPop;
					tempPop = trees.pop();
					trees.push(new Tree(token, trees.pop(), tempPop));
					break;
				default:
					trees.push(new Tree(token));
					break;
				}
			}catch(EmptyStackException e){
				return false;
			}
		}
		this.tree = trees.pop();
		return trees.isEmpty();
	}

	// Aus Musterlösung, musste nur die Methode "infixToPostfix" auf public stellen, um den Rest halbwegs möglich zu machen... 

	public StackRechner() {
		//
	}

	/**********************************************************************************************************/	
	/*** Aufgaben vom Uebungsblatt05 **************************************************************************/  
	/**********************************************************************************************************/

	// Methode fuer die Umwandlung eines in Infix-Notation gegebenen
	// String in die Postfix-Schreibweise
	public String infixToPostfix(String input) {
		int parseValue;
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
				// ...lege diesen einfach auf den Stack ab (beachte die Bemerkung
				// in der oberen Fallentscheidung). Operationen gleicher Prioritaet werden
				// durch diese Vorgehensweise von rechts nach links ausgewertet.
				// '8 / 2 * 4' ist also '1' und nicht '16'
				// In der Methode 'infixToPostfix_Klammerung(String input)' wurde dies abgeaendert!!!
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
		int value, operand01, operand02, result = 0;
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
			if ((token.equals("+")) || (token.equals("-")) || (token.equals("*")) || (token.equals("/")) || (token.equals("%"))) {
				// Falls dem so ist, hole die Operanden vom Stack und...
				operand02 = calcValue.pop();
				operand01 = calcValue.pop();
				// ...fuehre die jeweilige Operation durch.
				switch(token.charAt(0)) {
				case '+': result = operand01 + operand02; break;
				case '-': result = operand01 - operand02; break;
				case '*': result = operand01 * operand02; break;
				case '/': result = operand01 / operand02; break;
				// (Modulo-Rechnung war nicht gefordert)
				case '%': result = operand01 % operand02; break;
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

		return (calcValue.isEmpty() ? result : calcValue.pop());
	}

	public int getValueOf(String input) {
		System.out.println("Eingabe (Infix):\t"+input);
		String postfix = infixToPostfix(input);
		System.out.println("Stack (Postfix): \t"+postfix);
		int result = calculateValue(postfix);
		System.out.println("Berechnungsergebnis:\t"+result);
		return result;
	}

	/**********************************************************************************************************/	
	/*** Aufgaben vom Uebungsblatt06 **************************************************************************/  
	/**********************************************************************************************************/

	// Methode fuer die Umwandlung eines in Infix-Notation gegebenen
	// String in die Postfix-Schreibweise
	public String infixToPostfix_Klammerung(String input) {
		char c, cur;
		int parseValue;
		String tmp, postfix = "";
		// Hilfsstack fuer die Umwandlung in die Postfix-Notation
		Stack<Character> postfixStack = new Stack<Character>();
		// Hilfsstack zur Ueberpruefung der Klammerung
		Stack<Character> bracketStack = new Stack<Character>();
		StringBuilder inputTmp = new StringBuilder();

		// Fuer alle Zeichen des Input-Strings...
		for (int i = 0; i < input.length(); i++) {
			// ...nimm das naechste Zeichen und...
			cur = input.charAt(i);
			// ...ueberpruefe, ob es sich dabei um einen Operator handelt.
			switch(cur) {
			case '(':
			case '{':
			case '[':
			case '<':
				bracketStack.push(cur);
				while ((!bracketStack.isEmpty()) && (i < input.length()-1)) {
					c = input.charAt(++i);
					if (isBracket_Close(c)) {
						if (!isCorrectBracketPair(bracketStack.pop(), c)) return null;
						if (bracketStack.isEmpty()) {
							tmp = infixToPostfix_Klammerung(inputTmp.toString());
							if (tmp == null) return null;
							postfix += tmp;
							inputTmp.delete(0, inputTmp.length());
						} else inputTmp.append(c);
					} else if (isBracket_Open(c)) {
						bracketStack.push(c);
						inputTmp.append(c);
					} else inputTmp.append(c);
				}
				if (inputTmp.length() > 0) return null;
				break;
				// Falls es sich um ein '+' oder '-' handelt...
			case '+':
			case '-':
				// (folgende Abfrage war laut Aufgabenstellung nicht gefordert)
				if (!isValidOperation(input, i)) return null;
				// ...fuege saemtliche Operatoren, die auf dem Stack liegen
				// und ueber eine gleiche bzw. hoehere Prioritaet verfuegen
				// ("Punkt-Rechnung geht vor Strichrechnung") 
				// dem Ausgabestring hinzu und...
				while (!postfixStack.isEmpty())
					postfix += " " + postfixStack.pop();
				// ...setze den aktuell betrachteten Operator auf den Stack. 
				postfixStack.push(cur);
				postfix += " ";
				break;
				// Falls es sich bei dem aktuellen Operator um ein '*' oder '/' 
				// oder '%' handelt (Letzteres musste nicht beruecksichtig werden)...
			case '*':
			case '/':
			case '%':
				// (folgende Abfrage war laut Aufgabenstellung nicht gefordert)
				if (!isValidOperation(input, i)) return null;
				// ...schau auf dem Stack nach, ob ein Element mit
				// gleicher Prioritaet vorhanden ist und
				// (Auswertung von links nach rechts)...
				while (!postfixStack.isEmpty()) {
					c = postfixStack.pop();
					if ((c == '+') || (c == '-')) {
						postfixStack.push(c);
						break;
					}
					else postfix += " " + c;
				}
				// ...lege im Anschluss das Aktuelle auf den Stack ab.
				postfixStack.push(cur);
				postfix += " ";
				break;
				// Falls das aktuell betrachtete Element jedoch kein Operator ist...
			default:
				// ...ueberpruefe ob es sich um eine gueltige Zahlendarstellung handelt.
				try { parseValue = Integer.parseInt(cur+""); postfix += parseValue; }
				// Falls dem nicht so ist, liegt eine ungueltige Eingabe vor.
				catch(NumberFormatException ex) { return null; }
				break;
			}
		}
		// Zum Schluss muessen noch alle Operatoren, die sich evt. noch auf dem Stack
		// befinden, dem Ausgabestring hinzugefuegt werden.
		while (!postfixStack.isEmpty())
			postfix += " " + postfixStack.pop();

		return postfix;
	}

	private boolean isBracket_Open(char c) {
		return ((c == '(') || (c == '{') || (c == '[') || (c == '<'));
	}

	private boolean isBracket_Close(char c) {
		return ((c == ')') || (c == '}') || (c == ']') || (c == '>'));
	}

	private boolean isCorrectBracketPair(char open, char close) {
		return (((open == '(') && (close == ')')) || ((open == '{') && (close == '}')) || ((open == '[') && (close == ']')) || ((open == '<') && (close == '>')));
	}

	private boolean isValidOperation(String input, int id) {
		// Diese Bedingung war laut Aufgabenstellung nicht gefordert!!!
		return (((id-1) >= 0) && (Character.isDigit(input.charAt(id-1)) || isBracket_Close(input.charAt(id-1))) 
				&& ((id+1) < input.length()) && (Character.isDigit(input.charAt(id+1)) || isBracket_Open(input.charAt(id+1))));
	}

	public int getValueOf_Klammerung(String input) {
		System.out.println("Eingabe (Infix):\t"+input);
		// Fuer die Berechnungen ist es wichtig dass in der Eingabe
		// keinerlei Leerzeichen enthalten sind (replaceAll)!
		String postfix = infixToPostfix_Klammerung(input.replaceAll(" ", ""));
		if (postfix != null) {
			System.out.println("Stack (Postfix): \t"+postfix);
			int result = calculateValue(postfix);
			System.out.println("Berechnungsergebnis:\t"+result);
			return result;
		} else {
			System.out.println("Uengueltige Eingabe");
			return 0;
		}
	}
}
