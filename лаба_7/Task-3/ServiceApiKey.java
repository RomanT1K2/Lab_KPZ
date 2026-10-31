package variant5_userpermissions;

public final class ServiceApiKey {

    private final String value;

    public ServiceApiKey(String value) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Service API key cannot be null or blank."
            );
        }

        this.value = value;
    }

    public boolean matches(String candidate) {
        return candidate != null && value.equals(candidate);
    }
}