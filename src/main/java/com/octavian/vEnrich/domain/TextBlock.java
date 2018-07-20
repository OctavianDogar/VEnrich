package com.octavian.vEnrich.domain;

import java.util.Objects;

public class TextBlock {
    private String text;
    private Integer left;
    private Integer top;
    private Integer width;
    private Integer height;

    public TextBlock() {
    }

    public TextBlock(String text, Integer left, Integer top, Integer width, Integer height) {
        this.text = text;
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLeft() {
        return this.left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getTop() {
        return this.top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            TextBlock textBlock = (TextBlock)o;
            return Objects.equals(this.text, textBlock.text) && Objects.equals(this.left, textBlock.left) && Objects.equals(this.top, textBlock.top) && Objects.equals(this.width, textBlock.width) && Objects.equals(this.height, textBlock.height);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.text, this.left, this.top, this.width, this.height});
    }

    public String toString() {
        return "TextBlock{text='" + this.text + '\'' + ", left=" + this.left + ", top=" + this.top + ", width=" + this.width + ", height=" + this.height + '}';
    }

}
