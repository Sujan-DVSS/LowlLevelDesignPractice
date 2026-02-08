package main.lld.LRUCache;

public class DoublyLinkedList {
    DoublyLinkedList prev;
    DoublyLinkedList next;
    int value;

    DoublyLinkedList(int value){
        this.value = value;
        prev = null;
        next = null;
    }
}
