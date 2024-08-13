package io.github.hyeonsu06.MiningSimulator;

import org.bukkit.scheduler.BukkitRunnable;

public class Loop extends BukkitRunnable {
    @Override
    public void run() {
        ItemSetter.set();
    }
}
