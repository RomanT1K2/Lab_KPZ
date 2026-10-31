package variant5_userpermissions;

public final class UserAccount implements Account, PasswordResettable {

    private final AccountIdentity identity;
    private PasswordHash passwordHash;
    private boolean active = true;

    public UserAccount(String username, String email, String passwordHash) {

        this.identity = new AccountIdentity(username, email);
        this.passwordHash = new PasswordHash(passwordHash);
    }

    @Override
    public AccountIdentity getIdentity() {
        return identity;
    }

    public boolean isActive() {
        return active;
    }

    // Звичайна зміна пароля з перевіркою старого пароля.
    public void changePassword(String oldPasswordHash, String newPasswordHash) {

        ensureActive();

        PasswordHash oldHash = new PasswordHash(oldPasswordHash);
        PasswordHash newHash = new PasswordHash(newPasswordHash);

        if (!passwordHash.equals(oldHash)) {
            throw new IllegalArgumentException("Invalid old password.");
        }

        this.passwordHash = newHash;

        System.out.println(
                "Password updated for user " + identity.getUsername()
        );
    }

    // Адміністративне скидання пароля.
    @Override
    public void forcePasswordReset(PasswordHash newPasswordHash) {

        ensureActive();

        if (newPasswordHash == null) {
            throw new IllegalArgumentException(
                    "New password hash cannot be null."
            );
        }

        this.passwordHash = newPasswordHash;

        System.out.println(
                "Password reset by administrator for "
                        + identity.getUsername()
        );
    }

    // Деактивація облікового запису.
    public void deactivate() {

        ensureActive();

        this.active = false;

        System.out.println(
                "Account " + identity.getUsername() + " deactivated."
        );
    }

    // Перевірка стану облікового запису.
    private void ensureActive() {

        if (!active) {
            throw new IllegalStateException(
                    "Cannot modify an inactive account."
            );
        }
    }
}