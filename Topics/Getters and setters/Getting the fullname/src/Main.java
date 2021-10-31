
class User {
    private String firstName;
    private String lastName;
    private final String emptyString = "";

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        if (firstName != null && !firstName.equals(emptyString)) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (lastName != null && !lastName.equals(emptyString)) {
            this.lastName = lastName;
        }
    }

    public String getFullName() {
        String str = "";
        if (firstName != null && !firstName.equals(emptyString)) {
            str = str + firstName;
            if (lastName != null && !lastName.equals(emptyString)) {
                str = str + " " + lastName;
            }
        } else if (lastName != null && !lastName.equals(emptyString)) {
            str = str + lastName;
        } else {
            str = "Unknown";
        }
        return str;
    }
}