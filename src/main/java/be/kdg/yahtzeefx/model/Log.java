package be.kdg.yahtzeefx.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Log {
    private YahtzeeModel model;
    Path[] playerPaths;
    Path[] tournamentPaths;

    public Log(YahtzeeModel model) {
        this.model = model;
        this.playerPaths = new Path[model.getPlayers().size()];
        this.tournamentPaths = new Path[2];
    }

    public void saveGame() throws FileException {
        Path[] playerPaths = new Path[model.getPlayers().size()];
        try {
            for (int i =0 ; i< model.getPlayers().size(); i++) {
                playerPaths[i] = Paths.get("src/main/resources/log/log" + model.getPlayers().get(i).getName() +".txt");
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
        for (int i =0 ; i< model.getPlayers().size(); i++) {
            tournamentPaths[i] = Paths.get("src/main/resources/log/tournamnetLog" + model.getPlayers().get(i).getName() +".txt");
            Files.deleteIfExists(tournamentPaths[i]);
            Files.createFile(tournamentPaths[i]);
        }
    }

    public void saveRound() throws FileException {
        try {
            int index = 0;
            for (Player p : model.getPlayers()) {
                if(model.getTournamentRound() != 1){
                    List<String> gelezenLijnen = Files.readAllLines(tournamentPaths[index],
                            Charset.defaultCharset());
                    int score = Integer.parseInt(gelezenLijnen.get(0));
                    Files.write(tournamentPaths[index], ((Integer.parseInt(p.score.getPoints()) + score) + "\n").getBytes());
                }else{
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
        for (int i =0; i< model.getPlayers().size(); i++) {
            List<String> gelezenLijnen = Files.readAllLines(tournamentPaths[i],
                    Charset.defaultCharset());
            scores[i] =  Integer.parseInt(gelezenLijnen.get(0));
        }
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > maxScore) {
                maxScore = scores[i];
                maxIndex = i;
            }
        }

        return String.format("winnaar is %s met een score van %d", model.getPlayers().get(maxIndex).getName(), maxScore  );
    }

    public Path[] getPlayerPaths() {
        return playerPaths;
    }
}
