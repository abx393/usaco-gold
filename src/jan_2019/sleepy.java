import java.io.*;
import java.util.*;

public class sleepy {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sleepy.in"));
		int n = Integer.parseInt(br.readLine());
		LinkedList<Integer> c = new LinkedList<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) c.add(Integer.parseInt(st.nextToken()));
		
		br.close();
		int i = n-1;
		while (i > 0 && c.get(i-1) < c.get(i)) i--;
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
		System.out.println(i);
		while (i > 0) {
			int j=bSearch(c, c.get(0), i, n-1);
			System.out.print(j-1);
			if (i > 1) System.out.print(" ");
			int tmp = c.remove(0);
			c.add(j-1, tmp);
			System.out.println(c);
			i--;
		}
		out.close();
	}

	public static int bSearch(LinkedList<Integer> a, int num, int c, int d){
		int x = c;
		int y = d;
		int mid = 0;
		while (x <= y) {
			mid = (x+y) / 2;
			if (a.get(mid)>num) y = mid-1;
			else if (a.get(mid)<num) x = mid+1;
			else return mid;
		}
		if (a.get(mid) < num) return mid+1;
		else return mid;
	}
}
