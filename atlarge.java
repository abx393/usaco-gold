import java.io.*;
import java.util.*;

public class atlarge {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("atlarge.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken())-1;
		ArrayList<Integer>[] adj = new ArrayList[n];
		for (int i=0; i<n; i++) adj[i] = new ArrayList<Integer>();
		for (int i=0; i<n-1; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			adj[a].add(b);
			adj[b].add(a);
		}
		br.close();
		
		int[] rdist = new int[n];
		Arrays.fill(rdist, Integer.MAX_VALUE);
		rdist[k] = 0;
		
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(k);
		while (!q.isEmpty()){
			int curr = q.removeFirst();
			for (int node: adj[curr]) {
				if (rdist[node]==Integer.MAX_VALUE){
					rdist[node] = rdist[curr]+1;
					q.add(node);
				}
			}
		}
		
		int[] ldist = new int[n];
		Arrays.fill(ldist, Integer.MAX_VALUE);
		for (int i=0; i<n; i++){
			if (adj[i].size()==1) {
				q.add(i);
				ldist[i] = 0;
			}
		}
		
		while (!q.isEmpty()){
			int curr = q.removeFirst();
			for (int node: adj[curr]){
				if (ldist[curr]+1<ldist[node]) {
					ldist[node] = ldist[curr]+1;
					q.add(node);
				}
			}
		}
		
		int count = 0;
		boolean[] seen = new boolean[n];
		seen[k] = true;
		q.add(k);
		while (!q.isEmpty()){
			int curr = q.removeFirst();			
			for (int node: adj[curr]){
				if (!seen[node]){
					seen[node] = true;
					if (ldist[node]<=rdist[node]) count++;
					else q.add(node);
				}
			}
		}
		//System.out.println(Arrays.toString(ldist));
		//System.out.println(Arrays.toString(rdist));
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("atlarge.out")));
		out.println(count);
		out.close();
	}
}
