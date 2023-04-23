package xyz.hynse.forgemend;

import org.bukkit.ChatColor;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hynse.forgemend.Util.ExperienceUtil;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ForgeMend extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + " _____ _____                               ");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|   __|     |  ForgeMend                   ");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|   __| | | |  Plugin started successfully!");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|__|  |_|_|_|  @HYNSE                      ");
        getServer().getConsoleSender().sendMessage("");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[ForgeMend] " + "Plugin stopped successfully!");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) || !player.isSneaking()) {
            return;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();

        if (!meta.hasEnchant(Enchantment.MENDING)) {
            return;
        }

        int playerExperience = ExperienceUtil.getPlayerExp(player); // pass player object as argument


        if (item.getDurability() == 0) {
            // Item is already fully repaired, do nothing
            return;
        }

        // Check whether player has bettermending.use permission
        if (!player.hasPermission("bettermending.use")) {
            player.sendMessage("You do not have permission to use the repair feature!");
            return;
        }

        if (playerExperience >= 2) {
            player.giveExp(-2);
            item.setDurability((short) Math.max(item.getDurability() - 1, 0));
            player.updateInventory();
        }
    }
}
