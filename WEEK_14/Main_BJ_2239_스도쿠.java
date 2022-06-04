import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_2239_스도쿠 {
	final static int SIZE = 9;
	static int sudoku[][];
	static boolean isCompleted;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("2239.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sudoku = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			String line = br.readLine();
			for (int j = 0; j < SIZE; j++) {
				sudoku[i][j] = line.charAt(j) - '0';
			}
		}
		int complete = 0;
		play(complete);
	}

	private static void play(int complete) {
		if (complete == 81) { // 기저
			print();
			System.exit(0);
		}

		int r = complete / SIZE;
		int c = complete % SIZE;

		if (sudoku[r][c] != 0) {
			play(complete + 1);
		} else { // 채워지지 않은 숫자 발견
			for (int k = 1; k <= 9; k++) {
				if (!canPut(r, c, k))
					continue;
				sudoku[r][c] = k;
				play(complete + 1);
				sudoku[r][c] = 0;
			}
		}
	}

	private static boolean canPut(int r, int c, int num) {
		for (int i = 0; i < SIZE; i++) {
			if (sudoku[r][i] == num || sudoku[i][c] == num)
				return false;
		}
		int sr = r / 3 * 3;
		int sc = c / 3 * 3;
		for (int i = sr; i < sr + 3; i++) {
			for (int j = sc; j < sc + 3; j++) {
				if (sudoku[i][j] == num)
					return false;
			}
		}
		return true;
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
