package me.squid.eonhomes.commands;

import me.squid.eonhomes.EonHomes;
import me.squid.eonhomes.Home;
import me.squid.eonhomes.utils.HomeManager;
import me.squid.eonhomes.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public class HomesCommand implements CommandExecutor {

    EonHomes plugin;

    public HomesCommand(EonHomes plugin) {
        this.plugin = plugin;
        plugin.getCommand("homes").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            Set<String> list = HomeManager.getHomes(p);

            p.sendMessage(Utils.chat(EonHomes.prefix + "&7Homes:"));
            for (String home : list) {
                p.sendMessage(Utils.chat("&7   - " + home));
            }
        }

        return true;
    }
}