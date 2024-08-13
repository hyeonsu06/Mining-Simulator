package net.nuri.miningSimulator;

import org.bukkit.scheduler.BukkitRunnable;

public class Loop extends BukkitRunnable {
    @Override
    public void run() {
        itemSetter.set();
    }
}
