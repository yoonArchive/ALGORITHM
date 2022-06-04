public class Subset_test {

	static int[] arr = { 1, 3, 5, 7, 9 };
	static int N = 5;
	static boolean[] isSelected;

	public static void generateSubset(int cnt) {// 부분집합에 고려해야 하는 원소
		if (cnt == N) { // 마지막 원소까지 부분집합에 다 고려된 상황
			for (int i = 0; i < N; i++) {
				System.out.print(isSelected[i] ? arr[i] : "X");
			}
			System.out.println();
			return;
		}

		// 현재 원소를 선택
		isSelected[cnt] = true;
		generateSubset(cnt + 1);

		// 현재 원소를 비선택
		isSelected[cnt] = false;
		generateSubset(cnt + 1);
	}

	public static void main(String[] args) {

		isSelected = new boolean[N];
		generateSubset(0);

	}
}
