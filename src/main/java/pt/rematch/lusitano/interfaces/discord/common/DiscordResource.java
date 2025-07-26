package pt.rematch.lusitano.interfaces.discord.common;

import java.util.stream.Stream;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import pt.rematch.lusitano.interfaces.discord.enums.AthleteCommandEnum;

@Slf4j
@ApplicationScoped
public abstract class DiscordResource extends ListenerAdapter {

    protected final JDA jda;

    protected DiscordResource(JDA jda, AthleteCommandEnum[] commands) {
        this.jda = jda;
        setupCommands(commands);
        jda.addEventListener(this);

    }

    private void setupCommands(Command[] commandDefinitions) {
        var commands = jda.updateCommands();
        Stream.of(commandDefinitions)
                .forEach(definition -> {
                    log.info("Registering command: {} - {}", definition.getName(), definition.getDescription());
                    var command = Commands.slash(definition.getName(), definition.getDescription());
                    definition.getOptions().forEach(option -> {

                        command.addOption(option.getType(), option.getName(), option.getDescription(),
                                option.isRequired());
                    });
                    commands.addCommands(command);
                });
        commands.queue();
    }

}
