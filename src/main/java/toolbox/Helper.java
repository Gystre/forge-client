package toolbox;

import com.google.common.base.Strings;

import mod.loader.ModManager;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import org.apache.logging.log4j.Logger;


import java.util.Scanner;

public class Helper implements Globals{

    public static EntityPlayerSP getLocalPlayer() {
        return MC.player;
    }

    public static WorldClient getWorld(){
        return MC.world;
    }

    public static ModManager getModManager() {
        return ModManager.getInstance();
    }

    public static void printMessageNaked(String startWith, String message, Style firstStyle, Style secondStyle) {
        if (!Strings.isNullOrEmpty(message)) {
            if (message.contains("\n")) {
                Scanner scanner = new Scanner(message);
                scanner.useDelimiter("\n");
                Style s1 = firstStyle;
                Style s2 = secondStyle;
                while (scanner.hasNext()) {
                    printMessageNaked(startWith, scanner.next(), s1, s2);
                    // alternate between colors each newline
                    Style cpy = s1;
                    s1 = s2;
                    s2 = cpy;
                }
            } else {
                TextComponentString string = new TextComponentString(startWith + message.replaceAll("\r", ""));
                string.setStyle(firstStyle);
                outputMessage(string.getFormattedText());
            }
        }
    }

    // private function that is ultimately used to output the message
    private static void outputMessage(String text) {
        if (getLocalPlayer() != null) {
            getLocalPlayer().sendMessage(new TextComponentString(text));
        }
    }

    public static void printMessageNaked(String append, String message, Style style) {
        printMessageNaked(append, message, style, style);
    }

    public static void printMessageNaked(String append, String message) {
        printMessageNaked(
                append,
                message,
                new Style().setColor(TextFormatting.WHITE),
                new Style().setColor(TextFormatting.GRAY));
    }

    public static void printMessageNaked(String message) {
        printMessageNaked("", message);
    }
}
