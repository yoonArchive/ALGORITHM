import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_D4_3234_준환이의양팔저울 {
	static int ans;

	public static void measure(int left, int right, int count, int[] weight, int flag, int N) {

		if (count == N) {
			ans++;
			return;
		}

		for (int i = 0; i < N; i++) {
			int nowWeight = weight[i];

			if ((flag & 1 << i) != 0)
				continue;

			measure(left + nowWeight, right, count + 1, weight, flag | 1 << i, N);

			if (right + nowWeight <= left)
				measure(left, right + nowWeight, count + 1, weight, flag | 1 << i, N);

		}

	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input_3234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); 
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] weight = new int[N]; 

			ans = 0;
			for (int i = 0; i < N; i++)
				weight[i] = Integer.parseInt(st.nextToken());

			measure(0, 0, 0, weight, 0, N);
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb.toString());
		br.close();

	}

}
