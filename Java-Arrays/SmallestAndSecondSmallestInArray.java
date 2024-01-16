import java.util.*;

class LearnYard {
    public static void main(String[] args) {
        // you code goes here
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = scan.nextInt();
		}

		int min_element = Integer.MAX_VALUE;
		int sec_min = Integer.MAX_VALUE;

		for (int i=0; i<n; i++) {
			if (arr[i] < min_element) {
				sec_min = min_element;
				min_element = arr[i];
			} else if (arr[i] < sec_min && arr[i] != min_element) {
				sec_min = arr[i];
			}
		}

		if (sec_min == Integer.MAX_VALUE) {
			min_element = -1;
			sec_min = -1;
		}

		int[] ans = new int[2];
		ans[0] = -1;
		ans[1] = -1;

		return ans;
    }
}