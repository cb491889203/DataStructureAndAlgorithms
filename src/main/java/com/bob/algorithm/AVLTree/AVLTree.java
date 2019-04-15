package com.bob.algorithm.AVLTree;

public class AVLTree {

	private AVLNode<Integer> rootNode;

	public boolean insert(Integer i) {

		AVLNode<Integer> insert = insert(i, rootNode);

		return insert != null;
	}

	private AVLNode<Integer> insert(Integer i, AVLNode<Integer> r) {

		if (r == null) r = new AVLNode<>(i);

		return r;
	}

	private class AVLNode<T>{

		public T element;
		public AVLNode<T> leftNode;
		public AVLNode<T> rightNode;
		public int height;

		public AVLNode(T element) {
			this.element = element;
		}

		public AVLNode(T element, AVLNode<T> leftNode, AVLNode<T> rightNode) {
			this.element = element;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
		}
	}
}
