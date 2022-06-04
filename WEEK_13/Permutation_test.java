import java.util.Arrays;

public class Permutation_test {

	static int[] arr = { 1, 3, 5, 7, 9 };
	static int N = 5;
	static int R = 3;
	static int numbers[];

	public static void main(String[] args) {
		numbers = new int[R];
		combination(0, 0);
	}

	private static void combination(int start, int count) {
		if (count == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for (int i = start; i < N; i++) {
			numbers[count] = arr[i];
			combination(i + 1, count + 1);
		}
	}
}
