package xyz.codenestsolucoes.mindmate.assistent.exception;

import java.io.IOException;

public class RepositoryIsEmptyException extends IOException {
    public RepositoryIsEmptyException(){
        super("The given repository has no files");
    }
}
