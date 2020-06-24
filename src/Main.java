import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        String fileName = "pride-and-prejudice.txt";
        System.out.println(fileName);

        ArrayList<String> words = new ArrayList<>();


        if (FileOperation.readFile(fileName, words)) {


            Collections.sort(words);

            // Test BST
            long t1 = System.nanoTime();
            BST<String, Integer> bst = new BST<>();
            words.forEach(
                    (e) -> {
                        if (bst.contains(e)) {
                            bst.set(e, bst.get(e) + 1);
                        } else {
                            bst.add(e, 1);
                        }
                    }
            );
            words.forEach(bst::contains);
            long t2 = System.nanoTime();
            double t = (t2 - t1) / 1000000000.0;
            System.out.println("BST: " + t + " s.");

            // Test AVLTree
            t1 = System.nanoTime();
            AVLTree<String, Integer> avlTree = new AVLTree<>();
            words.forEach(
                    (e) -> {
                        if (avlTree.contains(e)) {
                            avlTree.set(e, avlTree.get(e) + 1);
                        } else {
                            avlTree.add(e, 1);
                        }
                    }
            );
            words.forEach(avlTree::contains);
            t2 = System.nanoTime();
            t = (t2 - t1) / 1000000000.0;
            System.out.println("AVLTree: " + t + " s.");

            // Test rbTree
            t1 = System.nanoTime();
            RBTree<String, Integer> rbTree = new RBTree<>();
            words.forEach(
                    (e) -> {
                        if (rbTree.contains(e)) {
                            rbTree.set(e, rbTree.get(e) + 1);
                        } else {
                            rbTree.add(e, 1);
                        }
                    }
            );
            words.forEach(rbTree::contains);
            t2 = System.nanoTime();
            t = (t2 - t1) / 1000000000.0;
            System.out.println("RBTree: " + t + " s.");

            // Test hashTable
            t1 = System.nanoTime();
            HashTable<String, Integer> hashTable = new HashTable<>();
            words.forEach(
                    (e) -> {
                        if (hashTable.contains(e)) {
                            hashTable.set(e, hashTable.get(e) + 1);
                        } else {
                            hashTable.add(e, 1);
                        }
                    }
            );
            words.forEach(hashTable::contains);
            t2 = System.nanoTime();
            t = (t2 - t1) / 1000000000.0;
            System.out.println("hashTable: " + t + " s.");
        }
    }
}
