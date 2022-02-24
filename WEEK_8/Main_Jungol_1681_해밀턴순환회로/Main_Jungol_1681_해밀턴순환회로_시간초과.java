import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_Jungol_1681_해밀턴순환회로_시간초과 {
	static int[][] cost;
	static int sum, min=Integer.MAX_VALUE;

	public static boolean isMovable(int[] numbers, int N) {
		sum=cost[numbers[N-1]][numbers[N]];
		for (int i = 0; i < N - 1; i++) {
				if (cost[numbers[i]][numbers[i+1]] == 0) return false;
				sum+=cost[numbers[i]][numbers[i+1]];
		}
		return true;
	}

	public static void permutation(int input[], int[] numbers, int count, int flag, int N) {
		if (count == N ) {
			numbers[N] = numbers[0];
			if(isMovable(numbers, N)) 
				if(min>sum) min=sum;
			return;
		}
		for (int i = 1; i < N; i++) {
			if ((flag & 1 << i) != 0) continue;
			numbers[count] = input[i];
			permutation(input, numbers, count + 1, flag | 1 << i, N);
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input_1681.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		cost = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int order[] = new int[N];
		int numbers[] = new int[N + 1];
		for (int i = 0; i < N; i++)
			order[i] = i;
		numbers[0] = 0;
		permutation(order, numbers, 1, 0, N);
		bw.write(Integer.toString(min));
		br.close();
		bw.flush();
		bw.close();
	}

}
