package xyz.hynse.forgemend;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hynse.forgemend.Util.ExperienceUtil;

import org.bukkit.Bukkit;
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
        Bukkit.getPluginManager().registerEvents(this, this);
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
        ItemStack item = player.getInventory().getItemInMainHand();

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
            if (item.getEnchantmentLevel(Enchantment.MENDING) == 0) {
                if (item.getDurability() == 0) {
                }
            }
            return;
        }

        if (ExperienceUtil.getPlayerExp(player) >= 1) {
            player.giveExp(-1);
            item.setDurability((short) Math.max(item.getDurability() - 2, 0));
            player.updateInventory();
        }
    }
}
