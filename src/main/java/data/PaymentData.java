package data;

public record PaymentData(
        String nameOnCard,
        long cardNumber,
        int cvc,
        String expiryMonth,
        String expiryYear
) {}

