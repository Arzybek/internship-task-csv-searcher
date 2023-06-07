package com.intern.testtask;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class AppProps {
    private int row;

    public int getRow(){
        return row;
    }

    public void setRow(int row){
        this.row = row;
    }
}
