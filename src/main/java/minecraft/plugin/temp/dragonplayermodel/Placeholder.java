package minecraft.plugin.temp.dragonplayermodel;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author white
 * @version 1.0
 */
public class Placeholder extends PlaceholderExpansion {
    @Override
    public @NotNull String getAuthor() {
        return "langle__";
        //作者
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
        //版本
    }

    @Override
    public @NotNull String getIdentifier() {
        return "DragonPlayerModel";
        //前缀
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        UUID uuid = player.getUniqueId();
        if (identifier.equals("isModel")) {
            return String.valueOf(DragonPlayerModel.models.contains(uuid));
        }
        return null;
    }

}
