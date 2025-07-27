package pt.rematch.lusitano.application.discord.common;

import java.util.List;
import java.util.Set;

import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public interface Command {

    String getCommand();

    String getDescription();

    InteractionContextType getContextType();

    Set<IntegrationType> getIntegrationType();

    List<OptionData> getOptions();

    public static <E extends Enum<E> & Command> E fromCommand(Class<E> enumClass, String name) {
        for (E command : enumClass.getEnumConstants()) {
            if (command.getCommand().equalsIgnoreCase(name)) {
                return command;
            }
        }
        throw new IllegalArgumentException("Unknown command: " + name);
    }

}
