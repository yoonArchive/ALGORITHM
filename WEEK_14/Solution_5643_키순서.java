import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5643_키순서 {
	final static int SMALLERCHECK = -1;
	final static int TALLERCHECK = 1;
	static int N, cnt;
	static int compare[][];
	static boolean isVisited[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("5643.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			compare = new int[N + 1][N + 1];
			StringTokenizer st = null;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int small = Integer.parseInt(st.nextToken());
				int tall = Integer.parseInt(st.nextToken());
				compare[small][tall] = TALLERCHECK;
				compare[tall][small] = SMALLERCHECK;
			}

			isVisited = new boolean[N + 1];

			int res = 0;
			for (int i = 1; i <= N; i++) {
				cnt = 0;
				Arrays.fill(isVisited, false);
				dfs(i, TALLERCHECK);
				
				Arrays.fill(isVisited, false);
				dfs(i, SMALLERCHECK);
				
				if (cnt == N - 1)
					res++;
			}
			sb.append("#" + tc + " " + res).append("\n");
		}
		System.out.println(sb.toString());

	}

	private static void dfs(int v, int checkNum) {
		for (int i = 1; i <= N; i++) {
			if (compare[v][i] == checkNum && !isVisited[i]) {
				cnt++;
				isVisited[i] = true;
				dfs(i, checkNum);
			}
		}
	}
	
}
