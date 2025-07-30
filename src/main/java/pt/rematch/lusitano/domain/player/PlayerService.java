package pt.rematch.lusitano.domain.player;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pt.rematch.lusitano.domain.enums.GamePlatformEnum;
import pt.rematch.lusitano.domain.exception.AppExeption;
import pt.rematch.lusitano.domain.steam.SteamAPIRequester;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class PlayerService {

    private final SteamAPIRequester steamClient;

    // Define methods that will be implemented by classes that handle athlete
    // operations
    public void registerAthlete(String discordId, GamePlatformEnum platform, String platformId) throws AppExeption {
        log.info("Registering athlete with Discord ID: {}, Platform: {}, Platform ID: {}", discordId, platform,
                platformId);
        switch (platform) {
            case STEAM -> {
                registerSteamAthlete(discordId, platformId);
            }
            default -> log.warn("Unsupported platform: {}", platform);
        };

    }

    private void registerSteamAthlete(String discordId, String steamId) throws AppExeption {
        log.debug("Registering Steam athlete with Discord ID: {}, Steam ID: {}", discordId, steamId);
        steamClient.validateSteamId(steamId);

    }

    public Player getAthleteByDiscordId(String discordId) {
        return null;
    }



}
