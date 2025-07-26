package pt.rematch.lusitano.interfaces.discord.resource;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import pt.rematch.lusitano.domain.annotation.ServiceListener;
import pt.rematch.lusitano.domain.athlete.AthleteService;
import pt.rematch.lusitano.domain.enums.GamePlatformEnum;
import pt.rematch.lusitano.interfaces.discord.common.DiscordResource;
import pt.rematch.lusitano.interfaces.discord.enums.AthleteCommandEnum;

@ApplicationScoped
@Slf4j
@ServiceListener
public class AthleteResource extends DiscordResource {

    private final AthleteService athleteService;

    public AthleteResource(JDA jda, AthleteService athleteService) {
        super(jda, AthleteCommandEnum.values());
        this.athleteService = athleteService;

    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        var command = AthleteCommandEnum.fromName(event.getName());
        switch (command) {
            case REGISTER -> {
                athleteService.registerAthlete(
                        event.getUser().getId(),
                        GamePlatformEnum.getByName(event.getOption("platform").getAsString()),
                        event.getOption("platformId").getAsString());
                event.reply("Athlete registered successfully!").setEphemeral(true).queue();
            }
            default -> {
                log.warn("Unhandled command: {}", command.getName());
                event.reply("Unknown command: " + command.getName()).setEphemeral(true).queue();
            }
        }

    }

}
