package com.example.oneplayer;

public class Themes {
    private static String theme = "default";
    private static boolean dark = false;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean getDark() {
        return dark;
    }

    public void setDark(boolean b) {
        this.dark = b;
    }

}
