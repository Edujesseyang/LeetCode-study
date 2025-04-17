package src.HashSet;


public class DesignHashSet_705 {
    //    Design a HashSet without using any built-in hash table libraries.
//
//    Implement MyHashSet class:
//
//    void add(key) Inserts the value key into the HashSet.
//    bool contains(key) Returns whether the value key exists in the HashSet or not.
//    void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
//
//
//            Example 1:
//
//    Input
//["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
//        [[], [1], [2], [1], [3], [2], [2], [2], [2]]
//    Output
//[null, null, null, true, false, null, true, null, false]
//
//    Explanation
//    MyHashSet myHashSet = new MyHashSet();
//myHashSet.add(1);      // set = [1]
//myHashSet.add(2);      // set = [1, 2]
//myHashSet.contains(1); // return True
//myHashSet.contains(3); // return False, (not found)
//myHashSet.add(2);      // set = [1, 2]
//myHashSet.contains(2); // return True
//myHashSet.remove(2);   // set = [1]
//myHashSet.contains(2); // return False, (already removed)
    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();

        System.out.println("=== 基本功能测试 ===");
        set.add(10);
        set.add(20);
        set.add(30);
        System.out.println("Contains 10: OutPut: " + set.contains(10) + "  Expected: true"); // true
        System.out.println("Contains 40: OutPut: " + set.contains(40) + "  Expected: false"); // false

        System.out.println("Delete 10: OutPut: " + set.remove(10) + "  Expected: true"); // true
        System.out.println("Contains 10:  OutPut: " + set.contains(10) + "  Expected: false"); // false
        System.out.println("Delete 10 again: OutPut: " + set.remove(10) + "  Expected: false"); // false

        System.out.println("=== 重复添加测试 ===");
        set.add(20);
        set.add(20); // 不应重复添加
        set.add(30);
        set.add(30);
        System.out.println("Delete 20 : OutPut: " + set.remove(20) + "  Expected: true"); // true
        System.out.println("Contains 20: OutPut: " + set.contains(20) + "  Expected: false"); // false
        System.out.println("Contains 30: OutPut: " + set.contains(30) + "  Expected: true"); // true

        System.out.println("=== rehashing 扩容测试 ===");
        for (int i = 0; i < 100; i++) {
            set.add(i * 7); // 插入一堆数据触发 rehash
        }

        boolean allPresent = true;
        for (int i = 0; i < 100; i++) {
            if (!set.contains(i * 7)) {
                System.out.println("❌ 丢失元素: " + (i * 7));
                allPresent = false;
            }
        }
        System.out.println(allPresent ? "✅ 所有元素都存在" : "❌ 有元素丢失");

        System.out.println("=== remove 全部测试 ===");
        for (int i = 0; i < 100; i++) {
            set.remove(i * 7);
        }

        boolean allRemoved = true;
        for (int i = 0; i < 100; i++) {
            if (set.contains(i * 7)) {
                System.out.println("❌ 未成功移除: " + (i * 7));
                allRemoved = false;
            }
        }
        System.out.println(allRemoved ? "✅ 所有元素都成功移除" : "❌ 有残留元素");

        System.out.println("=== 边界测试 ===");
        set.add(Integer.MIN_VALUE);
        set.add(Integer.MAX_VALUE);
        System.out.println("Add MIN_VALUE  OutPut: " + set.contains(Integer.MIN_VALUE) + "  Expected: true"); // true
        System.out.println("Add MAX_VALUE  OutPut: " + set.contains(Integer.MAX_VALUE) + "  Expected: true"); // true
        System.out.println("Remove MIN_VALUE   OutPut: " + set.remove(Integer.MIN_VALUE) + "  Expected: true"); // true
        System.out.println("Contains MIN_VALUE   OutPut: " + set.contains(Integer.MIN_VALUE) + "  Expected: false"); // false
    }

    /**
     * 这是一个利用了separate linkedList 的 array base 的 set, 主要核心是rehashing. 这个就基础了多练. 另外还又一个是利用了线性探测的方式的. 速度更快.
     */
    private static class MyHashSet {
        private static class Node {
            private final int val;
            private Node next;

            private Node(int val) {
                this.val = val;
            }
        }

        private static final double LOAD_FACTOR = 0.7;
        private int elementCount = 0;
        private int size = 37;
        private Node[] arr;

        private MyHashSet() {
            arr = new Node[size];
        }

        private void add(int key) {
            double loading = (double) elementCount / size;
            if (loading >= LOAD_FACTOR) {
                rehashing();
            }

            int index = Math.abs(key % size);
            if (this.contains(key)) {
                return;
            }

            Node newNode = new Node(key);
            if (arr[index] == null) {
                arr[index] = newNode;
            } else {
                Node current = arr[index];
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }

        private boolean contains(int key) {
            int index = Math.abs(key % size);
            Node current = arr[index];
            while (current != null) {
                if (current.val == key) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        private boolean remove(int key) {
            int index = Math.abs(key % size);

            Node current = arr[index];
            if (current == null) {
                return false;
            }
            if (current.val == key) {
                arr[index] = current.next;
                elementCount--;
                return true;
            }
            Node prev = current;
            current = current.next;
            while (current != null) {
                if (current.val == key) {
                    prev.next = current.next;
                    elementCount--;
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        private void rehashing() {
            this.size = findNextPrime(size * 2);
            Node[] newArr = new Node[size];
            for (Node n : arr) {
                Node current = n;
                while (current != null) {
                    int index = Math.abs(current.val % size);
                    Node newNode = new Node(current.val);
                    if (newArr[index] == null) {
                        newArr[index] = newNode;
                    } else {
                        Node newCur = newArr[index];
                        while (newCur.next != null) {
                            newCur = newCur.next;
                        }
                        newCur.next = newNode;
                    }
                    current = current.next;
                }
            }
            this.arr = newArr;
        }

        private boolean isPrime(int n) {
            if (n <= 3) {
                return n > 0;
            }

            if (n % 2 == 0 || n % 3 == 0) {
                return false;
            }

            for (int i = 5; i * i < n; i += 6) {
                if (n % i == 0 || n % (i + 2) == 0) {
                    return false;
                }
            }

            return true;
        }

        private int findNextPrime(int n) {
            int checkNext = n;
            while (!isPrime(checkNext)) {
                checkNext++;
            }
            return checkNext;
        }
    }


}
