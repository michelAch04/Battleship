package game;

import java.util.ArrayList;

public class History {
	private ArrayList<Memento> mementos; // Physical structure
	private int top; // Stack Top Index

	public History() {
		mementos = new ArrayList<Memento>();
		top = -1;
	}

	public boolean isEmpty() {
		return this.top == -1;
	}

	public boolean push(Memento m) {
			top++;
			mementos.add(m);
			return true;
	}

	public void pop() {
		if (this.isEmpty() == true)
			return;
		else {
			mementos.remove(top);
			top--;
		}
	}

	public Memento peek() {
		if (this.isEmpty() == true) {
			System.out.println("Stack is empty");
			return null;
		} else {
			return mementos.get(top);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
