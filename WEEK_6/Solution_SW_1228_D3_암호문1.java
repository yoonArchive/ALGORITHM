import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_SW_1228_D3_암호문1 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_1228.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine()); // 원본 암호문의 길이
			List<Integer> list = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++)
				list.add(Integer.parseInt(st.nextToken()));

			int c_num = Integer.parseInt(br.readLine()); // 명령어의 개수
			st = new StringTokenizer(br.readLine(), " ");
			int cnt = 0; // 수행한 명령어 개수
			while (true) {
				if (cnt == c_num) break;
				if (st.nextToken().equals("I")) {
					cnt++;
					int x = Integer.parseInt(st.nextToken()); //삽입할 위치
					int y = Integer.parseInt(st.nextToken()); //삽입할 개수
					for (int i = 0; i < y; i++) {
						list.add(x++, Integer.parseInt(st.nextToken()));
					}
				}
			}

			sb.append("#" + tc + " ");
			for (int i = 0; i < 10; i++)
				sb.append(list.get(i) + " ");
			sb.append("\n");

		}
		System.out.println(sb.toString());
		br.close();
	}
}
