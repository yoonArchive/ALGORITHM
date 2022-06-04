import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3307_D3_최장증가부분수열_binarySearch {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("3307.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int numbers[] = new int[N];
			int lcs[] = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++)
				numbers[i] = Integer.parseInt(st.nextToken());

			int size = 0;
			for (int i = 0; i < N; i++) {
				int tmp = Arrays.binarySearch(lcs, 0, size, numbers[i]); // 리턴값 : -insertPoint -1
				tmp = Math.abs(tmp) - 1; // 삽입위치
				lcs[tmp] = numbers[i]; // tmp 자리에 값을 update 하면 그 의미는
										// 0인덱스 위치부터 tmp위치까지의 원소 갯수가 tmp 위치에 저장된 그 값을 마지막으로 하는 LIS 길이가 됨
										// 배열의 원소가 LIS를 이루는 구성요소와는 관계가 없다.
				if (tmp == size)
					size++;// 삽입위치의 인덱스와 크기가 같으면(결국,마지막이 삽입위치라는 얘기임) 크기 1늘림.
			}
			sb.append("#" + tc + " " + size).append("\n");
		}
		System.out.println(sb.toString());
	}
}
