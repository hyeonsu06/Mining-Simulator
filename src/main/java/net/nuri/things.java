package net.nuri;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

import static java.io.File.separator;

public class things {
    final public static String pluginName = "MiningSimulator";
    final public static String prefix = "[" + Bukkit.getPluginManager().getPlugin(pluginName).getDescription().getPrefix() + "] ";
    final public static String pickaxeJSON = Bukkit.getPluginManager().getPlugin(pluginName).getDataFolder().getAbsolutePath() + separator + "pickaxes.json";
    final public static String oreJSON = Bukkit.getPluginManager().getPlugin(pluginName).getDataFolder().getAbsolutePath() + separator + "ores.json";

    public static String Parser(String json, ItemStack i, String value) {
        JSONParser parser = new JSONParser();
        JSONObject mainJSON = new JSONObject();
        try {
            Reader reader = new FileReader(json);
            mainJSON = (JSONObject) parser.parse(reader);
        } catch (FileNotFoundException e) {
            Bukkit.getLogger().log(Level.WARNING, prefix + "Hey, Where did you put the pickaxes.json? File not found!\n", e);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, prefix + "Huh, is file not accessible?\n", e);
        } catch (ParseException e) {
            Bukkit.getLogger().log(Level.WARNING, prefix + "Is file corrupted? I can't parse this!\n", e);
        }

        AtomicReference<String> itemType;
        itemType = new AtomicReference<>("air");
        NBT.get(i, nbt -> {itemType.set(nbt.getString("item_type"));});
        JSONObject item = (JSONObject) mainJSON.get(itemType.get());

        String name = "";
        if (Objects.nonNull(item)) name = item.get(value).toString();

        return name;
    }
}