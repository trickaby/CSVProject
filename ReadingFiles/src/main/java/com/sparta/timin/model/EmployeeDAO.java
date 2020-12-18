package com.sparta.timin.model;

//import jdk.vm.ci.meta.Local;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class EmployeeDAO {
    private static Connection connection;
    private static Properties properties = new Properties();

    private static void createProperties() {
        try {
            properties.load(new FileReader("src/main/resources/login.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void connectToDB(String url) {

        createProperties();
        String userName = properties.getProperty("userName") ;
        String password = properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    public static void selectFromSQL() {
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employeedatabase.employees");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
                System.out.println(resultSet.getString(5));
                System.out.println(resultSet.getString(6));
                System.out.println(resultSet.getString(7));
                System.out.println(resultSet.getDate(8));
                System.out.println(resultSet.getDate(9));
                System.out.println(resultSet.getInt(10));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void insertData(ArrayList<EmployeeDTO> employees) {
        try {
            for (int i = 0; i < employees.size(); i++) {
                EmployeeDTO employee = employees.get(i);

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `employeedatabase`.`employees` (`employeeID`, `name_prefix`, `first_name`, `middle_initial`, `last_name`, `gender`, `email`, `date_of_birth`, `date_of_joining`, `salary`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, employee.emp_ID);
                preparedStatement.setString(2, employee.namePrefix);
                preparedStatement.setString(3, employee.firstName);
                preparedStatement.setString(4, employee.middleInitial);
                preparedStatement.setString(5, employee.lastName);
                preparedStatement.setString(6, employee.gender);
                preparedStatement.setString(7, employee.email);
                preparedStatement.setDate(8, Date.valueOf(employee.dob));
                preparedStatement.setDate(9, Date.valueOf(employee.dateOfJoining));
                preparedStatement.setInt(10, employee.salary);
                preparedStatement.execute();
            }
            }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }







}



/*
crud: create read update delete
 */