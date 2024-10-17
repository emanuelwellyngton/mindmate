package xyz.codenestsolucoes.mindmate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.codenestsolucoes.mindmate.exception.RepositoryIsEmptyException;
import xyz.codenestsolucoes.mindmate.service.FileService;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class MindmateApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MindmateApplication.class, args);
	}

	@Override
	public void run(String... args) {
		FileService fileService = new FileService();
		String path = "/home/emanuel/diarios_estudo/journals";

		try {
			List<String> filesStrings = fileService.readsAllFilesFromDirectory(path);
			System.out.println(filesStrings);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
