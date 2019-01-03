package com.bob.algorithm.BinarySearchTree;

import java.util.Random;

public class TestBinarySearchTree {

	public static void main(String[] args) {
		//add some num;
		BinarySearchTree tree = new BinarySearchTree();

		Random random = new Random();
		for (int i = 0; i < 15; i++) {
			int value = random.nextInt(20);
			tree.add(value);
		}

		tree.add(5);
		tree.add(4);
		tree.add(6);

		boolean remove = tree.remove(5);

		System.out.println(remove);

	}
}
