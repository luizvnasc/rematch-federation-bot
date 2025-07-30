package pt.rematch.lusitano.application.discord.common;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Slf4j
public abstract class DiscordResource extends ListenerAdapter {

    protected final JDA jda;

    public DiscordResource() {
        this.jda = null;
    }

    public DiscordResource(JDA jda) {
        this.jda = jda;
        setupCommands();
        jda.addEventListener(this);

    }

    protected abstract void setupCommands();





}
