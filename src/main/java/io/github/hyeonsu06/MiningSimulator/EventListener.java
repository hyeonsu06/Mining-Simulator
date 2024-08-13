package io.github.hyeonsu06.MiningSimulator;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageAbortEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.json.simple.JSONObject;

import java.util.Objects;

import static net.nuri.JSONs.*;

public class EventListener implements Listener {
    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {
        /*
        File breakingData = new File(Things.blockBreakingData);
        if (!breakingData.exists()) {
            try {
                breakingData.createNewFile();
            } catch (IOException e) {
                Bukkit.getLogger().warning(Things.prefix + e);
            }
        }
         */
        String block = event.getBlock().getType().toString().toLowerCase();
        JSONObject blockData = (JSONObject) ores().get(block);
        long blockHardness = Long.MAX_VALUE;
        long blockPower = Long.MAX_VALUE;
        if (Objects.nonNull(blockData)) {
            blockHardness = (long) blockData.get("hardness");
            blockPower = (long) blockData.get("power");
        }
        ItemStack handItem = event.getPlayer().getInventory().getItemInMainHand();
        if (handItem.getType() == Material.AIR) handItem = new ItemStack(Material.WOODEN_PICKAXE);
        String itemID = NBT.get(handItem, nbt -> (String) nbt.getString("id"));
        JSONObject itemData = (JSONObject) JSONs.pickaxes().get(itemID);
        double itemSpeed = Double.MIN_VALUE;
        long itemPower = Long.MIN_VALUE;
        if (Objects.nonNull(itemData)) {
            itemSpeed = (double) itemData.get("speed");
            itemPower = (long) itemData.get("power");
        }

        double blockDamage = itemSpeed / blockHardness / 20;
        if (blockDamage > 1) blockDamage = 1;

        if (blockPower <= itemPower && blockDamage > 0) {
            float breaking = Float.parseFloat(Double.toString(blockDamage));
            event.getPlayer().sendBlockDamage(
                    event.getBlock().getLocation(),
                    breaking
                    );

        }
    }

    @EventHandler
    public void onBlockDamageAbort(BlockDamageAbortEvent event) {
        event.getPlayer().sendBlockDamage(event.getBlock().getLocation(), 0);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().addPotionEffect(
                new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 255)
        );
        event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100D);
        event.getPlayer().sendMessage(
                ores().toJSONString(),
                materials().toJSONString(),
                pickaxes().toJSONString()
        );
    }

    @EventHandler
    public void onPlayerHungerChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
