package project2;
/*
node and key num
class Node
{
	int key;
	Node left, right;

	public Node(int item)
	{
		key = item;
		left = right = null;
	}
}
*/
public class BTNode {
    //define as a string
    String key;
    //book
    Book num;
    //hegiht
    int height;
    //Binary Tree node
    BTNode leftPtr;
    BTNode rightPtr;

    BTNode(String key, Book num, int height) {
        this.key = key;
        this.num = num;
        this.height = height;
    }
}