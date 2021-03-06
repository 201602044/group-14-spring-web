package kr.ac.cnu.web.service;

import kr.ac.cnu.web.games.blackjack.Deck;
import kr.ac.cnu.web.games.blackjack.GameRoom;
import kr.ac.cnu.web.model.User;
import kr.ac.cnu.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rokim on 2018. 5. 26..
 */
@Service
public class BlackjackService {
    private final int DECK_NUMBER = 1;
    private final Map<String, GameRoom> gameRoomMap = new HashMap<>();
    @Autowired
    private UserRepository userRepository;

    public GameRoom createGameRoom(User user) {
        Deck deck = new Deck(DECK_NUMBER);

        GameRoom gameRoom = new GameRoom(deck);
        gameRoom.addPlayer(user.getName(), user.getAccount());

        gameRoomMap.put(gameRoom.getRoomId(), gameRoom);

        return gameRoom;
    }

    public GameRoom joinGameRoom(String roomId, User user) {
        // multi player Game 이 아니므로 필요가 없다.
        return null;
    }

    public void leaveGameRoom(String roomId, User user) {
        gameRoomMap.get(roomId).removePlayer(user.getName());
    }

    public GameRoom getGameRoom(String roomId) {
        return gameRoomMap.get(roomId);
    }

    public GameRoom bet(String roomId, User user, long bet) {

        GameRoom gameRoom = gameRoomMap.get(roomId);
        gameRoom.reset();
        gameRoom.bet(user.getName(), bet);
        gameRoom.deal();
        return gameRoom;
    }
    public GameRoom hit(String roomId, User user) {
        GameRoom gameRoom = gameRoomMap.get(roomId);
        gameRoom.hit(user.getName());
        //System.out.println(gameRoom.getPlayerList().get(user.getName()).getBalance());
        //user.setAccount(gameRoom.getPlayerList().get(user.getName()).getBalance());
        //userRepository.save(user);

        return gameRoom;
    }

    public GameRoom stand(String roomId, User user, long i) {
        GameRoom gameRoom = gameRoomMap.get(roomId);
        if(i==1)gameRoom.plusbet(user.getName());
        gameRoom.stand(user.getName());
        gameRoom.playDealer();
        gameRoom.shuffleDeck();
        user.setAccount(gameRoom.getPlayerList().get(user.getName()).getBalance());
        userRepository.save(user);

        return gameRoom;
    }

}
