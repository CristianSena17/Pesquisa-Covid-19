package data_structures;

import interfaces.functional.comparation;


 
public class AVLTree<T> 
{
    
    AVLNode<T> root;
    comparation comp;
    public AVLTree(comparation comp) {
        this.comp = comp;
    }
 
    // A utility function to get the height of the tree
    int height(AVLNode<T> N) {
        if (N == null)
            return 0;
 
        return N.height;
    }
 
    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }
 
    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    AVLNode<T> rightRotate(AVLNode<T> y) {
        AVLNode<T> x = y.left;
        AVLNode<T> T2 = x.right;
 
        // Perform rotation
        x.right = y;
        y.left = T2;
 
        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
 
        // Return new root
        return x;
    }
 
    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    AVLNode<T> leftRotate(AVLNode<T> x) {
        AVLNode<T> y = x.right;
        AVLNode<T> T2 = y.left;
 
        // Perform rotation
        y.left = x;
        x.right = T2;
 
        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
 
        // Return new root
        return y;
    }
 
    // Get Balance factor of node N
    public int getBalance(AVLNode<T> N) 
    {
        if (N == null)
            return 0;
 
        return height(N.left) - height(N.right);
    }

    public void insert(T key)
    {
        insert(root,key);
    }
    AVLNode<T> insert(AVLNode<T> node, T key) {
 
        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new AVLNode<T>(key));
        int cmp = comp.compare(key, node.key);

        if (cmp < 0)
            node.left = insert(node.left, key);
        else if (cmp > 0)
            node.right = insert(node.right, key);
        else // Duplicate keys not allowed
            return node;
 
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
                              height(node.right));
 
        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);
 
        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case

        if (balance > 1 && comp.compare(key, node.left.key) < 0)
            return rightRotate(node);
 
        // Right Right Case
        if (balance < -1 && comp.compare(key, node.right.key) > 0)
            return leftRotate(node);
 
        // Left Right Case
        if (balance > 1 && comp.compare(key, node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
 
        // Right Left Case
        if (balance < -1 && comp.compare(key, node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
 
        /* return the (unchanged) node pointer */
        return node;
    }
 
    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    void preOrder(AVLNode<T> node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    public T buscar(Object chave, comparation criterion) 
    {
        int cont=1;
        if (root == null){
            System.out.println("Numero de comparacoes na Arvore: 1");
            return null; // se arvore vazia
        } 
            
        
        AVLNode<T> atual = root;  // começa a procurar desde raiz
        int cmp = criterion.compare((Object)atual.key, chave); 
        cont++;
        while (cmp != 0) { // enquanto nao encontrou
            cont+=2;
            if(cmp < 0){
            atual = atual.left;// caminha para esquerda

            }else{
            atual = atual.right;// caminha para direita
            
            }
            cont++;
            if (atual == null){
            System.out.printf("Numero de comparacoes na Arvore: %d\n",cont);
            return null; // encontrou uma folha -> sai
            }
            cont++;
            cmp = criterion.compare(atual.key, chave);
        } // fim laço while
        System.out.printf("Numero de comparacoes na Arvore: %d\n",cont);
        return atual.key; // terminodeu o laço while e chegou aqui é pq encontrou item
    }
}