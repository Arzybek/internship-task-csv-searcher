package com.intern.testtask.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Trie {
    private Node root = new Node();

    public void insert(String word, int value) {
        Node current = root;

        for (char l: word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(l, k -> new Node());
        }

        if(current.isEndOfWord()){
            if(current.values==null){
                current.values = new ArrayList<>();
                current.values.add(current.value);
            }
            current.values.add(value);
        }
        else {
            current.setEndOfWord(true);
            current.value = value;
        }
    }

    public boolean find(String word) {
        Node current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            Node node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }

    public List<Integer> keysWithPrefix(String prefix){
        var results = new ArrayList<Integer>();
        var x = getNode(prefix);
        collect(x, prefix.chars().mapToObj(e -> (char)e).collect(Collectors.toList()), results);
        return results;
    }

    private void collect(Node x, List<Character> prefix, ArrayList<Integer> results){
        if(x == null){
            return;
        }
        if(x.isEndOfWord()){
//            var prefix_str = String.valueOf(prefix);
            if(x.values != null){
                results.addAll(x.values);
            }
            else results.add(x.value);
        }
        for (char c: x.getChildren().keySet()){
            prefix.add(c);
            collect(x.getChildren().get(c), prefix, results);
            prefix.remove(prefix.size()-1);
        }
    }

    private Node getNode(String key){
        var node = root;
        for(char ch: key.toCharArray()){
            if(node.getChildren().containsKey(ch)){
                node = node.getChildren().get(ch);
            }
            else return null;
        }
        return node;
    }
}
