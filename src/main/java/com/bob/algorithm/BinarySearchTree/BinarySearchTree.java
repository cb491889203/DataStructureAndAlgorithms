package com.bob.algorithm.BinarySearchTree;

/**
 * BinarySearchTree
 * 二叉查找树
 *
 * @author bob
 * @time 2019/1/2 21:48
 */
public class BinarySearchTree {

	private BinaryNode<Integer> rootNode;

	public BinaryNode<Integer> getRootNode() {
		return rootNode;
	}

	/**
	 * 添加对象到二叉查找树
	 *
	 * @param i 添加的值
	 * @return
	 */
	public boolean add(Integer i) {

		if (rootNode == null) {
			rootNode = new BinaryNode<>(i, null, null);
			return true;
		} else {

			BinaryNode<Integer> insert = insert(i, rootNode);
			return insert != null;
		}
	}

	/**
	 * Internal method to insert into a subtree.
	 *
	 * @param i the item to insert.
	 * @param r the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	public BinaryNode<Integer> insert(Integer i, BinaryNode<Integer> r) {

		if (r == null) {
			System.out.println("insert val = " + i);
			return new BinaryNode<Integer>(i, null, null);
		}
		;

		int cpr = i.compareTo(r.val);

		if (cpr < 0) {
			r.left = insert(i, r.left);
		} else if (cpr > 0) {
			r.right = insert(i, r.right);
		} else {
			//equal to the val , do nothing.
		}

		return r;
	}

	/**
	 * 删除指定的对象
	 *
	 * @param i
	 * @return
	 */
	public boolean remove(Integer i) {

		BinaryNode<Integer> remove = remove(i, rootNode);

		System.out.println("remove i = " + (remove == null ? null : remove.val));
		return remove != null;
	}

	/**
	 * Internal method to remove from a subtree.
	 *
	 * @param i the item to remove.
	 * @param r the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<Integer> remove(Integer i, BinaryNode<Integer> r) {

		if (r == null) {
			return r;
		}
		int rootV = r.val;
		System.out.println(rootV);

		int cpr = i.compareTo(rootV);

		if (cpr < 0) {

			r.left = remove(i, r.left);
		} else if (cpr > 0) {

			r.right = remove(i, r.right);
		} else if (r.left != null && r.right != null) {

			r.val = findMin(r.right).val;
			r.right = remove(r.val, r.right);
		} else {
			r = (r.left != null) ? r.left : r.right;
		}

		return r;
	}

	/**
	 * 找到最小的node
	 *
	 * @param r 根node
	 * @return
	 */
	private BinaryNode<Integer> findMin(BinaryNode<Integer> r) {
		if (r != null) {
			while (r.left != null) {
				r = r.left;
			}
		}
		return r;
	}
}
