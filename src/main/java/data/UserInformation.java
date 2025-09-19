package data;

public final class UserInformation {
    public final String firstName;
    public final String lastName;
    public final String company;
    public final String address;
    public final String country;
    public final String state;
    public final String city;
    public final String zipCode;
    public final String mobileNumber;

    public UserInformation(String firstName, String lastName, String company, String address, String country, String state, String city, String zipCode, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.mobileNumber = mobileNumber;
    }
}
