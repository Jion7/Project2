package project2;
//AVL Tree function
public class AVLTree {
    AVLNode root;
    //left and right imbalance to +- 2
    public static final int L_Imbalance = 2;
    public static final int R_Imbalance = -2;
    // balance error
    static class BalanceErrorException extends Exception {
        BalanceErrorException(String temp) {
            System.out.println(temp);
        }
    }
    //insertion
    public void insert(String key, Book value) {
        try {
            root = insert_insert(key, value, root);
        } 
        //balance error
        catch (BalanceErrorException e) {
            System.out.println("Error1");
            System.exit(1);
        }
    }
    //print all nodes
    public void printAllNodes(AVLNode start, int depth) {
        if (start.left != null) {
            printAllNodes(start.left, depth + 1);
        }
        if (start.right != null) {
            printAllNodes(start.right, depth + 1);
        }
        System.out.println(start.key + " " + start.num.title + " Depth : " + depth + " Height : " + start.height);
    }
    //Insertion function to the tree
    private AVLNode insert_insert(String key, Book value, AVLNode init) throws BalanceErrorException {
        if (init == null || init.key.compareTo(key) == 0) {
            return new AVLNode(key, value, 0);
        } else if (key.compareTo(init.key) < 0) {
            init.left = insert_insert(key, value, init.left);
        } else {
            init.right = insert_insert(key, value, init.right);
        }
        return verify(init, key);
    }
    //get height
    private int getHeight(AVLNode nodeTemp) {
        return nodeTemp != null ? nodeTemp.height : -1;
    }
    //to get current height
    private int getCurrentHeight(AVLNode nodeTemp) {
        return Math.max(getHeight(nodeTemp.left), getHeight(nodeTemp.right));
    }
    //it verify the avl node and balance
    private AVLNode verify(AVLNode nodeTemp, String recentInsertion) throws BalanceErrorException {
        int balance = getHeight(nodeTemp.left) - getHeight(nodeTemp.right);
        if (balance == L_Imbalance || balance == R_Imbalance) {
            return balance(nodeTemp, balance, recentInsertion);
        } else if (balance < L_Imbalance && balance > R_Imbalance) {
            nodeTemp.height = getCurrentHeight(nodeTemp) + 1;
            return nodeTemp;
        } else {
            throw new BalanceErrorException("Unacceptable balance of " + balance + " at node " + nodeTemp.key);
        }
    }

    // Verify L,LL,LR, R,RL,RR
    private AVLNode balance(AVLNode nodeTemp, int balance, String recentInsertion) throws BalanceErrorException {
        //left
        if (balance == L_Imbalance) { 
            AVLNode leftChild = nodeTemp.left;
            AVLNode rightChildOfLeftChild = leftChild.right;
            AVLNode leftChildOfLeftChild = leftChild.left;

            int llHeight = getHeight(leftChildOfLeftChild);
            int lrHeight = getHeight(rightChildOfLeftChild);
            //left left
            if (llHeight == Math.max(llHeight, lrHeight)) {
                nodeTemp.left = rightChildOfLeftChild;
                leftChild.right = nodeTemp;
                leftChild.height = getCurrentHeight(leftChild);
                System.out.println("Imbalance occurred at ISBN "+ nodeTemp.key + " while inserting ISBN " + recentInsertion + "; fixed in LeftLeft Rotation");
                return leftChild;
            } 
            //left right
            else {
                leftChild.right = rightChildOfLeftChild.left;
                rightChildOfLeftChild.left = leftChild;
                nodeTemp.left = rightChildOfLeftChild.right;
                rightChildOfLeftChild.right = nodeTemp;
                rightChildOfLeftChild.height = getCurrentHeight(rightChildOfLeftChild);
                System.out.println("Imbalance occurred at ISBN "+ nodeTemp.key + " while inserting ISBN " + recentInsertion + "; fixed in LeftRight Rotation");
                return rightChildOfLeftChild;
            }
        //right
        } else if (balance == R_Imbalance) {
            AVLNode rightChild = nodeTemp.right;
            AVLNode rightChildOfRightChild = rightChild.right;
            AVLNode leftChildOfRightChild = rightChild.left;
            int rlHeight = getHeight(leftChildOfRightChild);
            int rrHeight = getHeight(rightChildOfRightChild);
           //right left
            if (rlHeight == Math.max(rlHeight, rrHeight)) {
                rightChild.left = leftChildOfRightChild.right;
                leftChildOfRightChild.right = rightChild;
                nodeTemp.right = leftChildOfRightChild.left;
                leftChildOfRightChild.left = nodeTemp;
                leftChildOfRightChild.height = getCurrentHeight(leftChildOfRightChild);
                System.out.println("Imbalance occurred at ISBN "+ nodeTemp.key + " while inserting ISBN " + recentInsertion + "; fixed in RightLeft Rotation");
                return leftChildOfRightChild;
            } 
            //right right
            else {
                nodeTemp.right = leftChildOfRightChild;
                rightChild.left = nodeTemp;
                rightChild.height = getCurrentHeight(rightChild);
                System.out.println("Imbalance at ISBN "+ nodeTemp.key + " while inserting ISBN " + recentInsertion + "; fixed in RightRight Rotation");
                return rightChild;
            }

        } else {
            throw new BalanceErrorException("Invalid Method " + balance);
        }
    }
}
