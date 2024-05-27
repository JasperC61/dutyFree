package start;
/*平衡樹
請用遞迴寫一個平衡樹.並使用此平衡樹將下列值有小到大印出
    int[] y={9,100,4,80,3,5,1,60,90,25,33,6,2,77}
*/
public class demo {
	/*
	 AVLTree 是代表 AVL 樹的類別。
     Node 是 AVL 樹中的節點，每個節點包含一個鍵值 key、高度 height，以及指向左、右子節點的指標。
     Node 的構造函數用於初始化節點的鍵值並將高度設為 1。

	 */
	static class AVLTree {
	    class Node {
	        int key, height;
	        Node left, right;

	        Node(int d) {
	            key = d;
	            height = 1;
	        }
	    }

	    private Node root;

	    //取得樹高度的實用函數
	    int height(Node N) {
	        if (N == null)
	            return 0;
	        return N.height;
	    }

	    // 取得兩個整數中最大值的實用函數
	    int max(int a, int b) {
	        return (a > b) ? a : b;
	    }

	    // 右旋轉以 y 為根的子樹的實用函數
	    Node rightRotate(Node y) {
	        Node x = y.left;
	        Node T2 = x.right;

	        // 替換
	        x.right = y;
	        y.left = T2;

	        // 更新高度
	        y.height = max(height(y.left), height(y.right)) + 1;
	        x.height = max(height(x.left), height(x.right)) + 1;

	        // 返回新根
	        return x;
	    }

	    /* 對節點 x 進行左旋操作，使 x 的右子節點 y 成為新的根節點。
	    更新節點高度並返回新的根節點*/
	    Node leftRotate(Node x) {
	        Node y = x.right;
	        Node T2 = y.left;

	        //替換
	        y.left = x;
	        x.right = T2;

	        // 更新高度
	        x.height = max(height(x.left), height(x.right)) + 1;
	        y.height = max(height(y.left), height(y.right)) + 1;

	        // 返回新的根結點
	        return y;
	    }

	    //  取得節點N的平衡因子
	    int getBalance(Node N) {
	        if (N == null)
	            return 0;
	        return height(N.left) - height(N.right);
	    }

	    Node insert(Node node, int key) {
	        // 執行正常的BST插入
	        if (node == null)
	            return (new Node(key));

	        if (key < node.key)
	            node.left = insert(node.left, key);
	        else if (key > node.key)
	            node.right = insert(node.right, key);
	        else //不允許重複的鍵
	            return node;

	        /* 更新該祖先節點的高度*/
	        node.height = 1 + max(height(node.left), height(node.right));

	        // 取得該祖先節點的平衡因子，檢查是否該節點變得不平衡
	        int balance = getBalance(node);

	        // 如果這個節點變得不平衡，那麼有4種情況

	        // Left Left Case
	        if (balance > 1 && key < node.left.key)
	            return rightRotate(node);

	        // Right Right Case
	        if (balance < -1 && key > node.right.key)
	            return leftRotate(node);

	        // Left Right Case
	        if (balance > 1 && key > node.left.key) {
	            node.left = leftRotate(node.left);
	            return rightRotate(node);
	        }

	        /* 對節點 y 進行右旋操作，使 y 的左子節點 x 成為新的根節點。
	        更新節點高度並返回新的根節點
	        */

	        if (balance < -1 && key < node.right.key) {
	            node.right = rightRotate(node.right);
	            return leftRotate(node);
	        }

	        // 更新節點高度並返回新的根節點
	        return node;
	    }

	    // 遞迴地以中序遍歷方式打印樹的節點鍵值，從而按升序顯示
	    void inOrder(Node node) {
	        if (node != null) {
	            inOrder(node.left);
	            System.out.print(node.key + " ");
	            inOrder(node.right);
	        }
	    }

	    // 封裝的插入方法，用於插入鍵值到樹中
	    void insert(int key) {
	        root = insert(root, key);
	    }

	    // 封裝的中序遍歷打印方法，用於打印整個樹的鍵值
	    void printInOrder() {
	        inOrder(root);
	    }
	    
	    /*
	     創建一個 AVLTree 的實例並插入指定的鍵值數組
	     打印樹的中序遍歷結果
	      
	     */

	    public static void main(String[] args) {
	        AVLTree tree = new AVLTree();

	        int[] y = {9, 100, 4, 80, 3, 5, 1, 60, 90, 25, 33, 6, 2, 77};

	        for (int num : y) {
	            tree.insert(num);
	        }

	        tree.printInOrder();
	    }
	}}