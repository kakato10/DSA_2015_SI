public class LinkedList {

	private Node head, tail;

	public boolean contains(int element) {
		Node currNode = this.head;
		while (currNode != null) {
			if (currNode.getElement() == element) {
				return true;
			}
			currNode = currNode.getRight();
		}
		return false;
	}

	public boolean contains(Node node) {
		Node currNode = this.head;
		while (currNode != null) {
			if (currNode == node) {
				return true;
			}
			currNode = currNode.getRight();
		}
		return false;
	}

	public void add(int element) {
		Node node = new Node(element);

		if (this.tail == null) {
			this.head = node;
			this.tail = node;
			return;
		}
		node.setLeft(this.tail);
		this.tail.setRight(node);
		this.tail = node;
	}

	public Node getNode(int index) {
		Node node = this.head;
		for (int i = 0; i < index; i++) {
			node = node.getRight();
		}
		return node;
	}

	public void print() {
		Node curr = this.head;
		while (curr != null) {
			System.out.println(curr.getElement());
			curr = curr.getRight();
		}
	}

	public void insert(int element, Node leftNeighbour) {
		Node node = new Node(element);
		if (leftNeighbour == null) {
			node.setRight(this.head);
			this.head.setLeft(node);
			this.head = node;
		} else if (leftNeighbour == this.tail) {
			this.tail.setRight(node);
			node.setLeft(this.tail);
			this.tail = node;
		} else {
			Node rightNeighbour = leftNeighbour.getRight();
			leftNeighbour.setRight(node);
			rightNeighbour.setLeft(node);
			node.setLeft(leftNeighbour);
			node.setRight(rightNeighbour);
		}
	}

	public Node nodeOf(int element) {
		Node curr = this.head;
		while (curr != null) {
			if (curr.getElement() == element) {
				return curr;
			}
			curr = curr.getRight();
		}
		return null;
	}

	public int getSize() {
		Node curr = this.head;
		int counter = 0;
		while (curr != null) {
			counter++;
			curr = curr.getRight();
		}
		return counter;
	}

	public void removeAt(Node node) {
		if (node.getLeft() != null) {
			node.getLeft().setRight(node.getRight());
		} else {
			this.head = node.getRight();
		}
		if (node.getRight() != null) {
			node.getRight().setLeft(node.getLeft());
		}
	}

	public boolean removeElement(int element) {
		Node curr = this.head;
		while (curr != null) {
			if (curr.getElement() == element) {
				this.removeAt(curr);
				return true;
			}
			curr = curr.getRight();
		}
		return false;
	}

	public LinkedList copy() {
		LinkedList listCopy = new LinkedList();
		Node currNode = this.head;
		while (currNode != null) {
			listCopy.add(currNode.getElement());
			currNode = currNode.getRight();
		}
		return listCopy;
	}

	public LinkedList reverse() {
		LinkedList listReversed = new LinkedList();
		Node currNode = this.tail;
		while (currNode != null) {
			listReversed.add(currNode.getElement());
			currNode = currNode.getLeft();
		}
		return listReversed;
	}

	public boolean equals(LinkedList otherList) {
		if (this.getSize() != otherList.getSize()) {
			return false;
		}
		if (this == otherList) {
			return true;
		}
		Node currNode = this.head;
		for (int i = 0; i < otherList.getSize(); i++) {
			if (currNode.getElement() != otherList.getNode(i).getElement()) {
				return false;
			}
			currNode = currNode.getRight();
		}
		return false;
	}

	public LinkedList removeDuplicates() {
		// other way - quicksort and add only different elements
		LinkedList list = new LinkedList();
		int max = this.head.getElement();
		int min = this.head.getElement();
		Node currNode = this.head.getRight();
		while (currNode != null) {
			if (max < currNode.getElement()) {
				max = currNode.getElement();
			}
			if (min > currNode.getElement()) {
				min = currNode.getElement();
			}
			currNode = currNode.getRight();
		}
		boolean[] positive = new boolean[max + 1];
		boolean[] negative = new boolean[Math.abs(min) + 1];
		currNode = this.head;
		while (currNode != null) {
			if (currNode.getElement() >= 0) {
				positive[currNode.getElement()] = true;
			} else {
				negative[Math.abs(currNode.getElement())] = true;
			}
			currNode = currNode.getRight();
		}
		for (int i = 0; i < positive.length; i++) {
			if (positive[i] == true) {
				list.add(i);
			}
		}
		for (int i = 0; i < negative.length; i++) {
			if (negative[i] == true) {
				list.add(i * (-1));
			}
		}
		return list;
	}

	public void splice(LinkedList otherList, Node start, Node end) {
		Node currNode = otherList.nodeOf(start.getElement());
		Node endNode = otherList.nodeOf(end.getElement());
		while (currNode != endNode) {
			this.add(currNode.getElement());
			currNode = currNode.getRight();
		}
	}

	public LinkedList splitAt(Node node) {
		LinkedList list = new LinkedList();
		if (this.contains(node)) {
			Node currNode = node;
			Node nextNode;
			while (currNode != null) {
				nextNode = currNode.getRight();
				list.add(currNode.getElement());
				this.removeAt(currNode);
				currNode = nextNode;
			}
			return list;
		} else
			return null;
	}
}
