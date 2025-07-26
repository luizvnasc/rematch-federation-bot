package pt.rematch.lusitano.interfaces.discord.enums;

import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import pt.rematch.lusitano.interfaces.discord.common.Command;

@Getter
@RequiredArgsConstructor
public enum AthleteCommandEnum implements Command {
    REGISTER(
            "register",
            "Registers an athlete in the Federation",
            InteractionContextType.BOT_DM,
            IntegrationType.ALL,
            List.of(
                    new OptionData(OptionType.STRING, "platform", "Rematch game platform", true),
                    new OptionData(OptionType.STRING, "platformId", "Platform ID of the athlete", true)));

    private final String name;
    private final String description;
    private final InteractionContextType contextType;
    private final Set<IntegrationType> integrationType;
    private final List<OptionData> options;

    public static AthleteCommandEnum fromName(String name) {
        for (AthleteCommandEnum command : values()) {
            if (command.getName().equalsIgnoreCase(name)) {
                return command;
            }
        }
        throw new IllegalArgumentException("No command found with name: " + name);
    }

}