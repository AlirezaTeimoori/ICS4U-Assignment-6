



// Import libraries needed:
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


final class Game { // Game class
    
    // Intro Variables:
    private int bet;
    private int cash;
    private Deck deck;
    private List<Card> dealer; // Hand of dealer
    private List<Card> player; // Hand of player

    public Game(int starting_cash) {
        this.reset();
        this.cash = starting_cash;
    }


    // Function to reset the game of Blackjack and prepare it for another round:
    public void reset() {

        // Make all the properties new:
        this.bet = 0;
        this.deck = new Deck();
        this.dealer = new ArrayList<>();
        this.player = new ArrayList<>();

        // Shuffle the deck:
        this.deck.shuffle();

        // Deal the initial 2 cards for each of the sides of the game:
        this.dealer.add(this.getCard());
        this.player.add(this.getCard());
        this.dealer.add(this.getCard());
        this.player.add(this.getCard());
    }

    public int getCash() {
        return this.cash;
    }

    public int getBet() {
        return this.bet;
    }
    
    /**
     * Set a bet for a round of Blackjack
     * @param bet - Integer : amount to bet
     * @return - Boolean : whether placing the bet was successful or not
     */
    public boolean setBet(int bet) {
        boolean success = ((bet != 0) && (bet <= this.getCash()));
        if (success) {
            this.bet = bet;
        }
        return success;
    }
    
    /**
     * Deals a card randomly to the respective player
     * @param dealer - Boolean : Whether or not if its the dealers turn
     */
    public void hitme(boolean dealer) {
        if (dealer) {
            this.dealer.add(this.getCard());
        } else {
            this.player.add(this.getCard());
        }
    }
    
    /**
     * Gets the hand of the respective player 
     * 
     * @param dealer - Boolean : Whether or not if its the dealers turn
     * @return List - The hand of the respective player
     */
    public List getHand(boolean dealer) {
        return dealer ? Collections.unmodifiableList(this.dealer) : Collections.unmodifiableList(this.player);
    }
    
    /**
     * Gets the sum value of playing cards currently in the hand of the respective player, abiding
     * by blackjack rules where aces can be 1 or 11.
     * 
     * @param dealer - Boolean : Whether or not if its dealers turn
     * @return Integer - The total sum values of playing cards in hand of the player
     */
    public Integer getHandTotal(boolean dealer) {
        int aces = 0;
        int total = 0;
        List<Card> target = this.getHand(dealer);
        for (Card card : target) {
            int val = card.getValue();
            if (val == 1) {
                aces += 1;
            } else {
                total += val;
            }
        }
        while (aces > 0) {
            if ((11 + total) <= 21) {
                total += 11;
            } else {
                total += 1;
            }
            aces -= 1;
        }

        return total;
    }

    public void showHandInfo(boolean dealer) {
        if (dealer) {
            System.out.println("Dealer Hand: " + this.dealer + " -> Total: " + this.getHandTotal(dealer));
        } else {
            System.out.println("Dealer Hand: " + this.dealer.get(0) + " ?");
        }
        System.out.println("Your Hand: " + this.player + " -> Total: " + this.getHandTotal(false));
        System.out.println();
    }

    public void showCashInfo() {
        System.out.println("Total Cash: $" + (this.getCash() - this.getBet()));
        System.out.println("Current Bet: $" + (this.getBet()));
        System.out.println();
    }

    public void roundWin() {
        this.cash += this.getBet();
        System.out.println("You won $" + this.getBet());
        System.out.println("New balance: $" + this.getCash());
    }

    public void roundLose() {
        this.cash -= this.getBet();
        System.out.println("You lost: $" + this.getBet());
        System.out.println("New balance: $" + this.getCash());

        if (this.getCash() <= 0) {
            System.out.println();
            System.out.println("You have gone bankrupt! Game Over!");
            System.exit(0);
        }
    }
    
    /**
     * Retrieves a single card randomly from the deck
     * @return Card : A random card from the deck of playing cards
     */
    private Card getCard() {
        return (Card) this.deck.deal(1).get(0);
    }

}