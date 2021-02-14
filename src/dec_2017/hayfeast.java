import java.io.*;
import java.util.*;

public class hayfeast {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("hayfeast.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long m = Integer.parseInt(st.nextToken());
		long[] f = new long[n];
		Pair[] s = new Pair[n];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			f[i] = Long.parseLong(st.nextToken());
			s[i] = new Pair(Long.parseLong(st.nextToken()), i);
		}

		br.close();

		long sum = 0;
		TreeSet<Pair> bst = new TreeSet<Pair>();

		int j=0;
		long min = Long.MAX_VALUE;
		a: for (int i=0; i<n; i++) {

			while (sum < m) {
				if (j == n) break a;
				sum += f[j];
				bst.add(s[j]);
				j++;
			}

			min = Math.min(min, bst.last().a);
			bst.remove(s[i]);
			sum -= f[i];
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
		out.println(min);
		out.close();
	}

    private static class Pair implements Comparable<Pair>{
        public long a, b;

        public Pair(long c, long d){
            a = c;
            b = d;
        }

        public String toString(){
            return a + " " + b;
        }

        public int compareTo(Pair that){
            if (a != that.a) return (a > that.a ? 1 : -1);
            else if (b != that.b) return (b > that.b ? 1 : -1);
            return 0;
        }
    }
}
