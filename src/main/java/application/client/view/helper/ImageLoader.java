package application.client.view.helper;

import application.client.model.field.FieldType;

import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ImageLoader {
    private final Map<Character, ImageIcon> imageMap = new HashMap<>();

    public ImageLoader() {
        loadImages();
    }

    public ImageIcon getImageByKey(char key) {
        if (!imageMap.containsKey(key)) {
            throw new IllegalArgumentException("The key: " + key + " is does not exist in imageMap");
        }
        return imageMap.get(key);
    }

    private void loadImages() {
        for (FieldType fieldType : FieldType.values()) {
            imageMap.put(fieldType.getKey(), createImageIcon(fieldType.name()));
        }
    }

    private ImageIcon createImageIcon(String imageName) {
        return new ImageIcon(getPicture(imageName));
    }

    private URL getPicture(String image) {
        return Objects.requireNonNull(getClass().getResource("/bomberman/field/" + image + ".png"));
    }
}
