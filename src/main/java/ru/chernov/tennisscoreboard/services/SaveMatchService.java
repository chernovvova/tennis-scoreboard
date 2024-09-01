package ru.chernov.tennisscoreboard.services;

import ru.chernov.tennisscoreboard.models.Match;
import ru.chernov.tennisscoreboard.models.Player;
import ru.chernov.tennisscoreboard.repositories.MatchRepository;
import ru.chernov.tennisscoreboard.repositories.PlayerRepository;

import java.util.Optional;

public class SaveMatchService {
    private final PlayerRepository playerRepository = new PlayerRepository();
    private final MatchRepository matchRepository = new MatchRepository();

    public void saveMatch(Match match) {
        try{
            playerRepository.save(match.getPlayer1());
        } catch(Exception e) {
            Optional<Player> player = playerRepository.findByName(match.getPlayer1().getName());

            if(player.isPresent()) {
                match.setPlayer1(player.get());
            }
        }

        try{
            playerRepository.save(match.getPlayer2());
        } catch(Exception e) {
            Optional<Player> player = playerRepository.findByName(match.getPlayer2().getName());

            if(player.isPresent()) {
                match.setPlayer2(player.get());
            }
        }

        if(match.getPlayer1().getName().equals(match.getWinner().getName())) {
            match.setWinner(match.getPlayer1());
        }
        else {
            match.setWinner(match.getPlayer2());
        }

        matchRepository.save(match);
    }
}
