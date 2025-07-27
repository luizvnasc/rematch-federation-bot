package pt.rematch.lusitano.application.discord.resource;

import io.quarkus.runtime.Startup;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import pt.rematch.lusitano.application.discord.common.Command;
import pt.rematch.lusitano.domain.athlete.AthleteService;
import pt.rematch.lusitano.domain.enums.GamePlatformEnum;
import pt.rematch.lusitano.application.discord.common.DiscordResource;
import pt.rematch.lusitano.application.discord.enums.AthleteCommandEnum;

import java.util.Objects;


@Startup
@Slf4j
public class AthleteResource extends DiscordResource {

    private final AthleteService athleteService;

    public AthleteResource(JDA jda, AthleteService athleteService) {
        super(jda, AthleteCommandEnum.values());
        log.info("Creating AthleteResource with commands: {}", (Object[]) AthleteCommandEnum.values());
        this.athleteService = athleteService;

    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        event.deferReply(true).queue(hook -> {
            log.info("Received command: {} from user: {}", event.getName(), event.getUser().getId());
            var command = Command.fromCommand(AthleteCommandEnum.class, event.getName());
            String response;
            try {
                switch (command) {
                    case REGISTER -> {
                        athleteService.registerAthlete(
                                event.getUser().getId(),
                                GamePlatformEnum.getByName(Objects.requireNonNull(event.getOption("platform")).getAsString()),
                                Objects.requireNonNull(event.getOption("platform_id")).getAsString());
                        response = "Athlete registered successfully!";
                    }
                    default -> {
                        log.warn("Unhandled command: {}", command.getCommand());
                        response = "Command not recognized or not implemented.";
                    }
                }
            }catch (Exception e) {
                log.error("Error processing command: {}", command.getCommand(), e);
                response = "An error occurred while processing your request: " + e.getMessage();
            }
            hook.editOriginal(response).queue();
        });
    }

}
