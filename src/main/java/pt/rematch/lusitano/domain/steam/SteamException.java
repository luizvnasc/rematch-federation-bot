package pt.rematch.lusitano.domain.steam;

import pt.rematch.lusitano.domain.exception.AppExeption;

public class SteamException extends AppExeption {
    public SteamException(String message) {
        super(message);
    }

    public SteamException(String message, Throwable cause) {
        super(message, cause);
    }

    public SteamException(Throwable cause) {
        super(cause);
    }
}
