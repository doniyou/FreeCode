public class AVLTree<E> {
	// 根节点
	private Entry<E> root = null;
	// 树中元素个数
	private int size = 0;

	public AVLTree() {

	}

	public int size() {
		return size;
	}

	/**
	 * 向左旋转之后p移到p的左子树处，p的右子树B变为此最小子树根节点， B的左子树变为p的右子树
	 * 
	 * @param p 最小旋转子树的跟节点
	 */
	private void rotateLeft(Entry<E> p) {
		if (p != null) {
			Entry<E> r = p.right; 
			p.right = r.left; 
			if (r.left != null)
				r.left.parent = p;
			r.parent = p.parent;
			if (p.parent == null)
				root = r;
			else if (p.parent.left == p)
				p.parent.left = r;
			else if (p.parent.right == p)
				p.parent.right = r;
			r.left = p;
			p.parent = r;
		}
	}
	
	/**
	 * 向左旋转之后p移到p的右子树处，p的左子树B变为此最小子树根节点， B的右子树变为p的左子树
	 * 
	 * @param p 最小旋转子树的跟节点
	 */
	private void rotateRight(Entry<E> p) {
		if(p != null){
			Entry<E> l = p.left;
			p.left = l.right;
			if(l.right != null)
				l.right.parent = p;
			l.parent = p.parent;
			if(p.parent==null)
				root = l;
			else if (p.parent.left == p)
				p.parent.left = l;
			else if (p.parent.right == p)
				p.parent.right =l;
			
			l.right = p;
			p.parent = l;
		}
	}
	
	private boolean add(E element){
		Entry<E> t = root;
		if(t == null){
			root = new Entry<E>(element,null);
			size = 1;
			return true;
		}
		
		int cmp;
		Entry<E> parent;
		Comparable<? super E> e = (Comparable<? super E>) element;
		//查找插入位置
		do
		{
			parent = t;
			cmp = e.compareTo(t.element);
			if(cmp<0)
				t = t.left;
			else if (cmp >0)
				t =t.right;
			else
				return false;
		}while(t!=null);
		
		Entry<E> child = new Entry(element, parent);
		if(cmp<0)
			parent.left = child;
		else
			parent.right =child;
		
		//自上向下回溯，查找附近不平衡节点
		while(parent != null){
			cmp = e.compareTo(parent.element);
			if(cmp<0)
				parent.balance++;
			else
				parent.balance--;
			if(parent.balance == 0)
				break;
			if(Math.abs(parent.balance)==2){
				break;
			}
			parent = parent.parent;
		}
		size ++;
		return true;
	}

	
	private void fixAfterInsertion(Entry<E> p){
		if(p.balance == 2);
	}
	
	
	private static final int LH = 1; // 左高
	private static final int EH = 2; // 等高
	private static final int RH = -1;// 右高

	static class Entry<E> {
		E element;
		Entry<E> parent;
		Entry<E> left;
		Entry<E> right;
		int balance = EH;

		public Entry(E element, Entry<E> parent) {
			this.element = element;
			this.parent = parent;
		}

		public Entry() {

		}

		@Override
		public String toString() {
			return element + " BF=" + balance;
		}

	}
}
