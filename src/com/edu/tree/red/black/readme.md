# 插入平衡
`前提条件：插入结点x为红色，且与其父结点已连接  `

	x.red = true
	x:当前插入结点指针  
	xp:当前插入结点的父结点  
	xpp:当前插入结点的祖父结点  
	xppr,xppl:当前插入结点的叔结点(uncle)  
    
# 循环

## x为根结点
```java  
x.red = false;  
return x;  
```

## xp为黑结点或xp为根结点
```java  
return root;  
```

## 其他情况

### xp is left-child
#### x.uncle is red
```java  
xppr = fasle;  
xp.red = false;  
xpp = true;  
x = xpp;	// 向上遍历
```   
`从头开始循环`
#### x.uncle is black
```java  
if (x == xp.right) {  
	// 左旋xp，且调整x,xp,xpp  
}  
if (xp != null) {  
	xp.red = false;  
	if (xpp != null) {  
		xpp.red = true;  
		// 右旋xpp  
	}  
}  
```  
```从头开始循环```

### xp is right-child
```太麻烦了，省略了:octopus:```
