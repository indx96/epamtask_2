package com.epam.textparser.textcomponents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ivan on 6/25/14.
 */
public class ComponentContainer implements TextComponent {
    private ArrayList<TextComponent> components = new ArrayList<>();
    private TextComponentType type;

    public ComponentContainer(TextComponentType type) {
        this.type = type;
    }

    public TextComponentType getType() {
        return type;
    }

    public List<TextComponent> getComponents() {
        return Collections.unmodifiableList(components);
    }

    public void addComponent(TextComponent component) {
        components.add(component);
    }

    public boolean removeComponent(TextComponent component) {
        return components.remove(component);
    }

    public boolean replaceComponent(TextComponent componentToReplace,
                                    TextComponent componentToAdd) {
        return Collections.replaceAll(components, componentToReplace, componentToAdd);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        components.forEach(builder::append);
        return builder.toString();
    }

}
