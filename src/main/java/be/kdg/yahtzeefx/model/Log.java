package be.kdg.yahtzeefx.model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Log {
    private YahtzeeModel model;
    Path[] playerPaths;
    Path[] tournamentPaths;
    private Path mode;
    private Path highScores;

    private void appendFile(Path path, String message) throws IOException {
        Files.write(path, message.getBytes(), StandardOpenOption.APPEND);
    }

    public Log(YahtzeeModel model) {
        this.model = model;
        this.playerPaths = new Path[model.getPlayers().size()];
        this.tournamentPaths = new Path[2];
        this.mode = Paths.get("src/main/resources/log/mode.txt");
        this.highScores = Paths.get("src/main/resources/log/highScores.txt");
    }

    public void saveMode() throws IOException {
        Files.deleteIfExists(mode);
        Files.createFile(mode);
        appendFile(mode, model.getMode().toString());
        appendFile(mode, "\n" + model.getRound());
        appendFile(mode, "\n" + model.getTurn());
        appendFile(mode, "\n" + model.trows);
        appendFile(mode, "\n" + model.getTournamentRound());

    }

    public void saveDice() throws IOException {
        Path path = Paths.get("src/main/resources/log/dice.txt");
        Files.deleteIfExists(path);
        Files.createFile(path);
        for (Dice d : model.getDice()) {
            Files.write(path, (d.getValue() + ":" + d.isHeld() + "\n").getBytes(), StandardOpenOption.APPEND);
        }
    }

    public void saveGame() throws FileException {
        Path[] playerPaths = new Path[model.getPlayers().size()];
        try {
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

    public void createRounds() throws IOException {
        for (int i = 0; i < model.getPlayers().size(); i++) {
            tournamentPaths[i] = Paths.get("src/main/resources/log/tournamnetLog" + model.getPlayers().get(i).getName() + ".txt");
            Files.deleteIfExists(tournamentPaths[i]);
            Files.createFile(tournamentPaths[i]);
        }
    }

    public void saveRound() throws FileException {
        try {
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

    public void loadSave() throws IOException {
        Path directory = Paths.get("src/main/resources/log/");
        Path[] files = Arrays.stream(Objects.requireNonNull(directory.toFile().listFiles(File::isFile)))
                .filter(file -> file.getName().startsWith("log"))
                .map(File::toPath)
                .toArray(Path[]::new);

        int index = 0;
        List<Player> players = new ArrayList<>();
        for (Path file : files) {
            List<String> gelezenLijnen = Files.readAllLines(file,
                    Charset.defaultCharset());
            players.add(new Player(index, gelezenLijnen.get(0), new Score()));
            index++;
        }

        model.setPlayers(players);
        model.setRound(getRound());
        model.setMode(getMode());
        model.setTurn(getTurn());
        model.trows = getTrows();
        System.out.println(getTrows());

        for (Player p : model.getPlayers()) {
            Map<String, Integer> scoreCard = new HashMap<>();
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

    private int getTrows() throws IOException {
        List<String> gelezenLijnen = Files.readAllLines(mode,
                Charset.defaultCharset());
        return Integer.parseInt(gelezenLijnen.get(3));
    }

    private Modes getMode() throws IOException {
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

    public void loadDice() throws IOException {
        Dice[] dice = new Dice[5];
        Path path = Paths.get("src/main/resources/log/dice.txt");
        List<String> gelezenLijnen = Files.readAllLines(path,
                Charset.defaultCharset());
        int index = 0;
        for (String lijn : gelezenLijnen) {
            String[] entry = lijn.split(":");
            System.out.println(entry[1]);
            dice[index] = new Dice(Integer.parseInt(entry[0]), Boolean.parseBoolean(entry[1]));
            model.setDice(dice);
            index++;
        }
    }

    public int loadTournamentRound() throws IOException {
        List<String> gelezenLijnen = Files.readAllLines(mode,
                Charset.defaultCharset());
        return Integer.parseInt(gelezenLijnen.get(4));
    }

    public Path[] getPlayerPaths() {
        return playerPaths;
    }

    public void addHighScore(String text, int score) {
        try {
            Files.write(highScores, (text + ":" + score + "\n").getBytes(), StandardOpenOption.APPEND);

        } catch (IOException e) {
            System.out.println("couldn't save highscore\n" + e);
        }

    }

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
