package net.nuri;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static net.nuri.things.*;

public class MiningSimulator extends JavaPlugin {
    private final Plugin plugin = this;

    @Override
    public void onEnable() {
        File pickaxe = new File(pickaxeJSON);
        if (!pickaxe.exists()) {
            plugin.saveResource("pickaxes.json", false);
            Bukkit.getLogger().info(prefix + "Pickaxe config file created");
        } else {
            Bukkit.getLogger().info(prefix + "Pickaxe config file already exists, skipping it");
        }
        File ore = new File(oreJSON);
        if (!ore.exists()) {
            plugin.saveResource("ores.json", false);
            Bukkit.getLogger().info(prefix + "Pickaxe config file created");
        } else {
            Bukkit.getLogger().info(prefix + "Pickaxe config file already exists, skipping it");
        }
        new Loop().runTaskTimer(plugin, 0, 1);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
