package pt.rematch.lusitano.domain.enums;

/**
 * Enum representing different game platforms.
 * This can be used to categorize athletes based on their gaming platform.
 */
public enum GamePlatformEnum {
    STEAM, PSN, XBOX;


    public static GamePlatformEnum getByName(String platform) {
        try {
            return GamePlatformEnum.valueOf(platform.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid platform: " + platform);
        }
    }
}
