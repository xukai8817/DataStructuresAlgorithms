for (TreeNode<K, V> x, xp, xpp, xppl, xppr;;) {
	// 空树或只存在一个根结点
	if ((xp == x.parent) == null) {
		x.red = false;
		return x;
	} else if (!xp.red || (xpp = xp.parent) == null)
		return root;
	
	// x的父结点为左子结点
	if (xp == (xppl = xpp.left)) {
		// x的叔结点为红色
		if ((xppr = xpp.right) != null && xppr.red) {
			// 更改xp,xpp,xppr的颜色
			xppr = false;
			xp.red = false;
			xpp = true;
			x = xpp;
		} 
		// x的叔结点为黑色
		else {
			// x为右子结点
			if (x == xp.right) {
				root = rotateLeft(root, x = xp);
				if ((xp = x.parent) == null)
					xpp = null;
				else 
					xpp = xp.parent;
			}
			// x为左子结点
		}
	} 
	// x的父结点为右子结点
	else {
		
		
	}
	
}