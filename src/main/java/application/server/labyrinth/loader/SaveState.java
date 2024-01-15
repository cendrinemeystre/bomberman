package application.server.labyrinth.loader;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import application.server.labyrinth.Labyrinth;

public class SaveState {

    private final Labyrinth labyrinth;

    @JsonCreator
    public SaveState(@JsonProperty("Labyrinth") Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    public Labyrinth getLabyrinth() {
        return labyrinth;
    }
}
