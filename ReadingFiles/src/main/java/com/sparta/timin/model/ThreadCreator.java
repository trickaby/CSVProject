package com.sparta.timin.model;

import java.util.ArrayList;

public class ThreadCreator {

    public static Thread[] createThreads(int numberOfThreads, int sizeOfPartitions, ArrayList<EmployeeDTO> employees) {
        Thread[] threads = new Thread[numberOfThreads+1];

        for (int i = 0; i < numberOfThreads; i++) {
            ArrayList<EmployeeDTO> employeesSublist =  new ArrayList<>(employees.subList(i * sizeOfPartitions, (i + 1) * sizeOfPartitions));
            threads[i] = new Thread(new PopulationThreader(employeesSublist));
//
//
        }

        ArrayList<EmployeeDTO> lastSubList = new ArrayList<>(employees.subList(sizeOfPartitions*numberOfThreads, employees.size()));
        threads[numberOfThreads] = new Thread(new PopulationThreader(lastSubList));

        return threads;

    }



}
