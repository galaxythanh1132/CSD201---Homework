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
        //Variables
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        BinarySearchTree tree = new BinarySearchTree();
        ArrayList<Node> t2 = new ArrayList<>();
        int n = 10;
        int search_value;
        int[] arr = new int[n];
        //Random-ing element for array
        System.out.print("Array created with random elements: ");
        for (int i = 0; i < n; i++)
        {
            arr[i] = rand.nextInt(100);
            System.out.print(" "+arr[i]);
        }
        Insert array into tree
        for (int i : arr)
        {
            tree.insert(i);
        }
        
        System.out.print("\nPrefix traversal of tree: ");
        tree.prefixTravesal();
        System.out.print("\nInfix traversal of tree: ");
        tree.infixTravesal();
        System.out.print("\nPostfix traversal of tree: ");
        tree.postfixTravesal();
        System.out.print("\nHeight of tree is: " + tree.findHeight());
//        System.out.print("\nArray with infix traversal order");
//        for(Node a: t2)
//        {
//            System.out.print(" "+a.getKey());
//        }
//        
//        BinarySearchTree tree2 = new BinarySearchTree();
//        tree2.dungCay(t2);
//        System.out.print("\nHeight of tree is: " + tree2.findHeight());
        System.out.println("\nEnter the value you want to delete from tree: ");
        search_value = scanner.nextInt();
        tree.delete(search_value);
        System.out.print("\nInfix traversal of tree: ");
        tree.infixTravesal();
        System.out.println("");
    }

}



public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int key) {
        root = insertNode(root, key);
    }

    //Method for inserting node
    Node insertNode(Node root, int key) {
        //If this node is the previous node's null then add node 
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.getKey()) { //Traversing to the left
            root.setLeftChild(insertNode(root.getLeftChild(), key));
        } else if (key > root.getKey()) { //Traversing to the right
            root.setRightChild(insertNode(root.getRightChild(), key));
        }
        return root;
    }

    boolean isLeaf(Node node)
    {
        if(node.getLeftChild()==null && node.getRightChild()==null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    Node rightMostChild(Node node)
    {
        while(node.getRightChild() != null)
        {
            node = node.getRightChild();
        }
        return node;
    }
    
    Node leftMostChild(Node node)
    {
        while(node.getLeftChild() != null)
        {
            node = node.getLeftChild();
        }
        return node;
    }
    
    void deleteLeaf(Node leaf)
    {
        Node node = root;
        Node parent = null;
        int key = leaf.getKey();
        while(node.getKey() != key)
        {
            parent = node;
            if(node.getKey() > key)
            {
                node = node.getLeftChild();
            } else
            {
                node = node.getRightChild();
            }
        }
        if (parent.getLeftChild() != null)
        {
            if (parent.getLeftChild().getKey() == key)
            {
                parent.setLeftChild(null);
            }
        }
        if (parent.getRightChild() != null)
        {
            if (parent.getRightChild().getKey() == key)
            {
                parent.setRightChild(null);
            }
        }
    }
    //Search
    Node search(int key)  { 
        return search_Recursive(root, key);
    }
    
    Node search_Recursive(Node node, int key)  { 
        // Base Cases: root is null or key is present at root 
        if (node==null || node.getKey()==key) 
            return node; 
        // val is greater than root's key 
        if (node.getKey() > key) 
            return search_Recursive(node.getLeftChild(), key); 
        // val is less than root's key 
        return search_Recursive(node.getRightChild(), key); 
    } 
    void delete(int key){
        Node n = search(key);
        if(n != null)
        {
            deleteRecursion(n);
        }
        
    }

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

    private int key;
    private Node leftChild;
    private Node rightChild;

    public Node(int key)
    {
        this.key = key;
    }

    public Node(int key, Node leftChild, Node rightChild)
    {
        this.key = key;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public int getKey()
    {
        return key;
    }

    public void setKey(int key)
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
