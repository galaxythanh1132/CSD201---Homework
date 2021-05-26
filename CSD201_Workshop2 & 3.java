/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd201_workshop2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author triettmce150047 <your.name at your.org>
 */
public class CSD201_Workshop2
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
     do {
            System.out.println("================= Flower Manager program =================");
            System.out.print("|1. Add and update flower's price                        |\n"
                    + "|2. Find flower by name                                  |\n"
                    + "|3. Find flower by price (input min/max price)           |\n"
                    + "|4. Delete a flower                                      |\n"
                    + "|5. Exit                                                 |\n"
                    + "Your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    n = false;
                    break;
                default:
                    System.out.println("Please try again!");
                    break;
            }

        } while (n);   
    }

}



public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }
//--------------Triet--------------------------//
    //Method for inserting flower
    public void insert(Flower key) {
        root = insertNode(root, key);
    }

    //Method for inserting node
    Node insertNode(Node root, Flower key) {
        //If this node is the previous node's null then add node 
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key.getID() < root.getKey().getID()) { //Traversing to the left
            root.setLeftChild(insertNode(root.getLeftChild(), key));
        } else if (key.getID() > root.getKey().getID()) { //Traversing to the right
            root.setRightChild(insertNode(root.getRightChild(), key));
        }
        return root;
    }
//------------Completed----------------------//
   
//--------------Triet------------------------//
    //Method for searching flower by name
   void searchFlower() {
        Scanner scanner = new Scanner(System.in);
        String name;
        System.out.print("\nPlease enter the name of your wanted flower: ");
        name = scanner.nextLine();
        System.out.println("ID---Name of flower-----Price-----Amount");
        search_Recursive(root, name);
    }

    void search_Recursive(Node node, String name) {
        if (node == null) {
            return;
        }
        if (node.getKey().getName().contains(name))
        {
            System.out.println(node.getKey().toString());
        }
        search_Recursive(node.getLeftChild(), name); //Then left
        search_Recursive(node.getRightChild(), name);
    }
//----------------Completed-----------------//
    
    //Method for deletting
    void deleteRecursion(Node node) {
        if(isLeaf(node))
        {
            deleteLeaf(node);
        } else
        {
            Node m = node.getLeftChild();
            if (m != null)
            {
                m = rightMostChild(m);
            }
            else if (m == null)
            {
               m = leftMostChild(node.getRightChild());
            }
            int v = m.getKey();
            deleteRecursion(m);
            node.setKey(v);
        }
    }

    //Find height of tree
    public int findHeight() {
        return heightRecursion(root);
    }

    //*Recursion* for finding height of tree
    public int heightRecursion(Node node) {
        //This if statement is when you go to the final leaf node will return 0
        //Or if the tree is empty
        if (node == null) {
            return 0;
        }

        int left_height = heightRecursion(node.getLeftChild()); //Recursion for going to the furthest left leaf
        int right_height = heightRecursion(node.getRightChild());//__________________________________ right leaf

        if (left_height > right_height) {
            return left_height + 1; //Calculating height
        } else {
            return right_height + 1; //Calculating height
        }
    }

    //Dung cay
    public void dungCay(ArrayList<Node> list) {
        dungCayRecursion(0, list.size() - 1, list);

    }

    public void dungCayRecursion(int leftIndex, int rightIndex, ArrayList<Node> list) {
        int mid;
        mid = (leftIndex + rightIndex) / 2;
        if (leftIndex <= rightIndex) {
            insert(list.get(mid).getKey());
            dungCayRecursion(leftIndex, mid - 1, list);
            dungCayRecursion(mid + 1, rightIndex, list);
        }
    }

    //Prefix traversal for tree
    public void prefixTravesal() {
        prefixRecursion(root);
    }

    //*Recursion* for prefix order
    public void prefixRecursion(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getKey() + " "); //Root first
        prefixRecursion(node.getLeftChild()); //Then left
        prefixRecursion(node.getRightChild()); //Then right
    }

    //Infix traversal for tree
    public void infixTravesal(/*ArrayList<Node> list*/) {
        infixRecursion(root/*, list*/);
    }

    //*Recursion* for Infix order
    void infixRecursion(Node node/*, ArrayList<Node> list*/) {
        if (node == null) {
            return;
        }
        infixRecursion(node.getLeftChild()/*, list*/);    //Left first
        System.out.print(node.getKey() + " ");
        //list.add(node);
        infixRecursion(node.getRightChild()/*, list*/); //Then right
    }

    //Postfix traversal for tree
    public void postfixTravesal() {
        postfixRecursion(root);
    }

    void postfixRecursion(Node node) {
        if (node == null) {
            return;
        }
        postfixRecursion(node.getLeftChild()); //Left first
        postfixRecursion(node.getRightChild());//then right
        System.out.print(node.getKey() + " ");//then root
    }
}

public class Node
{

    private Flower key;
    private Node leftChild;
    private Node rightChild;

    public Node(Flower key)
    {
        this.key = key;
    }

    public Node(Flower key, Node leftChild, Node rightChild)
    {
        this.key = key;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Flower getKey()
    {
        return key;
    }

    public void setKey(Flower key)
    {
        this.key = key;
    }

    public Node getLeftChild()
    {
        return leftChild;
    }

    public void setLeftChild(Node leftChild)
    {
        this.leftChild = leftChild;
    }

    public Node getRightChild()
    {
        return rightChild;
    }

    public void setRightChild(Node rightChild)
    {
        this.rightChild = rightChild;
    }

}
