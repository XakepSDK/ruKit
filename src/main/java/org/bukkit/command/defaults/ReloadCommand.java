package org.bukkit.command.defaults;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends BukkitCommand {
    public ReloadCommand(String name) {
        super(name);
        this.description = "Reloads the server configuration and plugins";
        this.usageMessage = "/reload [permissions]"; // Paper
        this.setPermission("bukkit.command.reload");
        this.setAliases(Arrays.asList("rl"));
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {

        if (!testPermission(sender)) return true;

        // Paper start - Reload permissions.yml & require confirm
        boolean confirmed = System.getProperty("LetMeReload") != null;
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("permissions")) {
                Bukkit.getServer().reloadPermissions();
                Command.broadcastCommandMessage(sender, ChatColor.GREEN + "Permissions successfully reloaded.");
                return true;
            } else if ("confirm".equalsIgnoreCase(args[0])) {
                confirmed = true;
            }
        }
        if (!confirmed) {
            Command.broadcastCommandMessage(sender, ChatColor.RED + "Are you sure you wish to reload your server? Doing so may cause bugs and memory leaks. It is recommended to restart instead of using /reload. To confirm, please type " + ChatColor.YELLOW + "/reload confirm");
            return true;
        }
        // Paper end

        Command.broadcastCommandMessage(sender, ChatColor.RED + "Please note that this command is not supported and may cause issues when using some plugins.");
        Command.broadcastCommandMessage(sender, ChatColor.RED + "If you encounter any issues please use the /stop command to restart your server.");
        Bukkit.reload();
        Command.broadcastCommandMessage(sender, ChatColor.GREEN + "Reload complete.");

        return true;
    }

    // Spigot Start
    @Override
    public java.util.List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException
    {
        return java.util.Collections.singletonList("permissions"); // Paper
    }
    // Spigot End
}
