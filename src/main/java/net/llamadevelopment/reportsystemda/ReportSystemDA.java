package net.llamadevelopment.reportsystemda;

import cn.nukkit.plugin.PluginBase;
import lombok.Getter;
import net.llamadevelopment.reportsystemda.components.language.Language;
import net.llamadevelopment.reportsystemda.listeners.EventListener;

public class ReportSystemDA extends PluginBase {

    @Getter
    private static ReportSystemDA instance;

    @Override
    public void onEnable() {
        instance = this;
        try {
            this.saveDefaultConfig();
            Language.init();
            this.loadPlugin();
            this.getLogger().info("§aReportSystem-DA successfully started.");
        } catch (Exception e) {
            e.printStackTrace();
            this.getLogger().error("§4Failed to load ReportSystem-DA.");
        }
    }

    private void loadPlugin() {
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
    }
}
