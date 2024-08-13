package net.nuri.miningSimulator;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class MiningSimulator extends JavaPlugin {
    private final Plugin plugin = this;

    @Override
    public void onEnable() {
        File pickaxe = new File(things.pickaxeJSON);
        if (!pickaxe.exists()) {
            plugin.saveResource("pickaxes.json", true);
            Bukkit.getLogger().info(things.prefix + "Pickaxe config file created");
        } else {
            Bukkit.getLogger().info(things.prefix + "Pickaxe config file already exists, skipping it");
        }
        File ore = new File(things.oreJSON);
        if (!ore.exists()) {
            plugin.saveResource("ores.json", true);
            Bukkit.getLogger().info(things.prefix + "Ores config file created");
        } else {
            Bukkit.getLogger().info(things.prefix + "Ores config file already exists, skipping it");
        }
        File material = new File(things.materialJSON);
        if (!material.exists()) {
            plugin.saveResource("materials.json", true);
            Bukkit.getLogger().info(things.prefix + "Materials config file created");
        } else {
            Bukkit.getLogger().info(things.prefix + "Materials config file already exists, skipping it");
        }

        new Loop().runTaskTimer(plugin, 0, 1);
        Bukkit.getPluginManager().registerEvents(new Events(), plugin);
    }

    @Override
    public void onDisable() {

    }
}
