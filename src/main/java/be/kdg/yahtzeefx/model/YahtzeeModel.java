package be.kdg.yahtzeefx.model;

import be.kdg.yahtzeefx.model.scorings.*;

import java.util.*;

/**
 * The type Yahtzee model.
 */
public class YahtzeeModel {
    private Dice[] dice;
    /**
     * The Trows.
     */
    public int trows = 0;
    private int turn = 0;
    private List<Player> players;
    private boolean finished;
    private int round = 1;
    private Modes mode;
    private final Log log;
    private int tournamentRound;
    private final AI computer;
    /**
     * The Eye 1.
     */
    Eyes eye1;
    /**
     * The Eye 2.
     */
    Eyes eye2;
    /**
     * The Eye 3.
     */
    Eyes eye3;
    /**
     * The Eye 4.
     */
    Eyes eye4;
    /**
     * The Eye 5.
     */
    Eyes eye5;
    /**
     * The Eye 6.
     */
    Eyes eye6;
    /**
     * The Three of a kind.
     */
    ThreeOfAKind threeOfAKind;
    /**
     * The Four of a kind.
     */
    FourOfAKind fourOfAKind;
    /**
     * The Full house.
     */
    FullHouse fullHouse;
    /**
     * The Small street.
     */
    SmallStreet smallStreet;
    /**
     * The Big street.
     */
    BigStreet bigStreet;
    /**
     * The Yahtzee.
     */
    Yahtzee yahtzee;
    /**
     * The Chance.
     */
    Chance chance;


    /**
     * Instantiates a new Yahtzee model.
     *
     * @param players the players
     */
    public YahtzeeModel(List<Player> players) {
        this.dice = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = new Dice(1, false);
        }
        this.players = new ArrayList<>();
        this.players.addAll(players);
        this.finished = false;
        this.mode = Modes.SINGLE;
        this.log = new Log(this);
        this.tournamentRound = 1;
        this.computer = new AI(this.log);
        //checkers
        this.eye1 = new Eyes(1);
        this.eye2 = new Eyes(2);
        this.eye3 = new Eyes(3);
        this.eye4 = new Eyes(4);
        this.eye5 = new Eyes(5);
        this.eye6 = new Eyes(6);
        this.threeOfAKind = new ThreeOfAKind(7);
        this.fourOfAKind = new FourOfAKind(8);
        this.fullHouse = new FullHouse(9);
        this.smallStreet = new SmallStreet(10);
        this.bigStreet = new BigStreet(11);
        this.yahtzee = new Yahtzee(12);
        this.chance = new Chance(13);
    }

    /**
     * Roll dice.
     */
//roll alle stenen
    public void rollDice() {
        for (Dice d : this.dice) {
            if (!d.isHeld()) d.roll();
        }
        trows++;
    }

    /**
     * Next turn.
     */
//volgende beurt voor als er meerdere spelers zijn
    public void nextTurn() {
        if (turn == players.size() - 1) {
            turn = 0;
            round++;
        } else {
            turn++;
        }
    }

    /**
     * Scores map.
     *
     * @return the map
     */
//alle checkers worden opgeroepen en als ze valid zijn worde de scores in een map gereturned
    public Map<String, Integer> scores() {
        Map<String, Integer> score = new HashMap<>();
        List<Scoring> scoringRules = Arrays.asList(eye1, eye2, eye3, eye4, eye5, eye6, threeOfAKind, fourOfAKind, fullHouse, smallStreet, bigStreet, yahtzee, chance);
        scoringRules.forEach(rule -> {
            if (rule.isValid(this.dice)) {
                score.put(Integer.toString(rule.getId()), rule.getScore(this.dice));
            }
        });
        return score;
    }


    /**
     * All scores map.
     *
     * @return the map
     */
    public Map<String, Integer> allScores() {
        Map<String, Integer> score = new LinkedHashMap<>();
        for (int i = 1; i <= 13; i++) {
            score.put(Integer.toString(i), 0);
        }
        return score;
    }


    /**
     * Gets winner.
     *
     * @return the winner
     */
    public Player getWinner() {
        int topScore = 0;
        Player topPlayer = null;
        for (Player p : players) {
            int score = Integer.parseInt(p.score.getPoints());
            if (score > topScore) {
                topScore = score;
                topPlayer = p;
            }
        }
        return topPlayer;
    }

    /**
     * Player from string player.
     *
     * @param p the p
     * @return the player
     */
    public Player playerFromString(String p) {
        for (Player player : players) {
            if (Objects.equals(player.getName(), p)) {
                return player;
            }
        }
        return null;
    }


    /**
     * Score full boolean.
     *
     * @return the boolean
     */
//checkt of alle scorebladeren vol zijn
    public boolean scoreFull() {
        return this.round == 4 && turn == players.size() - 1;
    }

    /**
     * Get dice dice [ ].
     *
     * @return the dice [ ]
     */
    public Dice[] getDice() {
        return dice;
    }

    /**
     * Gets players.
     *
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Bonus check.
     *
     * @param player the player
     */
//als een speler meer als 63 punten haalt met alleen het bovenste deel wordt er een bonus van 35 gegeven
    public void bonusCheck(Player player) {
        int upperCount = 0;
        int upperScore = 0;
        //checkt voor waarde 1..6 of ze zijn ingevuld en telt alle waardes op
        for (int i = 1; i < 7; i++) {
            if (player.score.scores.containsKey(String.valueOf(i))) {
                upperCount++;
                upperScore += player.score.scores.get(String.valueOf(i));
            }
        }
        //als alle waarde zijn ingevuld en de score minstens 63 is en de speler de bonus nog niet heeft gekregen
        // krijgt de spler een bonus van 3
        if (upperCount == 6 && upperScore >= 63 && !player.score.upperBonus) {
            player.score.upperBonus = true;
            player.score.addScore(35);

        }
    }


    /**
     * Gets yahtzee.
     *
     * @return the yahtzee
     */
    public Yahtzee getYahtzee() {
        return yahtzee;
    }

    /**
     * Sets finished.
     *
     * @throws FileException the file exception
     */
    public void setFinished() throws FileException {
        this.finished = scoreFull();
        if (this.finished && this.mode == Modes.TOURNAMENT) {
            log.saveRound();
        }
    }

    /**
     * Is finished boolean.
     *
     * @return the boolean
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Current player player.
     *
     * @return the player
     */
    public Player currentPlayer() {
        return players.get(turn);
    }

    /**
     * Gets round.
     *
     * @return the round
     */
    public int getRound() {
        return round;
    }

    /**
     * Sets player.
     *
     * @param index  the index
     * @param player the player
     */
    public void setPlayer(int index, Player player) {
        this.players.set(index, player);
    }

    /**
     * Gets mode.
     *
     * @return the mode
     */
    public Modes getMode() {
        return mode;
    }

    /**
     * Sets mode.
     *
     * @param mode the mode
     */
    public void setMode(Modes mode) {
        this.mode = mode;
    }

    /**
     * Gets log.
     *
     * @return the log
     */
    public Log getLog() {
        return log;
    }

    /**
     * Reset.
     */
    public void reset() {
        for (Player p : players) {
            p.score = new Score();
        }
        this.trows = 0;
        this.turn = 0;
        this.round = 1;
        this.finished = false;
        this.tournamentRound++;
    }

    /**
     * Gets selected count.
     *
     * @return the selected count
     */
    public int getSelectedCount() {
        return (int) Arrays.stream(dice).filter(Dice::isHeld).count();
    }

    /**
     * Restart.
     */
    public void restart() {
        Player player1 = new Player(0, "player 1", new Score());
        players = new ArrayList<>();
        players.add(player1);
        this.trows = 0;
        this.turn = 0;
        this.round = 1;
        this.finished = false;
        this.tournamentRound++;
    }

    /**
     * Sets players.
     *
     * @param players the players
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Gets tournament round.
     *
     * @return the tournament round
     */
    public int getTournamentRound() {
        return tournamentRound;
    }

    /**
     * Sets round.
     *
     * @param round the round
     */
    public void setRound(int round) {
        this.round = round;
    }

    /**
     * Gets turn.
     *
     * @return the turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Sets turn.
     *
     * @param turn the turn
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * Sets dice.
     *
     * @param dice the dice
     */
    public void setDice(Dice[] dice) {
        this.dice = dice;
    }

    /**
     * Sets tournament round.
     *
     * @param tournamentRound the tournament round
     */
    public void setTournamentRound(int tournamentRound) {
        this.tournamentRound = tournamentRound;
    }

    /**
     * Gets computer.
     *
     * @return the computer
     */
    public AI getComputer() {
        return computer;
    }
}
