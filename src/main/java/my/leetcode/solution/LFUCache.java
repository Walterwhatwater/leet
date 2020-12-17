package my.leetcode.solution;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class LFUCache {
    private class Key implements Comparable<Key> {
        private int key;
        private int score = 0;
        private long ts = System.currentTimeMillis();

        @Override
        public int compareTo(Key other) {
            if (this.score == other.score) {
                return Long.compare(this.ts, other.ts);
            } else {
                return Integer.compare(this.score, other.score);
            }
        }
    }

    private int capacity;

    private Queue<Key> keyQueue;
    private Map<Integer, Integer> cache = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyQueue = new PriorityQueue<>();
    }

    public int get(int key) {
        int value = cache.getOrDefault(key, -1);
        if (value > 0) {
            keyVisited(key);
        }
        return value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }

        if (!cache.containsKey(key) && cache.size() >= capacity && !keyQueue.isEmpty()) {
            Key expired = keyQueue.poll();
            cache.remove(expired.key);
        }
        cache.put(key, value);
        keyPut(key);
    }

    private void keyPut(int i) {
        Iterator<Key> iterator = keyQueue.iterator();

        boolean found = false;
        while (iterator.hasNext()) {
            Key key = iterator.next();
            if (key.key == i) {
                key.ts = System.currentTimeMillis();
                iterator.remove();
                keyQueue.offer(key);
                found = true;
                break;
            }
        }

        if(!found){
            Key key = new Key();
            key.key = i;
            keyQueue.offer(key);
        }
    }

    private void keyVisited(int i) {
        Iterator<Key> iterator = keyQueue.iterator();

        while (iterator.hasNext()) {
            Key key = iterator.next();
            if (key.key == i) {
                key.score++;
                key.ts = System.currentTimeMillis();
                iterator.remove();
                keyQueue.offer(key);
                break;
            }
        }
    }
}
