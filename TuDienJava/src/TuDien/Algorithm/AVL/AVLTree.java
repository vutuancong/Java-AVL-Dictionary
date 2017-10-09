/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuDien.Algorithm.AVL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author vutuancong
 */
class Node {

    String key;
    int height;
    Node left, right;

    Node(String d) {
        key = d;
        height = 1;
    }
}

public class AVLTree {

    public Node root;

    int height(Node N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(Node N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }

    Node insert(Node node, String key) {
        if (node == null) {
            return (new Node(key));
        }

        if (key.compareToIgnoreCase(node.key) < 0) {
            node.left = insert(node.left, key);
        } else if (key.compareToIgnoreCase(node.key) > 0) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }
        node.height = 1 + max(height(node.left),
                height(node.right));

        int balance = getBalance(node);
        if (balance > 1 && key.compareToIgnoreCase(node.left.key) < 0) {
            return rightRotate(node);
        }
        if (balance < -1 && key.compareToIgnoreCase(node.right.key) > 0) {
            return leftRotate(node);
        }
        if (balance > 1 && key.compareToIgnoreCase(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key.compareToIgnoreCase(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void inOrder(Node node,String tu,DefaultListModel model) {
        if (node != null ) {
            inOrder(node.left,tu,model);
            if(node.key.startsWith(tu)) model.addElement(node.key);
            inOrder(node.right,tu,model);
        }
    }

    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    Node deleteNode(Node node, String key) {
        if (node == null) {
            return node;
        }
        if (key.compareToIgnoreCase(node.key) < 0) {
            node.left = deleteNode(node.left, key);
        } else if (key.compareToIgnoreCase(node.key) > 0) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                Node temp = null;
                if (node.left != null) {
                    temp = node.left;
                }
                if (node.left == null) {
                    temp = node.right;
                }
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node temp = minValueNode(node.right);
                node.key = temp.key;
                node.right = deleteNode(node.right, temp.key);
            }
        }
        if (node == null) {
            return node;
        }
        node.height = max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public Node search(Node node, String value) {
        if (node == null || node.key.compareToIgnoreCase(value) == 0) {
            return node;
        }
        else{
            if(value.compareToIgnoreCase(node.key)<0) return search(node.left,value);
            else
                return search(node.right,value);
        }
    }

    public void function(AVLTree tree,int choice, String data) {
        Node temp;
        System.out.println("Cac thao tac tren cay AVL");
        System.out.println("1.Them node vao cay");
        System.out.println("2.Loai node tren cay");
        System.out.println("3.Tim node tren cay");
        System.out.println("4.Duyet theo thu tu truoc");
        System.out.println("5.Duyet theo thu tu giua");
        System.out.println("6.Duyet theo thu tu sau");
        System.out.println("0.Thoat khoi chuong trinh");
        switch (choice) {
            case 1:
                System.out.println("Gia tri node can them:");
                tree.root = tree.insert(tree.root, data);
                break;
            case 2:
                System.out.println("Gia tri node can loai:");
                tree.root = tree.deleteNode(tree.root, data);
                break;
            case 3:
                System.out.println("Tim node tren cay:");
                temp = tree.search(tree.root, data);
                if (temp == null) {
                    System.out.println(data + " khong co tren cay");
                } else {
                    System.out.println(data + " co tren cay");
                }
                tree.search(tree.root, data);
                break;
            case 4:
                System.out.println("Hien thi theo truoc:");
                tree.preOrder(tree.root);
                break;
            case 5:
//                System.out.println("Hien thi theo giua: ");
//                tree.inOrder(tree.root,"",model);
//                break;
            case 6:
//                System.out.println("Hien thi theo sau: ");
//                tree.postOrder(tree.root," ");
                break;
            case 0:
                System.exit(0);

        }
    }

    public void loadData(AVLTree tree) {
        try {
            Scanner input = new Scanner(new File("tudien.txt"));
            while(input.hasNextLine()){
//                System.out.println(input.nextLine());
                tree.function(tree,1,input.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
//        int dem = 0;
//        try {
//            String input = "loaddata.txt";
//            FileInputStream fis = new FileInputStream(new File(input));
//            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                line = line.trim();
//                if (line != null && !line.isEmpty()) {
////                    System.out.println(line);
//                    tree.function(tree,1,line);
//                }
//                dem++;
//            }
//            br.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(dem);
//        tree.function(tree, 5, "");
    }
}
