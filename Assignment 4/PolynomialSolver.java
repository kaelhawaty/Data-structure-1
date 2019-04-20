package eg.edu.alexu.csd.datastructure.linkedList.cs23_cs51;

import javax.management.RuntimeErrorException;

public class PolynomialSolver {
	LinkedList[] List= { new LinkedList(new term()), new LinkedList(new term()), new LinkedList(new term()), new LinkedList(new term())};
	
	void setPolynomial(char poly, int[][] terms) {
		for(int i = 0 ; i < terms.length; i++) {
			node element = new node();
			term t = new term();
			t.setCoeff(terms[i][0]);
			t.setExp(terms[i][1]);
			element.obj = t;
			if(!List[poly - 'A'].checkExp(element)) {
				List[poly - 'A'].insertionSort(element);
			}
		}
	}
	String print(char poly){
			String ans = "";
			node cur = List[poly- 'A'].getHead();
			while(cur != null) {
				ans +=  cur.getPolynomial().getCoeff() + " X^" + cur.getPolynomial().getExp();
				if(cur.next != null) {
					ans += " + ";
				}
				cur  = cur.next;
			}
			return ans;
	}
	void clearPolynomial(char poly) {
		if(List[poly - 'A'].isEmpty()) {
			throw new RuntimeException("Empty polynomial!");
		}
		List[poly- 'A'].clear();
	}
	float evaluatePolynomial(char poly, float value) {
		if(List[poly - 'A'].isEmpty()) {
			throw new RuntimeException("Empty polynomial!");
		}
		float ans = 0;
		node cur = List[poly- 'A'].getHead();
		while(cur != null) {
			ans += cur.getPolynomial().getCoeff() * Math.pow(value, cur.getPolynomial().getExp());
			cur = cur.next;
		}
		return ans;
	}
	int[][] add(char poly1, char poly2){
		List[3].clear();
		node cur  =  List[poly1 - 'A'].getHead();
		while(cur != null) {
			List[3].add(cur.copy().getPolynomial());
			cur = cur.next;
		}
	     cur  =  List[poly2 - 'A'].getHead();
		while(cur != null) {
			if(!List[3].checkExp(cur.copy())) {
				List[3].insertionSort(cur.copy());
			}
			cur = cur.next;
		}
		int[][] ans = new int[List[3].size()][2];
		cur = List[3].getHead();
		for(int i = 0; i < ans.length; i++) {
			ans[i][0] = cur.getPolynomial().getCoeff();
			ans[i][1] = cur.getPolynomial().getExp();
			cur = cur.next;
		}
		return ans;
	}
	int[][] subtract(char poly1, char poly2){
		List[3].clear();
		node cur = List[poly2 - 'A'].getHead();
		while(cur != null) {
			cur.getPolynomial().setCoeff(-cur.getPolynomial().getCoeff());
			cur = cur.next;
		}
		int[][] ans = add(poly1, poly2);
		 cur = List[poly2 - 'A'].getHead();
		while(cur != null) {
			cur.getPolynomial().setCoeff(-cur.getPolynomial().getCoeff());
			cur = cur.next;
		}
		return ans;
	 }
	int[][] multiply(char poly1, char poly2) {
		List[3].clear();
		node cur = List[poly1 - 'A'].getHead();
		node cur2 = List[poly2 - 'A'].getHead();
		while(cur != null) {
			while(cur2 != null) {
				node temp = new node(new term());
				temp.getPolynomial().setCoeff(cur.getPolynomial().getCoeff() * cur2.getPolynomial().getCoeff());
				temp.getPolynomial().setExp(cur.getPolynomial().getExp() + cur2.getPolynomial().getExp());
				if(!List[3].checkExp(temp)) {
					List[3].insertionSort(temp);
				}
				cur2 = cur2.next;
			}
			cur2 = List[poly2 - 'A'].getHead();
			cur = cur.next;
		}
		int[][] ans = new int[List[3].size()][2];
		cur = List[3].getHead();
		for(int i = 0; i < ans.length; i++) {
			ans[i][0] = cur.getPolynomial().getCoeff();
			ans[i][1] = cur.getPolynomial().getExp();
			cur = cur.next;
		}
		return ans;
	}
	
	
}
