//Abgabe von Ewald Bayer und Lukas Schmidtmann	
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;


public class StackRechner_alt {

	private String infixToPostfix(String infix){
		if(!checkKlammern(infix))
			return "Angegebener infix ist nicht korrekt geklammert!";
		String returnString = "";
		String[] geklammert = infix.split("\\(");
		Stack<String> geklammert2 = new Stack<String>();
		for (int i = 0; i < geklammert.length; i++)
			geklammert2.push(geklammert[i]);
		int[] operationVorhanden = new int[4];
		while (!geklammert2.empty()) {
			String letztes = geklammert2.pop();
			switch (letztes.charAt(letztes.length()-1)) {
			case '+':
				operationVorhanden[0]++;
				break;
			case '-':
				operationVorhanden[1]++;
				break;
			case '*':
				operationVorhanden[2]++;
				break;
			case '/':
				operationVorhanden[3]++;
				break;
			default:
				break;
			}
			String[] untere = letztes.split("\\)");
			for (int i = 0; i < untere.length; i++) {
				if(1 < untere.length){
					while(i < untere.length){
						
					}
					break;
				}
				String[] add = untere[i].split("\\+");
				operationVorhanden[0] = add.length - 1;
				for(String addi: add){
					String[] sub = addi.split("-"); //Durch diese Reihenfolge wird zuerst / gerechnet, dann *, dann - und dann + - dadurch ist Punkt-vor-Strich berücksichtigt
					operationVorhanden[1] = sub.length - 1;
					for(String subi: sub){
						String[] mul = subi.split("\\*");
						operationVorhanden[2] = mul.length - 1;
						for(String muli: mul){
							String[] div = muli.split("/");
							operationVorhanden[3] = div.length - 1;
							for(String divi: div){
								if(returnString.length() == 0)
									returnString += sauberes(divi);
								else
									returnString += " " + sauberes(divi);
							}
							while(operationVorhanden[3] > 0){
								returnString += " /";
								operationVorhanden[3]--;
							}
						}
						while(operationVorhanden[2] > 0){
							returnString += " *";
							operationVorhanden[2]--;
						}
					}
					while(operationVorhanden[1] > 0){
						if(operationVorhanden[1] == 1)
							returnString += " -";
						else
							returnString += " +";
						operationVorhanden[1]--;
					}
				}
				while(operationVorhanden[0] > 0){
					returnString += " +";
					operationVorhanden[0]--;
				}	
			}
		}
		System.out.println(returnString);
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

	private String sauberes(String token){
		while(token.startsWith(" "))
			token = token.substring(1);
		while(token.endsWith(" "))
			token = token.substring(0, token.length()-1);
		try{
			return Integer.valueOf(token) + "";
		}catch(NumberFormatException e){
			if(!(token.equals("+")||token.equals("-")||token.equals("*")||token.equals("/")))
				return 0+"";
			else if(token.equals((char)255))
				return token;
			else
				return token;
		}
	}
	
	private boolean checkKlammern(String infix){
		Stack<Boolean> klammern = new Stack<Boolean>(); // Boolean hat eigentlich keine Bedeutung
		for (int i = 0; i < infix.length(); i++) {
			if(infix.charAt(i)=='(') // Für jede offene Klammer
				klammern.push(true); // wird ein "true" auf den Stack gelegt
			else if(infix.charAt(i)==')') // Und für jede geschlossene,
				try{
					klammern.pop(); //soll ein Element vom Stack genommen werden
				}catch(EmptyStackException e){ // Falls jedoch eine Klammer zu geht, bevor eine aufging, sind die Klammern definitiv nicht wohlgeformt
					return false; // und es wird false zurückgegeben
				}
		}
		return klammern.empty(); // Sollten alle Klammern in richtiger Reihenfolge auf und zu gegangen sein, dürfte sich kein Element mehr im Stack befinden, ansonsten gäbe es eine offene Klammer ohne zugehörige geschlossene, in welchem Fall "false" der Rückgabewert wäre.
	}
}
