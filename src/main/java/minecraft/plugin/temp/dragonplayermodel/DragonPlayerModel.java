package minecraft.plugin.temp.dragonplayermodel;

import eos.moe.dragoncore.api.ModelAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author white
 * @version 1.0
 */
public class DragonPlayerModel extends JavaPlugin {
    public static final Set<UUID> models = new HashSet<>();
    private static Placeholder placeholder;

    @Override
    public void onEnable() {
        placeholder = new Placeholder();
        placeholder.register();
    }

    @Override
    public void onDisable() {
        placeholder.unregister();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("/dpm model set 玩家 模型名称 §7设置模型");
            sender.sendMessage("/dpm model remove 玩家 §7移除模型");
            sender.sendMessage("/dpm action set 玩家 动画名 过渡时间 §7播放模型动画");
            sender.sendMessage("/dpm action remove 玩家 动画名 过渡时间 §7停止播放动画");
            return false;
        }
        String cmd = args[0];
        if (cmd.equals("model")) {
            if (args.length == 1) {
                sender.sendMessage("缺少执行方式");
                return false;
            }
            if (args.length == 2) {
                sender.sendMessage("缺少玩家");
                return false;
            }
            Player player = Bukkit.getPlayer(args[2]);
            if (player == null) {
                sender.sendMessage("玩家不存在");
                return false;
            }
            UUID uuid = player.getUniqueId();
            if (args[1].equals("set")) {
                if (args.length == 3) {
                    sender.sendMessage("缺少模型名称");
                    return false;
                }
                ModelAPI.setEntityModel(uuid, args[3]);
                models.add(uuid);
                return true;
            }
            if (args[1].equals("remove")) {
                ModelAPI.removeEntityModel(uuid);
                models.remove(uuid);
                return true;
            }
        } else if (cmd.equals("action")) {
            if (args.length == 1) {
                sender.sendMessage("缺少执行方式");
                return false;
            }
            if (args.length == 2) {
                sender.sendMessage("缺少玩家");
                return false;
            }
            Player player = Bukkit.getPlayer(args[2]);
            if (player == null) {
                sender.sendMessage("玩家不存在");
                return false;
            }
            if (args.length == 3) {
                sender.sendMessage("缺少模型名称");
                return false;
            }
            if (args.length == 4) {
                sender.sendMessage("缺少过渡时间");
                return false;
            }
            int time;
            try {
                time = Integer.parseInt(args[4]);
            } catch (NumberFormatException e) {
                sender.sendMessage("过渡时间必须为整数");
                return false;
            }
            if (args[1].equals("set")) {
                ModelAPI.setEntityAnimation(player, args[3], time);
                return true;
            }
            if (args[1].equals("remove")) {
                ModelAPI.removeEntityAnimation(player, args[3], time);
                return true;
            }
        } else {
            sender.sendMessage("无效子命令");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

}
