package com.octavian.vEnrich.domain;

import java.util.Objects;

public class Response {

    private TextBlock textBlock;

    public Response() {
    }

    public Response(TextBlock textBlock) {
        this.textBlock = textBlock;
    }

    public TextBlock getTextBlock() {
        return this.textBlock;
    }

    public void setTextBlock(TextBlock textBlock) {
        this.textBlock = textBlock;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Response response = (Response)o;
            return Objects.equals(this.textBlock, response.textBlock);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.textBlock});
    }

    public String toString() {
        return "Response{textBlock=" + this.textBlock + '}';
    }
}
