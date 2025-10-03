package data;

public class PaymentDataProvider {

    public static PaymentData getTestCardInformation() {
        return new PaymentData(
                "Mark Calaway",
                1234567890123456L,
                123,
                "05",
                "2030"
        );
    }
}
