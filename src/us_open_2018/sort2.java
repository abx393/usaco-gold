import java.io.*;
import java.util.*;

public class sort2 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sort.in"));
		int n = Integer.parseInt(br.readLine());
		
		elem[] a = new elem[n];
		int[] bit = new int[n+1];
		
		for (int i=0; i<n; i++){
			a[i] = new elem(Integer.parseInt(br.readLine()), i);
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
}

class elem implements Comparable<elem> {
	public int val, idx;
	public elem(int a, int b) {
		this.val = a;
		this.idx = b;
	}
	public int compareTo(elem that) {
		return this.val-that.val;
	}
	public String toString() {
		return val + " " + idx;
	}
}
