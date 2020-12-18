package com.sparta.timin.view;

import com.sparta.timin.controller.CSVReader;
import com.sparta.timin.model.EmployeeDAO;
import com.sparta.timin.model.EmployeeDTO;
import com.sparta.timin.model.EmployeeCleaner;
import com.sparta.timin.view.Starter;

import java.util.ArrayList;

/*
 read csv files
 convert to arraylist
 remove invalid entries
 put invalid entries in .txt file
 add clean files to database

 time taken to read csv
 time taken to populate
 overall time

 Thorough testing
 threading
 hashmap

 */
public class App 
{
    public static void main( String[] args ) {


        long overallStart = System.nanoTime();

        Starter.readCSVFile();

        Starter.filterData();

        Starter.populateDatabase();

        long overallFinish = System.nanoTime();
        long overallTime = (overallFinish-overallStart)/1_000_000_000;
        System.out.println("Overall time taken: " + overallTime + "s");

    }

}
