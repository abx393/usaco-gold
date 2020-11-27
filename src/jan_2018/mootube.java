import java.io.*;
import java.util.*;

public class mootube {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		int[][] dist = new int[n][n];

		TreeSet<Edge>[] rel = new TreeSet[n];
		ArrayList<Edge>[] adj = new ArrayList[n];

		for (int i=0; i<n; i++) rel[i] = new TreeSet<Edge>();
		for (int i=0; i<n; i++) adj[i] = new ArrayList<Edge>();
		
		for (int i=0; i<n-1; i++) { 
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			
			rel[a].add(new Edge(b, c));
			rel[b].add(new Edge(a, c));
			adj[a].add(new Edge(b, c));
			adj[b].add(new Edge(a, c));
			dist[a][b] = dist[b][a] = c;
		}

		for (int i=0; i<q; i++){
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken())-1;
			boolean[] visited = new boolean[n];
			LinkedList<Integer> qu = new LinkedList<Integer>();
			for (Edge e : adj[v]) {
				qu.add(e.to);
			}

			while (!qu.isEmpty()){
				int curr = qu.removeFirst();
				for (Edge e : adj[curr]){
					if (visited[e.to]) continue;
					int newdist = Math.min(dist[v][curr], dist[curr][e.to]);
					rel[v].add(new Edge(e.to, newdist));
					rel[e.to].add(new Edge(v, newdist));
					dist[v][e.to] = dist[e.to][v] = newdist;
					qu.add(e.to);
					visited[e.to] = true;
				}
			}
		
			int res = bSearch(rel[v], k);
		}
		out.close();
	}

	public static int bSearch(TreeSet<Edge> ts, int val) {
		Edge[] nums = new Edge[ts.size()];
		nums = ts.toArray(nums);
		//System.out.println(Arrays.toString(nums));
		int min = 0, mid = 0, max = nums.length-1;
		while (min <= max) {
			mid = (min + max) / 2;
			if (nums[mid].w > val) max = mid-1;
			else if (nums[mid].w < val) min = mid+1;
			else return mid;
		}
		return (nums[mid].w < val ? mid+1: mid);
	}
}

class Edge implements Comparable<Edge> {
	public int to, w;

	public Edge (int a, int b){
		to = a;
		w = b;
	} 

	public int compareTo(Edge that){
		return this.w - that.w;
	}

	public String toString(){
		return (to+1) + " " + w;
	}
}
