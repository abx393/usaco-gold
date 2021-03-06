import java.io.*;
import java.util.*;

public class piepie {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("piepie.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		int[] b = new int[2*n];
		int[] e = new int[2*n];
		Item[] sb = new Item[n];
		Item[] se = new Item[n];
		int[] dist = new int[2*n];

		for (int i=0; i<2*n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			b[i] = x;
			e[i] = y;
			if (i<n) se[i] = new Item(y, i);
			else sb[i-n] = new Item(x, i);

			if (i<n && y==0) dist[i] = 1;
			if (i>=n && x==0) dist[i] = 1;
		}
		br.close();
		Arrays.sort(se);
		Arrays.sort(sb);

		LinkedList<Integer> q = new LinkedList<Integer>();
		for (int i=0; i<2*n; i++) {
			if (dist[i]==1) q.add(i);
		}

		while (!q.isEmpty()) {
			int curr = q.removeFirst();
			if (curr<n) {
				// search sb for gifts valued between b[curr]-d and b[curr] and add to queue
				int low = Arrays.binarySearch(sb, new Item(b[curr]-d, 0));
				if (low<0) low = -(low+1);
				int hi = Arrays.binarySearch(sb, new Item(b[curr], 0));

				if (hi<0) hi = -(hi+1);
				if (hi>=n) hi--;
				if (sb[hi].val>b[curr]) hi--;

				for (int i=low; i<=hi; i++){
					if (dist[sb[i].idx]!=0) continue;
					dist[sb[i].idx] = dist[curr]+1;
					q.add(sb[i].idx);
				}
			}
			else {
				// search se for gifts valued between e[curr]-d and e[curr] and add to queue
				int low = Arrays.binarySearch(se, new Item(e[curr] - d, 0));
				if (low < 0) low = -(low+1);
				int hi = Arrays.binarySearch(se, new Item(e[curr], 0));
				if (hi < 0) hi = -(hi+1);
				if (hi >= n) hi--;
				if (se[hi].val>e[curr]) hi--;

				for (int i=low; i<=hi; i++) {
					if (dist[se[i].idx]!=0) continue;
					dist[se[i].idx] = dist[curr] + 1;
					q.add(se[i].idx);
				}
			}
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("piepie.out")));
		for (int i=0; i<n; i++) {
			if (dist[i]==0) out.println(-1);
			else out.println(dist[i]);
		}
		out.close();
	}

    private static class Item implements Comparable<Item> {
        public int val, idx;
        public Item(int a, int b){
            val = a;
            idx = b;
        }
        public int compareTo(Item that){
            return this.val - that.val;
        }
    }
}

