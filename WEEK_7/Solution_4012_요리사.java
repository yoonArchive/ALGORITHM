import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4012_요리사 {
	static int[][] sTable;
	static int N;
	static int ans;

	public static int getSynergy(int[] food_Index) {
		int sum = 0;
		for (int i = 0, size = food_Index.length; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				sum += sTable[food_Index[i]][food_Index[j]] + sTable[food_Index[j]][food_Index[i]];
			}
		}
		return sum;
	}

	public static boolean np(int[] p) {
		int length = p.length;

		int i = length - 1;
		while (i > 0 && p[i - 1] >= p[i])
			--i;
		if (i == 0)
			return false;

		int j = length - 1;
		while (p[i - 1] >= p[j])
			--j;

		swap(p, i - 1, j);

		int k = length - 1;
		while (i < k) {
			swap(p, i++, k--);
		}

		return true;
	}

	public static void swap(int[] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}

	public static void np_func() {
		int[] order = new int[N];
		for (int i = 0; i < N; i++)
			order[i] = i; // 조합을 위한 배열 생성
		int[] p = new int[N];
		boolean[] selectedIdx = new boolean[N]; // A음식으로 사용될 식재료 인덱스는 true
		int[] foodA_Index = new int[N / 2]; // A음식에 사용될 식재료 인덱스
		int[] foodB_Index = new int[N / 2];

		int cnt = 0;
		while (++cnt <= N / 2)
			p[N - cnt] = 1;
		do {
			for (int i = 0, j = 0; i < N; i++) { // A음식 식재료 구하기
				if (p[i] == 1) {
					foodA_Index[j++] = order[i];
					selectedIdx[i] = true;
				}
			}
			for (int i = 0, j = 0; i < N; i++) { // B음식 식재료 구하기
				if (!selectedIdx[i]) {
					foodB_Index[j] = i;
					j++;
				}
			}
			int diffA = getSynergy(foodA_Index);
			int diffB = getSynergy(foodB_Index);
			ans = Math.min(ans, Math.abs(diffA - diffB));
			Arrays.fill(selectedIdx, false);
		} while (np(p));
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_4012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 식재료의 수
			sTable = new int[N][N]; // 식재료 시너지 테이블
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					sTable[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = Integer.MAX_VALUE;
			np_func();
			sb.append("#" + tc + " " + ans + "\n");

		}
		System.out.println(sb.toString());
	}

}
