package variant5_userpermissions;

public final class AccountIdentity {

    private final String username;
    private final String email;

    public AccountIdentity(String username, String email) {

        this.username = requireNonBlank(username, "Username");
        this.email = requireNonBlank(email, "Email");

        if (!this.email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    private static String requireNonBlank(String value, String fieldName) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " cannot be null or blank."
            );
        }

        return value;
    }
}