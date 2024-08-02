package coffee.dislike.expee;

import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.blaze3d.platform.InputUtil;

import coffee.dislike.expee.network.packet.c2s.payload.PeePayload;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBind;

public class ExpeeClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("expee client");

    private static KeyBind peeKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBind(
        "key." + Expee.MOD_ID + ".pee", // The translation key of the keybinding's name
        InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
        GLFW.GLFW_KEY_P, // The keycode of the key
        "category."+Expee.MOD_ID // The translation key of the keybinding's category.
    ));

    @Override
    public void onInitializeClient(ModContainer mod) {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (peeKeyBinding.isPressed()) {
                ClientPlayNetworking.send(new PeePayload(0));
            }
        });
    }
}