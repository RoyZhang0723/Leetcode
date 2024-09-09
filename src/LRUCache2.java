import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class LRUCache2 {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode() {}

        public DLinkedNode(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache2(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        //使用伪头部和伪尾部
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            DLinkedNode node = cache.get(key);
            node.value = val;
            moveToHead(node);
            return;
        }
        if (cache.size() >= capacity) {
            cache.remove(tail.prev.key);
            removeNode(tail.prev);
            DLinkedNode node = new DLinkedNode(key, val);
            addToHead(node);
            cache.put(key, node);
            return;
        }
        size++;
        DLinkedNode node = new DLinkedNode(key, val);
        addToHead(node);
        cache.put(key, node);
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

}
