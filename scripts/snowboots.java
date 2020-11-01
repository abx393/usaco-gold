import java.io.*;
import java.util.*;

public class snowboots {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("snowboots.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int[] tiles = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++){
			tiles[i] = Integer.parseInt(st.nextToken());
		}
		Boots[] pairs = new Boots[b];
		for (int i=0; i<b; i++){
			st = new StringTokenizer(br.readLine());
			pairs[i] = new Boots(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
		}
		Arrays.sort(pairs);
		int minStep = Integer.MAX_VALUE;
		int minDepth = Integer.MAX_VALUE;
		boolean[] reachable = new boolean[b];
		
		for (int i=0; i<b; i++){
			int j=0;
			if (pairs[i].step>=minStep && pairs[i].depth>=minDepth){
				reachable[pairs[i].idx] = true;
				continue;
			}
			x: while (j<n){
				if (j==n-1) {
					reachable[pairs[i].idx] = true; 
					if (pairs[i].depth < minDepth) {
						minDepth = pairs[i].depth;
						minStep = pairs[i].step;
					}
					break;
				}
				for (int k=Math.min(n-1, j+ pairs[i].step); k>j; k--){
					if (pairs[i].depth<tiles[k] && k==j+1) break x;
					if (pairs[i].depth>=tiles[k]) {
						j = k;
						continue x;
					}
				}
			}
		}
		for (int i=0; i<b; i++) out.println(reachable[i] ? 1:0);
		
		br.close();
		out.close();
	}
}
class Boots implements Comparable<Boots> {
	public int depth, step, idx;
	public Boots (int a, int b, int c) {
		depth = a;
		step = b;
		idx = c;
	}
	public int compareTo(Boots that){
		int res = this.step-that.step;
		if (res!=0) return res;
		return this.depth-that.depth;
	}
}
