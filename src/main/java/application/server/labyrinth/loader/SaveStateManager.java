package application.server.labyrinth.loader;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

import application.server.labyrinth.tile.Tile;

/**
 * Diese Klasse Managt das Speichern und Laden eines Spielstandes.
 * Dafür werden ein Filepath und ein Objectmapper gebraucht.
 * Gespeichert wird als json-File
 */
public class SaveStateManager {
    private final File file;
    private final ObjectMapper mapper;

    public SaveStateManager(File file) {
        this.file = file;
        mapper = new ObjectMapper();
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder().allowIfSubType(Tile.class).build();
        mapper.activateDefaultTyping(ptv);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public SaveState read() {
        SaveState saveState = null;
        try {
            saveState = mapper.readValue(file, SaveState.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return saveState;
    }
}