package project2;

//scanner object, input
import java.util.Scanner;
//import this to file or directory path
import java.io.File;
//Exception when file can't be found
import java.io.FileNotFoundException;
// rnadom
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Print out Part 1
        System.out.println("PART-1");
        //AVLTree to modify
        AVLTree avl = new AVLTree();
        try {
            //scan the file name "example.txt" from the file
            Scanner sc = new Scanner(new File("example.txt"));
            //create a Book object
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.trim().equals("")) {
                    //Create object (save the data)
                    String ISBN = line.substring(0, line.indexOf(" "));
                    line = line.substring(line.indexOf(" ") + 1);
                    String title = line.substring(0, line.indexOf(" "));
                    line = line.substring(line.indexOf(" ") + 1);
                    String author = line.trim();
                    //insert the AVLNode with ISBN number as the key
                    //output all results
                    avl.insert(ISBN, new Book(title, author));
                }
            }
            // Uncomment the line to see nodes of the tree
            // avl.debug_printAllNodes(avl.root, 0);
        }//when filed no found, print out not found 
        catch (FileNotFoundException e) {
            System.out.println("File can not be found");
        }
        //-----------------------------------------------------------------------------
        // Part 2
        //I did not copy from part1, but I make random numbs
        //-----------to separate part1 and part2
        System.out.println("------------------------------------------------------");
        //printoutPart2
        System.out.println("PART 2");
        //BinaryTree function
        BinaryTree binaryTree = new BinaryTree();
        //at least 25 random keys
        int numNodes = 50;
        //random
        Random rand = new Random();
        for (int i = 0; i < numNodes; i++) {
            int randKeys = rand.nextInt(10000);
            //insert into BinaryTree function
            binaryTree.insert(Integer.toString(randKeys), new Book("foo", "bar"));
        }
        //printout all results
        System.out.println("Number of AVL balance violations: " + binaryTree.avlBalanceVerify(binaryTree.root));
        System.out.println("Number of BST order violations: " + binaryTree.bstOrderVerify(binaryTree.root));

    }
}
