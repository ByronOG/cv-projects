package a2_compression;

import util.BinaryStdIn;
import util.BinaryStdOut;
import util.*;

import java.util.PriorityQueue;


/**
 * the implementation of my methods is influenced by Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
 * <p>
 * I make use of a PriorityQueue in my implementation. The two smallest nodes are continuously removed and added together
 * to create parent nodes (internal nodes) which are added to the trie. This process ends once the PQ is size 1.
 * <p>
 * The helper methods are used to write and read the trie.
 * another helper method builds the character-frequency table
 * <p>
 * This class is run via cmd.
 * Begin by first outputting the number of bits in the binary file ‘q32x48.bin’
 * Use the command:
 * java BinaryDump 40 < q32x48.bin
 * Now let’s try to compress this file with Huffman Encoding and see what we get (we’ll combine
 * Huffman with BinaryDump to see how much compression we achieve)
 * Use the command:
 * java Huffman - < q32x48.bin | java BinaryDump
 * Note down the bits.
 * Calculate the compression ratio: uncompressed bits / compressed bits
 * Next we’ll output this compressed file to a new binary file and check we have the same
 * compression ratio.
 * Use the command:
 * java Huffman - < q32x48.bin > q32x48_comp.bin
 * Use Binary Dump to check the bits: you can create this command yourself now
 */
public class Huffman {
    // alphabet size of extended ASCII
    private static final int R = 256;

    // Huffman trie node
    private record Node(char ch, int freq, Node left, Node right) implements Comparable<Node> {

        // is the node a leaf node?
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return left == null;
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    /**
     * Reads a sequence of 8-bit bytes from standard input; compresses them
     * using Huffman codes with an 8-bit alphabet; and writes the results
     * to standard output.
     */
    public static void compress() {
        // read input
        String str = BinaryStdIn.readString();
        char[] input = str.toCharArray();

        // count frequencies
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++)
            freq[input[i]]++;

        // build trie
        Node root = buildTrie(freq);

        // build code table
        String[] s = new String[R];
        buildCode(s, root, "");

        // write trie for decoder
        writeTrie(root);

        // print number of bytes in original uncompressed message
        BinaryStdOut.write(input.length);

        // encode input
        for (int i = 0; i < input.length; i++) {
            String code = s[input[i]];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    BinaryStdOut.write(false);
                } else if (code.charAt(j) == '1') {
                    BinaryStdOut.write(true);
                } else {
                    throw new IllegalStateException("Illegal state");
                }
            }
        }

        // close output stream
        BinaryStdOut.close();
    }

    // build the trie
    private static Node buildTrie(int[] freq) {

        // initialise priority queue
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for (char c = 0; c < R; c++) {

            //disregard frequencies of 0
            if (freq[c] > 0)
                pq.add(new Node(c, freq[c], null, null));
        }

        //edge case:
        if (pq.size() == 1) {
            if (freq['\0'] == 0) {
                pq.add(new Node('\0', 0, null, null));
            } else {
                pq.add(new Node('\1', 0, null, null));
            }
        }

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left = pq.remove();
            Node right = pq.remove();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.add(parent);
        }

        return pq.remove();
    }

    // write trie to standard output
    private static void writeTrie(Node node) {
        if (node.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(node.ch, 8);
            return;
        }
        BinaryStdOut.write(false);
        writeTrie(node.left);
        writeTrie(node.right);
    }

    // make a lookup table from symbols and their encodings
    private static void buildCode(String[] s, Node node, String str) {
        if (!node.isLeaf()) {
            buildCode(s, node.left, str + '0');
            buildCode(s, node.right, str + '1');
        } else {
            s[node.ch] = str;
        }
    }

    private static Node readTrie() {
        boolean isLeaf = BinaryStdIn.readBoolean();
        if (isLeaf) {
            return new Node(BinaryStdIn.readChar(), -1, null, null);
        } else {
            return new Node('\0', -1, readTrie(), readTrie());
        }
    }

    /**
     * Reads a sequence of bits that represents a Huffman-compressed message from
     * standard input; expands them; and writes the results to standard output.
     */
    public static void expand() {
        // read in trie from input stream
        Node root = readTrie();

        // number of bytes to write
        int length = BinaryStdIn.readInt();

        // decode using the trie
        for (int i = 0; i < length; i++) {
            Node node = root;
            while (!node.isLeaf()) {
                boolean bit = BinaryStdIn.readBoolean();
                if (bit) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
            BinaryStdOut.write(node.ch, 8);
        }
        BinaryStdOut.close();
    }


    public static void main(String[] args) {
        if (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
