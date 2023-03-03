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
import java.util.List;
import java.util.Map;

public class Log {
    private YahtzeeModel model;

    public Log(YahtzeeModel model) {
        this.model = model;
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

            Arrays.stream(playerPaths).forEach(path-> {
                try {
                    List<String> gelezenLijnen = Files.readAllLines(path,
                            Charset.defaultCharset());
                    for (String lijn : gelezenLijnen) {
                        System.out.println("\t" + lijn);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            throw new FileException(e);
        }
    }


}
