import java.util.Scanner;

public class 아파트색칠하기 {

	public static void main(String[] args) {
		// 연습문제 1
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int color[] = new int[n + 1];
		color[0] = 1;
		color[1] = 2;
		for (int i = 2; i <= n; i++)
			color[i] = color[i - 1] + color[i - 2];

		System.out.println(color[n]);
		sc.close();

	}

}
