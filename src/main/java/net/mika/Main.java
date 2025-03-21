package net.mika;

import java.util.Arrays;
import net.minecraft.launchwrapper.Launch;

public class Main {
    public static void main(String[] args) {
        String[] trimmed = {};
        if (args.length > 1) {
            trimmed = Arrays.copyOfRange(args, 1, args.length);
        }
        System.out.println("DONT USE IT BRO!!!! USE net.minecraft.launchwrapper.Launch! But for you ill launch it:)");
        Launch.main(trimmed);
    }
}
