package eg.edu.alexu.csd.datastructure.stack.cs51;
import java.util.*;

/**
 * A basic UI to test the implementation of the stack
 * Asks for an input to determine the operation as following:
 * 1: Push
 * 2: Pop
 * 3: Peek
 * 4: Get size
 * 5: Check if empty
 * and -1 to get out of the loop and terminate
 */
public class TestStack {
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        Stack s = new Stack();
        int t = 0;
        while(t != -1){
            System.out.println("Enter operations from 1 to 5 and -1 to exit, Assuming integer input:");
            t = scan.nextInt();
            switch(t){
                case 1:
                    System.out.println("Enter Integer value to be pushed");
                    int temp =  scan.nextInt();
                    s.push(temp);
                    break;
                case 2:
                    System.out.println("The value is:");
                    try{
                    System.out.println(s.pop());
                    }catch(Exception e){
                        System.out.println("Invalid operation: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("The value is:");
                    try{
                        System.out.println(s.peek());
                    }catch(Exception e){
                        System.out.println("Invalid operation: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("The size of the stack is:");
                    System.out.println(s.size());
                    break;
                case 5:
                    if(s.isEmpty()){
                        System.out.println("Stack is empty");
                    }else{
                        System.out.println("Stack is not empty");
                    }
                    break;
                default:
                    if(t != -1) {
                        System.out.println("Please enter a correct number!");
                    }
            }
        }

    }
}
