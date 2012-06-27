
public class QuickSort {


	//Aus Skript Folie 03:

	public static void medianQuickSort(char[] a, int lo, int hi){
		int li = lo;
		int re = hi;
		int mid = (li+re)/2;
		//Vorsortieren
		if (a[li] > a[mid]) swap(a, li, mid);
		if (a[mid] > a[re]) swap(a, mid, re);
		if (a[li] > a[mid]) swap(a, li, mid);
		if ((re - li) > 2){
			char pivot = a[mid];
			swap(a, mid, re);
			int k = li;
			//Partitionieren
			for (int i= li; i < hi; i++){
				if (a[i] < pivot){
					swap(a,i,k);
					k++;
				}
			}
			swap(a,k,re);
			
			//Zwischenausgaben
			for (int i = 0; i < a.length; i++) {
				System.out.print(a[i] +" ");
			}
			System.out.println("       Pivotelement: " + pivot + " Linke Grenze: " + lo + " Rechte Grenze: " + hi);
			re = k-1; li = k+1;
			//Rekursive Aufrufe
			if (lo < re) medianQuickSort(a, lo, re);		
			if (li < hi) medianQuickSort(a, li, hi);
		}
	}
	private static void swap(char[] a, int lo, int hi){
		char temp = a[lo];
		a[lo] = a[hi];
		a[hi] = temp;
	}
}
