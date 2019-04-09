import java.io.*;
import java.util.*;
	
public class hayfeast {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("hayfeast.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long m = Integer.parseInt(st.nextToken());
		long[] f = new long[n];
		fuk[] s = new fuk[n];
		for (int i=0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			f[i] = Long.parseLong(st.nextToken());
			s[i] = new fuk(Long.parseLong(st.nextToken()), i);
		}
		
		br.close();
		
		long sum = 0;
		TreeSet<fuk> bst = new TreeSet<fuk>();
		
		int j=0;
		long min = Long.MAX_VALUE;
		a: for (int i=0; i<n; i++){
			
			while (sum<m) {
				if (j==n) break a;
				sum+=f[j];
				bst.add(s[j]);
				j++;
			}
			
			min = Math.min(min, bst.last().a);
			//System.out.println(bst);
			bst.remove(s[i]);
			//System.out.println(bst);
			//System.out.println();
			sum-=f[i];
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
		out.println(min);
		out.close();
	}
}
class fuk implements Comparable<fuk>{
	public long a, b;
	public fuk(long c, long d){
		a = c;
		b = d;
	}
	public String toString(){
		return a + " " + b;
	}
	public int compareTo(fuk that){
		if (a!=that.a) return (a>that.a ? 1:-1);
		else if (b!=that.b) return (b>that.b ? 1:-1);
		return 0;
	}
	
}