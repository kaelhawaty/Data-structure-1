package eg.edu.alexu.csd.datastructure.linkedList.cs23_cs51;
import java.util.*;
public class Application {
	static Scanner scan = new Scanner(System.in);
	static void printAction() {
		System.out.println("Please choose an action");
		System.out.println("-----------------------");
		System.out.println("1- Set a polynomial variable");
		System.out.println("2- Print the value of a polynomial variable");
		System.out.println("3- Add two polynomials");
		System.out.println("4- Subtract two polynomials");
		System.out.println("5- Multiply two polynomials");
		System.out.println("6- Evaluate a polynomial at some point");
		System.out.println("7- Clear a polynomial variable");
		System.out.println("====================================================================");
	}
	static char readChar() {
		boolean flag = false;
		char poly = 0;
		do {
			try{
				flag = false;
				poly = scan.next().charAt(0);
				if(poly < 'A' || poly > 'C') {
					throw new RuntimeException("Invalid input");
				}
				scan.nextLine();
			}catch(Exception e) {
				flag = true;
				System.out.println("Insert a correct Variable, Please!");
			}
		}while(flag);
		return poly;
	}
	static int[][] readTerms() throws RuntimeException{
		int[][] ans; 
		int counter  = 0;
		String s = scan.nextLine();
		for(int i = 0; i < s.length(); i++) {
			if(Character.isDigit(s.charAt(i))) {
				counter++;
			}
		}
		if(counter %2 != 0){
			throw new RuntimeException();
		}else {
			ans =  new int[counter/2][2];
			counter = 0;
			boolean flag = false;
			int cnt2 = 0;
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '-') {
					flag = true;
				}
				if(Character.isDigit(s.charAt(i))) {
					ans[cnt2][counter] = (flag) ? ('0' - s.charAt(i) ) : (s.charAt(i) - '0');
					flag = false;
					counter++;
					if(counter == 2) {
						counter = 0;
						cnt2++;
					}
				}
			}
			return ans;
		}
	}
	public static char getVar(PolynomialSolver solve) {
		char poly = 0;
		boolean flag = false;
		do {
			try {
				flag = false;
			poly = readChar();
			if(solve.List[poly-'A'].isEmpty()) {
				throw new RuntimeException();
			}
			}catch(Exception e) {
				flag = true;
				System.out.println("Variable not set");
			}
		}while(flag);
		return poly;
	}
	public static void main(String[] args) {
		int in = 1;
		PolynomialSolver solve = new PolynomialSolver();
		while(in >= 1) {
			printAction();
			in =  scan.nextInt();
			char poly = 0;
			char poly2 = 0;
			
			switch(in){
				case 1:
					System.out.println("Insert the variable name: A, B or C:");
					poly = readChar();
					System.out.println("Insert the polynomial terms in the form:");
					System.out.println("(coeff1, exponent1), (coeff2, exponent2), ..");
					boolean t = false;
					int[][] terms = null;
					do {
						try {
							t = false;
							terms = readTerms();	
						}catch(Exception e) {
							t = true;
							System.out.println("Input correct terms;");
						}
					}while(t);
					solve.setPolynomial(poly, terms);
					break;
				case 2:
					System.out.println("Insert the variable name: A, B or C:");
					boolean flag3 = false;
					do {
						try {
							flag3 = false;
							poly = readChar();
							if(solve.List[poly-'A'].isEmpty()) {
								throw new RuntimeException();
							}
						}catch(Exception e) {
							flag3 = true;
							System.out.println("Variable not set");
						}
						
					}while(flag3);
					System.out.println(solve.print(poly));
					break;
				case 3:
					System.out.println("Insert first operand variable name: A, B or C");
					poly = getVar(solve);
					System.out.println("Insert second operand variable name: A, B or C");
				     poly2 = getVar(solve);
					int[][] ans = solve.add(poly, poly2);
					System.out.println("Result set in R:");
					for(int i = 0; i < ans.length; i++) {
						if(i == ans.length-1) {
							System.out.print("(" + ans[i][0] + ", " + ans[i][1] + ")");
							continue;
						}
						System.out.print("(" + ans[i][0] + ", " + ans[i][1] + "), ");
					}
					System.out.println("");
					break;
				case 4:
					System.out.println("Insert first operand variable name: A, B or C");
					 poly = getVar(solve);
					System.out.println("Insert second operand variable name: A, B or C");
				     poly2 = getVar(solve);
					ans = solve.subtract(poly, poly2);
					System.out.println("Result set in R:");
					for(int i = 0; i < ans.length; i++) {
						if(i == ans.length-1) {
							System.out.print("(" + ans[i][0] + ", " + ans[i][1] + ")");
							continue;
						}
						System.out.print("(" + ans[i][0] + ", " + ans[i][1] + "), ");
					}
					System.out.println("");
					break;
				case 5:
					System.out.println("Insert first operand variable name: A, B or C");
					 poly = getVar(solve);
					System.out.println("Insert second operand variable name: A, B or C");
				     poly2 = getVar(solve);
					ans = solve.multiply(poly, poly2);
					System.out.println("Result set in R:");
					for(int i = 0; i < ans.length; i++) {
						if(i == ans.length-1) {
							System.out.print("(" + ans[i][0] + ", " + ans[i][1] + ")");
							continue;
						}
						System.out.print("(" + ans[i][0] + ", " + ans[i][1] + "), ");
					}
					System.out.println("");
					break;
				case 6:
					float value, an;
					System.out.println("Insert first operand variable name: A, B or C");
					poly = getVar(solve);
					System.out.println("Insert value");
				     value = scan.nextFloat();
					System.out.println(solve.evaluatePolynomial(poly, value));
					break;
				case 7:
					System.out.println("Insert operand variable name: A, B or C");
					 poly = getVar(solve);
					System.out.println("polynomial cleared successfully");
					solve.clearPolynomial(poly);
					break;
			}
		}
	}
}
