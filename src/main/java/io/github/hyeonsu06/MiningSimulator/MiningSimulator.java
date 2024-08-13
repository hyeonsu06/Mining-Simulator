package io.github.hyeonsu06.MiningSimulator;

import com.google.gson.Gson;
import io.github.hyeonsu06.MiningSimulator.Definitions.Materials;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Map;

public class MiningSimulator extends JavaPlugin {
    private final Plugin plugin = this;

    @Override
    public void onEnable() {
        File pickaxe = new File(Things.pickaxeJSON);
        if (!pickaxe.exists()) {
            plugin.saveResource("pickaxes.json", true);
            Bukkit.getLogger().info(Things.prefix + "Pickaxe config file created");
        } else {
            Bukkit.getLogger().info(Things.prefix + "Pickaxe config file already exists, skipping it");
        }
        File ore = new File(Things.oreJSON);
        if (!ore.exists()) {
            plugin.saveResource("ores.json", true);
            Bukkit.getLogger().info(Things.prefix + "Ores config file created");
        } else {
            Bukkit.getLogger().info(Things.prefix + "Ores config file already exists, skipping it");
        }
        File material = new File(Things.materialJSON);
        if (!material.exists()) {
            plugin.saveResource("materials.json", true);
            Bukkit.getLogger().info(Things.prefix + "Materials config file created");
        } else {
            Bukkit.getLogger().info(Things.prefix + "Materials config file already exists, skipping it");
        }

        new Loop().runTaskTimer(plugin, 0, 1);
        Bukkit.getPluginManager().registerEvents(new EventListener(), plugin);

        FileReader materialReader = null;
        FileReader oreReader = null;
        FileReader pickaxeReader = null;

        try {
            materialReader = new FileReader(material);
            oreReader = new FileReader(ore);
            pickaxeReader = new FileReader(pickaxe);
        } catch (FileNotFoundException e) {
            Bukkit.getLogger().warning(e.getMessage());
        }

        Gson gson = new Gson();
        Map<String, Materials> materials = gson.fromJson(Things.materialJSON, Map.class);
        Map<String, Materials> ores = gson.fromJson(Things.oreJSON, Map.class);
        Map<String, Materials> pickaxes = gson.fromJson(Things.pickaxeJSON, Map.class);
    }
}
