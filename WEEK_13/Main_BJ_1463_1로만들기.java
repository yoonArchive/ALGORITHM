import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_BJ_1463_1로만들기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int cnt[] = new int[N + 1];
		for (int i = 2; i <= N; i++) {
			cnt[i] = cnt[i - 1] + 1;
			if (i % 3 == 0) {
				cnt[i] = Math.min(cnt[i], cnt[i / 3] + 1);
			}
			if (i % 2 == 0) {
				cnt[i] = Math.min(cnt[i], cnt[i / 2] + 1);
			}
		}
		bw.write(Integer.toString(cnt[N]));
		br.close();
		bw.flush();
		bw.close();

	}

}
