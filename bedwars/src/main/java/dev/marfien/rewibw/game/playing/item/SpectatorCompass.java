package dev.marfien.rewibw.game.playing.item;

import dev.marfien.rewibw.Message;
import dev.marfien.rewibw.RewiBWPlugin;
import dev.marfien.rewibw.shared.ItemBuilder;
import dev.marfien.rewibw.shared.usable.ConsumeType;
import dev.marfien.rewibw.shared.usable.UsableItemInfo;
import dev.marfien.rewibw.team.GameTeam;
import dev.marfien.rewibw.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.NumberConversions;

public class SpectatorCompass extends UsableItemInfo {

    private static final Inventory SPECTATOR_INVENTORY = Bukkit.createInventory(
            null,
            Math.min(NumberConversions.ceil(RewiBWPlugin.getPluginConfig().getTeams().getMaxPlayers() / 9D), 6) * 9,
            "§8Liste des joueurs"
    );

    public SpectatorCompass() {
        super(ConsumeType.NONE, event -> event.getPlayer().openInventory(SPECTATOR_INVENTORY));
    }

    public static void refreshInventory() {
        ItemStack[] contents = new ItemStack[SPECTATOR_INVENTORY.getSize()];
        int slot = 0;
        for (GameTeam team : TeamManager.getTeams()) {
            for (Player player : team.getMembers()) {
                contents[slot++] = ItemBuilder.of(Material.SKULL_ITEM)
                        .setDamage((short) 3)
                        .setSkullOwner(player.getName())
                        .setDisplayName(player.getDisplayName())
                        .setLore(" ", " §fClique pour te téléporter")
                        .asItemStack();
            }
        }
        SPECTATOR_INVENTORY.setContents(contents);
    }

    public static class SpectatorCompassListener implements Listener {

        @EventHandler
        private void onClick(InventoryClickEvent event) {
            Inventory inventory = event.getClickedInventory();

            if (inventory == null) return;
            if (!inventory.getTitle().equals(SPECTATOR_INVENTORY.getTitle())) return;
            event.setCancelled(true);

            ItemStack item = event.getCurrentItem();
            if (item == null) return;
            ItemMeta meta = item.getItemMeta();

            if (!(meta instanceof SkullMeta)) return;
            SkullMeta skullMeta = (SkullMeta) meta;

            if (!skullMeta.hasOwner()) return;
            Player clicker = (Player) event.getWhoClicked();
            Player target = Bukkit.getPlayerExact(skullMeta.getOwner());

            if (target == null) {
                clicker.closeInventory();
                clicker.sendMessage(RewiBWPlugin.PREFIX + Message.PLAYER_NOT_FOUND);
                return;
            }

            event.getWhoClicked().teleport(target);
        }

    }
}
