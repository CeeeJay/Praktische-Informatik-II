package Aufgabe_6;

public class Sort {

	char[] array;
	
	public Sort() {
		super();
		this.quicksort();
		this.combsort();
	}

	public void combsort(){
		System.out.println();
		System.out.println("Combsort:");
		array = new char[] {'S','O','R','T','I','E','R','V','E','R','F','A','H','R','E','N'};
		int step = array.length;
		boolean changed;
		do {
			this.print();
			System.out.println("\t" + step);
			changed = false;
			if(step > 1) step = (int)(step/1.3);
			for (int i = 0; i < array.length - step; i++) {
				if (array[i] > array[i + step]) {
					this.exchange(i, i + step);
					changed = true;
				}
			}
		} while (changed || step > 1);
	}

	public void quicksort(){
		System.out.println();
		System.out.println("Quicksort:");
		array = new char[] {'S','O','R','T','I','E','R','V','E','R','F','A','H','R','E','N'};
		this.quicksort(0, array.length - 1);
	}
	
	private void quicksort(int left, int right){
		if (left < right) {
			int pivot = pivot(left, right);
			this.print();
			System.out.println("\t" + left+ "\t" + right + "\t" + pivot);
			quicksort(left, pivot - 1);
			quicksort(pivot + 1, right);
		}
	}

	private void print() {
		for (char i : array) {
			System.out.print("" + i);
		}

	}
	private void exchange(int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public int pivot(int left, int right){
		int e, i, j;
	      e = array[right];               
	      i     = left;
	      j     = right-1;
	      while(i<=j) {
	         if (array[i] > e) {     
	            this.exchange(i, j);
	            j--;
	         } else i++;            
	      }
	      this.exchange(i, right);
	      return i;
	}

	public static void main(String[] args) {

		Sort test = new Sort();

	}

}
