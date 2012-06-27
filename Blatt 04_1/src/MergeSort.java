
public class MergeSort {
	public static void doMergeSort(char[] arr){
		int i = 2;
		int j = arr.length/2;
		while(i != arr.length){
			char[][] merge = new char[i][j];
			for (int k = 0; k < merge.length; k++) {
				for (int k2 = 0; k2 < merge[k].length; k2++) {
					merge[k][k2] = arr[k*merge[k].length + k2];
				}
			}
		}
	}
}
