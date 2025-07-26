package pt.rematch.lusitano.interfaces.discord.common;

import java.util.List;
import java.util.Set;

import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public interface Command {

    String getName();

    String getDescription();

    InteractionContextType getContextType();

    Set<IntegrationType> getIntegrationType();

    List<OptionData> getOptions();

    public static <E extends Enum<E> & Command> E fromName(Class<E> enumClass, String name) {
        for (E command : enumClass.getEnumConstants()) {
            if (command.getName().equalsIgnoreCase(name)) {
                return command;
            }
        }
        throw new IllegalArgumentException("Unknown command: " + name);
    }

}
