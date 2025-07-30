package pt.rematch.lusitano.application.discord.resource;

import io.quarkus.runtime.Startup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import pt.rematch.lusitano.application.discord.enums.PlayerSubCommandEnum;
import pt.rematch.lusitano.domain.player.PlayerPositionEnum;
import pt.rematch.lusitano.domain.player.PlayerService;
import pt.rematch.lusitano.domain.enums.GamePlatformEnum;
import pt.rematch.lusitano.application.discord.common.DiscordResource;

import java.util.Objects;


@Startup
@Slf4j
public class PlayerResource extends DiscordResource {

    private final PlayerService athleteService;



    public PlayerResource(JDA jda, PlayerService athleteService) {
        super(jda);
        log.info("Initializing PlayerResource");
        this.athleteService = athleteService;

    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        event.deferReply(true).queue(hook -> {
            log.info("Received command: {} from user: {}", event.getName(), event.getUser().getAsTag());
            var command = PlayerSubCommandEnum.fromCommand(Objects.requireNonNull(event.getOption("command")).getAsString());
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
            } catch (Exception e) {
                log.error("Error processing command: {}", command.getCommand(), e);
                response = "An error occurred while processing your request: " + e.getMessage();
            }
            hook.editOriginal(response).queue();
        });
    }

    @Override
    protected void setupCommands() {

        var commands = jda.updateCommands();
        var command = Commands.slash("player", "Manage player registrations");
        command.addOptions(new OptionData(OptionType.STRING, "command", "Sub-command to execute", true)
                .addChoice("register", PlayerSubCommandEnum.REGISTER.getCommand()));
        command.addOptions(new OptionData(OptionType.STRING, "platform", "Game platform", false)
                .addChoice(GamePlatformEnum.STEAM.name(), GamePlatformEnum.STEAM.name())
                .addChoice(GamePlatformEnum.PSN.name(), GamePlatformEnum.PSN.name())
                .addChoice(GamePlatformEnum.XBOX.name(), GamePlatformEnum.XBOX.name()));
        command.addOptions(new OptionData(OptionType.STRING, "platform_id", "Platform-specific ID", false));
        command.addOptions(new OptionData(OptionType.STRING, "vrg", "vrg profile link", false));
        command.addOptions(new OptionData(OptionType.STRING, "preferred_position", "preferred position", false)
                .addChoice(PlayerPositionEnum.FORWARD.name(), PlayerPositionEnum.FORWARD.name())
                .addChoice(PlayerPositionEnum.MIDFIELDER.name(), PlayerPositionEnum.MIDFIELDER.name())
                .addChoice(PlayerPositionEnum.DEFENDER.name(), PlayerPositionEnum.DEFENDER.name())
                .addChoice(PlayerPositionEnum.GOALKEEPER.name(), PlayerPositionEnum.GOALKEEPER.name())
                .addChoice(PlayerPositionEnum.ALL.name(), PlayerPositionEnum.ALL.name())
                .addChoice(PlayerPositionEnum.UNKNOWN.name(), PlayerPositionEnum.UNKNOWN.name())
        );
        command.addOptions(new OptionData(OptionType.INTEGER, "number", "T-shirt preferred number", false));
        var ignored = commands.addCommands(command);
        commands.queue();
    }
}



