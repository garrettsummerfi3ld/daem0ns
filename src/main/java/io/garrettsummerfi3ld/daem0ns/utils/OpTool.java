package io.garrettsummerfi3ld.daem0ns.utils;

import org.bukkit.entity.Player;

import java.util.Objects;

public class OpTool {
    /**
     * Adds a user to the operator permission
     *
     * @param p Player
     */
    public static void addOpUser(Player p) {
        p.setOp(true);
    }

    /**
     * Removes a user from the operator permission
     *
     * @param p Player
     */
    public static void delOpUser(Player p) {
        p.setOp(false);
    }

    /**
     * Checks status of the player's operator permission
     *
     * @param p Player
     * @return boolean True if player is an operator, false if not an operator
     */
    public static boolean checkOpUser(Player p) {
        return Objects.requireNonNull(p).isOp();
    }
}
