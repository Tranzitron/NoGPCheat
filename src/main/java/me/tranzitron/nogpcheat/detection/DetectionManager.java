package me.tranzitron.nogpcheat.detection;

import lombok.val;
import me.tranzitron.nogpcheat.NoGPCheat;
import me.tranzitron.nogpcheat.detection.detections.ExampleCheck;
import me.tranzitron.nogpcheat.detection.detections.ReachA;
import me.tranzitron.nogpcheat.detection.detections.ReachB;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class DetectionManager implements Listener {
    private List<Detection> detections;

    public DetectionManager() {
        detections = new ArrayList<>();
        getServer().getPluginManager().registerEvents(this, NoGPCheat.INSTANCE);
        addChecks();
    }

    private void addChecks() {
        detections.add(new ExampleCheck("Example A", CheckType.OTHER, true));
        detections.add(new ReachA("Reach A", CheckType.COMBAT, true));
        detections.add(new ReachB("Reach B", CheckType.COMBAT, true));
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEvent(EntityDamageByEntityEvent event) {
        val damager = event.getDamager();
        val entity = event.getEntity();
        for (Detection detection : detections) {
            if (damager instanceof Player)
                detection.onCombat(NoGPCheat.INSTANCE.getUserManager().get((Player) damager), entity);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEvent(PlayerMoveEvent event) {
        for (Detection detection : detections) {
            detection.onMove(NoGPCheat.INSTANCE.getUserManager().get(event.getPlayer()), event.getTo(), event.getFrom());
        }
    }
}