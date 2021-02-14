import java.io.*;
import java.util.*;

public class sort2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sort.in"));
		int n = Integer.parseInt(br.readLine());

		Elem[] a = new Elem[n];
		int[] bit = new int[n+1];

		for (int i=0; i<n; i++){
			a[i] = new Elem(Integer.parseInt(br.readLine()), i);
		}
		//System.out.println(Arrays.toString(a));
		br.close();
		Arrays.sort(a);
		int res = 0;
		for (int i=0; i<n-1; i++){
			inc(bit, a[i].idx);
			res = Math.max(res, i+1-sum(bit, i));
		}

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		out.println(res);
		out.close();
	}

	public static void inc(int[] bit, int idx) {
		idx++;
		while (idx<bit.length){
			bit[idx]++;
			idx += idx & -idx;
		}
	}

	public static int sum(int[] bit, int idx) {
		idx++;
		int res = 0;
		while (idx>0){
			res += bit[idx];
			idx -= idx & -idx;
		}
		return res;
	}

	// An element and its index
    private static class Elem implements Comparable<Elem> {
        public int val, idx;
        public Elem(int a, int b) {
            this.val = a;
            this.idx = b;
        }
        public int compareTo(Elem that) {
            return this.val - that.val;
        }
        public String toString() {
            return val + " " + idx;
        }
    }
}

