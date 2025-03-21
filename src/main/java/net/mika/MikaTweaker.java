package net.mika;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import java.io.File;
import java.util.List;

public class MikaTweaker implements ITweaker {
    private List<String> launchArgs;

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.launchArgs = args;
        System.out.println("[MikaTweaker] Options parsed!");
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        System.out.println("[MikaTweaker] Inject with ClassLoader");
        classLoader.registerTransformer("net.mika.MikaTransformer");
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main"; // або server.Main
    }

    @Override
    public String[] getLaunchArguments() {
        return launchArgs.toArray(new String[0]);
    }
}
