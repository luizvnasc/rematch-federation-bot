package pt.rematch.lusitano.domain.athlete;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pt.rematch.lusitano.domain.enums.GamePlatformEnum;

@ApplicationScoped
@NoArgsConstructor
@Slf4j

public class AthleteService {

    // Define methods that will be implemented by classes that handle athlete
    // operations
    public void registerAthlete(String discordId, GamePlatformEnum platform, String platformId) {
        log.info("Registering athlete with Discord ID: {}, Platform: {}, Platform ID: {}", discordId, platform,
                platformId);
        // Logic to register the athlete goes here
        // This could involve saving the athlete's information to a database or an
        // in-memory store
    }

    public Athlete getAthleteByDiscordId(String discordId) {
        return null;
    }

}
