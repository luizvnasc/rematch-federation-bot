package pt.rematch.lusitano.domain.config;

import org.eclipse.microprofile.config.inject.ConfigProperties;

import lombok.Data;

@Data
@ConfigProperties(prefix = "rlbot.discord")
public class DiscordConfig {

    private String token;

}
