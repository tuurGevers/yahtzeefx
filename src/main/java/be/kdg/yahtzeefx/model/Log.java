package be.kdg.yahtzeefx.model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * The type Log.
 */
public class Log {
    private final YahtzeeModel model;
    /**
     * The Player paths.
     */
    Path[] playerPaths;
    /**
     * The Tournament paths.
     */
    Path[] tournamentPaths;
    private final Path mode;
    private final Path highScores;

    private void appendFile(Path path, String message) throws IOException {
        Files.write(path, message.getBytes(), StandardOpenOption.APPEND);
    }

    /**
     * Instantiates a new Log.
     *
     * @param model the model
     */
    public Log(YahtzeeModel model) {
        this.model = model;
        this.playerPaths = new Path[model.getPlayers().size()];
        this.mode = Paths.get("src/main/resources/log/mode.txt");
        this.highScores = Paths.get("src/main/resources/log/highScores.txt");
    }

    /**
     * Save mode.
     *
     * @throws IOException the io exception
     */
    public void saveMode() throws IOException {
        Files.deleteIfExists(mode);
        Files.createFile(mode);
        appendFile(mode, model.getMode().toString());
        appendFile(mode, "\n" + model.getRound());
        appendFile(mode, "\n" + model.getTurn());
        appendFile(mode, "\n" + model.trows);
        appendFile(mode, "\n" + model.getTournamentRound());

    }

    /**
     * Save dice.
     *
     * @throws IOException the io exception
     */
    public void saveDice() throws IOException {
        Path path = Paths.get("src/main/resources/log/dice.txt");
        Files.deleteIfExists(path);
        Files.createFile(path);
        for (Dice d : model.getDice()) {
            Files.write(path, (d.getValue() + ":" + d.isHeld() + "\n").getBytes(), StandardOpenOption.APPEND);
        }
    }

    /**
     * Save game.
     *
     * @throws FileException the file exception
     */
    public void saveGame() throws FileException {
        Path[] playerPaths = new Path[model.getPlayers().size()];
        try {
            Path directory = Paths.get("src/main/resources/log/");
            Path[] files = Arrays.stream(Objects.requireNonNull(directory.toFile().listFiles(File::isFile)))
                    .filter(file -> file.getName().startsWith("logplayer"))
                    .map(File::toPath)
                    .toArray(Path[]::new);

            for (Path file: files){
                Files.deleteIfExists(file);
            }

            for (int i = 0; i < model.getPlayers().size(); i++) {
                playerPaths[i] = Paths.get("src/main/resources/log/log" + model.getPlayers().get(i).getName() + ".txt");
                Files.deleteIfExists(playerPaths[i]);
                Files.createFile(playerPaths[i]);
            }
            int index = 0;
            for (Player p : model.getPlayers()) {
                Files.write(playerPaths[index], (p.getName() + "\n").getBytes());
                Map<String, Integer> scoreCard = p.score.scores;
                for (String key : scoreCard.keySet()) {
                    String writable = key + ":" + scoreCard.get(key) + "\n";
                    Files.write(playerPaths[index], writable.getBytes(), StandardOpenOption.APPEND);
                }
                index++;
            }

        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    /**
     * Create rounds.
     *
     * @throws IOException the io exception
     */
    public void createRounds() throws IOException {
       this.tournamentPaths = new Path[model.getPlayers().size()];
        System.out.println(model.getPlayers());
        for (int i = 0; i < model.getPlayers().size(); i++) {
            tournamentPaths[i] = Paths.get("src/main/resources/log/tournamnetLog" + model.getPlayers().get(i).getName() + ".txt");
            Files.deleteIfExists(tournamentPaths[i]);
            Files.createFile(tournamentPaths[i]);
        }
    }

    /**
     * Save round.
     *
     * @throws FileException the file exception
     */
    public void saveRound() throws FileException {
        try {
            createRounds();
            Path directory = Paths.get("src/main/resources/log/");
            Path[] files = Arrays.stream(Objects.requireNonNull(directory.toFile().listFiles(File::isFile)))
                    .filter(file -> file.getName().startsWith("tournamentLogplayer"))
                    .map(File::toPath)
                    .toArray(Path[]::new);
            for (Path file: files){
                Files.deleteIfExists(file);
            }
            int index = 0;
            for (Player p : model.getPlayers()) {
                if (model.getTournamentRound() != 1) {
                    List<String> gelezenLijnen = Files.readAllLines(tournamentPaths[index],
                            Charset.defaultCharset());
                    int score = Integer.parseInt(gelezenLijnen.get(0));
                    Files.write(tournamentPaths[index], ((Integer.parseInt(p.score.getPoints()) + score) + "\n").getBytes());
                } else {
                    Files.write(tournamentPaths[index], (p.score.getPoints() + "\n").getBytes());

                }

                index++;
            }
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    /**
     * Save ai.
     *
     * @throws IOException the io exception
     */
    public void saveAi() throws IOException {
        Path path = Paths.get("src/main/resources/log/logcomputer.txt");
        Files.deleteIfExists(path);
        Files.createFile(path);
        Files.write(path, (model.getComputer().getPlayer().getName() + "\n").getBytes());
        Map<String, Integer> scoreCard = model.getComputer().getPlayer().score.scores;
        for (String key : scoreCard.keySet()) {
            String writable = key + ":" + scoreCard.get(key) + "\n";
            appendFile(path, writable);
        }

    }

    /**
     * Gets winner.
     *
     * @return the winner
     * @throws IOException the io exception
     */
    public String getWinner() throws IOException {
        int[] scores = new int[model.getPlayers().size()];
        int maxScore = 0;
        int maxIndex = -1;
        for (int i = 0; i < model.getPlayers().size(); i++) {
            List<String> gelezenLijnen = Files.readAllLines(tournamentPaths[i],
                    Charset.defaultCharset());
            scores[i] = Integer.parseInt(gelezenLijnen.get(0));
        }
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > maxScore) {
                maxScore = scores[i];
                maxIndex = i;
            }
        }

        return String.format("winnaar is %s met een score van %d", model.getPlayers().get(maxIndex).getName(), maxScore);
    }

    /**
     * Load save.
     *
     * @throws IOException the io exception
     */
    public void loadSave() throws IOException {
        Path directory = Paths.get("src/main/resources/log/");
        Path[] files = Arrays.stream(Objects.requireNonNull(directory.toFile().listFiles(File::isFile)))
                .filter(file -> file.getName().startsWith("logplayer"))
                .map(File::toPath)
                .toArray(Path[]::new);

        loadPlayers(files);
    }

    private void loadPlayers(Path[] files) throws IOException {
        int index = 0;
        List<Player> players = new ArrayList<>();
        for (Path file : files) {
            List<String> gelezenLijnen = Files.readAllLines(file,
                    Charset.defaultCharset());
            players.add(new Player(index, gelezenLijnen.get(0), new Score()));
            index++;
        }

        loadModel(players);


        for (Player p : model.getPlayers()) {
            Map<String, Integer> scoreCard = new HashMap<>();
            System.out.println(p.getName());
            Path path = Paths.get("src/main/resources/log/log" + p.getName() + ".txt");
            List<String> gelezenLijnen = Files.readAllLines(path,
                    Charset.defaultCharset());
            index = 0;
            for (String lijn : gelezenLijnen) {
                if (index != 0) {
                    String[] entry = lijn.split(":");
                    int score = Integer.parseInt(entry[1]);
                    p.score.addScore(score);
                    scoreCard.put(entry[0], score);
                }
                index++;
            }
            p.score.scores = scoreCard;
        }
    }


    /**
     * Load ai.
     *
     * @throws IOException the io exception
     */
    public void loadAi() throws IOException {
        Path[] paths = {Paths.get("src/main/resources/log/logplayer 1.txt"),Paths.get("src/main/resources/log/logcomputer.txt")};
        loadPlayers(paths);
        model.getComputer().setPlayer(model.getPlayers().get(1));

    }

    private void loadModel(List<Player> players) throws IOException {
        model.setPlayers(players);
        model.setRound(getRound());
        model.setMode(getMode());
        model.setTurn(getTurn());
        model.trows = getTrows();
    }

    private int getTrows() throws IOException {
        List<String> gelezenLijnen = Files.readAllLines(mode,
                Charset.defaultCharset());
        return Integer.parseInt(gelezenLijnen.get(3));
    }

    /**
     * Gets mode.
     *
     * @return the mode
     * @throws IOException the io exception
     */
    public Modes getMode() throws IOException {
        List<String> gelezenLijnen = Files.readAllLines(mode,
                Charset.defaultCharset());
        return Modes.valueOf(gelezenLijnen.get(0));
    }

    private int getRound() throws IOException {
        List<String> gelezenLijnen = Files.readAllLines(mode,
                Charset.defaultCharset());
        return Integer.parseInt(gelezenLijnen.get(1));
    }

    private int getTurn() throws IOException {
        List<String> gelezenLijnen = Files.readAllLines(mode,
                Charset.defaultCharset());
        return Integer.parseInt(gelezenLijnen.get(2));
    }

    /**
     * Load dice.
     *
     * @throws IOException the io exception
     */
    public void loadDice() throws IOException {
        Dice[] dice = new Dice[5];
        Path path = Paths.get("src/main/resources/log/dice.txt");
        List<String> gelezenLijnen = Files.readAllLines(path,
                Charset.defaultCharset());
        int index = 0;
        for (String lijn : gelezenLijnen) {
            String[] entry = lijn.split(":");
            dice[index] = new Dice(Integer.parseInt(entry[0]), Boolean.parseBoolean(entry[1]));
            model.setDice(dice);
            index++;
        }
    }

    /**
     * Load tournament round int.
     *
     * @return the int
     * @throws IOException the io exception
     */
    public int loadTournamentRound() throws IOException {
        List<String> gelezenLijnen = Files.readAllLines(mode,
                Charset.defaultCharset());
        return Integer.parseInt(gelezenLijnen.get(4));
    }

    /**
     * Add high score.
     *
     * @param text  the text
     * @param score the score
     */
    public void addHighScore(String text, int score) {
        try {
            Files.write(highScores, (text + ":" + score + "\n").getBytes(), StandardOpenOption.APPEND);

        } catch (IOException e) {
            System.out.println("couldn't save highscore\n" + e);
        }

    }

    /**
     * Gets high score.
     *
     * @return the high score
     * @throws IOException the io exception
     */
    public Map<String, Integer> getHighScore() throws IOException {
        List<String> gelezenLijnen = Files.readAllLines(highScores,
                Charset.defaultCharset());
        Map<String, Integer> highScores = new HashMap<>();
        for (String lijn : gelezenLijnen) {
            String[] entry = lijn.split(":");
            highScores.put(entry[0], Integer.valueOf(entry[1]));
        }

        return highScores;
    }
}
