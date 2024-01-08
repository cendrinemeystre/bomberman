package application.client.view.panel;

import javax.swing.*;
import java.awt.*;

public abstract class Panel<T extends LayoutManager> extends JPanel {
    private final JPanel panel;

    protected Panel(T layoutManager) {
        this.panel = new JPanel(layoutManager);
    }

    protected void addToPanel(Component component, Object constraints) {
        if (constraints != null) {
            panel.add(component, constraints);
        } else {
            panel.add(component);
        }
    }

    protected JPanel getPanel() {
        return panel;
    }

    public abstract JPanel createPanel();
}
