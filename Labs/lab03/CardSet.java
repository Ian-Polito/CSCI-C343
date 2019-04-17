public interface CardSet {

    //create a full set of cards (with 52 cards; no Jokers)
    public void initialize();

    //draw a card, and return the card as string. for example "2S" (two of Spades)
    //using a single-letter representation for suits
    //S (spades) C (clubs) H (hearts) D (diamonds)
    public String drawRandomCard();
}
