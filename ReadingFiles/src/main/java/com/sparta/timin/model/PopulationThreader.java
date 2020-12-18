package com.sparta.timin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class PopulationThreader implements Runnable {
    private final ArrayList<EmployeeDTO> subsublist;
    public PopulationThreader(ArrayList<EmployeeDTO> sublist) {

        subsublist = sublist;
    }

    @Override
    public void run() {
        String url = "jdbc:mysql://localhost:3306/tester";


        EmployeeDAO.connectToDB(url);
        System.out.println("Connected to Database");
        EmployeeDAO.insertData(subsublist);


    }





}

/*




 */
