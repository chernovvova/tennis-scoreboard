package ru.chernov.tennisscoreboard.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player1", referencedColumnName = "id")
    private Player player1;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player2", referencedColumnName = "id")
    private Player player2;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winner", referencedColumnName = "id")
    private Player winner;


    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
}
