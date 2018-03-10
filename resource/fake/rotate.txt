/**
 * 左旋转
 */
if (y.left != T.nil)		// 非叶结点
	y.left.p = x			// y左子结点的父结点设置为x
y.p = x.p					// (y结点的父结点)设置为(x结点的父结点)
if (x.p == T.nil){			// x为根结点（root）的话
	T.root = y				// 设置y结点为根结点（root）
} else if(x == x.p.left){	// x结点为（其父结点的左子结点）
	x.p.left = y			// （x父结点的左子结点）赋值为y结点
} else
	x.p.right = y			// （x父结点的右子结点）赋值为y结点
y.left = x					// x结点设置为y结点的左子结点
x.parent = y				// y结点设置为x结点的父结点

/**
 * 右旋转
 */
// 1.（x的右子结点）不为空的话，设置其父结点为y
if (x.right != T.nil)
	x.right.p = y
x.p = y.p
// 2.x设置为（y父结点的左/右子结点）
if (y.p == T.nil) {
	T.root = y
} else if (y == y.p.left) {
	y.p.left = x
} else 
	y.p.right = x
// 3.关联x,y
x.right = y
y.p = x
	