package me.vovari2.denydropflags;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class DenyDropFlags extends JavaPlugin {
    private static String version;
    private static ConsoleCommandSender consoleSender;

    @Override
    public void onLoad(){
        Utils.load();
    }

    @Override
    public void onEnable() {
        version = getPluginMeta().getVersion();
        consoleSender = getServer().getConsoleSender();

        Utils.enable();
        getServer().getPluginManager().registerEvents(new Listeners(), this);

        Text.sendInfoMessage("<green>Plugin v" + version + " enabled!");
    }

    @Override
    public void onDisable() {
        Text.sendInfoMessage("<red>Plugin v" + version + " disabled!");
    }


    public static ConsoleCommandSender getConsoleSender(){
        return consoleSender;
    }
}
