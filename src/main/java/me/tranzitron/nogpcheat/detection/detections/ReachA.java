package me.tranzitron.nogpcheat.detection.detections;

import me.tranzitron.nogpcheat.Util.Util;
import me.tranzitron.nogpcheat.detection.CheckType;
import me.tranzitron.nogpcheat.detection.Detection;
import me.tranzitron.nogpcheat.user.CheatUser;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ReachA extends Detection {
    public ReachA(String name, CheckType checkType, boolean experimental) {
        super(name, checkType, experimental);
    }
    private final double REACH = 4.4;

    @Override
    public void onCombat(CheatUser user, Entity entity) {
        Player player = user.getPlayer();


        // Add a delay to compensate for player ping
        int ping = ((CraftPlayer) player).getHandle().ping;

        // calculate compensated locations for both players
        Location compensatedLocation1 = Util.getCompensatedLocation(entity, ping);
        Location compensatedLocation2 = Util.getCompensatedLocation(player, ping);

        if (compensatedLocation1.distanceSquared(compensatedLocation2) > REACH * REACH) {
            // players cannot reach each other, cancel the event
            if (user.vl.getOrDefault(this, 1) >= 3) {
                ban(user);
                //return;
            }
            flag(user);
        }

    }


    @Override
    public void onMove(CheatUser user, Location to, Location from) {
    }
}