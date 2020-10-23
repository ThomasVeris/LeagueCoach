package be.vdab.commands;

import java.util.Scanner;

public class GetSummonerNameCommand {
    public static String enterSummonerName() {
        System.out.println("Please enter your summoner name:");
        Scanner inputScanner = new Scanner(System.in);
        String accountName = inputScanner.nextLine();
        return accountName.replaceAll("\\s+", "");
    }
}
