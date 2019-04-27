package eg.edu.alexu.csd.datastructure.stack.cs51;

/**
 * A node implementation for the stack
 */
class node{
    /**
     * Object inside the node
     */
    private Object obj;
    /**
     * A node to point to the next nodew
     */
    private node next;

    /**
     * Constructor for the node
     * @param obj Object to contain
     * @param next Pointer to the next node
     */
    node(Object obj, node next){
        this.obj = obj;
        this.next = next;
    }

    /**
     * Sets the current node's next
     * @param next Next node
     */
    void setNext(node next) {
        this.next = next;
    }

    /**
     * Sets the current node's Object
     * @param obj Object
     */
    void setObj(Object obj) {
        this.obj = obj;
    }

    /**
     * @return Returns the Object of the node
     */
    Object getObject() {
        return obj;
    }

    /**
     * @return Returns the next of the current node
     */
    node getNext() {
        return next;
    }


}

/**
 * Implements a Stack
 */
public class Stack {
    /**
     * Initializing head with null
     */
    private node head = null;
    /**
     * A variable to represent the size of the stack
     */
    private int size = 0;

    /**
     * Removes the top of the stack and returns its value
     * @return The value of the top of the stack
     * @throws RuntimeException When stack is empty
     */
    public Object pop() {
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        Object temp = head.getObject();
        head = head.getNext();
        size++;
        return temp;
    }

    /**
     * Returns the top of the stack
     * @return The value of the top of the stack
     * @throws RuntimeException When stack is empty
     */
    public Object peek() {
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        return head.getObject();
    }

    /**
     * Pushes an Object into the stack
     * @param obj Object to be pushed
     */
    public void push(Object obj) {
        node temp = new node(obj, null);
        if(head == null) {
            head = temp;
        }else {
            temp.setNext(head);
            head = temp;
        }
        size++;
    }

    /**
     * Checks whether the stack is empty or no
     * @return Boolean whether stack is empty or no
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the size of the stack
     * @return Size
     */
    public int size() {
        return size;
    }


}
