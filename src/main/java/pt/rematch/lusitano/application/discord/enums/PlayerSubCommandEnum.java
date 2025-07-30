package pt.rematch.lusitano.application.discord.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum PlayerSubCommandEnum  {
    REGISTER("register");

    private final String command;

    public static PlayerSubCommandEnum fromCommand(String command) {
        for (PlayerSubCommandEnum subCommand : PlayerSubCommandEnum.values()) {
            if (subCommand.getCommand().equalsIgnoreCase(command)) {
                return subCommand;
            }
        }
        throw new IllegalArgumentException("Unknown command: " + command);
    }
}