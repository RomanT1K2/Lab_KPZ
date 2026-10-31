package variant5_userpermissions;

public final class SystemServiceAccount implements Account {

    private final AccountIdentity identity;
    private final ServiceApiKey serviceApiKey;

    public SystemServiceAccount(
            String username,
            String email,
            String serviceApiKey) {

        this.identity = new AccountIdentity(username, email);
        this.serviceApiKey = new ServiceApiKey(serviceApiKey);
    }

    @Override
    public AccountIdentity getIdentity() {
        return identity;
    }

    public boolean isApiKeyValid(String candidate) {
        return serviceApiKey.matches(candidate);
    }
}