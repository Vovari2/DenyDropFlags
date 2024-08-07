package me.vovari2.denydropflags;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Text {

    private static final Component startOfMessage = MiniMessage.miniMessage().deserialize("[DenyDropFlags] ");
    public static void sendInfoMessage(String message){
        DenyDropFlags.getConsoleSender().sendMessage(startOfMessage.append(MiniMessage.miniMessage().deserialize(message)));
    }
}
