import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ElementDex {
    // ELEMENT CLASS DECLARATION
    public static class Element {
        int atomicNumber;
        String symbol;
        String name;

        public Element(int anAtomicNumber, String aSymbol, String aName) {
            this.atomicNumber = anAtomicNumber;
            this.symbol = aSymbol;
            this.name = aName;
        }
    }

    // NODE DECLARATION
    private static class Node {
        Element element;
        Node left;
        Node right;

        public Node(Element element) {
            this.element = element;
            this.left = null;
            this.right = null;
        }
    }

    // BST DECLARATION FOR NUMBERS
    public static class BinarySearchTreeByNum {
        private Node root;

        public BinarySearchTreeByNum() {
            root = null;
        }

        // public adder
        public void add(Element element) {
            root = addRecursive(root, element);
        }

        // private adder
        private Node addRecursive(Node current, Element element) {
            if (current == null) {
                return new Node(element);
            }

            if (element.atomicNumber < current.element.atomicNumber) {
                current.left = addRecursive(current.left, element);
            } else if (element.atomicNumber > current.element.atomicNumber) {
                current.right = addRecursive(current.right, element);
            }

            return current;
        }

        // public NUMBER searcher
        public Element search(int atomicNumber) {
            // it's the number
            Node result = searchRecursive(root, atomicNumber);
            return result == null ? null : result.element;
        }

        // private searcher ATOMIC NUM
        private Node searchRecursive(Node current, int atomicNumber) {

            // System.out.println("I am checking your input against: " +
            // current.element.name);

            if (current == null || current.element.atomicNumber == atomicNumber) {
                // System.out.println("Found you! Were you looking for " +
                // current.element.name);
                return current;
            }

            if (atomicNumber < current.element.atomicNumber) {
                // System.out.println("bstNums " + current.element.name + " checking LEFT");
                return searchRecursive(current.left, atomicNumber);
            }

            // System.out.println("bstNums " + current.element.name + " checking
            // RIGHT");
            return searchRecursive(current.right, atomicNumber);
        }

        // PRINTING TREE TO TEST
        public void printTree(Node node) {
            if (node == null) {
                return;
            }
            printTree(node.left);
            System.out.println(node.element.name);
            printTree(node.right);
        }

        int totalNodes(Node root) {
            if (root == null)
                return 0;

            int leftCount = totalNodes(root.left);
            int rightCount = totalNodes(root.right);

            return 1 + leftCount + rightCount;
        }
    }

    // BST FOR NAMES
    public static class BinarySearchTreeByName {
        private Node root;

        public BinarySearchTreeByName() {
            root = null;
        }

        // public adder
        public void add(Element element) {
            root = addRecursive(root, element);
        }

        // private adder
        private Node addRecursive(Node current, Element element) {
            if (current == null) {
                return new Node(element);
            }

            if (element.atomicNumber < current.element.atomicNumber) {
                current.left = addRecursive(current.left, element);
            } else if (element.atomicNumber > current.element.atomicNumber) {
                current.right = addRecursive(current.right, element);
            }

            return current;
        }

        // Public name searcher
        public Element search(String symbolOrName) {

            Node result = searchRecursive(root, symbolOrName);

            return result == null ? null : result.element;
        }

        // Private name searcher
        private Node searchRecursive(Node current, String name) {

            if (current == null) {
                return current;
            }

            String currName = current.element.name;

            if (name.compareToIgnoreCase(currName) == 0) {
                return current;
            }

            if (name.compareToIgnoreCase(currName) < 0) {
                return searchRecursive(current.left, name);
            }
            return searchRecursive(current.right, name);
        }

        public void printTree(Node node) {
            if (node == null) {
                return;
            }
            printTree(node.left);
            System.out.println(node.element.name);
            printTree(node.right);
        }

        int totalNodes(Node root) {
            if (root == null)
                return 0;

            int leftCount = totalNodes(root.left);
            int rightCount = totalNodes(root.right);

            return 1 + leftCount + rightCount;
        }
    }

    // BST FOR SYMB
    public static class BinarySearchTreeBySymb {
        private Node root;

        public BinarySearchTreeBySymb() {
            root = null;
        }

        // public adder
        public void add(Element element) {
            root = addRecursive(root, element);
        }

        // private adder
        private Node addRecursive(Node current, Element element) {
            if (current == null) {
                return new Node(element);
            }

            String currSymb = current.element.symbol;

            if (element.symbol.compareToIgnoreCase(currSymb) < 0) {
                current.left = addRecursive(current.left, element);
            } else if (element.symbol.compareToIgnoreCase(currSymb) > 0) {
                current.right = addRecursive(current.right, element);
            }

            return current;
        }

        // Public symbol searcher
        public Element search(String symbolOrName) {

            Node result = searchRecursive(root, symbolOrName);

            return result == null ? null : result.element;
        }

        // Private symbol searcher
        private Node searchRecursive(Node current, String symbol) {
            if (current == null) {
                return current;
            }

            String currSymbol = current.element.symbol;

            if (symbol.compareToIgnoreCase(currSymbol) == 0) {
    
                return current;
            }

            if (symbol.compareToIgnoreCase(currSymbol) < 0) {
                return searchRecursive(current.left, symbol);
            }
            return searchRecursive(current.right, symbol);
        }

        public void printTree(Node node) {
            if (node == null) {
                return;
            }
            printTree(node.left);
            System.out.println(node.element.name);
            printTree(node.right);
        }

        int totalNodes(Node root) {
            if (root == null)
                return 0;

            int leftCount = totalNodes(root.left);
            int rightCount = totalNodes(root.right);

            return 1 + leftCount + rightCount;
        }
    }

    public static void main(String[] args) {

        BinarySearchTreeByNum bstNums = new BinarySearchTreeByNum();
        BinarySearchTreeByName bstNames = new BinarySearchTreeByName();
        BinarySearchTreeBySymb bstSymb = new BinarySearchTreeBySymb();

        try {
            File file = new File("elements.txt");
            Scanner scanner = new Scanner(file);

            long startTime = System.nanoTime();
            long endTime;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\t");

                int atomicNumber = Integer.parseInt(parts[0]);
                String symbol = parts[1];
                String name = parts[2];
                Element element = new Element(atomicNumber, symbol, name);
                bstNums.add(element);
                bstNames.add(element);
                bstSymb.add(element);
            }
            endTime = System.nanoTime();

            System.out.println("TIME REQUIRED TO ADD ELEMENTS TO 3 TREES: " + (endTime - startTime) / 1000000.0 + "ms" );

            scanner.close();

            scanner = new Scanner(System.in);
            String input = "";

            // PRINT TOTAL NUMBER OF NODES
            // System.out.println(bst.totalNodes(bst.root));

            while (!input.equalsIgnoreCase("STOP")) {
                System.out.println("SEARCH FOR AN ELEMENT (BY NAME, NUMBER or SYMBOL) Type 'STOP' to end\n");

                input = scanner.nextLine();

                if (input.equalsIgnoreCase("STOP")) {
                    break;
                }

                Element searched;

                if (input.matches("^\\d+$")) {
                    searched = bstNums.search(Integer.parseInt(input));
                    if (searched != null)
                        System.out.println(searched.name);
                    else
                        System.out.println("NOT FOUND");

                } else if (input.matches("^[A-Za-z]+$")) {
                    if (input.matches("^([A-Za-z]{1,2})$")) {
                        // search by symbol
                        searched = bstSymb.search(input);
                        if (searched != null)
                            System.out.println(searched.name);
                        else
                            System.out.println("NOT FOUND");
                    } else {
                        // Search by name
                        searched = bstNames.search(input);
                        if (searched != null)
                            System.out.println(searched.name);
                        else
                            System.out.println("NOT FOUND");
                    }
                } else {
                    // invalid input
                    System.out.println("PLEASE ONLY ENTER NAME, NUMBER, OR SYMBOL OF AN ELEMENT\n");
                }
            }
            System.out.println("Goodbye!");
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}