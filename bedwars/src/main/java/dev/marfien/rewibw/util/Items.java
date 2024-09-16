package dev.marfien.rewibw.util;

import dev.marfien.rewibw.shared.ItemBuilder;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class Items {

    // Lobby Items
    public static final ItemStack QUIT_ITEM = ItemBuilder.of(Material.SLIME_BALL).setDisplayName("§eQuitter").asItemStack();
    public static final ItemStack JUMP_AND_RUN_RESET_ITEM = ItemBuilder.of(Material.GLOWSTONE_DUST).setDisplayName("§eRetour au début").asItemStack();
    public static final ItemStack VOTE_ITEM = ItemBuilder.of(Material.PAPER).setDisplayName("§eMap-Voting").asItemStack();
    public static final ItemStack PERKS_ITEM = ItemBuilder.of(Material.DRAGON_EGG).setDisplayName("§ePerks").asItemStack();

    public static final ItemStack SPECTATOR_COMPASS = ItemBuilder.of(Material.COMPASS).setDisplayName("§eVue des joueurs").asItemStack();

    // Shop Items
    public static final ItemStack RED_SANDSTONE = ItemBuilder.of(Material.RED_SANDSTONE, 2).setDisplayName(ChatColor.GRAY + "RED_SANDSTONE").asItemStack();
    public static final ItemStack ENDSTONE = ItemBuilder.of(Material.ENDER_STONE, 1).setDisplayName(ChatColor.GRAY + "ENDSTONE").asItemStack();
    public static final ItemStack IRON_BLOCK = ItemBuilder.of(Material.IRON_BLOCK, 1).setDisplayName(ChatColor.GRAY + "IRON_BLOCK").asItemStack();
    public static final ItemStack CHEST = ItemBuilder.of(Material.CHEST).setDisplayName(ChatColor.GREEN + "CHEST").asItemStack();
    public static final ItemStack TEAM_CHEST = ItemBuilder.of(Material.ENDER_CHEST).setDisplayName(ChatColor.GREEN + "TEAM_CHEST").asItemStack();
    public static final ItemStack LADDER = ItemBuilder.of(Material.LADDER).setDisplayName(ChatColor.GOLD + "LADDER").asItemStack();
    public static final ItemStack TELEPORTER = ItemBuilder.of(Material.FIREWORK).setDisplayName(ChatColor.GOLD + "Teleporter").asItemStack();
    public static final ItemStack MOBILE_SHOP = ItemBuilder.of(Material.ARMOR_STAND).setDisplayName(ChatColor.GOLD + "Mobiler Shop").asItemStack();
    public static final ItemStack TNT = ItemBuilder.of(Material.TNT).setDisplayName(ChatColor.GOLD + "TNT").asItemStack();
    public static final ItemStack PARACHUTE = ItemBuilder.of(Material.MONSTER_EGG).setDamage((short) 93).setDisplayName(ChatColor.GOLD + "PARACHUTE").asItemStack();
    public static final ItemStack RESCUE_PLATFORM = ItemBuilder.of(Material.NETHER_STAR).setDisplayName(ChatColor.GOLD + "RESCUE_PLATFORM").asItemStack();

}
