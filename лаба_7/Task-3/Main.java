package variant5_userpermissions;

public class Variant5_UserPermissions {

    public static void main(String[] args) {

        // Створення звичайного користувача.
        UserAccount user = new UserAccount(
                "Roman",
                "roman@example.com",
                "old-hash"
        );

        // Створення системного акаунта.
        SystemServiceAccount service = new SystemServiceAccount(
                "PaymentService",
                "service@example.com",
                "secret-api-key"
        );

        // Створення адміністративного сервісу.
        AdminUserMaintenance admin = new AdminUserMaintenance();

        // Адміністративне скидання пароля.
        admin.forcePasswordReset(user, "temporary-hash");

        // Звичайна зміна пароля.
        user.changePassword("temporary-hash", "new-hash");

        // Обидва типи можна використовувати через Account.
        Account account1 = user;
        Account account2 = service;

        System.out.println(
                account1.getIdentity().getUsername()
        );

        System.out.println(
                account2.getIdentity().getUsername()
        );

        // Деактивація користувацького акаунта.
        user.deactivate();

        // Спроба змінити пароль після деактивації.
        try {

            user.changePassword("new-hash", "another-hash");

        } catch (IllegalStateException exception) {

            System.out.println(exception.getMessage());
        }

        // Цей виклик не скомпілюється:
        // admin.forcePasswordReset(service, "temporary-hash");
    }
}