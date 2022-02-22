package project2;
//AVL node
/* Java program for insertion in AVL Tree
class Node {
	int key, height;
	Node left, right;

	Node(int d) {
		key = d;
		height = 1;
	}
}
 */
//For this class I changed the key to string
public class AVLNode {
    String key;
    Book num;
    int height;
    AVLNode left;
    AVLNode right;

    AVLNode (String key, Book num, int height) {
        this.key = key;
        this.num = num;
        this.height = height;

    }
}