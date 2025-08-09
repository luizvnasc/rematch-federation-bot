package pt.rematch.lusitano.domain.player;

import java.util.UUID;

public interface PlayerRepository {

    Player save(Player player);
    Player findById(UUID id);
}
