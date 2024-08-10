package net.nuri;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static net.nuri.things.*;

public class loreWriter {
    private static Material returnType(ItemStack i) {
        AtomicReference<String> itemType = new AtomicReference<>("air");
        NBT.get(i, nbt -> {itemType.set(nbt.getString("item_type"));});
        return Material.getMaterial(itemType.get());
    }

    private static List<String> returnLore(ItemStack i) {
        List<String> lore = new ArrayList<>();
        final Double[] speedModifier = {0.0};
        final Double[] fortuneModifier = {0.0};

        NBT.get(i, nbt -> {
            speedModifier[0] = nbt.getDouble("speed_modifier");
            fortuneModifier[0] = nbt.getDouble("fortune_modifier");
        });

        JSONObject mainJSON = Parser(pickaxeJSON);
        AtomicReference<String> itemType;
        itemType = new AtomicReference<>("air");
        NBT.get(i, nbt -> {itemType.set(nbt.getString("item_type"));});
        JSONObject item = (JSONObject) mainJSON.get(itemType.get());
        boolean switching = !Objects.isNull(item);
        if (switching) {
            Double speed = (Double) item.get("speed");
            if (Objects.isNull(speed)) speed = 0.0;
            speed += speedModifier[0];
            Double fortune = (Double) item.get("fortune");
            if (Objects.isNull(fortune)) fortune = 0.0;
            fortune += fortuneModifier[0];

            if (speed != 0.0) {
                if (speed > 0) lore.add(ChatColor.GREEN + "+" + speed + ChatColor.GOLD + " Mining Speed");
                if (speed < 0) lore.add(ChatColor.RED + "-" + speed + ChatColor.GOLD + " Mining Speed");
            }
            if (fortune != 0.0) {
                if (fortune > 0) lore.add(ChatColor.GREEN + "+" + fortune + ChatColor.GOLD + " Mining Fortune");
                if (fortune > 0) lore.add(ChatColor.RED + "-" + fortune + ChatColor.GOLD + " Mining Fortune");
            }
        }
        return lore;
    }

    private static String returnName(ItemStack i) {
        return Parser(pickaxeJSON, i, "name");
    }

    public static void write() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            for (ItemStack i : p.getInventory().getArmorContents()) {
                if (!Objects.isNull(i)) {
                    ItemMeta meta = i.getItemMeta();
                    meta.setDisplayName(returnName(i));
                    meta.setLore(returnLore(i));
                    i.setItemMeta(meta);
                    i.setType(returnType(i));
                }
            }
            for (ItemStack i : p.getInventory().getContents()) {
                if (!Objects.isNull(i)) {
                    ItemMeta meta = i.getItemMeta();
                    meta.setDisplayName(returnName(i));
                    meta.setLore(returnLore(i));
                    i.setItemMeta(meta);
                    i.setType(returnType(i));
                }
            }
        }
    }
}
