package pt.rematch.lusitano.domain.athlete;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Athlete {
    String discordId;
    String steamId;
    String xboxId;
    String psnId;
    AthleteStatusEnum status;
}
