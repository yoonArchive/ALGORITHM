import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_D3_6808_규영이와인영이의카드게임 {

	public static int kyuWin, kyuLose;
	public static int[] kyuCards = new int[10];
	public static boolean[] isSelected;

	public static void game(int cnt, int kyuSum, int inSum) { //순열

		if (cnt == 9) {
			if (kyuSum > inSum)
				kyuWin++;
			else
				kyuLose++;
			return;
		}

		for (int n = 1; n <= 18; n++) {
			if (isSelected[n])
				continue;

			int sum = kyuCards[cnt + 1] + n;
			isSelected[n] = true;
			if (kyuCards[cnt + 1] > n)
				game(cnt + 1, kyuSum + sum, inSum);
			else
				game(cnt + 1, kyuSum, inSum + sum);
			isSelected[n] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("s_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			isSelected = new boolean[19];

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= 9; i++) {
				int num = Integer.parseInt(st.nextToken());
				kyuCards[i] = num;
				isSelected[num] = true;
			}
			kyuWin = 0;
			kyuLose = 0;
			game(0, 0, 0);
			sb.append("#" + tc + " " + kyuWin + " " + kyuLose + "\n");
		}
		System.out.println(sb.toString());
	}

}