import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BJ_2493_탑 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());

		int tower[][] = new int[2][N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			tower[0][i] = Integer.parseInt(st.nextToken()); // 탑의 높이
			tower[1][i] = 0; // 어느 탑에서 수신하는지 저장(결과로 얘를 출력)
		}

		Stack<Integer> stack = new Stack<>();
		stack.push(N - 1); // 맨 오른쪽 원소부터 시작, 인덱스 push
		for (int i = N - 2; i >= 0; i--) {
			if (tower[0][i] < tower[0][stack.peek()]) { // 현재 타워의 높이가 더 작을때만 push
				stack.push(i);
				continue;
			}
			while (!stack.isEmpty()) {
				if (tower[0][i] < tower[0][stack.peek()]) // 현재 타워의 높이보다 작은 타워들 모두 pop
					break;
				int idx = stack.pop();
				tower[1][idx] = i + 1; // 탑번호 1부터 시작
			}
			stack.push(i);

		}
		
		for (int i = 0; i < N; i++)
			bw.write(Integer.toString(tower[1][i]) + " ");

		br.close();
		bw.flush();
		bw.close();
	}

}