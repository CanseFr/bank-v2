package org.bank.bankv2.enums;

public enum Currency {
    USD(1.13),
    GBP(0.86),
    YUAN(7.74);

    private final double rate;

    Currency(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
