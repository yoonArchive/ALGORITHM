import java.util.Arrays;

public class Solution_N개의최소공배수 {

	public static void main(String[] args) {
		int arr[] = { 2, 6, 8, 14 };
		solution(arr);
	}

	public static int solution(int[] arr) {
		Arrays.sort(arr);
		int length = arr.length - 1;
		int answer = arr[length];
		for (int i = length - 1; i >= 0; i--) {
			int gcd = getGCD(arr[i], answer);
			int lcm = (answer * arr[i]) / gcd;
			answer = lcm;
		}
		return answer;
	}

	private static int getGCD(int a, int b) {
		while (b % a != 0) {
			int tmp = b % a;
			b = a;
			a = tmp;
		}
		return a;
	}
}
