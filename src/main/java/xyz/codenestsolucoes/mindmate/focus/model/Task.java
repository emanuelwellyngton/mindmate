package xyz.codenestsolucoes.mindmate.focus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "tasks")
public class Task {
    @Id
    private int id;

    private String title;
}
