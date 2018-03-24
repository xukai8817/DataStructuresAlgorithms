for (TreeNode<K, V> x, xp, xpp, xppl, xppr;;) {
	// 空树或只存在一个根结点
	if ((xp == x.parent) == null) {
		x.red = false;
		return x;
	} else if (!xp.red || (xpp = xp.parent) == null)
		return root;
	
	// 1.x的父结点为左子结点
	if (xp == (xppl = xpp.left)) {
		// 1-1.x的叔结点为红色
		if ((xppr = xpp.right) != null && xppr.red) {
			// 更改xp,xpp,xppr的颜色
			xppr = false;
			xp.red = false;
			xpp = true;
			x = xpp;
		} 
		// 1-2.x的叔结点为黑色
		else {
			// 1-2-1.x为右子结点
			if (x == xp.right) {
				root = rotateLeft(root, x = xp);
				xpp = (xp = x.parent) == null ? null : xp.parent;
			}
			// 1-2-2.得到情况三结构:x变为x左子结点
			// 执行右旋
			if (xp != null) {
				xp.red = false;
				if (xpp != null) {
					xpp.red = true;
					root = rotateRight(root, xpp);
				}
			}
			
		}
	} 
	// 2.x的父结点为右子结点
	else {
		
		
	}
	
}