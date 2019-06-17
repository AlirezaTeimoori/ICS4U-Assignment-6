

class Card {

    private final String Face;
    private final String Suit;

    /**
     *
     * @param Face - Either String value A, J, Q, K or a String value integer 2
     * - 10 (inclusive)
     * @param Suit - String value H, D, S or C
     */
    public Card(String Face, String Suit) {
        this.Face = Face;
        this.Suit = Suit;
    }

    /**
     *
     * @return String - Face value of the card
     */
    public String getFace() {
        return this.Face;
    }

    /**
     *
     * @return String - Suit value of the card
     */
    public String getSuit() {
        return this.Suit;
    }

    /**
     * Get the numerical face value of the card
     *
     * @return Integer - Face value of the card
     */
    public Integer getValue() {
        switch (this.getFace()) {
            case "A":
                return 1;
            case "J":
                return 10;
            case "Q":
                return 10;
            case "K":
                return 10;
            default:
                return Integer.parseInt(this.getFace());
        }
    }

    @Override
    public String toString() {
        return this.getFace() + this.getSuit();
    }

}