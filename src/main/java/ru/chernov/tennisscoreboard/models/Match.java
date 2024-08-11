package ru.chernov.tennisscoreboard.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "matches")
@Data
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

}
