import java.util.TreeMap;

public class HashTable<K, V> {

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private static final int initCap = 7;
    private TreeMap<K, V>[] hashTable;
    private int M;
    private int size;

    public HashTable(int M) {
        this.M = M;
        size = 0;
        hashTable = new TreeMap[M];
    }

    public HashTable() {
        this(initCap);
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {
        TreeMap<K, V> treeMap = hashTable[hash(key)];
        if (treeMap == null) {
            treeMap = new TreeMap<>();
        }
        if (treeMap.containsKey(key)) {
            treeMap.put(key, value);
        } else {
            treeMap.put(key, value);
            size++;

            if (size >= upperTol * M) {
                resize(2 * M);
            }
        }

    }

    private void resize(int newM) {
        TreeMap<K, V>[] treeMap = new TreeMap[newM];
        int oldM = this.M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashTable[i];
            if (map == null) {
                map = new TreeMap<>();
            }
            for (K key : map.keySet()) {
                treeMap[hash(key)].put(key, map.get(key));
            }
        }
        hashTable = treeMap;
    }

    public V remove(K key) {
        TreeMap<K, V> treeMap = hashTable[hash(key)];
        V ret = null;
        if (treeMap.containsKey(key)) {
            ret = treeMap.remove(key);
            size--;
            if (size >= upperTol * M && M / 2 >= initCap) {
                resize(M / 2);
            }
        }
        return ret;
    }

    public void set(K key, V value) {
        TreeMap<K, V> treeMap = hashTable[hash(key)];
        if (!treeMap.containsKey(key)) {
            throw new IllegalArgumentException(key + " dosen't exist!");
        }
        treeMap.put(key, value);
    }

    public boolean contains(K key) {
        TreeMap<K, V> treeMap = hashTable[hash(key)];
        if (treeMap == null) {
            treeMap = new TreeMap<>();
        }
        return treeMap.containsKey(key);
    }

    public V get(K key) {
        TreeMap<K, V> treeMap = hashTable[hash(key)];
        return treeMap.get(key);
    }
}
