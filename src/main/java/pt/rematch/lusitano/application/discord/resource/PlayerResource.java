package pt.rematch.lusitano.application.discord.resource;

import io.quarkus.runtime.Startup;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import pt.rematch.lusitano.application.discord.common.Command;
import pt.rematch.lusitano.application.discord.enums.PlayerCommandEnum;
import pt.rematch.lusitano.domain.exception.AppExeption;
import pt.rematch.lusitano.domain.player.Player;
import pt.rematch.lusitano.domain.player.PlayerPositionEnum;
import pt.rematch.lusitano.domain.player.PlayerService;
import pt.rematch.lusitano.domain.enums.GamePlatformEnum;
import pt.rematch.lusitano.application.discord.common.DiscordResource;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Startup
@Slf4j
public class PlayerResource extends DiscordResource {

    private final PlayerService playerService;



    public PlayerResource(JDA jda, PlayerService playerService) {
        super(jda,PlayerCommandEnum.values());
        log.info("Initializing PlayerResource");
        this.playerService = playerService;

    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        event.deferReply(true).queue(hook -> {
            String response  ;
            try {
                var user = event.getUser();
                log.info("Received command: {} from user: {}", event.getName(), user.getAsTag());
                validateParameters(event);
                var command = Command.fromCommand(PlayerCommandEnum.class, Objects.requireNonNull(event.getName()));

                switch (command) {
                    case REGISTER -> {

                        log.info("Processing registration command for user: {}", user.getGlobalName());
                        playerService.registerPlayer(
                                Player.builder()
                                        .discordId(event.getUser().getId())
                                        .discordNameTag(event.getUser().getAsTag())
                                        .platform(GamePlatformEnum.getByName(Objects.requireNonNull(event.getOption("platform")).getAsString()))
                                        .platformId(Objects.requireNonNull(event.getOption("platform_id")).getAsString())
                                        .preferredPosition(PlayerPositionEnum.valueOf(Objects.requireNonNull(event.getOption("preferred_position")).getAsString()))
                                        .number(Objects.requireNonNull(event.getOption("preferred_number")).getAsInt())
                                        .build()
                        );
                        
                        response = "Player registered successfully!";
                    }
                    default -> {;
                        log.warn("Unknown command: {}", command);
                        response = "Unknown command: " + command;
                    }
                }
            }catch (Exception e) {
                log.error("Error processing the event: {}", event.getName(), e);
                response = "An error occurred while processing your command: " + e.getMessage();
            }
            hook.editOriginal(response).queue();
        });
    }

    private void processCommand(SlashCommandInteractionEvent event, PlayerCommandEnum command) throws AppExeption {

    }

    private void validateParameters(SlashCommandInteractionEvent event) {
        if (event.getOption("platform") == null) {
            throw new IllegalArgumentException("Platform option is required.");
        }
        if (event.getOption("platform_id") == null) {
            throw new IllegalArgumentException("Platform ID option is required.");
        }
        if (event.getOption("preferred_position") == null) {
            throw new IllegalArgumentException("Preferred position option is required.");
        }
        if (event.getOption("preferred_number") == null) {
            throw new IllegalArgumentException("Number option is required.");
        }
    }



}



