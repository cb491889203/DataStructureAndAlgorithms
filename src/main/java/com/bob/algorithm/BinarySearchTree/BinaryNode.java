package com.bob.algorithm.BinarySearchTree;

public class BinaryNode<T> {

	public T val;
	public BinaryNode<T> left;
	public BinaryNode<T> right;

	public BinaryNode(T element, BinaryNode<T> leftNode, BinaryNode<T> rightNode) {
		this.val = element;
		this.left = leftNode;
		this.right = rightNode;
	}
}
