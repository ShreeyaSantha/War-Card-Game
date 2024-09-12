package com.example.warshreeyacardgame;

import java.util.Arrays;
import java.util.List;

public class Card {
    private String suit;
    private int number;
    //private Image image;


    public Card(String suit, int number) {
        setSuit(suit);
        setNumber(number);
    }

    public static List<String> getValidSuits() {
        return Arrays.asList("heart", "diamond", "spade", "clover");
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getNumber() {
        return number;
    }

    /**
     *
     */
    public static List<Integer> getValidNumbers() {
        return Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
    }

    /**
     * validates the argument and set instance varible
     *
     * @param number 1,2,3,4,5,6,7,8,9,10,11,12,13
     */

    public void setNumber(int number) {
        List<Integer> validNumber = getValidNumbers();
        if (validNumber.contains(number))
            this.number = number;
        else
            throw new IllegalArgumentException("Valid Number are" + validNumber);
    }

    public String toString()
    {
        return String.format("%s%s",suit,number);

    }


}
