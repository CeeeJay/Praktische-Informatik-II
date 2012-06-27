
public class main {

	public static void main(String[] args) {
		
		char[] chararray = {'S','O','R','T','I','E','R','V','E','R','F','A','H','R','E','N'};
		char[] sortiert = new char[16];
		
//		QuickSort.medianQuickSort(chararray, 0, 15);
		CombSort.combSort(chararray);
	}
}
