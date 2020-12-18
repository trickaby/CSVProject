package com.sparta.timin.controller;

import com.sparta.timin.model.EmployeeDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;


public class CSVReader {



    public static ArrayList<EmployeeDTO> readEmployees(String path) {
        ArrayList<EmployeeDTO> employees = new ArrayList<>();
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
//            EmployeeDTO employee = new EmployeeDTO();
            bufferedReader.readLine();
//            ArrayList<String[]> lineList = new ArrayList<>();
            //                System.out.println(line);
            //                String[] recordArray = line.split(",");
            //                lineList.add(recordArray);
            employees = bufferedReader.lines()
                    .map(line -> line.split(","))
                    .map(temp -> new EmployeeDTO(
                    temp[0],
                    temp[1],
                    temp[2],
                    temp[3],
                    temp[4],
                    temp[5],
                    temp[6],
                    temp[7],
                    temp[8],
                    temp[9])).collect(Collectors.toCollection(ArrayList::new));
            System.out.println();

            bufferedReader.close();
//            System.out.println(Arrays.toString(lineList.get(16)));
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return employees;

    }

    public static void printArrayList(ArrayList<EmployeeDTO> list) {

            System.out.println(list.toString());

    }





}
