package com.lukatrbovic;

import java.io.IOException;
import java.util.Scanner;

class Main {

    public static void main(String args[]) {
        try {
            System.out.println("University application");

            System.out.println("Administrator username: ");
            Scanner scan = new Scanner(System.in);
            String adminUsername = scan.nextLine();

            System.out.print("Administrator password: ");
            String adminPassword = scan.nextLine();

            Shell adminControl = new Shell();
            adminControl.login(adminUsername, adminPassword);

            scan.close();
        } catch (Exception e) {
            System.out.println("An error has occured while trying to start the application.\nError code is following; " + e);
        }

    }
}

