package pt.rematch.lusitano.domain.player;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pt.rematch.lusitano.domain.exception.AppExeption;
import pt.rematch.lusitano.domain.steam.SteamAPIRequester;
import pt.rematch.lusitano.domain.steam.SteamException;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class PlayerService {

    private final SteamAPIRequester steamClient;
    private final PlayerNotificationRepository notificationService;
    private final PlayerRepository repository;

    // Define methods that will be implemented by classes that handle athlete
    // operations
    public void registerPlayer(Player player) throws AppExeption {
        log.info("Registering player: {}", player);
        validateOnPlatform(player);
        repository.save(player);

        notificationService.notifyPlayerRegistered(player);

    }

    private void validateOnPlatform(Player player) throws SteamException {
        switch (player.getPlatform()) {
            case STEAM -> {
                steamClient.validateSteamId(player.getPlatformId());
            }
            default -> throw new UnsupportedOperationException("Unsupported platform: " + player.getPlatform().name());
        }
        ;
    }

    public Player getPlayerByDiscord(String discordId) {
        return null;
    }



}
