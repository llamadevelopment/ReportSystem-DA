package net.llamadevelopment.reportsystemda.listeners;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import net.llamadevelopment.reportsystem.components.event.ReportCloseEvent;
import net.llamadevelopment.reportsystem.components.event.ReportPlayerEvent;
import net.llamadevelopment.reportsystem.components.event.ReportUpdateStaffEvent;
import net.llamadevelopment.reportsystem.components.event.ReportUpdateStatusEvent;
import net.llamadevelopment.reportsystemda.ReportSystemDA;
import net.llamadevelopment.reportsystemda.components.language.Language;

import java.awt.*;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;

public class EventListener implements Listener {

    private final String url;

    public EventListener(ReportSystemDA instance) {
        this.url = instance.getConfig().getString("webhook.url");
    }

    @EventHandler
    public void on(ReportPlayerEvent event) {
        try (WebhookClient client = WebhookClient.withUrl(this.url)) {
            TemporalAccessor accessor = Instant.ofEpochMilli(System.currentTimeMillis());
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setTitle(new WebhookEmbed.EmbedTitle(Language.getNP("reportplayer-title"), ""))
                    .setDescription(Language.getNP("reportplayer-description", event.getTarget()))
                    .addField(new WebhookEmbed.EmbedField(true, Language.getNP("reportplayer-id"), event.getId()))
                    .addField(new WebhookEmbed.EmbedField(true, Language.getNP("reportplayer-reason"), event.getReason()))
                    .addField(new WebhookEmbed.EmbedField(true, Language.getNP("reportplayer-player"), event.getReportPlayer()))
                    .setColor(Color.decode(Language.getNP("reportplayer-color")).getRGB())
                    .setTimestamp(accessor)
                    .build();
            client.send(embed).thenAccept(readonlyMessage -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void on(ReportCloseEvent event) {
        try (WebhookClient client = WebhookClient.withUrl(this.url)) {
            TemporalAccessor accessor = Instant.ofEpochMilli(System.currentTimeMillis());
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setTitle(new WebhookEmbed.EmbedTitle(Language.getNP("reportclose-title"), ""))
                    .setDescription(Language.getNP("reportclose-description", event.getTarget()))
                    .addField(new WebhookEmbed.EmbedField(true, Language.getNP("reportclose-id"), event.getId()))
                    .addField(new WebhookEmbed.EmbedField(true, Language.getNP("reportclose-reason"), event.getReason()))
                    .addField(new WebhookEmbed.EmbedField(true, Language.getNP("reportclose-player"), event.getReportPlayer()))
                    .addField(new WebhookEmbed.EmbedField(true, Language.getNP("reportclose-member"), event.getMember()))
                    .setColor(Color.decode(Language.getNP("reportclose-color")).getRGB())
                    .setTimestamp(accessor)
                    .build();
            client.send(embed).thenAccept(readonlyMessage -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void on(ReportUpdateStatusEvent event) {
        try (WebhookClient client = WebhookClient.withUrl(this.url)) {
            TemporalAccessor accessor = Instant.ofEpochMilli(System.currentTimeMillis());
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setTitle(new WebhookEmbed.EmbedTitle(Language.getNP("updatestatus-title"), ""))
                    .setDescription(Language.getNP("updatestatus-description", event.getId(), event.getStatus()))
                    .setColor(Color.decode(Language.getNP("updatestatus-color")).getRGB())
                    .setTimestamp(accessor)
                    .build();
            client.send(embed).thenAccept(readonlyMessage -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void on(ReportUpdateStaffEvent event) {
        try (WebhookClient client = WebhookClient.withUrl(this.url)) {
            TemporalAccessor accessor = Instant.ofEpochMilli(System.currentTimeMillis());
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setTitle(new WebhookEmbed.EmbedTitle(Language.getNP("updatestaff-title"), ""))
                    .setDescription(Language.getNP("updatestaff-description", event.getId(), event.getMember()))
                    .setColor(Color.decode(Language.getNP("updatestaff-color")).getRGB())
                    .setTimestamp(accessor)
                    .build();
            client.send(embed).thenAccept(readonlyMessage -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
