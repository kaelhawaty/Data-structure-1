package eg.edu.alexu.csd.datastructure.linkedList.cs23_cs51;
class term{
	int coeff;
	int exp;
	term(){
		coeff = 0;
		exp = 0;
	}
	public int getCoeff() {
		return coeff;
	}
	public int getExp() {
		return exp;
	}
	public void setCoeff(int t) {
		coeff = t;
	}
	public void setExp(int t) {
		exp = t;
	}
	
}
class node {
     node next;
     Object obj;
     node(term t){
    	 obj = t;
     }
     node(){
 	 }
     node copy() {
    	 node copied = new node(new term());
    	 if(obj instanceof term) {
    		 copied.getPolynomial().setCoeff(this.getPolynomial().getCoeff());
    		 copied.getPolynomial().setExp(this.getPolynomial().getExp());
    	 }else {
    		 copied.obj = obj;
    	 }
    	 return copied;
     }
     public term getPolynomial() {
    	 if(obj instanceof term ) {
    		 return (term) obj;
    	 }
    	 return null;
     }
     public int compare(node second) throws RuntimeException {
    	if(obj instanceof term) {
    		return this.getPolynomial().getExp() - second.getPolynomial().getExp();
    	}
    	throw new RuntimeException("Unable to compare");
 	}
   
}
public class LinkedList {
	private node head;
	LinkedList(){
		head = null;
	}
	LinkedList(term t){
		head = new node(t);
	}
	
	public node getHead() {
		return head;
	}
	public void add(int index, Object element){
        node n = new node();
        node cur = head;
        if(element instanceof term) {
        	n.obj = new term();
        	n.getPolynomial().setCoeff(((term) element).getCoeff());
        	n.getPolynomial().setExp(((term) element).getExp());
        }else {
        	n.obj = element;
        }
        if(index <= 0) {
        	n.next = head;
        	head = n;
        }else {
        for (int i = 0; i < index-1 && cur.next != null; ++i)
            cur = cur.next;
        n.next = cur.next;
        cur.next = n;
        }
    }
	public boolean checkExp(node element) {
		node cur = head;
		int cnt = 0;
        while(cur != null){
            if(cur.compare(element) == 0){
            	cur.getPolynomial().setCoeff(cur.getPolynomial().getCoeff() + element.getPolynomial().getCoeff());
            	if(cur.getPolynomial().getCoeff() == 0)
            		this.remove(cnt);
                return true;
            }
            cnt++;
            cur = cur.next;
        }
		return false;
	}
	public  void insertionSort(node element) {
		if(head.getPolynomial().getCoeff() == 0 && head.getPolynomial().getExp() == 0) {
			head = element;
		}else {
			node cur = head;
			node prev =  null;
			while(cur != null && cur.compare(element) > 0 ) {
				prev = cur;
				cur = cur.next;
			}
			if(cur == head) {
				this.add(0, element.getPolynomial());
			}else {
				prev.next = element;
				element.next = cur;
			}
			
		}
	}
    public void add(Object element){
        node newNode = new node(new term());
        node cur = head;
        if(element instanceof term) {
        	newNode.getPolynomial().setCoeff(((term) element).getCoeff());
        	newNode.getPolynomial().setExp(((term) element).getExp());
        }else {
        	newNode.obj = element;
        }
        
        if(head == null || head.getPolynomial().getCoeff() == 0 && head.getPolynomial().getExp() == 0)
        	head = newNode;
        else {
        	while(cur.next != null)
        		cur = cur.next;
        	newNode.next = null;
        	cur.next = newNode;
        }
    }
    public Object get(int index){
        node cur = head;
        for (int i = 0; i < index && cur.next != null; ++i)
            cur = cur.next;
        return (cur.obj instanceof term) ? ((term) cur.obj) : (cur.obj);
    }
    public void set(int index, Object element){
        node cur = head;
        for (int i = 0; i < index && cur.next != null; ++i)
            cur = cur.next;
        if(element instanceof term) {
        	cur.obj = new term();
        	cur.getPolynomial().setCoeff(((term) element).getCoeff());
        	cur.getPolynomial().setExp(((term) element).getExp());
        }else {
        	cur.obj = element;
        }
    }
    public void clear(){
    	head.next = null;
    	if(head.obj instanceof term) {
    		head.getPolynomial().setCoeff(0);
    		head.getPolynomial().setExp(0);
    	}else {
    		head = null;
    	}
    }
    public boolean isEmpty(){
    	if(head.obj instanceof term) {
    		if(head.getPolynomial().getCoeff() == 0 && head.getPolynomial().getExp() == 0) {
    			return true;
    		}
    	}else {
    		if(head.obj == null) {
    			return true;
    		}
    	}
        return false;
    }
    public void remove(int index){
        node cur = head;
        if(index <= 0)
        	head = head.next;
        else {
        for (int i = 0; i < index-1 && cur.next.next != null; ++i)
            cur = cur.next;
        cur.next = cur.next.next;
        }
    }
    public int size(){
        node cur = head;
        int counter = 0;
        while (cur != null){
            cur = cur.next;
            counter++;
        }
        return counter;
    }
    public LinkedList sublist(int fromIndex, int toIndex){
    	LinkedList newLinked = new LinkedList();
        node cur = head;
        for (int i = 0; i < fromIndex && cur.next != null; ++i)
            cur = cur.next;
        node Ncur = newLinked.head;
        Ncur.obj = cur.obj;
        for(int i = fromIndex; i < toIndex && cur.next != null; ++i){
            node n = new node();
            n.obj = cur.next.obj;
            cur = cur.next;
            Ncur.next = n;
            Ncur = Ncur.next;
        }
        Ncur.next = null;
        return newLinked;
    }
    public boolean contain(Object o){
        node cur = head;
        while(cur != null){
            if(cur.obj instanceof term && o instanceof term) {
            	if(cur.getPolynomial().getCoeff() == ((term)o).getCoeff() && cur.getPolynomial().getCoeff() == ((term)o).getCoeff()) {
            		return true;
            	}
            }else {
            	if(cur.obj  == o) {
            		return true;
            	}
            }
            cur = cur.next;
        }
        return false;
    }
}
