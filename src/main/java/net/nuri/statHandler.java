package net.nuri;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class statHandler {
    private Double getSpeed(ItemStack i) {

    }
    public void sum() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            for (ItemStack i : p.getInventory().getArmorContents()) {
                if (!Objects.isNull(i)) {
                    
                }
            }
            for (ItemStack i : p.getInventory().getContents()) {
                if (!Objects.isNull(i)) {

                }
            }
        }
    }
    }
}
