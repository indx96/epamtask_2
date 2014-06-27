package com.epam.textparser.textcomponents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ivan on 6/25/14.
 */
public class ComponentContainer implements TextComponent {
    private ArrayList<TextComponent> components = new ArrayList<TextComponent>();

    public enum Type {TEXT, PARAGRAPH, SENTENCE}

    ;
    private Type type;

    public ComponentContainer(Type type) {
        this.type = type;
    }

    public Type getType() {
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
        if (componentToReplace == componentToAdd) {
            return false;
        }

        if (components.remove(componentToReplace)) {
            components.add(componentToAdd);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        components.forEach((component) -> builder.append(component + " "));
        return builder.toString();
    }
}
