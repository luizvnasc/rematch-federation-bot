package pt.rematch.lusitano.application.discord.enums;

import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import pt.rematch.lusitano.application.discord.common.Command;

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
                    new OptionData(OptionType.STRING, "platform_id", "Platform ID of the athlete", true)));

    private final String command;
    private final String description;
    private final InteractionContextType contextType;
    private final Set<IntegrationType> integrationType;
    private final List<OptionData> options;


}