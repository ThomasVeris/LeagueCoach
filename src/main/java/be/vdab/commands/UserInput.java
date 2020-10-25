package be.vdab.commands;

import java.util.Scanner;

public class UserInput {
    public static int enterMatchIndex(){
        System.out.println("Please enter which match you want to see, index is 0-100.");
        Scanner inputScanner = new Scanner(System.in);
        int matchIndex =  inputScanner.nextInt();
        while (!(matchIndex >= 0 && matchIndex <= 100)) {
            System.out.println("This is not a valid index!");
            matchIndex = inputScanner.nextInt();
        }
        return matchIndex;
    }

    public static String enterSummonerName() {
        System.out.println("Please enter your summoner name:");
        Scanner inputScanner = new Scanner(System.in);
        String accountName = inputScanner.nextLine();
        return accountName.replaceAll("\\s+", "");
    }
}
