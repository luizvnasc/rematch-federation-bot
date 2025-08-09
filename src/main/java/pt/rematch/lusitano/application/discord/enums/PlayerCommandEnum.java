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
import pt.rematch.lusitano.domain.enums.GamePlatformEnum;
import pt.rematch.lusitano.domain.player.PlayerPositionEnum;

@Getter
@RequiredArgsConstructor
public enum PlayerCommandEnum implements Command {

    REGISTER(
            "player-register",
            "Registers an player in the Federation",

            List.of(new OptionData(OptionType.STRING, "platform", "Rematch game platform", true)
                            .addChoice(GamePlatformEnum.STEAM.name(),GamePlatformEnum.STEAM.name())
                            .addChoice(GamePlatformEnum.PSN.name(), GamePlatformEnum.PSN.name())
                            .addChoice(GamePlatformEnum.XBOX.name(), GamePlatformEnum.XBOX.name()),
                    new OptionData(OptionType.STRING, "platform_id", "Rematch game platform ID", true),
                    new OptionData(OptionType.STRING, "preferred_position", "Preferred position", true)
                            .addChoice(PlayerPositionEnum.GOALKEEPER.name(),  PlayerPositionEnum.GOALKEEPER.name())
                            .addChoice(PlayerPositionEnum.DEFENDER.name(), PlayerPositionEnum.DEFENDER.name())
                            .addChoice(PlayerPositionEnum.MIDFIELDER.name(), PlayerPositionEnum.MIDFIELDER.name())
                            .addChoice(PlayerPositionEnum.FORWARD.name(), PlayerPositionEnum.FORWARD.name())
                            .addChoice(PlayerPositionEnum.UNKNOWN.name(),  PlayerPositionEnum.UNKNOWN.name()),
                    new OptionData(OptionType.INTEGER, "preferred_number", "Preferred number", true)
            )
    );

    private final Set<InteractionContextType> contextType = Set.of(InteractionContextType.GUILD);
    private final Set<IntegrationType> integrationType = IntegrationType.ALL;

    private final String command;
    private final String description;

    private final List<OptionData> options;


}