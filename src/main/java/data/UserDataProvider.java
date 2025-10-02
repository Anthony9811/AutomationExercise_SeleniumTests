package data;

public class UserDataProvider {

    public static UserInformation createValidUser() {
        return new UserInformation(
                "Mark",
                "Calaway",
                "Dead Man Inc",
                "Death Valley, WWE",
                "United States",
                "Connecticut",
                "Stamford",
                "50301",
                "12345678"
        );
    }
}
