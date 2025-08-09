package pt.rematch.lusitano.domain.player;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import pt.rematch.lusitano.domain.enums.GamePlatformEnum;
import pt.rematch.lusitano.domain.exception.ValidationException;

import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    static final ValidationException INVALID_DISCORD_ID_EXCEPTION = new ValidationException("Discord ID cannot be null or empty");
    static final ValidationException PLATFORM_CANNOT_BE_NULL_EXCEPTION = new ValidationException("Platform cannot be null");
    static final ValidationException INVALID_PLATFORM_ID_EXCEPTION = new ValidationException("Platform ID cannot be null or empty");
    static final ValidationException PREFERRED_POSITION_CANNOT_BE_NULL_EXCEPTION = new ValidationException("Preferred position cannot be null");
    static final ValidationException NUMBER_NOT_DEFINED_OR_INVALID_EXCEPTION = new ValidationException("Number not defined or invalid");

    @Id
    @GeneratedValue
    private UUID id;
    @jakarta.persistence.Column(unique = true)
    String discordId;
    String discordNameTag;
    GamePlatformEnum platform;
    String platformId;
    @Builder.Default
    PlayerStatusEnum status = PlayerStatusEnum.FREE_AGENT;
    @Builder.Default
    int number = -1;
    PlayerPositionEnum preferredPosition;

    public void validate() throws ValidationException {
        if (discordId == null || discordId.isEmpty()) {
            throw INVALID_DISCORD_ID_EXCEPTION;
        }
        if (platform == null) {
            throw PLATFORM_CANNOT_BE_NULL_EXCEPTION;
        }
        if (platformId == null || platformId.isEmpty()) {
            throw INVALID_PLATFORM_ID_EXCEPTION;
        }
        if (preferredPosition == null) {
            throw PREFERRED_POSITION_CANNOT_BE_NULL_EXCEPTION;
        }
        if (number < 0) {
            throw NUMBER_NOT_DEFINED_OR_INVALID_EXCEPTION;
        }
    }
}
