package com.bhone;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;

public class Listener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.printf("%#s is ready", event.getJDA().getSelfUser());
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        if (!event.getName().equals("ping")) return; // handle the command
        long time = System.currentTimeMillis();
        event.reply("Pong!").setEphemeral(true).flatMap(v -> event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time)).queue();
    }

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        String channelName = event.getGuild().getGuildChannelById("818694838663905330").getName();
        TextChannel textChannel = event.getGuild().getTextChannelsByName(channelName,true).get(0);

        File file = new File("bye.gif");

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(event.getMember().getUser().getAsTag() + " left from " + event.getGuild().getName());
        eb.setDescription("We will miss you forever.Thanks for the memories.\n" +
                "Good Bye We will remember all the times we had together !");
        eb.setImage("attachment://bye.gif");
        eb.setColor(Color.CYAN);

        textChannel.sendMessageEmbeds(eb.build()).addFile(file, "bye.gif").queue();
    }
}
