import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_6808_D3_규영이와인영이의카드게임_비트마스킹ver {

	public static int kyuWin, kyuLose;
	public static int[] kyuCards = new int[10];

	public static void game(int cnt, int kyuSum, int inSum, int flag) { // flag를 매개변수에 추가

		if (cnt == 9) {
			if (kyuSum > inSum)
				kyuWin++;
			else
				kyuLose++;
			return;
		}

		for (int n = 1; n <= 18; n++) {
			if ((flag & 1 << n) != 0)
				continue;

			int sum = kyuCards[cnt + 1] + n;
			if (kyuCards[cnt + 1] > n)
				game(cnt + 1, kyuSum + sum, inSum, flag | 1 << n);
			else
				game(cnt + 1, kyuSum, inSum + sum, flag | 1 << n);
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("s_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int flag = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= 9; i++) {
				int num = Integer.parseInt(st.nextToken());
				kyuCards[i] = num;
				flag = flag | 1 << num;
			}
			kyuWin = 0;
			kyuLose = 0;
			game(0, 0, 0, flag);
			sb.append("#" + tc + " " + kyuWin + " " + kyuLose + "\n");
		}
		System.out.println(sb.toString());
	}

}