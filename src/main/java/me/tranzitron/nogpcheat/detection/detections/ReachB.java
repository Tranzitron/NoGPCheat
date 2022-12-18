package me.tranzitron.nogpcheat.detection.detections;

import me.tranzitron.nogpcheat.detection.CheckType;
import me.tranzitron.nogpcheat.detection.Detection;
import me.tranzitron.nogpcheat.user.CheatUser;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ReachB extends Detection {
    public ReachB(String name, CheckType checkType, boolean experimental) {
        super(name, checkType, experimental);
    }
    private final double REACH = 4.4;

    @Override
    public void onCombat(CheatUser user, Entity entity) {
        Player player = user.getPlayer();

        Location attackerLoc = player.getLocation();
        Location defenderLoc = entity.getLocation();

        int pingCompensation = getPingCompensation(player);
        double distance = attackerLoc.distance(defenderLoc);// + pingCompensation;

        if (distance > REACH || !player.hasLineOfSight(entity)) {
            // players cannot reach each other, cancel the event
            if (user.vl.getOrDefault(this, 1) >= REACH) {
                ban(user);
                //return;
            }
            flag(user);
        }

    }

    private int getPingCompensation(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            CraftPlayer craftPlayer = (CraftPlayer) player;
            return craftPlayer.getHandle().ping;
        }
        return 0;
    }

    @Override
    public void onMove(CheatUser user, Location to, Location from) {
    }
}