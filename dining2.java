import java.io.*;
import java.util.*;

public class dining2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("dining2.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		ArrayList<Edje>[] adj = new ArrayList[n];
		for (int i=0; i<adj.length; i++) adj[i] = new ArrayList<Edje>();
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			long t = Long.parseLong(st.nextToken());
			adj[a].add(new Edje(b, t));
			adj[b].add(new Edje(a, t));
		}
		
		//Djikstra
		long[] dist = new long[n];
		dist[n-1] = 0;
		
		
	}
}
