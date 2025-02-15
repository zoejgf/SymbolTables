package edu.greenriver.sdev333;

/**
 * Binary search (in an ordered array implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BinarySearchST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
    private KeyType[] keys;
    private ValueType[] vals;
    private int n;

    public BinarySearchST(int capacity) {
        keys = (KeyType[]) new Comparable[capacity];
        vals = (ValueType[]) new Object[capacity];
    }

    @Override
    public void put(KeyType key, ValueType value) {
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0)
        { vals[i] = value; return; }

        for (int j = n; j > i ; j--) {
            keys[j] = key;
            vals[i] = value;
        }
        keys[i] = key;
        vals[i] = value;
        n++;
    }

    @Override
    public ValueType get(KeyType key) {
        if(isEmpty()) {
            return null;
        }
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public KeyType min() {
        return keys[0];
    }

    @Override
    public KeyType max() {
        return keys[n-1];
    }

    @Override
    public KeyType floor(KeyType key) {
        return null;
    }

    @Override
    public KeyType ceiling(KeyType key) {
        int i = rank(key);
        return keys[i];
    }

    @Override
    public int rank(KeyType key) {
        int lo = 0, hi = n-1;
        while(lo <= hi) {
            int mid = lo + (hi-lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0) {
                hi = mid-1;
            } else if(cmp > 0) {
                lo = mid+1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    @Override
    public KeyType select(int k) {
        return null;
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> queue = new Queue<>();

        for (int i = 0; i < n; i++) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }
}
