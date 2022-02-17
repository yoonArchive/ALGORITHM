import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2961_도영이가만든맛있는음식_조합_비트마스킹ver {
	static int foodInfo[][];
	static int limit, N;
	static int min;

	public static void combination(int count, int start, int flag) {
		if (count == limit) {
			int sourness = 1;
			int bitterness = 0;
			for (int i = 0; i < N; i++) {
				if ((flag & 1 << i) != 0) {
					sourness *= foodInfo[i][0];
					bitterness += foodInfo[i][1];
				}
			}
			int diff = sourness > bitterness ? sourness - bitterness : bitterness - sourness;
			min = Math.min(min, diff);
			return;
		}

		for (int i = start; i < N; i++) {
			combination(count + 1, i + 1, flag | 1 << i);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine()); // 재료의 개수
		foodInfo = new int[N][2]; // 0열: 신맛, 1열: 쓴맛
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			foodInfo[i][0] = Integer.parseInt(st.nextToken());
			foodInfo[i][1] = Integer.parseInt(st.nextToken());
		}

		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int flag = 0;
			limit = i + 1;
			combination(0, 0, flag);
		}
		sb.append(min);
		System.out.println(sb.toString());
		br.close();
	}

}
