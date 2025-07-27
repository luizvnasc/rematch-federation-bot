package pt.rematch.lusitano.application.discord.common;

import java.util.EnumSet;
import java.util.stream.Collectors;

import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.Startup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import pt.rematch.lusitano.domain.annotation.ServiceListener;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class Factory {

    @ConfigProperty(name = "rematch-federation-bot.discord.token")
    String token;

    @Produces
    public JDA jdaFactory() {
        return JDABuilder.createLight(token, EnumSet.noneOf(GatewayIntent.class)).build();
    }

}
