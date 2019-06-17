
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import java.security.SecureRandom;


class Deck {

    private List<Card> Deck = new ArrayList<>();

    public Deck() {
        Arrays.asList("H", "D", "S", "C").forEach((suit) -> {
            this.Deck.add(new Card("A", suit));
            IntStream.range(2, 10).forEachOrdered(n -> {
                this.Deck.add(new Card(String.valueOf(n), suit));
            });
            this.Deck.add(new Card("J", suit));
            this.Deck.add(new Card("Q", suit));
            this.Deck.add(new Card("K", suit));
        });
    }

    public List getDeck() {
        return Collections.unmodifiableList(this.Deck);
    }
    
    /**
     *  Shuffles the current Deck of playing cards
     */
    public void shuffle() {
        Collections.shuffle(this.Deck);
    }

    /**
     * Randomly gets num_cards and removes them from the Deck
     *
     * @param num_cards - Integer - Number of cards to deal
     * @return List - The cards that have been dealt
     */
    public List deal(int num_cards) {
        SecureRandom random = new SecureRandom();
        List<Card> cards_dealt = new ArrayList<>();

        for (int i = 0; i < num_cards; i++) {
            Card aRandomCard = this.Deck.get(random.nextInt(this.getDeck().size()));
            cards_dealt.add(aRandomCard);
            this.Deck.remove(aRandomCard);
        }

        return cards_dealt;
    }
}