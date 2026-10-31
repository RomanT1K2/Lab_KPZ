package variant5_userpermissions;

public final class PasswordHash {

    private final String value;

    public PasswordHash(String value) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Password hash cannot be null or blank."
            );
        }

        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PasswordHash other = (PasswordHash) obj;

        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}