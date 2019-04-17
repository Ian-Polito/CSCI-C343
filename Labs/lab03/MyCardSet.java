import java.util.*;

public class MyCardSet implements CardSet {

    private String[] deck;

    public MyCardSet() {
        initialize();
    }

    public void initialize() {
        //create an array of Strings, represented as cards
        this.deck = new String[52];
        int j = 1;
        for (int i = 0; i < 13; i++) {
            deck[i] = j + "S";
            j++;
        }
        j = 1;
        for (int i = 13; i < 26; i++) {
            deck[i] = j + "C";
            j++;
        }
        j = 1;
        for (int i = 26; i < 39; i++) {
            deck[i] = j + "H";
            j++;
        }
        j = 1;
        for (int i = 39; i < 52; i++) {
            deck[i] = j + "D";
            j++;
        }
    }

    public String drawRandomCard() {
        Random r = new Random();
        int random = r.nextInt(52);

        return deck[random];
    }
}
