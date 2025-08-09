package pt.rematch.lusitano.infrastructure.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pt.rematch.lusitano.domain.player.Player;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@ApplicationScoped
public class PlayerRepository implements pt.rematch.lusitano.domain.player.PlayerRepository, PanacheRepository<Player> {

    @Transactional
    @Override
    public Player save(Player player) {
        persist(player);
        return player;
    }

    @Override
    public Player findById(UUID id) {
        return find("id", id)
                .firstResultOptional()
                .orElseThrow(() -> new IllegalArgumentException("Player not found with id: " + id));
    }
}
