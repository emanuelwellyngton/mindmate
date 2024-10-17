package xyz.codenestsolucoes.mindmate.exception;

import java.io.IOException;

public class RepositoryNotFoundException extends IOException {
    public RepositoryNotFoundException() {
        super("The path passed is not a directory");
    }
}
