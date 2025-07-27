package pt.rematch.lusitano.infrastructure.steam;

import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
class SteamWebApiClientFactory {
    @ConfigProperty(name = "steam.api.key")
    String apiKey;

    @Produces
    public SteamWebApiClient create() {
        return new SteamWebApiClient.SteamWebApiClientBuilder(apiKey).build();
    }
}
