package com.t.suanfa;

import java.util.HashMap;
import java.util.Map;

public class BM100_LRU02<K,V> {
    private Node<K,V> head;
    private Node<K,V> tail;
    private Map<K,Node<K,V>> map;
    private int cap;

    public BM100_LRU02(int cap){
        head = new Node(null,null);
        tail = new Node(null,null);
        head.next = tail;
        tail.pre = head;
        this.cap = cap;
        map = new HashMap<>();
    }

    public Node add(K key,V val){
        if(map.containsKey(key)){
            Node<K, V> kvNode = map.get(key);
            kvNode.value = val;
            moveToHead(kvNode);
            return kvNode;
        }

        Node node = new Node(key,val);
        if(map.size() < cap){
            map.put(key,node);
            addHead(node);
        }else {
            Node lastNode = tail.pre;
            removeNode(lastNode);
            map.put(key,node);
            addHead(node);
        }
         return node;
    }

    private Node remove(K key){
        if(!map.containsKey(key))
            return null;
        Node<K, V> kvNode = map.get(key);
        removeNode(kvNode);
        kvNode.next = null;
        kvNode.pre = null;
        return kvNode;
    }

    private void removeNode(Node node) {
        map.remove(node.key);
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void addHead(Node node) {
        head.next.pre = node;
        node.next = head.next;
        head.next = node;
        node.pre = head;
    }

    private void moveToHead(Node<K,V> kvNode) {
        kvNode.pre.next = kvNode.next;
        kvNode.next.pre = kvNode.pre;
        head.next.pre = kvNode;
        kvNode.next = head.next;
        head.next = kvNode;
        kvNode.pre = head;

    }

    class Node<K,V>{
        Node pre;
        Node next;
        K key;
        V value;
        Node(K key,V value){
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        BM100_LRU02<Integer,String> lru = new BM100_LRU02<>(3);
        for (int i = 0; i < 10; i++) {
            lru.add(i,"i=" + i);
        }
    }

}
