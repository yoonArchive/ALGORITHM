import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_SW_6808_D3_규영이와인영이의카드게임_nextPermutation {

	public static int kyuWin, kyuLose;
	public static int[] kyuCards; // 라운드별 규영이 카드 -> 고정
	public static int[] inCards; // 라운드별 인영이 카드

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_6808.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			boolean[] check = new boolean[19];
			kyuCards = new int[9];
			inCards = new int[9];

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 9; i++) {
				int num = Integer.parseInt(st.nextToken());
				kyuCards[i] = num;
				check[num] = true;
			}

			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (!check[i]) {
					inCards[idx++] = i;
				}
			}

			kyuWin = 0;
			kyuLose = 0;

			// nextPermutation 위한 오름차순 정렬
			Arrays.sort(inCards);
			do {
				calcTotal();
			} while (np()); // 카드 선택하기(순열 만들기)

			sb.append("#" + tc + " " + kyuWin + " " + kyuLose + "\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean np() {
		// 1. 꼭대기 인덱스 찾기
		int N = 9;
		int i = N - 1; // 꼭대기 인덱스 담을 변수.
		while (i > 0 && inCards[i - 1] >= inCards[i]) // 맨 뒤부터 꼭대기 인덱스 탐색
			--i;
		if (i == 0) // 꼭대기 인덱스가 0이라면-> 모든 수가 내림차순 정렬되어 있다는 뜻이므로 이미 가장 큰 수이다.
			return false;

		// 2. (i-1) 요소와 교환할 요소의 인덱스 찾기
		int j = N - 1; // 교환할 인덱스 담을 변수
		while (inCards[i - 1] >= inCards[j])
			--j;

		// 3. (i-1)과 j를 swap
		swap(i - 1, j);

		// 4. i부터 끝까지 오름차순 정렬
		int k = N - 1; // 끝 인덱스
		while (i < k) {
			swap(i++, k--);
		}
		return true;
	}

	private static void swap(int i, int j) {
		int temp = inCards[i];
		inCards[i] = inCards[j];
		inCards[j] = temp;
	}

	private static void calcTotal() { // 인영이 카드 9장 조합 완성되었을 때 calcTotal 실행됨
		int kyuSum = 0;
		int inSum = 0;

		// 라운드별 카드 비교 후 점수 계산
		for (int i = 0; i < 9; i++) {
			if (kyuCards[i] > inCards[i])
				kyuSum += (kyuCards[i] + inCards[i]);
			else
				inSum += (kyuCards[i] + inCards[i]);
		}
		if (kyuSum > inSum)
			kyuWin++;
		else if (kyuSum < inSum)
			kyuLose++;
	}

}