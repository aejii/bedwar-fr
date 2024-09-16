package dev.marfien.rewibw.shop;

import dev.marfien.rewibw.ResourceType;
import dev.marfien.rewibw.perk.PerkManager;
import dev.marfien.rewibw.shared.InventoryUtil;
import dev.marfien.rewibw.shared.ItemBuilder;
import dev.marfien.rewibw.shared.gui.GuiInventory;
import dev.marfien.rewibw.team.TeamManager;
import dev.marfien.rewibw.util.Items;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@UtilityClass
public class Shop {

    private static final ShopCategory[] CATEGORIES = new ShopCategory[]{
            new ShopCategory("Blocks", Material.RED_SANDSTONE,
                    new StaticShopItem(Items.RED_SANDSTONE, ResourceType.BRONZE.withAmount(1), 32),
                    new StaticShopItem(Items.ENDSTONE, ResourceType.BRONZE.withAmount(7), 64),
                    new StaticShopItem(Items.IRON_BLOCK, ResourceType.SILVER.withAmount(3), 64),
                    new DynamicShopItem(
                            player -> ItemBuilder.of(Material.STAINED_GLASS, TeamManager.getTeam(player).getColor().getDyeColor().getData(), 1)
                                    .setDisplayName(ChatColor.GRAY + "Verre")
                                    .asItemStack(),
                            ResourceType.BRONZE.withAmount(4),
                            64),
                    new StaticShopItem(ItemBuilder.of(Material.GLOWSTONE, 4).setDisplayName(ChatColor.GRAY + "Glowstone").asItemStack(), ResourceType.BRONZE.withAmount(16), 16)
            ),
            new ShopCategory("Armures", Material.CHAINMAIL_CHESTPLATE,
                    new DynamicShopItem(
                            player -> ItemBuilder.of(Material.LEATHER_HELMET)
                                    .setDisplayName(ChatColor.BLUE + "Casque en cuir")
                                    .setLeatherArmorColor(TeamManager.getTeam(player).getColor().getDyeColor().getColor())
                                    .asItemStack(),
                            ResourceType.BRONZE.withAmount(1),
                            1),
                    new DynamicShopItem(
                            player -> ItemBuilder.of(Material.LEATHER_LEGGINGS)
                                    .setDisplayName(ChatColor.BLUE + "Jambières en cuir")
                                    .setLeatherArmorColor(TeamManager.getTeam(player).getColor().getDyeColor().getColor())
                                    .asItemStack(),
                            ResourceType.BRONZE.withAmount(1),
                            1),
                    new DynamicShopItem(
                            player -> ItemBuilder.of(Material.LEATHER_BOOTS)
                                    .setDisplayName(ChatColor.BLUE + "Bottes en cuir")
                                    .setLeatherArmorColor(TeamManager.getTeam(player).getColor().getDyeColor().getColor())
                                    .asItemStack(),
                            ResourceType.BRONZE.withAmount(1),
                            1),
                    new StaticShopItem(ItemBuilder.of(Material.CHAINMAIL_CHESTPLATE).setDisplayName(ChatColor.BLUE + "Brustplatte I").asItemStack(),
                            ResourceType.SILVER.withAmount(1), 1),
                    new StaticShopItem(
                            ItemBuilder.of(Material.CHAINMAIL_CHESTPLATE)
                                    .setDisplayName(ChatColor.BLUE + "Plastron II")
                                    .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                                    .asItemStack(),
                            ResourceType.SILVER.withAmount(3),
                            1),
                    new StaticShopItem(
                            ItemBuilder.of(Material.CHAINMAIL_CHESTPLATE)
                                    .setDisplayName(ChatColor.BLUE + "Plastron III")
                                    .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                                    .asItemStack(),
                            ResourceType.SILVER.withAmount(7),
                            1),
                    new StaticShopItem(
                            ItemBuilder.of(Material.CHAINMAIL_CHESTPLATE)
                                    .setDisplayName(ChatColor.BLUE + "Plastron IV")
                                    .addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                                    .addEnchantment(Enchantment.THORNS, 1)
                                    .asItemStack(),
                            ResourceType.SILVER.withAmount(11),
                            1)
            ),
            new ShopCategory("Pioches", Material.IRON_PICKAXE,
                    new StaticShopItem(ItemBuilder.of(Material.WOOD_PICKAXE).setDisplayName(ChatColor.YELLOW + "Pioche I").asItemStack(), ResourceType.BRONZE.withAmount(7), 1),
                    new StaticShopItem(ItemBuilder.of(Material.STONE_PICKAXE).setDisplayName(ChatColor.YELLOW + "Pioche II").asItemStack(), ResourceType.SILVER.withAmount(2), 1),
                    new StaticShopItem(ItemBuilder.of(Material.IRON_PICKAXE).setDisplayName(ChatColor.YELLOW + "Pioche III").asItemStack(), ResourceType.GOLD.withAmount(1), 1)
            ),
            new ShopCategory("Épées", Material.WOOD_SWORD,
                    new PerkShopItem(
                            PerkManager.STICK_PERK_GROUP,
                            ItemBuilder.of(Material.STICK)
                                    .setDisplayName(ChatColor.RED + "Bâton")
                                    .addEnchantment(Enchantment.KNOCKBACK, 1)
                                    .asItemStack(),
                            ResourceType.BRONZE.withAmount(10),
                            1),
                    new StaticShopItem(ItemBuilder.of(Material.WOOD_SWORD).setDisplayName(ChatColor.RED + "Épée I").asItemStack(), ResourceType.SILVER.withAmount(1), 1),
                    new StaticShopItem(
                            ItemBuilder.of(Material.WOOD_SWORD)
                                    .setDisplayName(ChatColor.RED + "Épée II")
                                    .addEnchantment(Enchantment.DAMAGE_ALL, 1)
                                    .asItemStack(),
                            ResourceType.SILVER.withAmount(3),
                            1),
                    new StaticShopItem(
                            ItemBuilder.of(Material.WOOD_SWORD)
                                    .setDisplayName(ChatColor.RED + "Épée III")
                                    .addEnchantment(Enchantment.DAMAGE_ALL, 2)
                                    .asItemStack(),
                            ResourceType.SILVER.withAmount(5),
                            1),
                    new StaticShopItem(
                            ItemBuilder.of(Material.IRON_SWORD)
                                    .setDisplayName(ChatColor.RED + "Épée IV")
                                    .addEnchantment(Enchantment.DAMAGE_ALL, 1)
                                    .addEnchantment(Enchantment.KNOCKBACK, 1)
                                    .asItemStack(),
                            ResourceType.GOLD.withAmount(5),
                            1)

            ),
            new ShopCategory("Arcs", Material.BOW,
                    new StaticShopItem(
                            ItemBuilder.of(Material.BOW)
                                    .setDisplayName(ChatColor.DARK_PURPLE + "Arc I")
                                    .setDurability((short) 120)
                                    .addEnchantment(Enchantment.ARROW_INFINITE, 1)
                                    .asItemStack(),
                            ResourceType.GOLD.withAmount(3),
                            1),
                    new StaticShopItem(
                            ItemBuilder.of(Material.BOW)
                                    .setDisplayName(ChatColor.DARK_PURPLE + "Arc II")
                                    .setDurability((short) 120)
                                    .addEnchantment(Enchantment.ARROW_INFINITE, 1)
                                    .addEnchantment(Enchantment.ARROW_DAMAGE, 1)
                                    .asItemStack(),
                            ResourceType.GOLD.withAmount(7),
                            1),
                    new StaticShopItem(
                            ItemBuilder.of(Material.BOW)
                                    .setDisplayName(ChatColor.DARK_PURPLE + "Arc III")
                                    .setDurability((short) 120)
                                    .addEnchantment(Enchantment.ARROW_INFINITE, 1)
                                    .addEnchantment(Enchantment.ARROW_DAMAGE, 2)
                                    .addEnchantment(Enchantment.ARROW_KNOCKBACK, 1)
                                    .asItemStack(),
                            ResourceType.GOLD.withAmount(11),
                            1),
                    Shoppable.SPACER,
                    new StaticShopItem(ItemBuilder.of(Material.ARROW).setDisplayName(ChatColor.DARK_PURPLE + "Flèche").asItemStack(), ResourceType.GOLD.withAmount(1), 1)
            ),
            new ShopCategory("Nourriture", Material.CAKE,
                    new StaticShopItem(ItemBuilder.of(Material.APPLE).setDisplayName(ChatColor.DARK_GREEN + "Pomme").asItemStack(), ResourceType.BRONZE.withAmount(2), 64),
                    new StaticShopItem(ItemBuilder.of(Material.COOKED_BEEF).setDisplayName(ChatColor.DARK_GREEN + "Streak").asItemStack(), ResourceType.BRONZE.withAmount(4), 64),
                    new StaticShopItem(ItemBuilder.of(Material.CAKE).setDisplayName(ChatColor.DARK_GREEN + "Gâteau").asItemStack(), ResourceType.SILVER.withAmount(1), 64),
                    Shoppable.SPACER,
                    new StaticShopItem(ItemBuilder.of(Material.GOLDEN_APPLE).setDisplayName(ChatColor.DARK_GREEN + "Pomme dorée").asItemStack(), ResourceType.GOLD.withAmount(2), 64)
            ),
            new ShopCategory("Coffres", Material.ENDER_CHEST,
                    new StaticShopItem(Items.CHEST, ResourceType.SILVER.withAmount(2), 64),
                    new StaticShopItem(Items.TEAM_CHEST, ResourceType.GOLD.withAmount(1), 64)
            ),
            new ShopCategory("Potions", Material.POTION, item -> item.setDurability((short) 8230),
                    new StaticShopItem(
                            ItemBuilder.of(Material.POTION, (short) 8261)
                                    .setDisplayName(ChatColor.LIGHT_PURPLE + "Soin I")
                                    .addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0))
                                    .asItemStack(),
                            ResourceType.SILVER.withAmount(5),
                            1),
                    new StaticShopItem(
                            ItemBuilder.of(Material.POTION, (short) 8229)
                                    .setDisplayName(ChatColor.LIGHT_PURPLE + "Soin II")
                                    .addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1))
                                    .asItemStack(),
                            ResourceType.SILVER.withAmount(7),
                            1),
                    new StaticShopItem(
                            ItemBuilder.of(Material.POTION, (short) 8203)
                                    .setDisplayName(ChatColor.LIGHT_PURPLE + "Jumpboost")
                                    .addCustomPotionEffect(new PotionEffect(PotionEffectType.JUMP, 3600, 0))
                                    .asItemStack(),
                            ResourceType.SILVER.withAmount(10),
                            1),
                    new StaticShopItem(
                            ItemBuilder.of(Material.POTION, (short) 8201)
                                    .setDisplayName(ChatColor.LIGHT_PURPLE + "Force")
                                    .addCustomPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 0))
                                    .asItemStack(),
                            ResourceType.GOLD.withAmount(5),
                            1)
            ),
            new ShopCategory("Extras", Material.EMERALD,
                    new StaticShopItem(Items.LADDER, ResourceType.BRONZE.withAmount(4), 64),
                    new StaticShopItem(Items.TELEPORTER, ResourceType.SILVER.withAmount(10), 64),
                    new StaticShopItem(Items.MOBILE_SHOP, ResourceType.SILVER.withAmount(12), 64),
                    new StaticShopItem(Items.TNT, ResourceType.GOLD.withAmount(5), 64),
                    new StaticShopItem(Items.PARACHUTE, ResourceType.GOLD.withAmount(2), 64),
                    new StaticShopItem(Items.RESCUE_PLATFORM, ResourceType.GOLD.withAmount(3), 64),
                    new StaticShopItem(ItemBuilder.of(Material.ENDER_PEARL).setDisplayName(ChatColor.GOLD + "Ender peal").asItemStack(), ResourceType.GOLD.withAmount(14), 64),
                    new StaticShopItem(ItemBuilder.of(Material.WEB).setDisplayName(ChatColor.GOLD + "toile d'araignée").asItemStack(), ResourceType.BRONZE.withAmount(20), 64)
            )
    };

    private static final GuiInventory NO_CATEGORY;

    static {
        int[] categoryIndexes = InventoryUtil.calcCenterIndexes(CATEGORIES.length);
        int categoryRows = InventoryUtil.rowsNeeded(CATEGORIES.length);
        ShopCategory[] indexedShopCategories = new ShopCategory[categoryRows * 9];

        for (int i = 0; i < CATEGORIES.length; i++) {
            indexedShopCategories[categoryIndexes[i]] = CATEGORIES[i];
        }

        for (ShopCategory category : CATEGORIES) {
            category.init(indexedShopCategories);
        }

        NO_CATEGORY = new GuiInventory(categoryRows + 1, ChatColor.DARK_AQUA + "BedWars Shop");
        NO_CATEGORY.setContents(indexedShopCategories);
    }

    public static void open(Player player) {
        NO_CATEGORY.openTo(player);
    }
}
