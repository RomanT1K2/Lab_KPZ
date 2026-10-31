package variant5_userpermissions;

public class AdminUserMaintenance {

    public void forcePasswordReset(
            PasswordResettable user,
            String temporaryPasswordHash) {

        if (user == null) {
            throw new IllegalArgumentException(
                    "User cannot be null."
            );
        }

        PasswordHash newHash = new PasswordHash(temporaryPasswordHash);

        user.forcePasswordReset(newHash);
    }
}