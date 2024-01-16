import java.util.*;
class LearnYard {
    public static void main(String[] args) {
        // you code goes here
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		int[] arr = new int[n];

		for (int i=0; i < n; i++) {
			arr[i] = scan.nextInt();
		}

		int start = 0;
		int end = arr.length -1;

		while (start < end) {
			int swap = arr[start];
			arr[start] = arr[end];
			arr[end] = swap;
			start++;
			end--;
		} 

		for (int val: arr) {
			System.out.println(val);
		}
    }
}