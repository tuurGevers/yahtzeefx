package be.kdg.yahtzeefx.model;

import java.io.IOException;

public class FileException extends Throwable {
    public FileException(IOException e) {
        System.out.printf("error saving game, %s",e);
    }
}
