package structures;

import java.util.NoSuchElementException;

public class Queue<T> implements UnboundedQueueInterface<T> {
	public LLNode<T> head;
	public LLNode<T> tail;
	private int size;

	public Queue() {
		head = null;
		tail = null;
		size = 0;
	}

	public Queue(Queue<T> other) {
		int s = other.size();
		@SuppressWarnings("unchecked")
		T[] arr = (T[]) new Object[s];
		for(int i = 0; i < s; i++) {
			arr[i] = other.dequeue();
		}
		for(int i = 0; i < s; i++) {
			other.enqueue(arr[i]);
			this.enqueue(arr[i]);
		}
	}

	@Override
	public boolean isEmpty() {
		return (head == null) ? true : false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void enqueue(T element) {
		if (head == null) {
			head = new LLNode<T>(element);
			tail = head;
		} else {
			LLNode<T> temp = new LLNode<T>(element, tail, null);
			tail.next = temp;
			tail = temp;
		}
		size++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		T data = head.data;
		head = head.next;
		if(head != null) {
			head.before = null;
		}
		size--;
		return data;
	}

	@Override
	public T peek() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return head.data;
	}

	@Override
	public UnboundedQueueInterface<T> reversed() {
		Queue<T> newQueue = new Queue<T>();
		LLNode<T> temp = tail;
		
		while(temp != null) {
			newQueue.enqueue(temp.data);
			temp = temp.before;
		}
		return newQueue;
	}
}

class LLNode<T> {
	public T data;
	public LLNode<T> next;
	public LLNode<T> before;

	public LLNode(T data) {
		this.data = data;
	}

	public LLNode(T data, LLNode<T> before, LLNode<T> next) {
		this.data = data;
		this.next = next;
		this.before = before;
	}
}
