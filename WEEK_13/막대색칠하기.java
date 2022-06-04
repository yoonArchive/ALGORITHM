import java.util.Scanner;

public class 막대색칠하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] stick = new int[N + 1];
		stick[1] = 2;
		stick[2] = 5;

		for (int i = 3; i <= N; i++)
			stick[i] = 2 * stick[i - 1] + stick[i - 2];

		System.out.println(stick[N]);
		sc.close();
	}
}