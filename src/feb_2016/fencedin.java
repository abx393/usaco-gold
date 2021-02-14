import java.io.*;
import java.util.*;

public class fencedin {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("fencedin.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken()) + 1;
		int h = Integer.parseInt(st.nextToken()) + 1;

		long[][][] graph = new long[h][w][4];
		int[] vert = new int[w], horiz = new int[h];

		for (int i=0; i<w-1; i++) vert[i] = Integer.parseInt(br.readLine());
		for (int i=0; i<h-1; i++) horiz[i] = Integer.parseInt(br.readLine());
		br.close();

		vert[w-1] = a;
		horiz[h-1] = b;
		Arrays.sort(vert);
		Arrays.sort(horiz);

		int prev = 0;
		for (int i=0; i<w; i++) {
			for (int j=0; j<h-1; j++) {
				graph[j][i][2] = graph[j+1][i][0] = vert[i] - prev;
			}
			prev = vert[i];
		}

		prev = 0;
		for (int i=0; i<h; i++) {
			for (int j=0; j<w-1; j++) {
				graph[i][j][1] = graph[i][j+1][3] = horiz[i] - prev;
			}
			prev = horiz[i];
		}

		long[][] dist = new long[h][w];
		for (int i=0; i<h; i++) Arrays.fill(dist[i], Long.MAX_VALUE);

		PriorityQueue<Node> pq = new PriorityQueue<Node>();

		boolean[][] intree = new boolean[h][w];
		intree[0][0] = true;

		dist[0][1] = graph[0][0][1];
		dist[1][0] = graph[0][0][2];
		pq.add(new Node(0, 1, dist[0][1]));
		pq.add(new Node(1, 0, dist[1][0]));

		int treesize = 1;
		long treecost = 0;

		while (treesize < h * w) {
			Node min = pq.poll();
			int i = min.i, j = min.j;

			if (intree[i][j]) continue;
			intree[i][j] = true;
			treesize++;
			treecost += min.w;
			if (i < h-1 && graph[i][j][2] < dist[i+1][j] && !intree[i+1][j]) {
				dist[i+1][j] = graph[i][j][2];
				pq.add(new Node(i+1, j, dist[i+1][j]));
			}
			if (i > 0 && graph[i][j][0] < dist[i-1][j] && !intree[i-1][j]) {
				dist[i-1][j] = graph[i][j][0];
				pq.add(new Node(i-1, j, dist[i-1][j]));
			}
			if (j < w-1 && graph[i][j][1] < dist[i][j+1] && !intree[i][j+1]) {
				dist[i][j+1] = graph[i][j][1];
				pq.add(new Node(i, j+1, dist[i][j+1]));
			}
			if (j > 0 && graph[i][j][3] < dist[i][j-1] && !intree[i][j-1]) {
				dist[i][j-1] = graph[i][j][3];
				pq.add(new Node(i, j-1, dist[i][j-1]));
			}
		}

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
		out.println(treecost);
		out.close();
	}

    private static class Node implements Comparable<Node> {
        public int i, j;
        public long w;

        public Node(int a, int b, long c){
            i = a;
            j = b;
            w = c;
        }

        public int compareTo(Node that){
            return (this.w > that.w ? 1 : -1);
        }
    }
}

