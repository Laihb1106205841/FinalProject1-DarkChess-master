package model;

import java.awt.*;

/**
 * 这个类主要用于包装Color对象，用于Chess游戏使用。
 * GB：             这里呢，基本上也不怎么管就行
 */
public enum ChessColor {
    BLACK("Black", Color.BLACK), RED("RED", Color.RED), NONE("No Player", Color.WHITE);

    private final String name;
    private final Color color;

    ChessColor(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
