import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_16926_배열돌리기1 {
	static int N, M;
	static int arr[][];
	static int square;

	public static void rotate(int tmp) {
		if (tmp == square)
			return;

		int store = arr[tmp][tmp]; // 왼쪽 맨 위

		// 맨위 -> 왼쪽으로 이동
		for (int i = tmp; i < M - tmp - 1; i++) {
			arr[tmp][i] = arr[tmp][i + 1];
		}

		// 맨오른쪽 -> 위쪽으로 이동
		for (int i = tmp; i < N - tmp - 1; i++) {
			arr[i][M - tmp - 1] = arr[i + 1][M - tmp - 1];
		}

		// 맨밑 -> 오른쪽으로 이동
		for (int i = M - tmp - 1; i > tmp; i--) {
			arr[N - tmp - 1][i] = arr[N - tmp - 1][i - 1];
		}

		// 맨왼쪽 -> 밑으로 이동
		for (int i = N - tmp - 1; i > tmp + 1; i--) {
			arr[i][tmp] = arr[i - 1][tmp];
		}

		arr[tmp + 1][tmp] = store;
		rotate(tmp + 1);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		square = Math.min(N, M) / 2; // 한 번 rotate 시 회전해야하는 내부 사각형 개수

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < R; ++i) {
			rotate(0);
		}

		for (int[] row : arr) {
			for (int num : row) {
				sb.append(num + " ");
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

}
