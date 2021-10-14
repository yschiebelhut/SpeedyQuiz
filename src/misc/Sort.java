package misc;

import java.util.Arrays;
import java.util.Random;

public class Sort {
//	public static int[] arr;
	public static int[] arr = { 3, 7, 2, 19, 9, 53, 6, 4, 32, -1 };

	public static void main(String[] args) {
		createRandomArray(10);

//		System.out.println(Arrays.toString(bubbleSort(arr)));
//		System.out.println(Arrays.toString(insertionSort(arr)));
//		System.out.println(Arrays.toString(selectionSort(arr)));
//		System.out.println(Arrays.toString(mergeSort(arr)));
		System.out.println(Arrays.toString(quickSort(arr)));
	}

	/**
	 * sorts the given array of integers with the bubble sorting algorithm
	 * 
	 * @param pArr array of integer values
	 * @return sortedArray
	 */
	public static int[] bubbleSort(int[] pArr) {
		// remember how far you have to compare
		for (int tmpEnd = pArr.length; tmpEnd > 0; tmpEnd--) {
			// go through the array
			for (int i = 0; i < tmpEnd - 1; i++) {
				// if the current value is bigger than the following one, swap them
				if (pArr[i] > pArr[i + 1]) {
					int tmp = pArr[i];
					pArr[i] = pArr[i + 1];
					pArr[i + 1] = tmp;
				}
			}
		}
		return pArr;
	}

	/**
	 * sorts the given array of integers with the insertion sorting algorithm
	 * 
	 * @param pArr array of integer values
	 * @return sortedArray
	 */
	public static int[] insertionSort(int[] pArr) {
		// start with the second value and repeat the procedure for each following value
		// in the array
		for (int i = 1; i < pArr.length; i++) {
			// if the current value is smaller than the prior swap it backwards, till it's
			// in the right position
			if (pArr[i] < pArr[i - 1]) {
				for (int j = i; (j > 0) && (pArr[j] < pArr[j - 1]); j--) {
					int tmp = pArr[j - 1];
					pArr[j - 1] = pArr[j];
					pArr[j] = tmp;
				}
			}
		}
		return pArr;
	}

	/**
	 * Wrapper method for the merge sort algorithm, so it can be called easily on
	 * every array of integers without having to calculate important values for
	 * calling the actual merge sort.
	 * 
	 * @param pArr unsorted array of integers
	 * @return sorted array of integers
	 */
	public static int[] mergeSort(int[] pArr) {
		mergeSort(pArr, 0, pArr.length);
		return pArr;
	}

	public static void mergeSort(int[] pArr, int low, int high) {
		if ((high - low) <= 1) // when there is only one element, it's already sorted
			return;
		int split = (low + high) / 2;

		mergeSort(pArr, low, split);
		mergeSort(pArr, split, high);

		int[] scratch = new int[split - low];
		for (int i = low; i < split ; i++) {
			scratch[i - low] = pArr[i];
		}
		int tmp1 = 0;
		int tmp2 = split;
		int i = low;
		while (i < tmp2 && tmp2 < high) {
			if (scratch[tmp1] <= pArr[tmp2]) {
				pArr[i++] = scratch[tmp1++];
			} else
				pArr[i++] = pArr[tmp2++];
		}
		while (i < tmp2) {
			pArr[i++] = scratch[tmp1++];
		}
	}
	
	/*
	 * mergesort(low high)
        falls high-low kleiner gleich 1
                überspringe
        split gleich (low+high) / 2
        mergesort(low split)
        mergesort(split high)

        kopiere [low bis split-1] in neues Array tmp[]

        hilf1 = 0
        hilf2 = split
        zähler = low

        während (zähler < hilf2 && hilf2 < high
                falls ( tmp[hilf1] < array[hilf2] )
                        array[i++] = tmp[hilf1++]
                sonst
                        array[i++] = array[hilf2++]
        während (zähler < hilf2)
                array[i++] = tmp[hilf1++]
	 */
//	public static void mergeSort(int[] pArr, int low, int high) {
//		if(high-low <= 1) return;
//		int split = (high+low) / 2;
//		mergeSort(pArr, low, split);
//		mergeSort(pArr, split, high);
//		
//		int[] tmp = new int[split-low];
//		for(int i=low; i<split; i++) {
//			tmp[i-low] = pArr[i];
//		}
//		
//		int help1 = 0;
//		int help2 = split;
//		int i = low;
//		
//		while(i<help2 && help2 < high) {
//			if(tmp[help1]<=pArr[help2]) {
//				pArr[i++] = tmp[help1++];
//			}
//			else {
//				pArr[i++] = pArr[help2++];
//			}
//		}
//		
//		while (i<help2) {
//			pArr[i++] = tmp[help1++];
//		}
//	}

	/**
	 * sorts the given array of integers with the selection sorting algorithm
	 * 
	 * @param pArr array of integer values
	 * @return sortedArray
	 */
	public static int[] selectionSort(int[] pArr) {
		// logically separate the sorted part of the array from the unsorted
		for (int curPos = 0; curPos < pArr.length; curPos++) {
			// remember the value from the first unsorted position
			int min = pArr[curPos];
			// go through the rest of the unsorted array
			for (int i = curPos + 1; i < pArr.length; i++) {
				// if smaller value is found, insert the current one into the position of the
				// smaller one and from then remember the new smallest value
				if (min > pArr[i]) {
					int tmp = min;
					min = pArr[i];
					pArr[i] = tmp;
				}
			}
			// paste the smallest value found into the first position of the unsorted array
			// which then becomes the last position of the sorted part
			// all other values remain in the array as they just swapped places with the
			// smallest value
			pArr[curPos] = min;
		}
		return pArr;
	}

	/**
	 * Wrapper method for the quick sort algorithm, so it can be called easily on
	 * every array of integers without having to calculate important values for
	 * calling the actual quick sort.
	 * 
	 * @param pArr unsorted array of integers
	 * @return sorted array of integers
	 */
	public static int[] quickSort(int[] pArr) {
		quickSort(pArr, 0, pArr.length);
		return pArr;
	}

	public static void quickSort(int[] pArr, int low, int high) {
		if ((high - low) <= 1) // when there is only one element, it's already sorted
			return;
		int pivot = pArr[high - 1]; // set the pivot to the value of the last index in the range, given by the
									// parameters
		int split = low; // set the splitting point to the very first index; variable used to mark the
							// end of the section containing elements smaller than the pivot

		for (int i = low; i < high - 1; i++) { // rush through each element in the given range
			// if the current element is smaller than the pivot element, swap it to the end
			// of the splitting area and increase the latter by one
			if (pArr[i] < pivot) {
				int tmp = pArr[i];
				pArr[i] = pArr[split];
				pArr[split] = tmp;
				split++;
			}
		}

		// swap the pivot element to the end of the section of smaller elements than the
		// pivot
		int tmp = pArr[split];
		pArr[split] = pArr[high - 1];
		pArr[high - 1] = tmp;

		// recursion
		quickSort(pArr, low, split); // call the quick sort on the elements before the pivot
		quickSort(pArr, ++split, high); // call the quick sort on the elements behind the pivot
	}

	/**
	 * small method to fill the array "arr" with random integers between -50 and 50.
	 * 
	 * @param length how many indexes the array will have
	 */
	public static void createRandomArray(int length) {
		Random rand = new Random();
		arr = new int[length]; // set the length for the array
		for (int i = 0; i < arr.length; i++) { // fill each index of the array with a new random number
			arr[i] = rand.nextInt(100) - 50;
		}
	}
}