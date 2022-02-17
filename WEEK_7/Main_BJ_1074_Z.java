import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1074_Z {
	static int r, c;
	static int num;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void search(int x, int y, int size) throws IOException {
		if (size == 2) {
			if ((x == r || x == r - 1) && (y == c || y == c - 1)) {
				for (int i = x; i < x + size; i++) {
					for (int j = y; j < y + size; j++) {
						if (i == r && j == c) {
							bw.write(Integer.toString(num));
							bw.flush();
							System.exit(0);
						}
						num++;
					}
				}
			} else
				num += 4;
			return;

		}

		for (int i = x, half = size / 2; i <= x + half; i += half) {
			for (int j = y; j <= y + half; j += half) {
				if (i <= r && i + half >= r && j <= c && j + half >= c) // 해당 영역 안에 r과 c가 있을 때만
					search(i, j, half);
				else // 아니면 더 분할하지 않는다.
					num += (half * half);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int size = (int) Math.pow(2, N);
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		num = 0;
		search(0, 0, size);
	}

}
