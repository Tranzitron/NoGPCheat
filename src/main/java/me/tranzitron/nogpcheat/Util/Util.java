package me.tranzitron.nogpcheat.Util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class Util {

    public static Location getCompensatedLocation(Entity entity, long ping) {
        Location currentLocation = entity.getLocation();
        Vector velocity = entity.getVelocity();
        double x = currentLocation.getX() + velocity.getX() * (ping / 1000.0);
        double y = currentLocation.getY() + velocity.getY() * (ping / 1000.0);
        double z = currentLocation.getZ() + velocity.getZ() * (ping / 1000.0);
        return new Location(currentLocation.getWorld(), x, y, z);
    }
}
