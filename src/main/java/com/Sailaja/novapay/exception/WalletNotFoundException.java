package com.Sailaja .novapay.exception;

public class WalletNotFoundException extends RuntimeException  {
    public WalletNotFoundException(String message) {
        super(message);
    }
}
