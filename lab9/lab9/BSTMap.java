package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>, Iterable<K> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        } else if (p.key.equals(key)) {
            return p.value;
        } else {
            if (key.compareTo(p.key) > 0) {
                return getHelper(key, p.right);
            } else {
                return getHelper(key, p.left);
            }
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        } else if (key.compareTo(p.key) > 0) {
            p.right = putHelper(key, value, p.right);
        } else if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.value = value;
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        return keySetHelper(keys, root);
    }

    private Set<K> keySetHelper(Set<K> keys, Node rootNode) {
        if (rootNode == null) {
            return null;
        } else {
            keys.add(rootNode.key);
            keySetHelper(keys, rootNode.left);
            keySetHelper(keys, rootNode.right);
            return keys;
        }
    }

    /**
     * Return the node whose Key is key in the subtree rooted in P.
     */
    private Node getNode(K key, Node rootNode) {
        if (rootNode == null) {
            return null;
        } else if (key.compareTo(rootNode.key) > 0) {
            return getNode(key, rootNode.right);
        } else if (key.compareTo(rootNode.key) < 0) {
            return getNode(key, rootNode.left);
        } else {
            return rootNode;
        }
    }

    private Node findMin(Node rootNode) {
        if (rootNode == null) {
            return null;
        } else {
            Node left = findMin(rootNode.left);
            Node right = findMin(rootNode.right);
            if (left == null && right == null) {
                return rootNode;
            } else if (left == null) {
                return right.key.compareTo(rootNode.key) < 0 ? right : rootNode;
            } else if (right == null) {
                return left.key.compareTo(rootNode.key) < 0 ? left : rootNode;
            } else {
                Node rst = left.key.compareTo(right.key) < 0 ? left : right;
                return rst.key.compareTo(rootNode.key) < 0 ? rst : rootNode;
            }
        }
    }

    private void deleteNode(Node target) {
        if (target.right == null && target.left == null) {
            target = null;
        } else if (target.right == null) {
            Node reminderOfOriginal = target.left;
            target.key = reminderOfOriginal.key;
            target.value = reminderOfOriginal.value;
            target.left = reminderOfOriginal.left;
            target.right = reminderOfOriginal.right;
            reminderOfOriginal = null;
        } else if (target.left == null) {
            Node reminderOfOriginal = target.right;
            target.key = reminderOfOriginal.key;
            target.value = reminderOfOriginal.value;
            target.left = reminderOfOriginal.left;
            target.right = reminderOfOriginal.right;
            reminderOfOriginal = null;
        } else {
            Node min = findMin(target.right);
            target.key = min.key;
            target.value = min.value;
            if (min.right != null) {
                Node reminderOfOriginal = min.right;
                min.key = reminderOfOriginal.key;
                min.value = reminderOfOriginal.value;
                min.left = reminderOfOriginal.left;
                min.right = reminderOfOriginal.right;
                reminderOfOriginal = null;
            } else min = null;
        }
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        Node target = getNode(key, root);
        if (target == null) {
            return null;
        }
        V value = target.value;
        deleteNode(target);
        return value;
    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        Node target = getNode(key, root);
        if (target == null || target.value != value) {
            return null;
        }
        deleteNode(target);
        return value;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
