import java.io.*;
import java.util.*;

public class mootube2 {
	public static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		Edge[] edges = new Edge[n-1];
		for (int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge(Integer.parseInt(st.nextToken())-1,
								Integer.parseInt(st.nextToken())-1,
								Integer.parseInt(st.nextToken())-1);
		}

		Query[] queries = new Query[q];
		for (int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			queries[i] = new Query(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())-1,
					i);
		}

		br.close();
		Arrays.sort(queries);
		Arrays.sort(edges);
		par = new int[n];
		sz = new int[n];
		for(int i = 0; i < n; i++) {
			par[i] = i;
			sz[i] = 1;
		}

		int[] res = new int[q];
		int idx = 0;
		for (int i=0; i<q; i++) {
			while (idx < n-1 && edges[idx].w >= queries[i].w) {
				Edge e = edges[idx];
				if (e.w >= queries[i].w) union(e.a, e.b);
				idx++;
			}
			res[queries[i].i] = sizeOf(queries[i].v);
		}
		for (int i=0; i<q; i++) System.out.println(res[i]);
		out.close();
	}

	static int[] par;
	static int[] sz;

	public static int sizeOf(int x) {
		return sz[find(x)];
	}

	public static int find(int x) {
		if (par[x]==x) return x;
		return par[x] = find(par[x]);
	}

	public static void union(int x, int y) {
		if (find(x)==find(y)) return;
		int fx = find(x);
		int fy = find(y);
		par[fx] = fy;
		sz[fy] += sz[fx];
	}

    private static class Edge implements Comparable<Edge> {
        public int a, b, w;
        public Edge(int c, int d, int e){
            a = c;
            b = d;
            w = e;
        }
        public int compareTo(Edge that){
            return that.w - this.w;
        }
    }

    private static class Query implements Comparable<Query> {
        public int w, v, i;
        public Query(int x, int y, int z) {
            w=x;
            v=y;
            i=z;
        }
        public int compareTo(Query q) {
            return q.w - w;
        }
    }
}

