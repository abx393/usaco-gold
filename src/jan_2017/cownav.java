import java.io.*;
import java.util.*;

public class cownav {
	public static int n;
	public static boolean[][][][][][] seen;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cownav.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));

		n = Integer.parseInt(br.readLine());
		char[][] grid = new char[n][n];
		for (int i=n-1; i>=0; i--) grid[i] = br.readLine().toCharArray();
		br.close();


		seen = new boolean[n][n][4][n][n][4];

		seen[0][0][2][0][0][1] = true;

		int orient1 = 0, orient2 = 0;
		LinkedList<State> q = new LinkedList<State>();
		q.add(new State(0, 0, 2, 0, 0, 1, 0));

		while (!q.isEmpty()) {
			State s = q.removeFirst();

			// check if reached destination
			if (s.xpos1==n-1 && s.ypos1==n-1 && s.xpos2==n-1 && s.ypos2==n-1){
				out.println(s.dist);
				break;
			}

			// turn left
			orient1 = (s.orient1+3) % 4;
			orient2 = (s.orient2+3) % 4;
			State s2 = new State(s.xpos1, s.ypos1, orient1, s.xpos2, s.ypos2, orient2, s.dist+1);
			if (!visited(s2)) {
				markSeen(s2);
				q.add(s2);
			}

			// turn right
			orient1 = (s.orient1+1) % 4;
			orient2 = (s.orient2+1) % 4;
			s2 = new State(s.xpos1, s.ypos1, orient1, s.xpos2, s.ypos2, orient2, s.dist+1);
			if (!visited(s2)) {
				markSeen(s2);
				q.add(s2);
			}

			// move forward
			int[] new1;
			if (s.xpos1 == n-1 && s.ypos1 == n-1) new1 = new int[] {n-1, n-1, s.orient1};
			else new1 = forward(s.xpos1, s.ypos1, s.orient1);

			int[] new2;
			if (s.xpos2 == n-1 && s.ypos2 == n-1) new2 = new int[] {n-1, n-1, s.orient2};
			else new2 = forward(s.xpos2, s.ypos2, s.orient2);

			if (grid[new1[0]][new1[1]] == 'H') {
				new1[0] = s.xpos1;
				new1[1] = s.ypos1;
			}
			if (grid[new2[0]][new2[1]] == 'H') {
				new2[0] = s.xpos2;
				new2[1] = s.ypos2;
			}
			s2 = new State(new1[0], new1[1], new1[2], new2[0], new2[1], new2[2], s.dist+1);
			if (!visited(s2)) {
				markSeen(s2);
				q.add(s2);
			}
		}
		out.close();
	}

	public static int[] forward(int i, int j, int o) {
		int i2=i, j2=j;
		if (o==0 && i>0) i2--;
		else if (o==1 && j<n-1) j2++;
		else if (o==2 && i<n-1) i2++;
		else if (o==3 && j>0) j2--;
		return new int[] {i2, j2, o};
	}

	public static boolean visited(State s) {
		return seen[s.xpos1][s.ypos1][s.orient1][s.xpos2][s.ypos2][s.orient2];
	}

	public static void markSeen(State s) {
		seen[s.xpos1][s.ypos1][s.orient1][s.xpos2][s.ypos2][s.orient2] = true;
	}

    private static class State {
        public int xpos1, ypos1, orient1, xpos2, ypos2, orient2, dist;
        public State(int a, int b, int c, int d, int e, int f, int g){
            xpos1 = a;
            ypos1 = b;
            orient1 = c;
            xpos2 = d;
            ypos2 = e;
            orient2 = f;
            dist = g;
        }
    }
}
