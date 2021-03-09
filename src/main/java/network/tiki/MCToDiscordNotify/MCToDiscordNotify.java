package network.tiki.MCToDiscordNotify;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import network.tiki.MCToDiscordNotify.configuration.Config;
import network.tiki.MCToDiscordNotify.event.EventsHandler;

@Mod("mc2discord-notify")
public class MCToDiscordNotify
{
    public MCToDiscordNotify() {
        // Configuration
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT, "mc2discord-notify-client.toml");
        Config.loadConfig(Config.CLIENT, FMLPaths.CONFIGDIR.get().resolve("mc2discord-notify-client.toml").toString());

        // Events
        MinecraftForge.EVENT_BUS.register(EventsHandler.class);
    }
}
