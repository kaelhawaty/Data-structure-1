package eg.edu.alexu.csd.datastructure.iceHockey.cs51;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
class BoundingBox{
	int minX;
	int maxX;
	int minY;
	int maxY;
	public void setDefault(int x,int y) {
		minX = x;
		maxX = x;
		minY = y;
		maxY = y;
	}
	public int getX() {
		return minX + maxX + 1;
	}
	public int getY() {
		return minY + maxY + 1;
	}
	
	
}
 class pointComparator implements Comparator<java.awt.Point>{
	public int compare(java.awt.Point p1, java.awt.Point p2) {
		if(p1.x != p2.x) {
			return p1.x - p2.x;
		}
		return p1.y -p2.y;
	}
	
}
public class Finder implements IPlayersFinder{
	char k;
	int t;
	BoundingBox box = new BoundingBox();
	int dfs(String[] photo, int[][] a, int x, int y) {
		if(x < 0 ||x == photo.length || y < 0 || y == photo[0].length()) {
			return 0;
		}
		if(photo[x].charAt(y) != k || a[x][y] == 1) {
			return 0;
		}
		a[x][y] = 1;
		box.minX = (x < box.minX) ? (x) : box.minX;
		box.maxX = (x > box.maxX) ? (x) : box.maxX;
		box.minY = (y < box.minY) ? (y) : box.minY;
		box.maxY = (y > box.maxY) ? (y) : box.maxY;
		return 1 + dfs(photo, a, x+1, y) + dfs(photo, a, x-1, y) + dfs(photo, a, x, y+1) + dfs(photo, a, x, y-1);
	}
	java.awt.Point[] findPlayers(String[] photo, int team, int threshold){
		k = Character.forDigit(team,10);
		t = threshold;
		ArrayList<java.awt.Point> array = new ArrayList<java.awt.Point>();
		if(photo.length == 0) {
			java.awt.Point[] ans = new java.awt.Point[0];
			return ans;
		}
		int[][] a = new int[photo.length][photo[0].length()];
		for(int i = 0 ; i < photo.length; i++) {
			Arrays.fill(a[i], 0);
		}
		for(int i = 0; i < photo.length; i++) {
			for(int j = 0; j < photo[i].length(); j++) {
				if((photo[i].charAt(j) == k) && a[i][j] == 0){
					box.setDefault(i, j);
					if(dfs(photo, a, i, j)*4 >= t) {
						java.awt.Point temp = new java.awt.Point();
						temp.setLocation(box.getY(),box.getX());
						array.add(temp);
					}
				}
			}
		}
		java.awt.Point[] ans = new java.awt.Point[array.size()];
		array.toArray(ans);
		Arrays.sort(ans, new pointComparator());
		return ans;
	}
}
