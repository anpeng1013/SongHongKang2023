package day08_java_dataStructure;

/**
 * 专有名词解释：
 *      - 节点：树中的数据元素都称之为节点。
 *      - 根节点：最上面的节点称之为根，一颗树只有一个根且由根发展而来，从另外一个角度来说，每个节点都可以认为是其子树的根。
 *      - 父节点：节点的直接上层节点。
 *      - 子节点：节点的直接下层节点。
 *      - 兄弟节点：具有相同父节点的结点称为兄弟节点。
 *      - 节点的度数：每个节点所拥有的子树的个数称为节点的度数。
 *      - 树叶：度数为0的节点，也叫终端节点。
 *      - 分支节点：树叶以外的节点，或度数不为0的节点，也叫非终端节点。
 *      - 高度：树中节点的最大层数，也叫树的深度。
 *      - 节点的层数：从根节点到树种某节点所经路径上的分支数称为该节点的层数，根节点的层数规定为1，其余节点为父节点的层数+1。
 *
 * 二叉树的基本概念：二叉树（Binary tree）是树形结构的一个重要类型。二叉树特点是每个结点最多只能有两棵子树，且有左右之分。
 *                 许多实际问题抽象出来的数据结构往往是二叉树形式，二叉树的存储结构及其算法都较为简单，因此二叉树显得特别重要。
 *
 * 二叉树的遍历：
 *      - 前序遍历：中左右（根左右）
 *          即先访问根结点，再前序遍历左子树，最后再前序遍历右子 树。前序遍历运算访问二叉树各结点是以根、左、右的顺序进行访问的。
 *      - 中序遍历：左中右（左根右）
 *          即先中前序遍历左子树，然后再访问根结点，最后再中序遍 历右子树。中序遍历运算访问二叉树各结点是以左、根、右的顺序进行访问的。
 *      - 后序遍历：左右中（左右根）
 *          即先后序遍历左子树，然后再后序遍历右子树，最后访问根 结点。后序遍历运算访问二叉树各结点是以左、右、根的顺序进行访问的。
 *
 * 经典二叉树：
 *      - 满二叉树：除最后一层无任何子节点外，每一层的所有节点都有两个子节点的二叉树。第n层的节点数是2的n-1次方，总的节点数是2的n次方-1。
 *      - 完全二叉树：叶节点只能出现在最低的两层，且最底层叶子结点均处于此层叶节点的左侧。
 *      - 二叉排序树：（满足如下性质）
 *          * 若它的左子树不为空，则左子树上所有节点的值均小于它的根节点的值。
 *          * 若它的右子树不为空，则右子树上所有节点的值均大于它的根节点的值。
 *          * 它的左、右子树也分别为二叉排序树。
 *          * 对二叉排序树进行中序遍历，得到有序集合。便于检索。
 *      - 平衡二叉树：（首先也是二叉排序树，其次满足如下性质）
 *          * 它是一颗空树或它的左右两个子树的高度差的绝对值不超过1。
 *          * 它的左右两个子树也都是一颗平衡二叉树
 *          * 不要求非叶节点都有两个子节点。
 *          * 平衡二叉树的目的是为了减少二叉查找树的高度，提高查找速度。
 *          * 当平衡二叉树失去平衡时，通过旋转平衡因子的方法（LL、RR、RL、LR），重新达到平衡。
 *          * 平衡二叉树的常用实现有红黑树、AVL、替罪羊树、Treap、伸展树等。
 *      - 红黑树：（即Red-Black Tree。红黑树的每个节点都有存储位表示节点的颜色，可以是红色或黑色。）
 *          * 红黑树是一种（Self-balancing binary search tree）自平衡的二叉查找树，是在计算机科学中用到的一种数据结构，在1972年由
 *              Rudolf Bayer发明的。红黑树是复杂的，但它的操作有着良好的最坏情况运行时间，并且在实践中是最高效的。它可以在O(logn)时间
 *              内做查找、插入、删除，这里的n是树中元素的数目。
 *          * 红黑树满足以下特点：
 *              -- 每个节点都是红色或黑色。
 *              -- 根节点是黑色的。
 *              -- 每个叶子节点都是黑色。（注意：这里叶子节点，是指为空(NULL)的叶子节点）。
 *              -- 每个红色节点的两个子节点都是黑色的。（从每个叶子到根的所有路径上不能有两个连续的红色节点）。
 *              -- 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点（确保没有一条路径会比其他路径长出 2 倍）。
 *          * 当我们插入或删除节点时，可能会破坏已有的红黑树，需要进行以下处理：
 *              -- recolor：将某个节点变红或变黑
 *              -- retation：将红黑树某些节点分支进行旋转（左旋或右旋）
 *
 * @ClassName: F_TreeTest.java
 * @Author: anpeng
 * @Date: 2024/2/29 17:45
 */
@SuppressWarnings("all")
public class F_TreeTest {
}