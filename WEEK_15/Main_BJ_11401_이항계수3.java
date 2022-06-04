import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_11401_이항계수3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int p = 1000000007;
		if (n - k < k) // nCk=nCn-k
			k = n - k;

		long[] factorial = new long[n + 1];
		getFactorial(factorial, n, p);
		bw.write(Long.toString((factorial[n] * fermat((factorial[k] * factorial[n - k]) % p, p)) % p));
		br.close();
		bw.flush();
		bw.close();
	}

	private static void getFactorial(long[] factorial, int n, int p) {
		factorial[0] = 1;
		factorial[1] = 1;
		for (int i = 2; i <= n; i++)
			factorial[i] = (factorial[i - 1] * i) % p;
	}

	// l^(p-2) 구하기
	private static long fermat(long l, int p) {
		int tmpP = p - 2;
		long res = 1;
		while (tmpP > 0) {
			if (tmpP % 2 != 0) {
				res *= l;
				res %= p;
			}
			l = (l * l) % p;
			tmpP /= 2;
		}
		return res % p;
	}
}
