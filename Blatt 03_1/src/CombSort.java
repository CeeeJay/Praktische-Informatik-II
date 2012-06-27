
public class CombSort {

	static int laenge;
	static boolean swap;
	static char temp;


	public static void combSort(char[]chararray){

		laenge = chararray.length;

		do {
			swap = false;

			laenge = (int)(laenge/1.3);
			
			if (laenge < 1) {
				laenge = 1;
			}

			for (int i = 0; i < chararray.length-laenge; i++) {
				if (chararray[i] > chararray[i+laenge]) {
					temp = chararray[i];
					chararray[i] = chararray[i+laenge];
					chararray[i+laenge] = temp;
					swap = true;
					
					//Zwischenausgaben
					for (int j = 0; j < chararray.length; j++) {
						System.out.print(chararray[j] + " ");
					}
					System.out.println("     h: " + laenge);	
				}
			}
		} while (swap == true || laenge != 1 );
	}
}
