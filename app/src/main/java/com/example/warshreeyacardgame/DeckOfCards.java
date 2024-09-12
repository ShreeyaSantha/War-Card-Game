package com.example.warshreeyacardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {
    private ArrayList<Card> deck;

    private int size;



    public DeckOfCards(ArrayList<Card> deck) {
        this.deck = deck;

    }

    public DeckOfCards()
    {
        List<String> suits = Card.getValidSuits();

        List<Integer> number = Card.getValidNumbers();

        deck = new ArrayList<>();

        for(String suit:suits)
        {
            for (Integer Number:number)
                deck.add(new Card(suit,Number));
        }
    }

    public int getSize() {
        return deck.size();
    }

    public Card dealTopCard()
    {
        if(deck.size()>0)
            return deck.remove(0);
        else
            return null;
    }

    public void giveBackCard(Card card)
    {
        deck.add(card);

    }


    public void shuffle()
    {
        Collections.shuffle(deck);
    }

}
