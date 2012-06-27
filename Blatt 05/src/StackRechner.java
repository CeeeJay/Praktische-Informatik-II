//Abgabe von Ewald Bayer und Lukas Schmidtmann	
import java.util.Stack;
import java.util.StringTokenizer;


public class StackRechner {
	
	private String infixToPostfix(String infix){
		// String, der am Ende zurückgegeben wird
		String returnString = "";
		// Ein Array von int-Variablen, die angeben, wie oft die Operation am Ende seiner Schleife hinten angefügt wird (0='+', 1='-', 2='*', 3='/')
		int[] operationVorhanden = new int[4];
		//(Vorgehensweise nur für + Kommentiert) String wird in Tokens geteilt, wenn ein '+' vorkommt (mit, ohne oder wie viele Leerzeichen ist somit egal)
		StringTokenizer add = new StringTokenizer(infix, "+");
		// Sollte kein '+' vorkommen, wird es nur ein Token (den ganzen Term) geben und somit wird auch kein '+' hinten angefügt
		operationVorhanden[0] = add.countTokens() - 1;
		// Vorgang wird für jedes Token wiederholt (kommt kein '+' vor, wird dies nur einmal ausgeführt)
		while(add.hasMoreTokens()){
			// Token wird als String zwischengespeichert
			String addToken = add.nextToken();
			// Das erste Token wird nun auf Untertokens geteilt, wenn ein '-' vorkommt und so geht es weiter
			StringTokenizer sub = new StringTokenizer(addToken, "-"); //Durch diese Reihenfolge wird zuerst / gerechnet, dann *, dann - und dann + - dadurch ist Punkt-vor-Strich berücksichtigt
			// Siehe '+'
			operationVorhanden[1] = sub.countTokens() - 1;
			while(sub.hasMoreTokens()){
				String subToken = sub.nextToken();
				StringTokenizer mul = new StringTokenizer(subToken, "*");
				operationVorhanden[2] = mul.countTokens() - 1;
				while(mul.hasMoreTokens()){
					String mulToken = mul.nextToken();
					StringTokenizer div = new StringTokenizer(mulToken, "/");
					operationVorhanden[3] = div.countTokens() - 1;
					while(div.hasMoreTokens()){
						String divToken = div.nextToken();
						// Der Sauberkeit halber, wird sichergestellt, dass der Postfix ohne Leerzeichen am Anfang zurückgegeben wird
						if(returnString.length() == 0)
							returnString += sauberes(divToken); // Dieser Vorgang befreit das Token von überflüssigen Leerzeichen und wandelt "Buchstaben" in Nullen um
						else
							returnString += " " + sauberes(divToken); // Siehe if() + Leerzeichen am Anfang, um die Zahlen von einander zu trennen
					}
					// Siehe '+'
					while(operationVorhanden[3] > 0){
						returnString += " /";
						operationVorhanden[3]--;
					}
				}
				// Siehe '+'
				while(operationVorhanden[2] > 0){
					returnString += " *";
					operationVorhanden[2]--;
				}
			}
			// Siehe '+'
			while(operationVorhanden[1] > 0){
				if(operationVorhanden[1] == 1) // Das letzte bzw. das einzige '-' wird auch als '-' eingetragen
					returnString += " -";
				else
					returnString += " +"; // Weiter "rechts" stehende '-'-Operationen werden eigentlich vorher adddiert (a - b - c = a - (b+c))
				operationVorhanden[1]--;
			}
		}
		// Je nach dem, wie oft es im Token (bei + eig. im ganzen String) vorkam, wird das '+' hinten angehängt
		while(operationVorhanden[0] > 0){
			returnString += " +";
			operationVorhanden[0]--;
		}
//		System.out.println(returnString); // Ehemalige Testausgabe
		return returnString;
	}
	
	private int calculateValue(String postfix){
		// Der Stack...
		Stack<Integer> s = new Stack<Integer>();
		StringTokenizer st = new StringTokenizer(postfix);
		// Für den Fall, dass sich eine negative Zahl ergibt, muss das Vorzeichen gewechselt werden (z.B. 12-2-9 soll nich 19 ergeben...[2-9=-7, 12-(-7)=19])
		while(st.hasMoreTokens()){
			// Zur Sicherheit wird das Token nochmals "gesäubert"
			String token = sauberes(st.nextToken());
			// Es wird versucht, das Token als Zahl umgewandelt in den vorher definierten Stack zu pushen
			try{
				s.push(Integer.valueOf(token));			// Sollte das misslingen (im Fall, dass es sich um eine Operation handelt oder zuvor "übersehene" Fehler auftreten)
			}catch(NumberFormatException e){
			// wird geprüft, ob das Token nun eine Operation ist, ansonsten wird einfach 0 gepusht
				if(token.length() == 1){
					if(token.equals("+"))
						s.push(s.pop() + s.pop()); // Die letzten 2 Objekt im Stack werden addiert 
					else if(token.equals("*"))
						s.push(s.pop() * s.pop()); // bzw. multipliziert und durch das Ergebnis "ersetzt"
					else if(token.equals("-")){
						int tempPop = s.pop(); // Da wir hierfür "dasVorletzte - dasLetzte" ...
						s.push(s.pop() - tempPop);
					}else if(token.equals("/")){
						int tempPop = s.pop(); // ... bzw. "dasVorletzte / dasLetzte" rechnen müssen, wird "dasLetzte" zwischen gespeichert
						s.push(s.pop() / tempPop); // und erst anschließend gerechnet
					}else
						s.push(0);
				}else{
					s.push(0);
				}
			}
		}
		return s.pop();
	}
	
	public int getValueOf(String input){
		return calculateValue(infixToPostfix(input)); // Der input-String wird von Infix zu Postfix umgewandelt und nachdem mit dem Ergebnis gerechnet wurde, wird das Gesamtergebnis von der Methode zurückgegeben. Sorry, aber ich stehe halt auf Einzeiler dieser Art. (: 
	}
	
	//Diese Methode ist entstanden, um Tokens bereits direkt nach der Überführung ins Postfix vor "Buchstaben" zu befreien und hat sich später auch als praktisch erwiesen.
	private String sauberes(String token){
		//Etwaige Leerzeichen werden am Anfang und am Ende ausradiert
		while(token.startsWith(" "))
			token = token.substring(1);
		while(token.endsWith(" "))
			token = token.substring(0, token.length()-1);
		//Diese Stelle folgt dem Beispiel aus der calculateValue-Methode
		try{
			return Integer.valueOf(token) + "";
		}catch(NumberFormatException e){
			//Sollte es sich beim Token um keine saubere Zahl handeln, werden höchsten noch Operationen zurückgegeben, ansonsten eine 0.
			if(!(token.equals("+")||token.equals("-")||token.equals("*")||token.equals("/")))
				return 0+"";
			else
				return token;
		}
	}
}
