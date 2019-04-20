package eg.edu.alexu.csd.datastructure.linkedList.cs23_cs51;

class Dnode {
     Dnode next;
     Dnode prev;
     Object obj;
}
public class DLinkedList {
	private Dnode head;
	DLinkedList(){
		head = new Dnode();
	}
	public Dnode getHead() {
		return head;
	}
	public void add(int index, Object element){
        Dnode n = new Dnode();
        Dnode cur = head;
        n.obj = element;
        if(index <= 0) {
        	n.next = head;
        	n.prev=null;
        	head.prev = n;
        	head = n;
        }else {
        	int i;
        for ( i = 0; i < index-1 && cur.next != null; ++i)
            cur = cur.next;
        n.next = cur.next;
        cur.next.prev=n;
        n.prev=cur;
        cur.next=n;
        }
    }
	
    public void add(Object element){
        Dnode newNode = new Dnode();
        Dnode cur = head;
        newNode.obj = element;
        if(head.obj == null){
        	newNode.prev=null;
        	newNode.next=null;
        	head = newNode;
        }else {
        	while(cur.next != null)
        		cur = cur.next;
        	newNode.next = null;
        	newNode.prev=cur;
        	cur.next = newNode;
        }
    }
    public Object get(int index){
        Dnode cur = head;
        for (int i = 0; i < index && cur.next != null; ++i)
            cur = cur.next;
        return cur.obj;
    }
    public void set(int index, Object element){
        Dnode cur = head;
        for (int i = 0; i < index && cur.next != null; ++i)
            cur = cur.next;
        cur.obj = element;
    }
    public void clear(){
    	head.next = null;
    	head.obj = null;
    }
    public boolean isEmpty(){
    	if(head.obj == null) {
    		return true;
    		}
        return false;
    }
    public void remove(int index){
        Dnode cur = head;
        if(index <= 0){
        	head = head.next;
            head.prev=null;
        }
        else {
        for (int i = 0; i < index-1 && cur.next.next != null; ++i)
            cur = cur.next;
        cur.next = cur.next.next;
        cur.next.prev=cur;
        }
    }
    public int size(){
        Dnode cur = head;
        int counter = 0;
        while (cur != null){
            cur = cur.next;
            counter++;
        }
        return counter;
    }
    public DLinkedList sublist(int fromIndex, int toIndex){
    	DLinkedList newLinked = new DLinkedList();
        Dnode cur = head;
        for (int i = 0; i < fromIndex && cur.next != null; ++i)
            cur = cur.next;
        Dnode Ncur = newLinked.head;
        Ncur.obj = cur.obj;
        for(int i = fromIndex; i < toIndex && cur.next != null; ++i){
            Dnode n = new Dnode();
            n.obj = cur.next.obj;
            cur = cur.next;
            Ncur.next = n;
            n.prev=Ncur;
            Ncur = Ncur.next;
        }
        Ncur.next = null;
        return newLinked;
    }
    public boolean contain(Object o){
        Dnode cur = head;
        while(cur != null){
            if(cur.obj  == o) 
            	return true;
            cur = cur.next;
        }
        return false;
    }
}
