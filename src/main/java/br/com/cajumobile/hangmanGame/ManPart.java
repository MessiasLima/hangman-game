package br.com.cajumobile.hangmanGame;

public class ManPart {
    private String part;
    private Boolean newLine;

    public ManPart(String part, Boolean newLine) {
        this.part = part;
        this.newLine = newLine;
    }

    public String getPart() {
        return part;
    }

    public Boolean getNewLine() {
        return newLine;
    }
}
