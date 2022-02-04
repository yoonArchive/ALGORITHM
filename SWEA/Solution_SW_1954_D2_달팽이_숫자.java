import java.util.Arrays;
import java.util.Scanner;

public class Solution_SW_1954_D2_달팽이_숫자 {
	static int N;
	static int num = 0;
	static int arr[][];

	public static void write_right(int row, int col) {
		for (int i = 0; i < N; i++) {
			if (num == N * N)
				return;
			if (arr[row][i] == -1) {
				arr[row][i] = ++num;
				if (col < N - 1)
					col++;
			}
		}
		write_down(row, col);
	}

	public static void write_down(int row, int col) {
		for (int i = 0; i < N; i++) {
			if (num == N * N)
				return;
			if (arr[i][col] == -1) {
				arr[i][col] = ++num;
				if (row < N - 1)
					row++;
			}
		}
		write_left(row, col);
	}

	public static void write_left(int row, int col) {
		for (int i = N - 1; i >= 0; i--) {
			if (num == N * N)
				return;
			if (arr[row][i] == -1) {
				arr[row][i] = ++num;
				if (col > 0)
					col--;
			}
		}
		write_up(row, col);
	}

	public static void write_up(int row, int col) {
		for (int i = N - 1; i >= 0; i--) {
			if (num == N * N)
				return;
			if (arr[i][col] == -1) {
				arr[i][col] = ++num;
				if (row > 0)
					row--;
			}
		}
		write_right(row, col);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			arr = new int[N][N];
			for (int i = 0; i < N; i++)
				Arrays.fill(arr[i], -1);
			num=0;
			write_right(0, 0);
			
			System.out.println("#"+tc);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
		sc.close();
	}
}
