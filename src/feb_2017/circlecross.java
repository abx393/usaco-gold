import java.io.*;
import java.util.*;

public class circlecross {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("circlecross.in"));
		int n = Integer.parseInt(br.readLine());
		Path[] pairs = new Path[n];
		for (int i = 0; i< 2 * n; i++) {
			int x = Integer.parseInt(br.readLine()) - 1;
			if (pairs[x] == null) pairs[x] = new Path(i, -1);
			else pairs[x].e = i;
		}
		br.close();

		Arrays.sort(pairs);
		for (int i=0; i<n; i++) {
			System.out.println(pairs[i].s + " " + pairs[i].e);
		}

		BT bit = new BT(2 * n);
		int res = 0;
		for (int i=0; i<n; i++) {
			res += bit.sum(pairs[i].e) - bit.sum(pairs[i].s);
			bit.add(pairs[i].e, 1);
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
		out.println(res);
		out.close();
	}
}

class Path implements Comparable<Path> {
	public int s, e;
	public Path(int a, int b){
		this.s = a;
		this.e = b;
	}
	public int compareTo(Path that){
		return this.s-that.s;
	}
}

class BT {
	public int[] a;
	public BT(int n){
		a = new int[n+1];
	}
	public void add(int i, int n){
		i++;
		while (i<a.length){
			a[i] += n;
			i += i & -i;
		}
	}
	public int sum(int i){
		i++;
		int ret = 0;
		while (i>0) {
			ret += a[i];
			i -= i & -i;
		}
		return ret;
	}
}
