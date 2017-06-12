package com.rentalcars.utils;

public class SIPPUtil {

    public static String toString(char letter, int position) {
        switch (letter) {
            case 'A':
                return "Automatic";
            case 'B':
                return "2 doors";
            case 'C':
                if (position == 0) return "Compact";
                return "4 doors";
            case 'D':
                return "5 doors";
            case 'E':
                return "Economy";
            case 'F':
                if (position == 0) return "Full size";
                return "SUV";
            case 'I':
                return "Intermediate";
            case 'L':
                return "Luxury";
            case 'M':
                if (position == 0) return "Mini";
                return "Manual";
            case 'N':
                return "Petrol - No AC";
            case 'P':
                if (position == 0) return "Premium";
                return "Pick up";
            case 'R':
                return "Petrol - AC";
            case 'S':
                return "Standard";
            case 'T':
                return "Convertible";
            case 'V':
                return "Passenger van";
            case 'W':
                return "Estate";
            // 'X'
            default:
                return "Special";

        }
    }
}