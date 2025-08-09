package pt.rematch.lusitano.application.discord.common;

import java.util.List;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import pt.rematch.lusitano.application.discord.enums.PlayerCommandEnum;

@Slf4j
public abstract class DiscordResource extends ListenerAdapter {

    protected final JDA jda;

    protected DiscordResource() {
        this.jda = null; // This constructor is for CDI injection
    }

    public DiscordResource(JDA jda, PlayerCommandEnum[] commands) {
        this.jda = jda;
        setupCommands(commands);
        jda.addEventListener(this);

    }

    private void setupCommands(Command[] commandDefinitions) {
        var commands = jda.updateCommands();
        Stream.of(commandDefinitions)
                .forEach(definition -> {
                    log.info("Registering command: {} - {}", definition.getCommand(), definition.getDescription());
                    var command = Commands.slash(definition.getCommand(), definition.getDescription());
                    definition.getOptions().forEach(command::addOptions);
                    var ignored = commands.addCommands(command);
                });
        commands.queue();
    }



}
