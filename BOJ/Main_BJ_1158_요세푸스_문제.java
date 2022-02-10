import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1158_요세푸스_문제{

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		int count = 0;
		int pollnum = 0;
		sb.append("<");
		while (true) {
			if (pollnum == N)
				break;
			int num=queue.poll();
			count++;
			if (count == K) {
				if(pollnum<N-1) sb.append(num + ", ");
				else sb.append(num);
				pollnum++;
				count = 0;
				continue;
			}
			queue.offer(num);

		}
		sb.append(">");
		System.out.println(sb.toString());
	}

}
