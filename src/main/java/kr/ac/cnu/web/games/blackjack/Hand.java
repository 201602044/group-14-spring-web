package kr.ac.cnu.web.games.blackjack;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Hand {
    private Deck deck;
    @Getter
    private List<Card> cardList = new ArrayList<>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }

    public int getCardSum() {
        int sum = 0;
        for(int i = 0; i < cardList.size(); i++){
            Card card = cardList.get(i);
            if(card.getRank() > 10) {
                sum += 10;
            }
            else{
                sum += card.getRank();
            }
        }
        return sum;
        //return cardList.stream().mapToInt(card -> card.getRank()).sum();
    }

    public void reset() {
        cardList.clear();
    }
}
