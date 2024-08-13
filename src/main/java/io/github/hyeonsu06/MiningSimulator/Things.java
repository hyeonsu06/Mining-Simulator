package io.github.hyeonsu06.MiningSimulator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;

import static java.io.File.separator;

public class Things {
    final public static String pluginName = "MiningSimulator";
    private static final String path =
            Bukkit.getPluginManager().getPlugin(pluginName).getDataFolder().getAbsolutePath() + separator;
    final public static String prefix =
            "[" + Bukkit.getPluginManager().getPlugin(pluginName).getDescription().getPrefix() + "] ";

    final public static String pickaxeJSON = path + "pickaxes.json";
    final public static String oreJSON = path + "ores.json";
    final public static String materialJSON = path + "materials.json";
    final public static String blockBreakingData = path + "blockBreakingData";

    final public static List<String> rarity = List.of(
            ChatColor.WHITE + "COMMON",
            ChatColor.GREEN + "UNCOMMON",
            ChatColor.BLUE + "RARE",
            ChatColor.DARK_PURPLE + "EPIC",
            ChatColor.GOLD + "LEGENDARY",
            ChatColor.LIGHT_PURPLE + "MYTHIC",
            ChatColor.AQUA + "DIVINE",
            ChatColor.RED + "UNIQUE",
            ChatColor.DARK_RED + "TREMENDOUS",
            ChatColor.GRAY + "FORBIDDEN",
            ChatColor.DARK_BLUE + "ADMIN"
    );

    final public static List<ChatColor> rarityColor = List.of(
            ChatColor.WHITE,
            ChatColor.GREEN,
            ChatColor.BLUE,
            ChatColor.DARK_PURPLE,
            ChatColor.GOLD,
            ChatColor.LIGHT_PURPLE,
            ChatColor.AQUA,
            ChatColor.RED,
            ChatColor.DARK_RED,
            ChatColor.GRAY,
            ChatColor.DARK_BLUE
    );
}