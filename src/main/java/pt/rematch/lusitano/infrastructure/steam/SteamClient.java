package pt.rematch.lusitano.infrastructure.steam;


import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.playersummaries.GetPlayerSummaries;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pt.rematch.lusitano.domain.steam.SteamAPIRequester;
import pt.rematch.lusitano.domain.steam.SteamException;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class SteamClient implements SteamAPIRequester {

    private final SteamWebApiClient client;

    @Override
    public void validateSteamId(String steamId) throws SteamException {

        try {
            if (steamId == null || steamId.isEmpty()) {
                log.error("Steam ID is null or empty for Discord ID: {}", steamId);
                throw new IllegalArgumentException("Steam ID cannot be null or empty");
            }
            var playerSummariesRequest = SteamWebApiRequestFactory.createGetPlayerSummariesRequest(List.of(steamId));
            var playerSummaries = client.<GetPlayerSummaries> processRequest(playerSummariesRequest);
            log.info(playerSummaries.toString());
            if(playerSummaries.getResponse().getPlayers().isEmpty()) {;
                throw new SteamException("Invalid Steam ID: " + steamId);
            }
        } catch (SteamApiException e) {
            throw new SteamException("Error trying to validate the user", e);
        }
    }

    // Implement other methods from SteamAPIRequester as needed
}
