import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_SW_D4_1233_사칙연산유효성검사 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input_1233(2).txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {

			int N = Integer.parseInt(br.readLine());
			int result = 1;

			if (N % 2 == 0) // 이진트리 노드 개수는 홀수개
				result = 0;

			for (int i = 1; i <= N; i++) {

				String[] node = br.readLine().split(" ");
				if (result == 0)
					continue; //다음 tc로 가기 위해 계속 readLine해야함

				// 연산자: 자식노드가 2개
				if (node[1].equals("+") || node[1].equals("-") || node[1].equals("*") || node[1].equals("/")) {
					if (node.length != 4)
						result = 0;
				}

				// 숫자면 자식 노드 x
				else {
					if (node.length != 2)
						result = 0;
				}
			}
			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.println(sb.toString());
	}
}
