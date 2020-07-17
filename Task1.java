/*
Task 1
    This Program takes in a user input value of N, generates N number of random integers
    between 1 and 1,000,000,000 and prints each number on a separate line. The filepath
    is named N.txt. If the file already exists it will be overwritten and if the file does
    not exist it will be created.
*/

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        System.out.println("Enter A Number");
        Scanner input = new Scanner(System.in);
        String N = input.next();
        String filepath = N + ".txt";
        saveName(N, filepath);
    }

    public static void saveName(String N, String filepath) {

        try {

            Random rand = new Random();
            FileWriter fw = new FileWriter(filepath, false);
            PrintWriter pw = new PrintWriter(fw);

            for (int i = 0; i < Integer.parseInt(N); i++) {
                int randomNum = rand.nextInt(1000000000);
                pw.println(randomNum + 1);
            }

            pw.flush();
            pw.close();

            JOptionPane.showMessageDialog(null, "File Of Random Numbers Saved!");

        }

        catch (Exception E) {

            File myFile = new File(filepath);
            myFile.delete();

            JOptionPane.showMessageDialog(null,
                    "Failed To Save The File." +
                    " Run The Program Again And Make Sure You Enter A Valid Number.");

        }

    }

}
