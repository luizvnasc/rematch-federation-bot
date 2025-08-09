package pt.rematch.lusitano.application.discord.common;

import java.util.concurrent.CompletableFuture;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class JDAFactory {

    @ConfigProperty(name = "rematch-federation-bot.discord.token")
    String token;


    private JDA jda;

    @Produces
    public synchronized JDA jdaFactory() {
        if (jda != null) {
            return jda;
        }

        // Cria e faz login do JDA fora de qualquer callback thread
        jda = CompletableFuture.supplyAsync(() ->
                JDABuilder
                        .createLight(token, java.util.EnumSet.noneOf(GatewayIntent.class))
                        .enableIntents(GatewayIntent.GUILD_MEMBERS)
                        .build()
        ).thenApply(j -> {
            try {
                // Opcional: aguardar ficar pronto antes de expor o bean
                j.awaitReady();
                return j;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Interrupted while waiting for JDA to be ready", e);
            }
        }).join();

        return jda;
    }

}
