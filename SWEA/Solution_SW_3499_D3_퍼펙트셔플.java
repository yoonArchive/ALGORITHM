import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SW_D3_3499_퍼펙트셔플 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("sample_input (2).txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			Queue<String> q1 = new LinkedList<>();
			Queue<String> q2 = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				if (N % 2 == 0) {
					if (i < N / 2)
						q1.offer(st.nextToken());
					else
						q2.offer(st.nextToken());
				} else {
					if (i <= N / 2)
						q1.offer(st.nextToken());
					else
						q2.offer(st.nextToken());
				}

			}

			sb.append("#" + tc + " ");
			for (int i = 0; i < N; i++) {
				if (i % 2 == 0)
					sb.append(q1.poll() + " ");
				else
					sb.append(q2.poll() + " ");
			}
			sb.append("\n");

		}
		System.out.println(sb.toString());

	}

}
