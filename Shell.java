package com.lukatrbovic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Shell {

    private static String adminName;

    Shell() {
        validate();
    }
    
    void validate() {
        File accounts = new File("accounts.data");

        if (!accounts.exists()) {
            System.out.println("CRITICAL ERROR: Accounts database is missing or the file is corrupt");
            // Stop the program.
        }

    }

    public int getTotalStudents() {
        try {
            FileReader input = new FileReader("students.data");
            BufferedReader buffReader = new BufferedReader(input);

            String currentLine = null;
            int totalStudents = 0;

            while ((currentLine = buffReader.readLine()) != null) {
                totalStudents++;
            }

            buffReader.close();
            return totalStudents;

        } catch (IOException e) {
            System.out.println("Caught  an exception while executing program!");
        }

        return 0;
    }

    private void initShell() {
        try {
            String command = ".";

            while (!command.equals("")) {
                System.out.println("> ");
                Scanner cmdsCanner = new Scanner(System.in);
                command = cmdsCanner.nextLine();

                if (command.startsWith("help")) {
                    if (command.endsWith("general")) System.out.println("General!");
                }

                if (command.startsWith("student")) {
                    if (command.endsWith("list")) Student.list();
                    if (command.endsWith("add")) Student.add();
                }

            }
        } catch (Exception e) {
            System.out.println("Caught  an exception while executing program!");
        }
    }

    private void initDashboard() {
        System.out.println("\nYou've logged in as administrator. Welcome " + adminName);
        try {
            initShell();
        } catch (Exception e) {
            System.out.println("An error has occured while trying to start shell");
        }
    }

    void login(String username, String password) {
        try {
            FileReader input = new FileReader("accounts.data");
            BufferedReader buffReader = new BufferedReader(input);

            String currentLine;
            boolean isAccountFound = false;

            while ((currentLine = buffReader.readLine()) != null) {
                int userId = Integer.parseInt(currentLine.substring(currentLine.indexOf("(") + 1, currentLine.indexOf(")")));
                String userName = currentLine.substring(currentLine.indexOf("{") + 1, currentLine.indexOf("}"));
                String userPassword = currentLine.substring(currentLine.indexOf("[") + 1, currentLine.indexOf("]"));

                if (userName.equals(username) && password.equals(userPassword)) {
                    isAccountFound = true;

                    adminName = userName;
                    //
                    initDashboard();
                    // The point of no return.
                }
            }

            buffReader.close();
            if (!isAccountFound) System.out.println("We could not find your account :(");
        } catch (IOException e) {
            System.out.println("Caught  an exception while executing program!");
        }

    }
}