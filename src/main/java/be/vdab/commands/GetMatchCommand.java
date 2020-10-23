package be.vdab.commands;

import java.util.Scanner;

public class GetMatchCommand {
    public static int getMatchIndex(){
        System.out.println("Please enter which match you want to see, index is 0-100.");
        Scanner inputScanner = new Scanner(System.in);
        int matchIndex =  inputScanner.nextInt();
        while (!(matchIndex >= 0 && matchIndex <= 100)) {
            System.out.println("This is not a valid index!");
            matchIndex = inputScanner.nextInt();
        }
        return matchIndex;
    }
}
