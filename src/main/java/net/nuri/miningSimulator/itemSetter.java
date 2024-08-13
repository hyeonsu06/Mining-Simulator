package net.nuri.miningSimulator;

import com.google.common.collect.Multimap;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class itemSetter {
    public static void set() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            ItemStack[] items1 = p.getInventory().getContents();
            ItemStack[] items2 = p.getInventory().getArmorContents();
            List<ItemStack[]> items = List.of(items1, items2);
            for (ItemStack[] i1 : items) {
                for (ItemStack i2 : i1) {
                    if (!Objects.isNull(i2)) {
                        boolean defined = NBT.get(i2, nbt -> (Boolean) nbt.getBoolean("defined"));
                        if (!defined) {
                            String id = NBT.get(i2, nbt -> (String) nbt.getString("id"));
                            if (!id.isEmpty()) {
                                Multimap<Material, ItemMeta> value = itemFormer.get(JSONs.pickaxes, id);
                                if (Objects.isNull(value)) value = itemFormer.get(JSONs.materials, id);
                                if (Objects.isNull(value)) {
                                    break;
                                }
                                setItem(value, i2);
                                NBT.modify(i2, nbt -> {
                                    nbt.setBoolean("defined", true);
                                    nbt.setString("id", id);
                                });
                            } else invalidItem(i2);
                        }
                    }
                }
            }
        }
    }

    private static ItemMeta invalidItem(ItemStack i2) {
        ItemMeta meta = i2.getItemMeta();
        String id = NBT.get(i2, nbt -> (String) nbt.getString("id"));
        meta.setDisplayName(ChatColor.RED + "Invalid Item");
        meta.setLore(List.of(
                ChatColor.DARK_GRAY + "This item has been removed because it has",
                ChatColor.DARK_GRAY + "invalid data. If you're think this is an error,",
                ChatColor.DARK_GRAY + "contact the server's administrator.",
                ChatColor.DARK_GRAY + "Item ID was" + "\"" + id + "\""
        ));
        return meta;
    }

    private static void setItem(Multimap<Material, ItemMeta> value, ItemStack i) {
        try {
            i.setType(value.keySet().iterator().next());
        } catch (NullPointerException e) {
            i.setType(Material.BARRIER);
            ItemMeta meta = invalidItem(i);
            i.setItemMeta(meta);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(e.toString());
            }
        }
        try {
            i.setItemMeta(value.values().iterator().next());
        } catch (NullPointerException e) {
            i.setType(Material.BARRIER);
            ItemMeta meta = invalidItem(i);
            i.setItemMeta(meta);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(e.toString());
            }
        }
    }
}
