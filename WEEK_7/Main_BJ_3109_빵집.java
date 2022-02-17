import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_3109_빵집 {

	static int dr[] = { -1, 0, 1 };
	static int dc[] = { 1, 1, 1 };
	static int R, C;
	static char map[][];
	static int count;

	public static boolean dfs(int sr, int sc) {
		if (sc == C - 1) {
			count++;
			return true; // 현재 위치에 설치 성공하면 true 반환
		}

		for (int i = 0, length = dr.length; i < length; i++) {
			int nr = sr + dr[i];
			int nc = sc + dc[i];
			if (nr < 0 || nr >= R || map[nr][nc] == 'x')
				continue;
			map[nr][nc] = 'x';
			if (dfs(nr, nc))
				return true;
		}
		return false; // 설치가 가능한 방향이 없는 경우
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];

		for (int i = 0; i < R; i++)
			map[i] = br.readLine().toCharArray();

		count = 0;
		for (int row = 0; row < R; row++)
			dfs(row, 0);
		
		bw.write(Integer.toString(count));

		br.close();
		bw.flush();
		bw.close();

	}

}
