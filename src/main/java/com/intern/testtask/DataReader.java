package com.intern.testtask;

import com.intern.testtask.trie.Trie;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataReader implements Runnable {
    private volatile Trie trie = new Trie();
    private int row;

    public Trie getTrie() {
        return trie;
    }

    public DataReader(int row){
        this.row = row;
    }

    public void run(){
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new ClassPathResource("airports.dat").getInputStream()
                ), 512)){
            String s;

            int count = 0;
            while((s=br.readLine())!=null){
                var splitted = CSVSplitter.SplitCSV(s);
                var word = splitted.get(row-1);
                trie.insert(word,count);
                count++;
            }
        }
        catch (IOException | OutOfMemoryError e) {
                e.printStackTrace();
        }
    }
}
