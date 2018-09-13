package br.com.cajumobile.hangmanGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Game {

    private Terminal terminal;
    private String[] word;
    private int errorCount = 0;
    private String[] hiddenWord;
    private List<String> usedLetters = new ArrayList<>();
    private boolean running = true;

    Game() {
        terminal = new Terminal();
    }

    void start() {
        showWelcomeMessage();
        getWord();
        do {
            showHang();
            askForNewLetter();
            verifyEndGame();
        }while (running);
    }

    private void verifyEndGame() {
        if (errorCount == 6){
            lostGame();
        } else {
            boolean won = true;

            for (String letter : hiddenWord){
                if (letter == null){
                    won  = false;
                }
            }

            if (won){
                wonGame();
            }
        }
    }

    private void wonGame() {
        running=false;
        terminal.writeLine();
        terminal.newLine();
        terminal.write("Congratulations! You guessed the word! " + Arrays.toString(hiddenWord)
                .replace(",","")
                .replace("[", "")
                .replace("]", "")
        );
        showMan();
    }

    private void lostGame() {
        running=false;
        terminal.writeLine();
        terminal.newLine();
        terminal.write("Unfortunately you lost :( ");
        showMan();
    }

    private void askForNewLetter() {
        boolean error = true;
        terminal.writeLine();
        terminal.newLine();
        terminal.write("Type a new Letter: ", false);
        String letter = terminal.readLetter();

        for (int i = 0 ; i < word.length ; i++){
            if (letter.equals(word[i])){
                error = false;
                hiddenWord[i] = word[i];
            }
        }

        if (error){
            errorCount++;
            if (!usedLetters.contains(letter)){
                usedLetters.add(letter);
            }
        }
    }

    private void showHang() {
        terminal.clear();
        showMan();
        showHiddenWord();
        showUsedLetters();
    }

    private void showUsedLetters() {
        terminal.writeLine();
        terminal.newLine();
        terminal.write("Used letters :");
        for (String letter:usedLetters) {
            terminal.write(letter + ", ", false);
        }
        terminal.newLine();
    }

    private void showHiddenWord() {
        terminal.write("\n");
        terminal.write("Hidden word: ");
        terminal.newLine();
        for (String letter : hiddenWord){
            if (letter == null){
                terminal.write("-", false);
            }else {
                terminal.write(letter, false);
            }
        }
        terminal.write("\n");
    }

    private void showMan() {
        ManPart[] manParts = new ManPart[]{
                new ManPart(" 0 ", true),
                new ManPart("/", false),
                new ManPart("|", false),
                new ManPart("\\", true),
                new ManPart("/ ", false),
                new ManPart("\\", true)
        };

        for (int i = 0; i < errorCount; i++) {
            terminal.write(manParts[i].getPart(), manParts[i].getNewLine());
        }
    }

    private void getWord() {
        terminal.write("\n");
        terminal.write("Type the word to be guessed: ", false);
        word = terminal.read().toUpperCase().split("");
        hiddenWord = new String[word.length];
    }

    private void showWelcomeMessage() {
        terminal.writeLine();
        terminal.newLine();
        terminal.write("--- Welcome to hangman game ---");
        terminal.writeLine();
    }
}
