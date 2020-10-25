package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

public class GameImpl implements Game{

    // == constant ==
    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    // == field ==
    private NumberGenerator numberGenerator;
    private int guessCount = 10;
    private int number;
    private int guess;
    private int smallest;
    private int biggest;
    private int remaininGuesses;
    private boolean validNumberRange = true;

    // == init ==
    @PostConstruct
    @Override
    public void reset() {
        smallest = 0;
        guess = 0;
        remaininGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.debug("the number is {}", number);

    }

    // == public methods ==
    public void setNumberGenerator(NumberGenerator numberGenerator){
        this.numberGenerator = numberGenerator;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBigggest() {
        return biggest;
    }

    @Override
    public int getRemainingGuesses() {
        return remaininGuesses;
    }

    @Override
    public void check() {
        checkValidNumberRange();

        if (validNumberRange){
            if (guess > number){
                biggest = guess -1;
            }

            if (guess < number){
                smallest = guess -1;
            }
        }

        remaininGuesses--;
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remaininGuesses <= 0;
    }

    // == private method ==
    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
