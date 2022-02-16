import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1992_쿼드트리 {
	static char[][] image;
	static StringBuilder sb = new StringBuilder();

	private static boolean isAllSame(int r, int c, int size) {
		char val = image[r][c];
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (image[i][j] != val)
					return false;
			}
		}
		return true;
	}

	private static void compression(int r, int c, int size) {
		if (isAllSame(r, c, size)) {
			sb.append(image[r][c]);
		} else {
			sb.append("(");
			for (int i = r, half = size / 2; i <= r + half; i += half) {
				for (int j = c; j <= c + half; j += half) {
					compression(i, j, half);
				}
			}
			sb.append(")");
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		image = new char[N][N];
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			image[i] = str.toCharArray();
		}
		int size = image.length;
		compression(0, 0, size);
		System.out.println(sb.toString());

	}

}
