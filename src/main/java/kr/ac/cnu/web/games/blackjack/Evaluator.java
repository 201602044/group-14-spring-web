package kr.ac.cnu.web.games.blackjack;

import java.util.Map;

/**
 * Created by rokim on 2018. 5. 27...
 */
public class Evaluator {
    private Map<String, Player> playerMap;
    private Dealer dealer;

    public Evaluator(Map<String, Player> playerMap, Dealer dealer) {
        this.playerMap = playerMap;
        this.dealer = dealer;
    }

    public boolean evaluate() {
        if (playerMap.values().stream().anyMatch(player -> player.isPlaying())) {
            return false;
        }


        playerMap.forEach((s, player) -> {
            int dealerResult = dealer.getHand().getCardSum();
            if(dealerResult > 21) dealerResult=0;
            int playerResult = player.getHand().getCardSum();
            if(playerResult==21){
                player.blackjack();
            }
            else if (playerResult > 21) {
                player.lost();
            }else if (playerResult > dealerResult) {
                player.win();
            }else if (playerResult == dealerResult) {
                player.tie();
            }else {
                if(dealerResult < 22)
                    player.lost();
                else
                    player.win();
            }
            player.getHand();
        });



        return true;
    }


}
