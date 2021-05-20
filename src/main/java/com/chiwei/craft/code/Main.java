package com.chiwei.craft.code;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static int maxPoints(int[][] points) {
		int n = points.length;
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		int res = 0;
		for (int i = 0; i < n - 1; i++) {
			Map<String, Integer> slope = new HashMap<>();
			int repeat = 0;
			int tmpMax = 0;
			for (int j = i + 1; j < n; j++) {
				int dy = points[i][1] - points[j][1];
				int dx = points[i][0] - points[j][0];
				if (dy == 0 && dx == 0) {
					repeat++;
					continue;
				}
				int g = gcd(dy, dx);
				if (g != 0) {
					dy /= g;
					dx /= g;
				}
				String tmp = dy + "/" + dx;
				slope.put(tmp, slope.getOrDefault(tmp, 0) + 1);
				tmpMax = Math.max(tmpMax, slope.get(tmp));
			}
			res = Math.max(res, repeat + tmpMax + 1);
		}
		return res;
	}

	private static int gcd(int dy, int dx) {
		if (dx == 0) {
			return dy;
		} else {
			return gcd(dx, dy % dx);
		}
	}

	public static void main(String[] args) {
		int[][] input = { { 1, 1 }, { 3, 2 }, { 5, 3 }, { 4, 1 }, { 2, 3 }, { 1, 4 } };
		// {{1,1},{2,2},{3,3}};
		System.out.println(maxPoints(input));
	}
}
