//Abgabe von Ewald Bayer und Lukas Schmidtmann	
//Nicht mehr genug Energie und Phantasie Punkt-vor-Strich auﬂerhalb der Klammern zu implementieren, sorry. [Programm rechnet (A)-(B)*(C)+(D) wie ((A)-B))*(C)+(D)]
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;


public class StackRechner {

	private String infixToPostfix(String infix){
		if(!checkKlammern(infix)){
			System.out.println("Angegebener infix ist nicht korrekt geklammert!");
			return "0";
		}
		String returnString = "";
		int[] operationVorhanden = new int[4];
		boolean spaeter = false;
		Stack<String> spaeterRechnen = new Stack<String>();
		String jetztRechnen = "";
		for (int i = 0; i < infix.length(); i++)
			if(infix.charAt(i)=='('){
				spaeterRechnen.push(jetztRechnen);
				jetztRechnen = "";	
			}else if(infix.charAt(i)==')'){
				returnString += infixToPostfix(jetztRechnen);
				if(spaeter)
					spaeter = false;
				if(i != infix.length() -1)
					if(infix.charAt(i+1)=='*'||infix.charAt(i+1)=='/')
						spaeter = true;
					else
						spaeter = false;
				if(!spaeter)
					try{
						String tempString = infixToPostfix(spaeterRechnen.pop());
						if(tempString.length() > 1)
							returnString =  tempString.substring(0, tempString.length()-1) + " " + returnString + " " + tempString.charAt(tempString.length()-1);
						else
							returnString += " " + tempString + " ";
					}catch(EmptyStackException e){/*Bei leerem Stack normal weiter*/}
				jetztRechnen = "";
			}else if(infix.charAt(i)!=' ')
				jetztRechnen += infix.charAt(i);
		if(spaeter)
			try{
				String tempString = infixToPostfix(spaeterRechnen.pop());
				returnString =  tempString.substring(0, tempString.length()-1) + returnString + tempString.charAt(tempString.length()-1);
			}catch(EmptyStackException e){/*Bei leerem Stack normal weiter*/}
		if(jetztRechnen != ""){
			String[] add = jetztRechnen.split("\\+");
			operationVorhanden[0] = add.length - 1;
			if(jetztRechnen.endsWith("+"))
				operationVorhanden[0]++;
			if(jetztRechnen.startsWith("+"))
				operationVorhanden[0]++;
			for(String addi: add){
				if(addi.equals(""))
					operationVorhanden[0]--;
				else{
					String[] sub = addi.split("-");
					operationVorhanden[1] = sub.length - 1;
					if(jetztRechnen.endsWith("-"))
						operationVorhanden[1]++;
					if(jetztRechnen.startsWith("-"))
						operationVorhanden[1]++;
					for(String subi: sub){
						if(subi.equals(""))
							operationVorhanden[1]--;
						else{
							String[] mul = subi.split("\\*");
							operationVorhanden[2] = mul.length - 1;
							if(jetztRechnen.endsWith("*"))
								operationVorhanden[2]++;
							if(jetztRechnen.startsWith("*"))
								operationVorhanden[2]++;
							for(String muli: mul){
								if(muli.equals(""))
									operationVorhanden[2]--;
								else{
									String[] div = muli.split("/");
									operationVorhanden[3] = div.length - 1;
									if(jetztRechnen.endsWith("/"))
										operationVorhanden[3]++;
									if(jetztRechnen.startsWith("/"))
										operationVorhanden[3]++;
									for(String divi: div){
										if(divi.equals(""))
											operationVorhanden[3]--;
										else{
											if(returnString.length() == 0)
												returnString += sauberes(divi);
											else
												returnString += " " + sauberes(divi);
										}
									}
									while(operationVorhanden[3] > 0){
										returnString += " /";
										operationVorhanden[3]--;
									}
								}
							}
							while(operationVorhanden[2] > 0){
								returnString += " *";
								operationVorhanden[2]--;
							}
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
			}
			while(operationVorhanden[0] > 0){
				returnString += " +";
				operationVorhanden[0]--;
			}
		}
		while(returnString.startsWith(" "))
			returnString = returnString.substring(1);
		while(returnString.endsWith(" "))
			returnString = returnString.substring(0, returnString.length()-1);
		String realRS = returnString;
		returnString = "";
		for (int i = 0; i < realRS.length(); i++) {
			try{
				if(realRS.charAt(i)!=' ')
					returnString += realRS.charAt(i);
				else if(realRS.charAt(i+1)!= ' ')
					returnString += realRS.charAt(i) + " ";
				if((realRS.charAt(i)=='+'||realRS.charAt(i)=='-'||realRS.charAt(i)=='*'||realRS.charAt(i)=='/') && realRS.charAt(i+1) != ' ')
					returnString += " ";
			}catch(StringIndexOutOfBoundsException e){}
		}
		System.out.println(returnString);
		return returnString;
	}

	private int calculateValue(String postfix){
		Stack<Integer> s = new Stack<Integer>();
		StringTokenizer st = new StringTokenizer(postfix);
		while(st.hasMoreTokens()){
			String token = sauberes(st.nextToken());
			try{
				s.push(Integer.valueOf(token));
			}catch(NumberFormatException e){
				if(token.length() == 1){
					if(token.equals("+"))
						s.push(s.pop() + s.pop()); 
					else if(token.equals("*"))
						s.push(s.pop() * s.pop());
					else if(token.equals("-")){
						int tempPop = s.pop();
						s.push(s.pop() - tempPop);
					}else if(token.equals("/")){
						int tempPop = s.pop();
						s.push(s.pop() / tempPop);
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
		return calculateValue(infixToPostfix(input));
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
			if(infix.charAt(i)=='(') // F¸r jede offene Klammer
				klammern.push(true); // wird ein "true" auf den Stack gelegt
			else if(infix.charAt(i)==')') // Und f¸r jede geschlossene,
				try{
					klammern.pop(); //soll ein Element vom Stack genommen werden
				}catch(EmptyStackException e){ // Falls jedoch eine Klammer zu geht, bevor eine aufging, sind die Klammern definitiv nicht wohlgeformt
					return false; // und es wird false zur¸ckgegeben
				}
		}
		return klammern.empty(); // Sollten alle Klammern in richtiger Reihenfolge auf und zu gegangen sein, d¸rfte sich kein Element mehr im Stack befinden, ansonsten g‰be es eine offene Klammer ohne zugehˆrige geschlossene, in welchem Fall "false" der R¸ckgabewert w‰re.
	}
}
