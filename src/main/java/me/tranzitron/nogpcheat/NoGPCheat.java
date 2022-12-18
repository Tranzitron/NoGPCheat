package me.tranzitron.nogpcheat;

import lombok.Getter;
import me.tranzitron.nogpcheat.command.AlertsCommand;
import me.tranzitron.nogpcheat.detection.DetectionManager;
import me.tranzitron.nogpcheat.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class NoGPCheat extends JavaPlugin {
    public static NoGPCheat INSTANCE;

    private UserManager userManager;
    private DetectionManager detectionManager;

    @Override
    public void onEnable() {
        INSTANCE = this;

        System.out.println("Setting up user manager...");
        userManager = new UserManager();
        if (Bukkit.getOnlinePlayers().size() > 0)
            userManager.addAll();

        System.out.println("Setting up detection manager...");
        detectionManager = new DetectionManager();

        System.out.println("Adding commands...");
        registerCommands();
    }

    private void registerCommands(){
        getCommand("alerts").setExecutor(new AlertsCommand());
    }
}
