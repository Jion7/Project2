package project2;
//random
import java.util.Random;
//BinaryTree function = it is similar to the AVLtree function
public class BinaryTree {
    BTNode root;
    //left and right imbalance to +- 2
    public static final int LEFT_IMBALANCE = 2;
    public static final int RIGHT_IMBALANCE = -2;
    //root node 
    public void insert(String key, Book value) {
        root = insert_insert(key, value, root);
    }

    private BTNode insert_insert(String key, Book value, BTNode nodeTemp) {
        if (nodeTemp == null || nodeTemp.key.compareTo(key) == 0) {
            return new BTNode(key, value, 0);
        }
        
        Random rand = new Random();
        int randomInt = rand.nextInt(2);
        //if the random number ==0, move left
        if (randomInt == 0 ) {
            nodeTemp.leftPtr = insert_insert(key, value, nodeTemp.leftPtr);
        } 
        //else it moves to right
        else {
            nodeTemp.rightPtr = insert_insert(key, value, nodeTemp.rightPtr);
        }
        return replaceHeight(nodeTemp);
    }
    //+1 to current to set new register
    private BTNode replaceHeight(BTNode nodeTemp) {
        nodeTemp.height = getCurrentHeight(nodeTemp) + 1;
        return nodeTemp;
    }
    //get height 
    private int getHeight(BTNode nodeTemp) {
        return nodeTemp != null ? nodeTemp.height : -1;
    }
    //to get current hegiht
    private int getCurrentHeight(BTNode nodeTemp) {
        return Math.max(getHeight(nodeTemp.leftPtr), getHeight(nodeTemp.rightPtr));
    }

    public int bstOrderVerify
    (BTNode nodeTemp) {
        //count =0
        int count = 0;
        //print out the order verification
        if (nodeTemp.rightPtr != null && nodeTemp.key.compareTo(nodeTemp.rightPtr.key) > 0) {
            System.out.println("BST order between the parent " + nodeTemp.key + " and the right child " + nodeTemp.rightPtr.key);
            count++;
        }
        if (nodeTemp.leftPtr != null && nodeTemp.key.compareTo(nodeTemp.leftPtr.key) < 0) {
            System.out.println("BST order between the parent " + nodeTemp.key + " and the left child " + nodeTemp.leftPtr.key);
            count++;
        }
        if (nodeTemp.leftPtr != null) {
            count = count + bstOrderVerify
            (nodeTemp.leftPtr);
        }
        if (nodeTemp.rightPtr != null) {
            count = count + bstOrderVerify
            (nodeTemp.rightPtr);
        }
        return count;
    }
    //this is for AVL verification
    public int avlBalanceVerify(BTNode nodeTemp) {
        int count = 0;
        int balance = getHeight(nodeTemp.leftPtr) - getHeight(nodeTemp.rightPtr);
        if (balance >= LEFT_IMBALANCE || balance <= RIGHT_IMBALANCE) {
            System.out.println("AVL Balance at the parent " + nodeTemp.key + " with the balance of " + balance);
            count++;
        }
        if (nodeTemp.leftPtr != null) {
            count = count + avlBalanceVerify(nodeTemp.leftPtr);
        }
        if (nodeTemp.rightPtr != null) {
            count = count + avlBalanceVerify(nodeTemp.rightPtr);
        }
        return count;
    }
}