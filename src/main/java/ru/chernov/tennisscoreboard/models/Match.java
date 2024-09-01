package ru.chernov.tennisscoreboard.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chernov.tennisscoreboard.services.score.MatchScore;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "player1", referencedColumnName = "id")
    private Player player1;
    @ManyToOne
    @JoinColumn(name = "player2", referencedColumnName = "id")
    private Player player2;
    @ManyToOne
    @JoinColumn(name = "winner", referencedColumnName = "id")
    private Player winner;

    @Transient
    private MatchScore matchScore;


    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchScore = new MatchScore(MatchScore.DEFAULT_COUNT_SETS);
    }

    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
        this.matchScore = new MatchScore(MatchScore.DEFAULT_COUNT_SETS);
    }
}
