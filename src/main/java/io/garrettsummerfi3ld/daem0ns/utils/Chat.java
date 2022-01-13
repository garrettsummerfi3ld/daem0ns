package io.garrettsummerfi3ld.daem0ns.utils;

import org.bukkit.ChatColor;

public class Chat {
    /**
     * Allows the '&' to be used for color codes in strings
     *
     * @param str Message string with color codes
     * @return message with color formatting
     */
    public static String colorMsg(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
