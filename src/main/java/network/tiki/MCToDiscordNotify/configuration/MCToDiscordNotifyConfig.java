package network.tiki.MCToDiscordNotify.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class MCToDiscordNotifyConfig {
    public static ForgeConfigSpec.ConfigValue<String> discord_webhook_url;
    public static ForgeConfigSpec.ConfigValue<String> discord_webhook_avatar_url;
    public static ForgeConfigSpec.ConfigValue<String> discord_webhook_username;

    public static void init(ForgeConfigSpec.Builder client)
    {
        discord_webhook_url = client
                .comment("The webhook url of the discord channel you would like to receive messages in (keep empty if you do not want to use discord).")
                .define("mc2discord-notify.discord_webhook_url", "");

        discord_webhook_avatar_url = client
                .comment("The avatar url which the webhook should use when sending a message.")
                .define("mc2discord-notify.discord_webhook_avatar_url", "");

        discord_webhook_username = client
                .comment("The username which the webhook should use when sending a message.")
                .define("mc2discord-notify.discord_webhook_username", "MC2Discord");
    }
}
