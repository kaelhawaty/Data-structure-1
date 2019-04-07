public class MySpecialLinkedListUtils {
	public static double[] summary(LinkedListNode head) {
		double[] ans = {0,0,0,0,0};
		LinkedListNode temp = head;
		int counter = 0;
		int max = -10000000;
		int min = 10000000;
		while(temp != null) {
			ans[0] += temp.getValue();
			if(max < temp.getValue()) {
				max = temp.getValue();
			}else {
				min = temp.getValue();
			}
			counter++;
			temp = temp.getNext();
		}
		ans[1] = ans[0]/counter;
		ans[3] = max;
		ans[4] = min;
		temp = head;
		for(int i = 0; i < counter/2;i++){
			temp = temp.getNext();
		}
		if(counter%2 == 0 && counter != 1) {	
			ans[2] = (temp.getValue()+ temp.getNext().getValue())/2;
		}else{
			ans[2] = temp.getValue();
		}
		return ans;
	}
	public static LinkedListNode reverse(LinkedListNode head) {
		LinkedListNode prev = null;
		LinkedListNode current = head;
		LinkedListNode next = head.getNext();
		while(current != null) {
			current.setNext(prev);
			prev = current;
			current = next;
			if(current != null) {
				next =  current.getNext();
			}
		}
		return prev;
	}
	public static LinkedListNode evenIndexedElements(LinkedListNode head) {
		LinkedListNode temp = head;
		while(temp != null) {
			if(temp.getNext() != null) {
				temp.setNext(temp.getNext().getNext());
			}else {
				temp.setNext(null);
			}
			temp = temp.getNext();
		}
		return head;
	}
	public static LinkedListNode removeCentralNode(LinkedListNode head) {
		LinkedListNode temp = head;
		int counter = 0;
		while(temp != null) {
			counter++;
			temp = temp.getNext();
		}
		temp = head;
		if(head == null || head.getNext() == null) {
			return null;
		}else if(counter == 2) {
			return head.getNext();
		}else {
			for(int i = 0; i < counter/2 -2+counter%2;i++){
				temp = temp.getNext();
			}
			temp.setNext(temp.getNext().getNext());
		}
		return head;
	}
	public static LinkedListNode insertionSort(LinkedListNode head) {
		if(head.getNext()==null)
			return head;
		LinkedListNode prev = head, current = head.getNext(), next = head.getNext().getNext();
		LinkedListNode tempprev = null, temp = head, tempnext =head.getNext();
		int i = 1;
		while(current != null) {
			prev.setNext(next);
			tempprev= null;
			temp = head;
			tempnext = head.getNext();
			int j;
			for(j = 0; j < i && temp != null ; j++) {
				if(current.getValue() <= temp.getValue()) {
					if(tempprev != null) {
						tempprev.setNext(current);
					}else {
						head = current;
					}
					current.setNext(temp);
					break;
				}else{
					tempprev = temp;
					temp = tempnext;
					if(tempnext != null) {
						tempnext = tempnext.getNext();
					}
				}
			}
			if(j == i) {
				tempprev.setNext(current);
				current.setNext(temp);
				prev = current;
			}
			current = next;
			if(next != null) {
				next = next.getNext();
			}
			i++;
		}
		return head;
	}
	public static LinkedListNode getMiddle(LinkedListNode head) {
		LinkedListNode oneStep=head, twoStep=head;
		while(twoStep.getNext() != null && twoStep.getNext().getNext() != null) {
			oneStep = oneStep.getNext();
			twoStep = twoStep.getNext().getNext();
		}
		return oneStep;
	}
	public static LinkedListNode merge(LinkedListNode left, LinkedListNode right) {
		LinkedListNode sorted = new LinkedListNode(0);
		LinkedListNode current;
		for(current = sorted ; left != null && right != null; current = current.getNext()) {
			if(left.getValue() <= right.getValue()) {
				current.setNext(left); 
				left = left.getNext();
			}else {
				current.setNext(right); 
				right = right.getNext();
			}
		}
		current.setNext((left == null) ? right : left);
		return sorted.getNext();
	}
	public static LinkedListNode mergeSort(LinkedListNode head) {
		if(head == null || head.getNext() == null) {
			return head;
		}
		LinkedListNode right, left, middle;
		middle = getMiddle(head);
		left = head;
		right = middle.getNext();
		middle.setNext(null);
		return merge(mergeSort(left), mergeSort(right));
		
	}
	public static boolean palindrome(LinkedListNode head) {
		LinkedListNode curNode = head, temp = null;
		int size = 0;
		boolean palindrome = true;
		while (curNode != null) {
			curNode = curNode.getNext();
			size++;
		}
		for(int i = 0; i < size/2 && palindrome; ++i) {
			curNode = head;
			for (int j = 0; j < size-i-1 && palindrome; ++j) {
				if(i==j) {
					temp = curNode;
				}
				curNode = curNode.getNext();
			}
			if(temp.getValue() != curNode.getValue())
				palindrome = false;
		}
		return palindrome;
	}
	
}