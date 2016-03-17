package com.s31b.logic.interfaces;

/**
 *
 * @author Goos
 */
public interface Pricable {
    /**
     * Removes currency from buying player
     * @return true if payment was made, otherwise false
     */
    boolean Buy();
    
    float getPrice();
    void setPrice(float price);
}
