package net.nuri;

import org.bukkit.scheduler.BukkitRunnable;

public class Loop extends BukkitRunnable {
    @Override
    public void run() {
        loreWriter.write();
    }
}
