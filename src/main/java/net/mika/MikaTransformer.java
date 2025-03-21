package net.mika;

import net.minecraft.launchwrapper.IClassTransformer;

public class MikaTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (name.equals("net.minecraft.client.Minecraft")) {
            System.out.println("[MikaTransformer] Founded Minecraft class! Transforming.");
        }
        return basicClass;
    }
}

