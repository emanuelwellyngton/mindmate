package xyz.codenestsolucoes.mindmate.service;

import xyz.codenestsolucoes.mindmate.exception.RepositoryIsEmptyException;
import xyz.codenestsolucoes.mindmate.exception.RepositoryNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileService {

    //Lists all the files in a given directory
    public List<Path> listDirectoryFiles(Path path) throws IOException  {
        try(var files = Files.list(path)) {
            return files.filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (NoSuchFileException e){
            throw new RepositoryNotFoundException();
        }
    }

    public List<String> readsAllFilesFromDirectory(String pathString) throws IOException, RepositoryIsEmptyException {
        Path path = Path.of(pathString);

        List<Path> pathList = listDirectoryFiles(path);
        var filesStrings = new ArrayList<String>();

        if(!pathList.isEmpty()) {
            pathList.forEach((pathListItem) -> {
                String fileString = null;
                try {
                    fileString = Files.readString(pathListItem);
                    filesStrings.add(fileString);
                } catch (IOException e) {
                    throw new RuntimeException("There was an error when tryng to read the file");
                }
            });
            return filesStrings;
        } else {
            throw new RepositoryIsEmptyException();
        }
    }
}
