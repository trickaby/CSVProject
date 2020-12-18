package com.sparta.timin;

import com.sparta.timin.controller.CSVReader;
import com.sparta.timin.model.EmployeeCleaner;
import com.sparta.timin.model.EmployeeDAO;
import com.sparta.timin.model.EmployeeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;


public class AppTest {

    @Test
    void readFromCSVTest() {
        String path = "src/test/resources/testEntries.csv";
        ArrayList<EmployeeDTO> testList = CSVReader.readEmployees(path);
        CSVReader.printArrayList(testList);

    }

    @Test
    void findDuplicatesTest() {
        String path = "src/test/resources/testEntries.csv";
        ArrayList<EmployeeDTO> testList = CSVReader.readEmployees(path);
        Assertions.assertEquals(EmployeeCleaner.FindDuplicates(testList).size(), 2);
    }

    @Test
    void removeDuplicatesTest() {
        String path = "src/test/resources/testEntries.csv";
        ArrayList<EmployeeDTO> testList = CSVReader.readEmployees(path);
        Assertions.assertEquals(EmployeeCleaner.RemoveDuplicates(testList).size(), 7);
    }

    @Test
    void databaseTest() {
        String path = "src/test/resources/testEntries.csv";
        ArrayList<EmployeeDTO> testList = CSVReader.readEmployees(path);
        testList.remove(0);
        EmployeeDAO.connectToDB("jdbc:mysql://localhost:3306/tester");
        EmployeeDAO.insertData(testList);

        EmployeeDAO.selectFromSQL();
    }

    @Test
    void emptyCSVTests() {
        String path = "src/test/resources/EmptyFile.csv";
        ArrayList<EmployeeDTO> emptyList = CSVReader.readEmployees(path);
        System.out.println(emptyList);
    }

    @Test
    void writeToFile() {
        String path = "src/test/resources/testEntries.csv";
        ArrayList<EmployeeDTO> testList = CSVReader.readEmployees(path);
        EmployeeCleaner.exportCorrupted(testList, "src/test/resources/CorruptedTestFile.txt");



    }








}
