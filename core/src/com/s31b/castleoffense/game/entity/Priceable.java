package com.s31b.castleoffense.game.entity;

/**
 * Everything that has a price should implement this interface
 *
 * @author Goos
 */
public interface Priceable {

    // every child should implement a float variable called price
    float getPrice();
}
