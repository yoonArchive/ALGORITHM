import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_Jungol_1828_냉장고 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine()); // 화학물질의 수
		int temp[][] = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			temp[i][0] = Integer.parseInt(st.nextToken()); // 0열: 최저보관온도
			temp[i][1] = Integer.parseInt(st.nextToken()); // 1열: 최고보관온도
		}

		// 최고 온도 기준 오름차순 정렬 -> 최고 온도가 같다면 최저 온도 기준 오름차순 정렬
		Arrays.sort(temp, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1])
					return o1[0] - o2[0];
				else
					return o1[1] - o2[1];
			}
		});
		
		int count=1;
		int max=temp[0][1];
		for (int i = 1, size = temp.length; i < size; i++) {
			if(temp[i][0]<=max) continue;
			else {
				max=temp[i][1]; 
				count++;
			}
		}
		sb.append(count);
		System.out.println(count);
	}

}
