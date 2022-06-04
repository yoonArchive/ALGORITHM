import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014_활주로건설 {
	static int N, X;
	static int height[][];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("4014.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			height = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					height[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int count = 0;

			for (int i = 0; i < N; i++) {
				if (isValid(height[i])) // 행 count
					count++;
				int[] col = new int[N];
				for (int j = 0; j < N; j++)
					col[j] = height[j][i];
				if (isValid(col)) // 열 count
					count++;
			}
			sb.append("#" + tc + " " + count).append("\n");
		}
		System.out.println(sb.toString());

	}

	private static boolean isValid(int[] arr) {
		boolean[] isBuilt = new boolean[N];
		for (int i = 1; i < N; i++) {
			if (arr[i - 1] == arr[i])
				continue;
			int diff = Math.abs(arr[i - 1] - arr[i]);
			if (diff != 1) // 인접칸과 높이가 다를 때 차이가 1이 아니면 false
				return false;
			if (arr[i - 1] > arr[i]) { // arr[i-1]보다 높이가 1 작다면
				for (int j = 0; j < X; j++) {
					int tmp = i + j;
					if (tmp >= N || isBuilt[tmp] || arr[tmp] != arr[i])
						return false;
					isBuilt[tmp] = true;
				}
			} else { // arr[i-1]보다 높이가 1 크다면
				for (int j = 1; j <= X; j++) {
					int tmp = i - j;
					if (tmp < 0 || isBuilt[tmp] || arr[tmp] != arr[i - 1])
						return false;
					isBuilt[tmp] = true;
				}
			}
		}
		return true;
	}

}
