import java.util.Arrays;

public class Combination_test {

	static int[] arr = { 1, 3, 5, 7, 9 };
	static int numbers[];

	public static void main(String[] args) {
		numbers = new int[5];
		permutaion(0, 0);
	}

	private static void permutaion(int count, int flag) {
		if (count == 5) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for (int i = 0; i < 5; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			numbers[count] = arr[i];
			permutaion(count + 1, flag | 1 << i);
		}
	}
}
