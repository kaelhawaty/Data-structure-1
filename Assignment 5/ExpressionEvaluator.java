package eg.edu.alexu.csd.datastructure.stack.cs51;

import java.util.Scanner;

/**
 * A class which has two main methods:
 * infixToPostfix and evaluate.
 */
public class ExpressionEvaluator {
    /**
     * A function that returns a value representing the precedence of an operator over another
     * @param c The operator
     * @return Integer represents the the precedence of an operator
     */
    public int prec(char c) {
        if (c == '*' || c == '/')
            return 2;
        else if (c == '+' || c == '-')
            return 1;
        else
            return -1;
    }

    /**
     * Takes number a, number b and a character representing the operation and returns the value as a float.
     * @param a First number
     * @param b Second number
     * @param c Character representing the operation
     * @return Float value of the result
     */
    public float comp(float a, float b, char c) {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                return 0;
        }
    }

    /**
     * Checks whether a character is a number or not.
     * @param c Character to be checked
     * @return Boolean true if it is a number, Otherwise false.
     */
    public boolean isNumber(char c) {
        return (c >= '0' && c <= '9');
    }

    /**
     * A function that converts from InFix to Postfix notation.
     * @param expression A String in InFix notation
     * @return A String in PostFix notation
     * @throws RuntimeException for invalid input
     */
    public String infixToPostfix(String expression) {
        if (expression == null) {
            throw new RuntimeException("Expression is null");
        }
        String converted = "";
        Stack s = new Stack();
        boolean flag2 = false;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ' ') {
                continue;
            }
            if (expression.charAt(i) == '-' && !flag2 && i + 1 < expression.length() && isNumber(expression.charAt(i + 1)) || isNumber(expression.charAt(i))) {
                if (!converted.isEmpty() && !isNumber(converted.charAt(converted.length() - 1)) && converted.charAt(converted.length() - 1) != ' ' && converted.charAt(converted.length() - 1) != '-') {
                    converted += ' ';
                }
                if (flag2) {
                    throw new RuntimeException("Invalid input, Please enter a correct expression");
                }
                if (i + 1 == expression.length() || !isNumber(expression.charAt(i + 1))) {
                    flag2 = true;
                }
                converted += expression.charAt(i);
            } else if (expression.charAt(i) == '(') {
                s.push('(');
            } else if (expression.charAt(i) == ')') {
                while ((char) s.peek() != '(' && !s.isEmpty()) {
                    converted += ' ';
                    converted += (char) s.pop();
                }
                if (s.isEmpty()) {
                    throw new RuntimeException("Missing parentheses");
                } else {
                    s.pop();
                }
            } else if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*' || expression.charAt(i) == '/') {
                boolean flag = true;
                if (flag2) {
                    flag2 = false;
                } else {
                    throw new RuntimeException("Invalid input, Please enter a correct expression");
                }
                while (!s.isEmpty() && (char) s.peek() != '(' && prec((char) s.peek()) >= prec(expression.charAt(i))) {
                    flag = false;
                    converted += ' ';
                    converted += s.pop();
                }
                if (flag) {
                    converted += ' ';
                }
                s.push(expression.charAt(i));
            } else {
                throw new RuntimeException("Invalid input, Please enter a correct expression");
            }
        }

        while (!s.isEmpty()) {
            converted += ' ';
            if ((char) s.peek() == '(') {
                throw new RuntimeException("Invalid input, Please enter a correct expression");
            }
            converted += s.pop();
        }
        return converted;
    }

    /**
     * A function that evaluates the expression in PostFix notation
     * @param expression A String in Postfix notation.
     * @return Returns the value of the expression
     * @throws RuntimeException for invalid input
     */
    public int evaluate(String expression) {
        Stack s = new Stack();
        int counter = 0;
        Scanner test = new Scanner(expression);
        while(test.hasNext()){
            String temp = test.next();
            if(temp.charAt(0) == '-' && 1 != temp.length() && isNumber(temp.charAt(1))){
                counter++;
            }else if (isNumber(temp.charAt(0))){
                counter ++;
            }else if(temp.charAt(0) == '+' || temp.charAt(0) == '-' || temp.charAt(0) == '*' || temp.charAt(0) == '/'){
                if(counter == 1){
                    throw new RuntimeException("Invalid input, Please enter a correct expression");
                }else{
                    counter--;
                }
            }else{
                throw new RuntimeException("Invalid input, Please enter a correct expression");
            }
        }
        if(counter != 1){
            throw new RuntimeException("Invalid input, Please enter a correct expression");
        }
        Scanner scan = new Scanner(expression);
        while (scan.hasNext()) {
            String temp = scan.next();
            if (temp.charAt(0) == '-' && 1 != temp.length() && isNumber(temp.charAt(1))) {
                s.push((float) Integer.parseInt(temp));
            } else if (isNumber(temp.charAt(0))) {
                s.push((float) Integer.parseInt(temp));
            } else if (temp.charAt(0) == '+' || temp.charAt(0) == '-' || temp.charAt(0) == '*' || temp.charAt(0) == '/') {
                float b = (float) s.pop();
                float a = (float) s.pop();
                s.push(comp(a, b, temp.charAt(0)));
            }

        }
        return (int) (float) s.pop();
    }
}
