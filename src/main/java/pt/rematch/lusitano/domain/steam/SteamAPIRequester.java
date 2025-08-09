package pt.rematch.lusitano.domain.steam;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pt.rematch.lusitano.domain.exception.AppExeption;


public interface SteamAPIRequester {
    void validateSteamId(String steamId) throws SteamException;
}
