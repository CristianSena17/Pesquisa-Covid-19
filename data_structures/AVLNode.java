package data_structures;

public class AVLNode<T> {
    T key;
    int height;
    AVLNode<T> left, right;
 
    AVLNode(T d) {
        key = d;
        height = 1;
    }
}