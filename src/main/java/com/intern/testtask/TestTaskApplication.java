package com.intern.testtask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

@SpringBootApplication
public class TestTaskApplication implements CommandLineRunner {

    @Autowired
    private AppProps appProps;

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if(args.length > 0) {
            try {
                int row = Integer.parseInt(args[0]);
                if(row<1 || row>14) { throw new NumberFormatException(); }
                appProps.setRow(row);
            }
            catch(NumberFormatException e){
                System.out.println("Необязательный аргумент: номер столбца для поиска, должен быть натуральным числом " +
                        "<= количеству столбцов (14).");
            }
        }

        var dr = new DataReader(appProps.getRow());
        Thread myThread = new Thread(dr, "DataReader");
        myThread.start();
        System.out.printf("Столбик номер: %s \n", appProps.getRow());

        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите строку: ");
            String searchString = in.nextLine();

            final long startTime = System.currentTimeMillis();
            var resultIds = dr.getTrie().keysWithPrefix(searchString);
            final long endTime = System.currentTimeMillis();

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new ClassPathResource("airports.dat").getInputStream()
                    ),512)) {
                String s;
                int count = 0;

                while ((s = br.readLine()) != null) {
                    if(resultIds.size()<7000) {
                        if (resultIds.contains(count)) {
                            System.out.println(s);
                        }
                    }
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.printf("Количество найденных строк: %d \n", resultIds.size());
            System.out.printf("Время, затраченное на поиск: %d мс. \n", endTime - startTime);
        }
    }
}
