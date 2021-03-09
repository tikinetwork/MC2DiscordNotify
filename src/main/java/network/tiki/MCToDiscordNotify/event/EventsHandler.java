package network.tiki.MCToDiscordNotify.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import network.tiki.MCToDiscordNotify.configuration.MCToDiscordNotifyConfig;
import network.tiki.MCToDiscordNotify.utils.DiscordWebhook;

import java.awt.*;
import java.io.IOException;

public class EventsHandler {
    @SubscribeEvent
    public static void chatMessage(ClientChatReceivedEvent e) {
        ClientPlayerEntity me = Minecraft.getInstance().player;
        boolean isPlayer = me != null;

        if (isPlayer) {
            boolean senderIsPlayer = e.getSenderUUID() != null && !e.getSenderUUID().equals(Util.DUMMY_UUID);
            boolean isSelf = senderIsPlayer && me.getUniqueID().equals(e.getSenderUUID());

            if (senderIsPlayer && !isSelf) {
                String myName = ((StringTextComponent) me.getName()).getText();
                String message = e.getMessage().getString();

                if (message.toLowerCase().contains(myName.toLowerCase())) {
                    SoundEvent event = SoundEvents.BLOCK_BELL_USE;
                    me.playSound(event, 1.0F, 1.0F);

                    sendDiscordWebhook(message);
                }
            }
        }
    }

    private static void sendDiscordWebhook(String message) {
        if (!MCToDiscordNotifyConfig.discord_webhook_url.get().isEmpty()) {
            try {
                DiscordWebhook webhook = new DiscordWebhook(MCToDiscordNotifyConfig.discord_webhook_url.get());
                webhook.setAvatarUrl(MCToDiscordNotifyConfig.discord_webhook_avatar_url.get());
                webhook.setUsername(MCToDiscordNotifyConfig.discord_webhook_username.get());
                webhook.addEmbed(new DiscordWebhook.EmbedObject().setDescription(message).setColor(Color.CYAN));
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
