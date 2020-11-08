import java.io.*;
import java.util.*;

public class dining {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("dining.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dining.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		LinkedList<Edje>[] adj = new LinkedList[n + 1];

		for (int i = 0; i <= n; i++) adj[i] = new LinkedList<Edje>();

		long[] distend = new long[n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			long t = Integer.parseInt(st.nextToken());
			adj[a].add(new Edje(b, t));
			adj[b].add(new Edje(a, t));
		}
		
		long[] hb = new long[n];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken()) - 1; 
			hb[idx] = Math.max(Long.parseLong(st.nextToken()), hb[idx]);
		}

		br.close();
		
		// Dijkstra's algorithm to find shortest path from end to any other vertex
		Arrays.fill(distend, Long.MAX_VALUE);
		distend[n - 1] = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		pq.add(new Pair(n - 1, 0));
		boolean[] seen = new boolean[n];
		while (!pq.isEmpty()) {
			// Find minimum unvisited
			Pair p = pq.poll();
			int minV = p.i; 
			if (seen[minV]) continue;
			seen[minV] = true;
			
			for (Edje e : adj[minV]){
				if (distend[minV] + e.w < distend[e.to]) {
					distend[e.to] = distend[minV] + e.w;
					pq.add(new Pair(e.to, distend[e.to]));
				}
			}
		}
		
		// Find shortest path from end to any other vertex through at least 1 haybale
		for (int i = 0; i < n; i++) {
			if (hb[i] == 0) continue;
			adj[n].add(new Edje(i, distend[i] - hb[i]));
		}
		
		long[] dist2 = new long[n + 1];
		Arrays.fill(dist2, Long.MAX_VALUE);
		dist2[n] = 0;
		seen = new boolean[n + 1];
		
		pq = new PriorityQueue<Pair>();
		pq.add(new Pair(n, 0));
		
		while (!pq.isEmpty()) {
			// Find minimum unvisited
			Pair p = pq.poll();
			int minV = p.i;
			if (seen[minV]) continue;
			seen[minV] = true;
			
			for (Edje e : adj[minV]){
				if (dist2[minV] + e.w < dist2[e.to]) {
					dist2[e.to] = dist2[minV] + e.w;
					pq.add(new Pair(e.to, dist2[e.to]));
				}
			}
		}
		
		for (int i = 0; i < n - 1; i++) {
			if (dist2[i] <= distend[i]) out.println(1);
			else out.println(0);
		}
		out.close();
	}
}

class Edje {
	public int to;
	public long w;

	public Edje(int a, long b) {
		this.to = a;
		this.w = b;
	}
}

class Pair implements Comparable<Pair> {
	public int i;
	public long w;

	public Pair(int a, long b) {
		this.i = a;
		this.w = b;
	}

	public int compareTo(Pair that) {
		return (this.w > that.w ? 1 : -1);
	}
}
