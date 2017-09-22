# 插入平衡
`前提条件：插入结点x为红色，且与其父结点已连接  `

	x:当前插入结点指针  
	xp:当前插入结点的父结点  
	xpp:当前插入结点的祖父结点  
	xppr,xppl:当前插入结点的叔结点(uncle)  
    
## 循环条件
### x为根结点
```java  
x.red = false;  
return x;  
```
### xp为黑结点或xp为根结点
```java  
return root;  
```