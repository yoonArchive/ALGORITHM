import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BJ_2839_설탕배달 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		if (N % 5 == 0) ans = N / 5;
		else {
			int tmp = N;
			for (int i = N / 5; i >= 0; i--) {
				tmp -= (5 * i);
				if (tmp % 3 == 0) {
					ans += (tmp / 3) + i;
					break;
				}
				else tmp = N; //다시 원래대로
			}
		}
		if (ans == 0) ans = -1;
		sb.append(ans);
		System.out.println(ans);
		br.close();
	}

}
