package variant5_userpermissions;

public interface PasswordResettable {

    void forcePasswordReset(PasswordHash newPasswordHash);
}