package com.lukatrbovic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

class Student {

    static void list() {
        try {
            FileReader input = new FileReader("students.data");
            BufferedReader buffReader = new BufferedReader(input);

            String currentLine = null;
            String studentStatus = null; // Is student present or not.
            String studentName = null;
            String dateOfBirth = null;

            while ((currentLine = buffReader.readLine()) != null) {
                studentName = currentLine.substring(currentLine.indexOf(":") + 1, currentLine.indexOf(":["));
                dateOfBirth = currentLine.substring(currentLine.indexOf("Birth:") + 6, currentLine.indexOf("]"));

                System.out.println(String.format("Student: %s\n |> Status: Active\n |> Birthday %s\n", studentName, dateOfBirth));
            }

            buffReader.close();
        } catch (Exception e) {
            System.out.println("An error has occured while trying to list students. Error code " + e);
        }
    }

    static void add() {
        try {
            Scanner scan = new Scanner(System.in);

            System.out.println("Student's First Name: ");
            String studentFirstName = scan.nextLine();
            System.out.println("Student's Last Name: ");
            String studentLastName = scan.nextLine();

            FileReader input = new FileReader("students.data");
            BufferedReader buffReader = new BufferedReader(input);

            String currentLine = null;

            int fileLine = 0;

            while ((currentLine = buffReader.readLine()) != null) {
                fileLine++;
            }

            String line = String.format("\n(%d):%s %s:[Birth:undefined];", fileLine++, studentFirstName, studentLastName);
            Files.write(Paths.get("students.data"), line.getBytes(), StandardOpenOption.APPEND);

            buffReader.close();

            System.out.println("User has been added!");
        } catch (IOException e) {
            System.out.println("An error has occured while trying to add a student. Error code " + e);
        }

    }
}