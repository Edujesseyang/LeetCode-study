package src.HashMap;

public class DesignHashMap_706 {
    /*
    Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.


Example 1:

Input
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
Output
[null, null, null, 1, -1, null, 1, null, -1]

Explanation
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // The map is now [[1,1]]
myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]


Constraints:

0 <= key, value <= 106
At most 104 calls will be made to put, get, and remove.
    */

    private static class MyHashMap<K, V> {
        private static class Pair<K, V> {
            private K key;
            private V val;

            private Pair(K key, V val) {
                this.key = key;
                this.val = val;
            }

        }

        private final double LOAD_FACTOR = 0.6;
        private Pair<K, V>[] baseArr;
        private final Class<K> clazzK;
        private final Class<V> clazzV;
        private int size;
        private int pairCount = 0;

        @SuppressWarnings("unchecked")
        private MyHashMap(Class<K> clazzK, Class<V> clazzV) {
            this.size = 37;
            baseArr = (Pair<K, V>[]) java.lang.reflect.Array.newInstance(Pair.class, size);
            this.clazzK = clazzK;
            this.clazzV = clazzV;

        }

        private void put(K key, V value) {
            if (key == null) {
                throw new IllegalArgumentException("Invalid key");
            }
            double loadingStatus = (double) pairCount / size;
            if (loadingStatus >= LOAD_FACTOR) {
                rehashing();
            }

            int index = Math.abs(key.hashCode() % size);

            while (index < size && baseArr[index] != null) {
                if (baseArr[index].key.equals(key)) {
                    baseArr[index].val = value;
                    return;
                }
                index = (index + 1) % size; // 线性探测的核心机制, indexing 回绕
            }

            baseArr[index] = new Pair<>(key, value);
            pairCount++;
        }

        private V get(K key) {
            if (key == null) {
                throw new IllegalArgumentException("Invalid key");
            }
            int index = Math.abs(key.hashCode() % size);
            while (baseArr[index] != null) {
                if (baseArr[index].key.equals(key)) {
                    return baseArr[index].val;
                }
                index = (index + 1) % size;

            }
            return null;
        }

        private boolean remove(K key) {
            if (key == null) {
                throw new IllegalArgumentException("Invalid key");
            }
            int index = Math.abs(key.hashCode() % size);
            // find the key
            while (baseArr[index] != null) {
                if (!baseArr[index].key.equals(key)) {
                    index = (index + 1) % size;
                } else {
                    break;
                }
            }
            if (baseArr[index] == null) return false; // nothing found, return

            // deleting
            baseArr[index] = null;
            pairCount--;

            // rehashing the following
            while (baseArr[index] != null) {
                Pair<K, V> temp = baseArr[index]; // need a temp, because we need to free the spot before re-add, it may put back to same spot, or linear probing
                baseArr[index] = null;
                pairCount--;
                put(temp.key, temp.val);
                index = (index + 1) % size;
            }
            return true;
        }


        @SuppressWarnings("unchecked")
        private void rehashing() {
            Pair<K, V>[] newArr = (Pair<K, V>[]) java.lang.reflect.Array.newInstance(Pair.class);
            for (Pair<K, V> p : baseArr) {
                int index = Math.abs(p.key.hashCode() % findNextPrime(size * 2));
                Pair<K, V> newPair = new Pair<>(p.key, p.val);
                if (baseArr[index] != null) {
                    while (baseArr[index] != null) {
                        index++;
                    }
                }
                newArr[index] = newPair;
            }
            this.baseArr = newArr;
        }

        private int findNextPrime(int size) {
            while (!isPrime(size)) {
                size++;
            }
            return size;
        }

        private boolean isPrime(int n) {
            if (n < 3) return n > 0;
            if (n % 2 == 0 || n % 3 == 0) {
                return false;
            }

            for (int i = 5; i * i <= n; i += 6) { // we want to check 6k+1 and 6k-1, from 5 to sqrt(n)
                if (n % i == 0 || n % (i + 2) == 0) { // let i = 6k - 1,  i+2 = 6k + 1
                    return false;
                }
            }
            return true;
        }

    }

}
