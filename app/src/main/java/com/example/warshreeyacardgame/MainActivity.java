package com.example.warshreeyacardgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DeckOfCards deck1 = new DeckOfCards();
    DeckOfCards deck2 = new DeckOfCards();
    int Score1;
    int Score2;

    int Deck1card;

    Card removed;
    Card removed2;

    String Deck1Suit;

    int Deck2card;
    String Deck2Suit;
    TextView TextScore1;
    TextView TextScore2;
    Button deal;
    Button WarButton;

    Button Instructions;
    ImageView LeftCard;
    ImageView RightCard;

    ImageView War1;
    ImageView War2;

    ImageView War3;
    ImageView War4;
    char winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button shuffle = findViewById(R.id.shuffle);

        deal = (Button) findViewById(R.id.Deal);
        deal.setEnabled(false);
        LeftCard = (ImageView) findViewById(R.id.initial1);
        RightCard = (ImageView) findViewById(R.id.Initial2);
        War1 = findViewById(R.id.P1War2);
        War2 = findViewById(R.id.P2War1);
        War3 = findViewById(R.id.P1War1);
        War4 = findViewById(R.id.P2War2);
        WarButton = findViewById(R.id.WarButton);
        Instructions = findViewById(R.id.instructions);
        TextScore1 = findViewById(R.id.Score1);
        TextScore2 = findViewById(R.id.Score2);
        TextScore1.setText(String.valueOf(Score1));
        TextScore2.setText(String.valueOf(Score2));
        WarButton.setEnabled(false);
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //shuffle decks
                deck1.shuffle();
                deck2.shuffle();
                shuffle.setEnabled(false);
                deal.setEnabled(true);
            }
        });
        deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WarButton.setEnabled(false);
                War2.setImageResource(R.drawable.initial);
                War1.setImageResource(R.drawable.initial);
                removed = deck1.dealTopCard();
                Deck1card = removed.getNumber();
                Deck1Suit = removed.getSuit();

                removed2 = deck2.dealTopCard();
                Deck2card = removed2.getNumber();
                Deck2Suit = removed2.getSuit();

                //set images to the card dealt

                int leftImage = getResources().getIdentifier(Deck1Suit + Deck1card, "drawable", getPackageName());
                LeftCard.setImageResource(leftImage);

                int rightImage = getResources().getIdentifier(Deck2Suit + Deck2card, "drawable", getPackageName());
                RightCard.setImageResource(rightImage);

                //compare cards
                if (Deck1card > Deck2card) {
                    Score1++;
                    deck1.giveBackCard(removed);
                    deck1.giveBackCard(removed2);

                } else if (Deck1card < Deck2card) {
                    Score2++;
                    deck2.giveBackCard(removed);
                    deck2.giveBackCard(removed2);
                }
                else{
                    WarButton.setEnabled(true);
                }
                TextScore1.setText(String.valueOf(Score1));
                TextScore2.setText(String.valueOf(Score2));
                Win();

            }

        });

        WarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                War();
            }
        });

        Instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.MainActivity,new Instructions()).commit();

            }
        });

    }
// win conditions, if deck ==0 or score == 25
    public void Win() {

        if (deck1.getSize() == 0)
            winner = '1';

        else if (deck2.getSize() == 0)
            winner = '2';

        else if (Score1 == 25)
            winner = '1';
        else if (Score2 == 25)
            winner = '2';

        if (winner == '1') {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Congrats, Player 1 Wins");
            builder.setTitle("Winner Player 1!");
            builder.setCancelable(false);
            builder.setNeutralButton("Play Again", (dialog, which) -> {
                // When the user click yes button then app will close
                Reset();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else if (winner == '2') {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Congrats,Skeleton Wins");
            builder.setTitle("Winner Skeleton!");
            builder.setCancelable(false);
            builder.setNeutralButton("Reset", (dialog, which) -> {
                // When the user click yes button then app will close
                Reset();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }
    private void Reset() {
        Score1=0;
        Score2 = 0;
        TextScore1.setText(String.valueOf(Score1));
        TextScore2.setText(String.valueOf(Score2));
        LeftCard.setImageResource(R.drawable.initial);
        RightCard.setImageResource(R.drawable.initial);
        deck1.shuffle();
        deck2.shuffle();
        Deck1card = 0;
        Deck2card = 0;
        Deck1Suit.equals("hearts");
        Deck2Suit.equals("hearts");


    }

    public void War(){



        Card war1 = deck1.dealTopCard();
        Card war2 = deck1.dealTopCard();

        Card war3 = deck2.dealTopCard();
        Card war4 = deck2.dealTopCard();

        int leftImage = getResources().getIdentifier(war2.getSuit() + war2.getNumber(), "drawable", getPackageName());
        War1.setImageResource(leftImage);

        int rightImage = getResources().getIdentifier(war4.getSuit() + war4.getNumber(), "drawable", getPackageName());
        War2.setImageResource(rightImage);

//comapre the cards
        if(war2.getNumber()>war4.getNumber())
        {
            Score1++;
            deck1.giveBackCard(removed);
            deck1.giveBackCard(removed2);
            deck1.giveBackCard(war1);
            deck1.giveBackCard(war2);
            deck1.giveBackCard(war3);
            deck1.giveBackCard(war4);
        }
        else if ((war2.getNumber()<war4.getNumber()))

        {
            Score2++;
            deck2.giveBackCard(removed);
            deck2.giveBackCard(removed2);
            deck2.giveBackCard(war1);
            deck2.giveBackCard(war2);
            deck2.giveBackCard(war3);
            deck2.giveBackCard(war4);
        }

        else
        {

        }
        TextScore1.setText(String.valueOf(Score1));
        TextScore2.setText(String.valueOf(Score2));
    }


}