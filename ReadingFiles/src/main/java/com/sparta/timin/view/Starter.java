package com.sparta.timin.view;

import com.sparta.timin.controller.CSVReader;
import com.sparta.timin.model.*;

import java.util.ArrayList;

public class Starter {

    private static ArrayList<EmployeeDTO> employees;


    public static void readCSVFile() {
        System.out.println("Reading CSV Files...");
        long CSVReadStart = System.nanoTime();
        String path = "src/main/resources/EmployeeRecords.csv";
        String largerFile = "src/main/resources/EmployeeRecordsLarge.csv";
        employees = CSVReader.readEmployees(path);
        ArrayList<EmployeeDTO> largerEmployees = CSVReader.readEmployees(largerFile);
        employees.addAll(largerEmployees);
        long CSVReadFinish = System.nanoTime();
        long CSVReadTime = (CSVReadFinish - CSVReadStart) / 1_000_000_000;
        System.out.println("CSV file has been read");
        System.out.println("Size of CSV file: " + employees.size());
        System.out.println("Time taken to read the CSV file: " + CSVReadTime + "s");
    }

    public static void filterData() {
        System.out.println("Filtering corrupt data...");
        long filterDataStart = System.nanoTime();
        ArrayList<EmployeeDTO> corruptedList = EmployeeCleaner.FindDuplicates(employees);
        employees = EmployeeCleaner.RemoveDuplicates(employees);
        EmployeeCleaner.exportCorrupted(corruptedList, "src/main/resources/corruptedEntries.txt");
        long filterDataFinish = System.nanoTime();
        long filterDataTime = (filterDataFinish - filterDataStart) / 1_000_000_000;
        System.out.println("Data has been cleaned");
        System.out.println("Time taken to filter data: " + filterDataTime + "s");
        System.out.println("Size of cleaned list to populate: " + employees.size());
    }

    public static void populateDatabase() {
        String url = "jdbc:mysql://localhost:3306/employeedatabase";
        long populateStart = System.nanoTime();

        final int numberOfPartitions = 20;
        int sizeOfPartitions = employees.size() / numberOfPartitions;

        Thread[] threads = ThreadCreator.createThreads(numberOfPartitions, sizeOfPartitions, employees);

        System.out.println("Populating database");

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
            System.out.println(threads[i].getName() + " started");
        }

        for (int i = 0; i < threads.length; i++) {
            while (threads[i].isAlive()) ;
            {

            }
        }

//        ArrayList<EmployeeDTO> employeesSublist1 = new ArrayList<>(employees.subList(0, sizeOfPartitions));
//        ArrayList<EmployeeDTO> employeesSublist2 = new ArrayList<>(employees.subList( sizeOfPartitions, employees.size()));
//
//        System.out.println(employeesSublist1.size());
//        System.out.println(employeesSublist2.size());
//
//        Thread thread1 = new Thread(new PopulationThreader(employeesSublist1));
//        Thread thread2 = new Thread(new PopulationThreader(employeesSublist2));
//
//        thread1.start();
//        thread2.start();
//        while(thread1.isAlive() && thread2.isAlive()){}


        long populateFinish = System.nanoTime();
        long populateTime = (populateFinish - populateStart) / 1_000_000_000;
        System.out.println("Database has been populated");
        System.out.println("Time taken to populate database: " + populateTime + "s");
    }


}
