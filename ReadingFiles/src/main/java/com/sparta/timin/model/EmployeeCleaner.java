package com.sparta.timin.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeCleaner {


//    public static void clean() {
//
//    }

    public static ArrayList<EmployeeDTO> FindDuplicates(ArrayList<EmployeeDTO> employees) {
        ArrayList<EmployeeDTO> duplicates = new ArrayList<>();
        for (int i=0 ; i < employees.size(); i++) {
            EmployeeDTO employeeI = employees.get(i);
            for (int j = i + 1; j < employees.size(); j++ ) {
                EmployeeDTO employeeJ = employees.get(j);
                if(employeeI.emp_ID.equals(employeeJ.emp_ID)) {
                    duplicates.add(employeeI);
                    duplicates.add(employeeJ);
                }
            }
        }
        System.out.println("Size of corrupted data: " + duplicates.size());
        return duplicates;
    }

//    public static ArrayList<EmployeeDTO> FindDuplicates(ArrayList<EmployeeDTO> employees) {
//        ArrayList<EmployeeDTO> duplicates = new ArrayList<>();
//        for (int i=0 ; i < employees.size(); i++) {
//            EmployeeDTO employeeI = employees.get(i);
//            for (int j = i + 1; j < employees.size(); j++ ) {
//                EmployeeDTO employeeJ = employees.get(j);
//                if(employeeI.emp_ID.equals(employeeJ.emp_ID)) {
//                    duplicates.add(employeeI);
//                    duplicates.add(employeeJ);
//                }
//            }
//        } return duplicates;
//    }

    public static ArrayList<EmployeeDTO> RemoveDuplicates(ArrayList<EmployeeDTO> employees) {
        for (int i=0 ; i < employees.size(); i++) {
            EmployeeDTO employeeI = employees.get(i);
            for (int j = i + 1; j < employees.size(); j++ ) {
                EmployeeDTO employeeJ = employees.get(j);
                if(employeeI.emp_ID.equals(employeeJ.emp_ID) || employeeI.email.equals(employeeJ.email)) {
                    employees.remove(employeeI);
                    employees.remove(employeeJ);
                }
            }
        } return employees;
    }

//    public static ArrayList<EmployeeDTO> CleanLastName (ArrayList<EmployeeDTO> employees) {
//        for (int i=0 ; i < employees.size(); i++) {
//            EmployeeDTO employee = employees.get(i);
//            if (employee.lastName.equalsIgnoreCase("TRUE") || employee.lastName.equalsIgnoreCase("FALSE")) {
//                employees.remove(employee);
//            }
//        }
//        return employees;
//    }

    public static void exportCorrupted(ArrayList<EmployeeDTO> corruptedList, String source) {
        try {
            FileWriter fileWriter = new FileWriter(source);
            for (EmployeeDTO e : corruptedList) {
                fileWriter.write(e.emp_ID + ", " + e.namePrefix + ", " + e.firstName + ", " + e.middleInitial + ", " + e.lastName + ", " + e.gender + ", " + e.email + ", " + e.dob + ", " + e.dateOfJoining + ", " + e.salary + "\n" );


            }

            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        } System.out.println("Corrupted data has been sent to .txt file");
    }

}
