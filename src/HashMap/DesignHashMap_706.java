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

    public static void main(String[] args) {

        System.out.println("****** Testing separate linkedList approach ******");
        MyHashMap1 map = new MyHashMap1();

        map.put(1, 10);
        map.put(2, 20);
        map.put(3, 30);

        System.out.println("1: " + map.get(1)); // 10
        System.out.println("2: " + map.get(2)); // 20
        System.out.println("3: " + map.get(3)); // 30

        map.put(2, 25); // update
        System.out.println("2 updated: " + map.get(2)); // 25

        map.remove(2);
        System.out.println("2 removed: " + map.get(2)); // -1

        map.remove(99); // non-existing key
        System.out.println("99 removed: " + map.get(99)); // -1

        for (int i = 100; i < 140; i++) {
            map.put(i, i + 1000); // trigger rehash if implemented
        }
        System.out.println("132: " + map.get(132)); // 1132


        System.out.println("\n****** Testing separate linkedList approach ******");
        MyHashMap<String, Integer> map1 = new MyHashMap<>();

        // put 测试
        map1.put("apple", 100);
        map1.put("banana", 200);
        map1.put("cherry", 300);
        System.out.println("apple: " + map1.get("apple"));   // 100
        System.out.println("banana: " + map1.get("banana")); // 200
        System.out.println("cherry: " + map1.get("cherry")); // 300

        // update 测试
        map1.put("banana", 250);
        System.out.println("banana (updated): " + map1.get("banana")); // 250

        // remove 测试
        System.out.println("remove banana: " + map1.remove("banana")); // true
        System.out.println("banana: " + map1.get("banana"));           // null

        // 非存在 key 测试
        System.out.println("grape: " + map1.get("grape")); // null
        System.out.println("remove grape: " + map1.remove("grape")); // false

        // rehash 测试（插入大量元素触发扩容）
        for (int i = 0; i < 50; i++) {
            map1.put("key" + i, i);
        }
        System.out.println("key42: " + map1.get("key42")); // 42
        System.out.println("key0: " + map1.get("key0"));   // 0


    }

    /**
     * 原题的给定输入是两个int, 所以不需要考虑泛型. 这里使用了separate linkedList的方法来解决collision.
     * 并且在pair中加入next连接. 实现linking
     */
    private static class MyHashMap1 {
        // define 一个 pair class
        private static class Pair {
            private final int key;
            private int val;
            private Pair next;

            private Pair(int k, int v) {
                this.key = k;
                this.val = v;
            }
        }

        private Pair[] map;
        private final double LOAD_FACTOR = 0.7;
        private int size = 37;
        private int count = 0;

        private MyHashMap1() {
            this.map = new Pair[size];
        }

        private void put(int key, int val) {
            // 如果负载因子大于70%, rehashing
            if ((double) count / size >= LOAD_FACTOR) {
                rehashing();
            }

            int index = Math.floorMod(key, size); // hashing key, 找到index
            if (map[index] == null) { // if the bucket is empty, put val there
                map[index] = new Pair(key, val);
                count++;
                return;
            } // fill in if the spot is empty


            Pair current = map[index]; // define a head of that spot
            while (current != null) { // iterate the chain, check if the key is present
                if (current.key == key) { // if so, update val, return
                    current.val = val;
                    return;
                }
                if (current.next == null) { // when hit the last element, stop
                    break;
                }
                current = current.next; // updating curr
            }
            assert current != null;
            current.next = new Pair(key, val); // add newPair to the end
            count++;
        }

        private int get(int key) {
            int index = Math.floorMod(key, size); // hashing the key to find index

            if (map[index] == null) {
                return -1; // if the bucket is empty, key no found
            }

            Pair current = map[index];
            while (current != null) { // iterate the separate chain
                if (current.key == key) { // looking for the key
                    return current.val; // if key is found, return val
                }
                current = current.next; // update curr
            }
            return -1; // not found, return
        }

        private void remove(int key) {
            int index = Math.floorMod(key, size); // hashing key for index
            if (map[index] == null) { // the bucket is empty, nothing found
                return;
            }

            if (map[index].key == key) { // check the first element in the bucket
                map[index] = map[index].next; // set the bucket to be the next of it
                count--; // update count, return
                return;
            }

            Pair curr = map[index]; // define curr
            while (curr.next != null) { // the first element has already squared away
                if (curr.next.key == key) { // check if the next.key = key
                    curr.next = curr.next.next; // if key found, remove it by bypassing the pair
                    count--;
                    return;
                }
                curr = curr.next;
            }
        }


        private void rehashing() {
            this.size = nextPrime(size * 2); // find the next prime after doubling the size
            this.count = 0; // reset the count

            Pair[] oldMap = map; // copy the old map
            this.map = new Pair[size]; // update a new map with new size

            for (Pair p : oldMap) { // iterate the oldMap
                while (p != null) { // put every pair to the newMap
                    put(p.key, p.val);
                    p = p.next;
                }
            }
        }

        private int nextPrime(int n) {
            while (!isPrime(n)) { // find the next prime
                n++;
            }
            return n;
        }

        private boolean isPrime(int n) {
            if (n < 4) return n > 0; // 1, 2, 3 are prime
            if (n % 2 == 0 || n % 3 == 0) { // filter out 2k and 3k
                return false;
            }
            for (int i = 5; i * i <= n; i += 6) { // prime is 6k +- 1
                if (n % i == 0 || n % (i + 2) == 0) { // let i = 6k - 1,  then  i + 2 = 6k + 1
                    return false;
                }
            }
            return true;
        }

    }


    /**
     * 以下的是泛型加线性探测的版本
     */
    private static class MyHashMap<K, V> {

        private static class Pair<K, V> {
            private final K key;
            private V val;

            private Pair(K key, V val) {
                this.key = key;
                this.val = val;
            }

        }

        private final double LOAD_FACTOR = 0.6;
        private Pair<K, V>[] baseArr;
        private int size;
        private int pairCount = 0;

        @SuppressWarnings("unchecked")
        private MyHashMap() {
            this.size = 37;
            baseArr = (Pair<K, V>[]) java.lang.reflect.Array.newInstance(Pair.class, size);

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
            this.size = findNextPrime(size * 2);
            pairCount = 0;
            Pair<K, V>[] oldArr = baseArr;
            this.baseArr = (Pair<K, V>[]) java.lang.reflect.Array.newInstance(Pair.class, size);
            for (Pair<K, V> p : oldArr) {
                if (p != null) {
                    put(p.key, p.val);
                }
            }
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
