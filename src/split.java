import java.io.*;
import java.util.*;

public class split {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("split.in"));
		int n = Integer.parseInt(br.readLine());
		X[] pairX= new X[n];
		Y[] pairY = new Y[n];
		StringTokenizer st;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			pairX[i] = new X(x, y);
			pairY[i] = new Y(x, y);
		}

		br.close();
		
		Arrays.sort(pairX);
		Arrays.sort(pairY);
		long totalArea = (pairX[n-1].x - pairX[0].x) * (pairY[n-1].y - pairY[0].y);
		
		long ymin1 = Long.MAX_VALUE; 
		long ymax1 = 0;
		long[] ymin2 = new long[n];
		long[] ymax2 = new long[n];
		
		long min = Long.MAX_VALUE;
		long max = 0;
		for (int i = n - 1; i >= 0; i--) {
			min = Math.min(min, pairX[i].y);
			max = Math.max(max, pairX[i].y);
			ymin2[i] = min;
			ymax2[i] = max;
		}
		
		long minArea = Long.MAX_VALUE;
		
		long area1, area2;
		for (int i = 0; i < pairX.length - 1; i++) {
			ymin1 = Math.min(ymin1, pairX[i].y);
			ymax1 = Math.max(ymax1, pairX[i].y);
			area1 = (pairX[i].x - pairX[0].x) * (ymax1 - ymin1);
			area2 = (pairX[n-1].x - pairX[i+1].x) * (ymax2[i+1] - ymin2[i+1]);
			minArea = Math.min(area1 + area2, minArea);
		}
		
		long xmin1 = Long.MAX_VALUE; 
		long xmax1 = 0;
		long[] xmin2 = new long[n];
		long[] xmax2 = new long[n];
		
		min = Long.MAX_VALUE;
		max = 0;
		for (int i = n - 1; i >= 0; i--) {
			min = Math.min(min, pairY[i].x);
			max = Math.max(max, pairY[i].x);
			xmin2[i] = min;
			xmax2[i] = max;
		}
		
		for (int i = 0; i < pairY.length - 1; i++) {
			xmin1 = Math.min(xmin1, pairY[i].x);
			xmax1 = Math.max(xmax1, pairY[i].x);
			area1 = (pairY[i].y - pairY[0].y) * (xmax1 - xmin1);
			area2 = (pairY[n-1].y - pairY[i+1].y) * (xmax2[i+1] - xmin2[i+1]);
			minArea = Math.min(area1 + area2, minArea);
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
		out.println(totalArea - minArea);
		out.close();
	}
}

class X implements Comparable<X> {
	public long x, y;

	public X(long a, long b) {
		x = a;
		y = b;
	}

	public int compareTo(X that) {
		return (this.x > that.x ? 1 : -1);
	}
}

class Y implements Comparable<Y> {
	public long x, y;

	public Y(long a, long b) {
		x = a;
		y = b;
	}

	public int compareTo(Y that) {
		return (this.y > that.y ? 1 : -1);
	}
}
