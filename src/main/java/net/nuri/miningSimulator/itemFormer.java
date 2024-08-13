package net.nuri.miningSimulator;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class itemFormer {
    public static Multimap<Material, ItemMeta> get(JSONObject json, String id) {
        JSONObject itemData = (JSONObject) json.get(id);
        if (itemData == null) return null;
        Material type = Material.getMaterial(itemData.get("item").toString().toUpperCase());
        if (Objects.isNull(type)) return null;
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();

        ChatColor itemRarityColor = things.rarityColor.get(Math.toIntExact((long) itemData.get("rarity") - 1));
        String itemName = itemData.get("name").toString();
        meta.setDisplayName(itemRarityColor + itemName);

        List<String> lore = new ArrayList<>();

        if (json.equals(JSONs.materials)) {
            Double itemWorth = (Double) itemData.get("worth");
            if (!(Objects.isNull(itemWorth) || itemWorth != 0))
                lore.add(ChatColor.GOLD + "Sell price : " + ChatColor.GREEN + itemWorth);

            meta.setLore(lore);
            item.setItemMeta(meta);
        } else if (json.equals(JSONs.pickaxes)) {
            Object JSONItemPower = itemData.get("power");
            if (Objects.nonNull(JSONItemPower)) {
                long itemPower = (Long) JSONItemPower;
                lore.add(ChatColor.DARK_GRAY + "Breaking Power " + itemPower);
                lore.add(" ");
            }
            Object JSONItemSpeed = itemData.get("speed");
            if (Objects.nonNull(JSONItemSpeed)) {
                double itemSpeed = (Double) JSONItemSpeed;
                if (itemSpeed != 0) {
                    if (itemSpeed > 0)
                        lore.add(ChatColor.GOLD + "Mining Speed : " + ChatColor.GREEN + "+" + itemSpeed);
                    if (itemSpeed < 0)
                        lore.add(ChatColor.GOLD + "Mining Speed : " + ChatColor.RED + itemSpeed);
                }
            }
            Object JSONItemFortune = itemData.get("fortune");
            if (Objects.nonNull(JSONItemFortune)) {
                double itemFortune = (Double) JSONItemFortune;
                if (itemFortune != 0) {
                    if (itemFortune > 0)
                        lore.add(ChatColor.GOLD + "Mining Fortune : " + ChatColor.GREEN + "+" + itemFortune);
                    if (itemFortune < 0)
                        lore.add(ChatColor.GOLD + "Mining Fortune : " + ChatColor.RED + itemFortune);
                }
            }
            String itemRarity = things.rarity.get(Math.toIntExact((Long) itemData.get("rarity") - 1));
            lore.add(" ");
            lore.add(itemRarity);

            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            meta.addItemFlags(ItemFlag.HIDE_DYE);
            meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
            meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
            item.setItemMeta(meta);
        }

        Multimap<Material, ItemMeta> value = ArrayListMultimap.create();
        value.put(type, meta);
        return value;
    }
}
