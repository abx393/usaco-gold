import java.io.*;
import java.util.*;

public class sleepy2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sleepy.in"));
		int n = Integer.parseInt(br.readLine());
		int[] c = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) c[i] = Integer.parseInt(st.nextToken());
		br.close();
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
		BTree bit = new BTree(n);
		int i = n;
		do {
			i--;
			bit.inc(c[i]-1);
		}while (i>0 && c[i-1]<c[i]);
		out.println(i);
		
		for (int j=0; j<i; j++) {
			int move = (i-j-1)+bit.sum(c[j]-1);
			out.print(move);
			if (j<i-1) out.print(" ");
			bit.inc(c[j]-1);
		}
		
		out.close();
	}
}
class BTree {
	public int[] arr;
	public BTree(int n){
		arr = new int[n+1];
	}
	public void inc(int i){
		i++;
		while (i<arr.length){
			arr[i]++;
			i += i & -i;
		}
	}
	public int sum(int i){
		i++;
		int ret = 0;
		while (i>0){
			ret += arr[i];
			i -= i & -i;
		}
		return ret;
	}
}