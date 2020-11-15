import java.io.*;
import java.util.*;

public class mountains {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
		int n = Integer.parseInt(br.readLine());
		Pare[] m = new Pare[n];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			m[i] = new Pare(x - y, x + y);
		}

		br.close();
		Arrays.sort(m);
		
		int i = 1;
		int prev = m[0].up;
		int count = 1;
		while (i < n) {
			if (m[i].up > prev) {
				prev = m[i].up;
				count++;
			}
			i++;
		}

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
		out.println(count);
		out.close();
		
	}
}

class Pare implements Comparable<Pare> {
	public int low, up;

	public Pare(int a, int b) {
		this.low = a;
		this.up = b;
	}

	public int compareTo(Pare other) {
		return this.low - other.low;
	}
}
