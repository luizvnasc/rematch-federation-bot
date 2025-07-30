package pt.rematch.lusitano.domain.player;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
    String discordId;
    String steamId;
    String xboxId;
    String psnId;
    PlayerStatusEnum status;
}
