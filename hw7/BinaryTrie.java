import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class BinaryTrie implements Serializable {
    private Node binaryTrie;

    private class Node implements Comparable<Node>, Serializable {
        private Character ch;
        private int freq;
        private Node left;
        private Node right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        this.binaryTrie = buildTrie(frequencyTable);
    }

    public Match longestPrefixMatch(BitSequence querySequence) {
        int position = 0;
        Node ptr = binaryTrie;
        while (!ptr.isLeaf()) {
            if (querySequence.bitAt(position) == 0) {
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
            position += 1;
        }
        return new Match(querySequence.firstNBits(position), ptr.ch);
    }

    public Map<Character, BitSequence> buildLookupTable() {
        Map<Character, BitSequence> map = new HashMap<>();
        BitSequence bitSequence = new BitSequence();
        traverse(bitSequence, binaryTrie, map);
        return map;
    }

    private Node buildTrie(Map<Character, Integer> frequencyTable) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue(), null, null));
        }
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.add(new Node('\0', left.freq + right.freq, left, right));
        }
        return pq.poll();
    }

    private void traverse(BitSequence bitSequence, Node root, Map<Character, BitSequence> map) {
        if (root.isLeaf()) {
            map.put(root.ch, bitSequence);
            return;
        }
        traverse(bitSequence.appended(0), root.left, map);
        traverse(bitSequence.appended(1), root.right, map);
    }
}
