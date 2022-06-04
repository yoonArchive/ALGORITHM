import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_BJ_2239_스도쿠_후보구하기ver {
	final static int SIZE = 9;
	static int sudoku[][];
	static int zeroCnt;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("2239.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sudoku = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			String line = br.readLine();
			for (int j = 0; j < SIZE; j++) {
				int tmp = line.charAt(j) - '0';
				sudoku[i][j] = tmp;
				if (tmp == 0)
					zeroCnt++;
			}
		}
		int r = 0, c = 0;
		solve(r, c);
	}

	private static void solve(int r, int c) {
		ArrayList<Integer> candidates = new ArrayList<>();
		if (c == SIZE) {
			r++;
			c = 0;
		}
		if (sudoku[r][c] == 0) { // 아직 채워지지 않은 칸을 발견하면
			candidates = getCandidates(r, c);
			int length = candidates.size();
			for (int k = 0; k < length; k++) {
				sudoku[r][c] = candidates.get(k);
				zeroCnt--;
				if (zeroCnt == 0) {
					print();
					System.exit(0);
				}
				solve(r, c + 1);
				sudoku[r][c] = 0;
				zeroCnt++;
			}
		} else {
			solve(r, c + 1);
		}
	}

	private static ArrayList<Integer> getCandidates(int r, int c) { // 해당 칸에 올 수 있는 후보 구하기
		ArrayList<Integer> excluders = new ArrayList<>();
		ArrayList<Integer> candidates = new ArrayList<>();
		for (int i = 0; i < SIZE; i++) {
			if (sudoku[i][c] != 0)
				excluders.add(sudoku[i][c]);
			if (sudoku[r][i] != 0)
				excluders.add(sudoku[r][i]);
		}
		int sr = r / 3 * 3;
		int sc = c / 3 * 3;
		for (int i = sr; i < sr + 3; i++) {
			for (int j = sc; j < sc + 3; j++) {
				if (sudoku[i][j] != 0)
					excluders.add(sudoku[i][j]);
			}
		}
		for (int i = 1; i <= SIZE; i++) {
			if (excluders.indexOf(i) == -1)
				candidates.add(i);
		}
		return candidates;
	}

	private static void print() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				sb.append(sudoku[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
