import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1759_암호만들기 {
	static int L, C;
	static char[] chars;
	static char[] pw;
	static StringBuilder sb = new StringBuilder();

	public static void comb(int start, int count, int countV, int countC) {
		if (count == L) {
			if (countV >= 1 && countC >= 2) {
				for (int i = 0; i < L; i++) {
					sb.append(pw[i]);
				}
				sb.append("\n");
			}
			return;
		}
		for (int i = start; i < C; i++) {
			char c = chars[i];
			pw[count] = c;
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
				comb(i + 1, count + 1, countV + 1, countC);
			else
				comb(i + 1, count + 1, countV, countC + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		chars = br.readLine().replace(" ", "").toCharArray();
		Arrays.sort(chars);
		pw = new char[L];
		comb(0, 0, 0, 0);
		System.out.println(sb.toString());
		br.close();
	}

}
