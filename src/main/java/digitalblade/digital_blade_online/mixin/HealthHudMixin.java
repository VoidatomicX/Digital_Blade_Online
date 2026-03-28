package digitalblade.digital_blade_online.mixin;

import digitalblade.digital_blade_online.Digital_blade_online;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class HealthHudMixin {
    @Unique
    private float displayedHealth = 20.0f;
    @Unique private float displayedHunger = 20.0f;
    @Unique private long lastUpdateTime = 0;

    @Inject(method = "renderStatusBars", at = @At("HEAD"), cancellable = true)
    private void renderSAOHealthAndFoodBar(DrawContext context, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;
        if (player == null) return;

        float currentHealth = player.getHealth();
        float currentHunger = player.getHungerManager().getFoodLevel();
        float maxHealth = player.getMaxHealth();

        // Get Current System Time in Milliseconds
        long time = System.currentTimeMillis();

        // If lastUpdateTime is 0, so on our first run through, set it to the current time
        if (lastUpdateTime == 0) lastUpdateTime = time;

        // Calculate the delta time, ergo the time which has passed since the last call
        float delta = (time - lastUpdateTime) / 1000f;
        lastUpdateTime = time;

        float speed = 10.0f;

        float healthDiff = currentHealth - displayedHealth;
        if (healthDiff > 0.1f) {
            // sigma idk
            displayedHealth += Math.signum(healthDiff) * Math.min(Math.abs(healthDiff), speed * delta);
        } else {
            displayedHealth = currentHealth;
        }

        float hungerDiff = currentHunger - displayedHunger;
        if (hungerDiff > 0.1f) {
            // sigma idk
            displayedHunger += Math.signum(hungerDiff) * Math.min(Math.abs(hungerDiff), speed * delta);
        } else {
            displayedHunger = currentHunger;
        }

        int healthY = 15;
        int x = 15;

        context.drawTexture(Identifier.of(Digital_blade_online.MOD_ID, "textures/gui/sprites/health/health_bar_case.png"), x, healthY, 0, 7, 6, 238, 21, 240, 32);
        context.drawTexture(Identifier.of(Digital_blade_online.MOD_ID, "textures/gui/sprites/health/health_bar_display.png"), x + 2, healthY + 2, 0, 9, 8, (int) (223 * (displayedHealth / maxHealth)), 11, 240, 32);


        context.drawTexture(Identifier.of(Digital_blade_online.MOD_ID, "textures/gui/sprites/hunger/food_bar_case.png"), x, healthY + 17, 0, 3, 9, 69, 14, 75, 32);
        context.drawTexture(Identifier.of(Digital_blade_online.MOD_ID, "textures/gui/sprites/hunger/food_bar_display.png"), x + 2, healthY + 19, 0, 5, 11, (int) (64 * (displayedHunger / maxHealth)), 10,  75, 32);

        // Draw text by ceilinging (rounding up) the health values and then trowing it at the pulled out of my ass cords.
        context.drawText(client.textRenderer, (int) Math.ceil(currentHealth) + "/" + (int) Math.ceil(maxHealth), x+75, healthY+12, 0x1EC800, true);

        ci.cancel();
    }
}
//hi