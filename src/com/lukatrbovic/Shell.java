package com.lukatrbovic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Shell {

    private static String adminName;

    public Shell(){

    }

    public void validate(){
        File accounts = new File("accounts.data");

        if(!accounts.exists())
        {
            System.out.println("CRITICAL ERROR: Accounts database is missing or the file is corrupt");
            // Stop the program.
        }

    }

    public int getTotalStudents() throws IOException{
        FileReader input = new FileReader("students.data");
        BufferedReader buffReader = new BufferedReader(input);

        String currentLine = null;
        int totalStudents = 0;

        while((currentLine = buffReader.readLine()) != null)
        {
            totalStudents ++;
        }

        buffReader.close();
        return totalStudents;
    }

    private void initShell() throws IOException{
        String command = ".";

        while(!command.equals(""))
        {
            System.out.println("> ");
            Scanner cmdsCanner = new Scanner(System.in);
            command = cmdsCanner.nextLine();

            if(command.startsWith("help"))
            {
                if(command.endsWith("general")) System.out.println("General!");
            }

            if(command.startsWith("student"))
            {
                if(command.endsWith("list")) Student.list();
                if(command.endsWith("add")) Student.add();
            }

        }
    }

    private void initDashboard() throws IOException{
        System.out.println("\nYou've logged in as administrator. Welcome " + adminName);

        File annoucments = new File("announce.data");

		/*if(annoucments.exists())
		{
			FileReader input = new FileReader("announce.data");
			BufferedReader buffReader = new BufferedReader(input);

			String announcLine = null;

			while((announcLine = buffReader.readLine()) != null)
			{
				System.out.print("\n ANNOUNCEMENT: " + announcLine);
			}
		}
		*/

        initShell();
    }

    public void login(String username, String password) throws IOException
    {
        FileReader input = new FileReader("accounts.data");
        BufferedReader buffReader = new BufferedReader(input);

        String currentLine;
        boolean isAccountFound = false;

        while((currentLine = buffReader.readLine()) != null)
        {
            int userId = Integer.parseInt(currentLine.substring(currentLine.indexOf("(") + 1, currentLine.indexOf(")")));
            String userName = currentLine.substring(currentLine.indexOf("{") + 1, currentLine.indexOf("}"));
            String userPassword = currentLine.substring(currentLine.indexOf("[") + 1, currentLine.indexOf("]"));

            if(userName.equals(username) && password.equals(userPassword))
            {
                isAccountFound = true;

                adminName = userName;

                initDashboard();
                // The point of no return.
            }
        }

        buffReader.close();
        if(!isAccountFound) System.out.println("We could not find your account :(");
    }
}