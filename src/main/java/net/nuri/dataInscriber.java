package net.nuri;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.atomic.AtomicReference;

public class dataInscriber {
    public static void inscribeData(ItemStack i) {

        AtomicReference<String> itemType = new AtomicReference<>("air");
        NBT.get(i, nbt -> {itemType.set(nbt.getString("item_type"));});
        if (itemType.get().isEmpty()) {
            switch (i.getType()) {
                case WOODEN_PICKAXE:
                    NBT.modify(i, nbt -> {nbt.setString("item_type", "wooden_pickaxe");});
                    break;
                case STONE_PICKAXE:
                    NBT.modify(i, nbt -> {nbt.setString("item_type", "stone_pickaxe");});
                    break;
                case IRON_PICKAXE:
                    NBT.modify(i, nbt -> {nbt.setString("item_type", "iron_pickaxe");});
                    break;
                case GOLDEN_PICKAXE:
                    NBT.modify(i, nbt -> {nbt.setString("item_type", "golden_pickaxe");});
                    break;
                case DIAMOND_PICKAXE:
                    NBT.modify(i, nbt -> {nbt.setString("item_type", "diamond_pickaxe");});
                    break;
                case NETHERITE_PICKAXE:
                    NBT.modify(i, nbt -> {nbt.setString("item_type", "netherite_pickaxe");});
                    break;
                case PRISMARINE_SHARD:
                    NBT.modify(i, nbt -> {nbt.setString("item_type", "advanced_drill");});
                    break;
            }
        }
    }
}
