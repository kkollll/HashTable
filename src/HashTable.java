import java.util.TreeMap;

public class HashTable<K, V> {

    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private int capacityIndex = 0;
    private TreeMap<K, V>[] hashTable;
    private int M;
    private int size;

    public HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashTable = new TreeMap[M];
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

            if (size >= upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }

    }

    private void resize(int newM) {
        TreeMap<K, V>[] treeMap = new TreeMap[newM];
        System.arraycopy(hashTable, 0, treeMap, 0, M - 1);
        M = newM;
        hashTable = treeMap;
    }

    public V remove(K key) {
        TreeMap<K, V> treeMap = hashTable[hash(key)];
        V ret = null;
        if (treeMap.containsKey(key)) {
            ret = treeMap.remove(key);
            size--;
            if (size >= upperTol * M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
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
