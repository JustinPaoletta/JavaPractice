import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        System.out.println("What File Are We Reading Today?");
        Scanner inputFile = new Scanner(System.in);
        String fileToRead = inputFile.next();
        System.out.println("Specify Sort Order? Y or N");
        Scanner sortOption = new Scanner(System.in);
        String yOrN = sortOption.next();

        while (!yOrN.equals("N") & !yOrN.equals("Y")) {
            System.out.println("Please pick a valid option, Y or N?");
            Scanner validChoice = new Scanner(System.in);
            yOrN = validChoice.next();
        }

        if (yOrN.equals("N")) {
            sortFile(fileToRead, null);
        } else {
            System.out.println("desc or asc?");
            Scanner chose = new Scanner(System.in);
            String optionChosen = chose.next();
            while (!optionChosen.equals("desc") & !optionChosen.equals("asc")) {
                System.out.println("Please pick a valid option - desc or asc?");
                Scanner validPick = new Scanner(System.in);
                optionChosen = validPick.next();
            }
            sortFile(fileToRead, optionChosen);
        }

    }

    public static void sortFile(String filepath, String order) {

        order = order != null ? order : "asc";

        try {
            File myFile = new File(filepath);
            Scanner read = new Scanner(myFile);

            ArrayList <Integer> myNumbs = new ArrayList<Integer>();

            while (read.hasNextLine()) {
                String number = read.nextLine();
                try {
                    myNumbs.add(Integer.parseInt(number));
                } catch (NumberFormatException ignored) {

                }
            }

            Collections.sort(myNumbs);

            if (order.equals("desc")) {
                Collections.reverse(myNumbs);
            }

            System.out.println(myNumbs);
            read.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. You Must Choose A Valid Filename & Path");
            e.printStackTrace();
        }
    }
}
