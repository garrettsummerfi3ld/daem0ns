package io.garrettsummerfi3ld.daem0ns.listeners;

import io.garrettsummerfi3ld.daem0ns.utils.Chat;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import io.garrettsummerfi3ld.daem0ns.commands.CMDMuteAll;

public class MuteAllListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncChatEvent e) {
        if (CMDMuteAll.getServerMuteState()) {
            if (!e.getPlayer().hasPermission("daem0ns.muteall.bypass")){
                e.getPlayer().sendMessage(Chat.colorMsg("&8[&7daem&80&7ns&8] &7&oThe server is currently muted, your message did not send."));
                e.setCancelled(true);
            }
        }
    }
}
