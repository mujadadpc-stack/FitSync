package com.example.javafx;

import javafx.scene.Scene;
import java.util.prefs.Preferences;

public class ThemeManager {

    public enum Theme {
        DARK("Dark", "theme-dark"),
        LIGHT("Light", "theme-light");

        public final String label;
        public final String cssClass;

        Theme(String label, String cssClass) {
            this.label = label;
            this.cssClass = cssClass;
        }
    }

    private static final Preferences prefs =
            Preferences.userNodeForPackage(ThemeManager.class);
    private static final String PREF_KEY = "selectedTheme";
    private static Theme current = loadSaved();

    private static Theme loadSaved() {
        String saved = prefs.get(PREF_KEY, Theme.DARK.name());
        try { return Theme.valueOf(saved); }
        catch (Exception e) { return Theme.DARK; }
    }

    public static Theme getCurrent() { return current; }

    public static Theme toggle() {
        current = (current == Theme.DARK) ? Theme.LIGHT : Theme.DARK;
        prefs.put(PREF_KEY, current.name());
        return current;
    }

    public static void apply(Scene scene) {
        if (scene == null) return;
        var classes = scene.getRoot().getStyleClass();
        classes.removeIf(c -> c.startsWith("theme-"));
        classes.add(current.cssClass);
    }
}