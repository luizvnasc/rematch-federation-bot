package pt.rematch.lusitano.infrastructure.repository.discord;


import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import pt.rematch.lusitano.domain.player.Player;

@RequiredArgsConstructor
@Slf4j
@ApplicationScoped
public class PlayerNotificationRepository implements pt.rematch.lusitano.domain.player.PlayerNotificationRepository {

    private final JDA jda;

    @Override
    public void notifyPlayerRegistered(Player player){
        jda.
    }
}
