import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1244_스위치_켜고_끄기 {
	static int switch_num;
	static int arr[];

	public static void male_func(int bun) {
		for (int i = 1; i <= switch_num; i++) {
			if (i % bun == 0) {
				if (arr[i - 1] == 1)
					arr[i - 1] = 0;
				else
					arr[i - 1] = 1;
			}
		}
	}

	public static void female_func(int bun) {
		int i = 0;
		while (true) {
			if ((bun - 1) - i < 0 || (bun - 1) + i >= switch_num)
				break;
			if (arr[(bun - 1) - i] == arr[(bun - 1) + i]) {
				if (arr[(bun - 1) - i] == 1) {
					arr[(bun - 1) - i] = 0;
					arr[(bun - 1) + i] = 0;
				} else {
					arr[(bun - 1) - i] = 1;
					arr[(bun - 1) + i] = 1;
				}
				i++;
			} else
				break;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		switch_num = Integer.parseInt(br.readLine()); // 스위치 개수
		arr = new int[switch_num];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < switch_num; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int stu_num = Integer.parseInt(br.readLine()); // 학생 수

		for (int i = 0; i < stu_num; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int gender = Integer.parseInt(st.nextToken());
			int bun = Integer.parseInt(st.nextToken());
			if (gender == 1)
				male_func(bun);
			else
				female_func(bun);

		}
		for (int i = 1; i <= arr.length; i++) {
			bw.write(Integer.toString(arr[i-1]) + " ");
			if (i % 20 == 0)
				bw.newLine();
		}
		br.close();
		bw.flush();
		bw.close();
	}

}
