package com.intern.testtask.trie;

import java.util.List;
import java.util.TreeMap;

public class Node {
    private TreeMap<Character, Node> children;
    public int value;
    private boolean isEndOfWord;
    public List<Integer> values;

    public Node(){
        this.children = new TreeMap<>();
    }

    public Node(int value){
        this.value = value;
    }

    public TreeMap<Character, Node> getChildren() {
        return children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean word) {
        isEndOfWord = word;
    }
}
