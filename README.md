# DataStructuresAlgorithms
数据结构和算法
```
graph TD;
    S[Start]-->A
    A{是否满足遍历条件}-->B[x父结点为左子结点];
    A-->C[x父结点为右子结点];
    
    B-->D[x的叔结点不为空且为红色]
    D-->E[xppr=fasle</br>xp.red=false</br>xpp=true</br>x=xpp]
    E-->C
    E-->A
    
    C-->F[x的叔结点为黑色]
    F-->G[x为右子结点]
    G-->H[左旋且重新排列x,xp,xpp]
    H-->I[xp不为空且为黑色]
    G-->I
    I-->J{xpp是否为空}
    J-->K[xpp设为红色,对其右旋]
    K-->A
```
