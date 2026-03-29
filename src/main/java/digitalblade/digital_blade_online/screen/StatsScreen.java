package digitalblade.digital_blade_online.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;

public class StatsScreen extends HandledScreen<StatsScreenHandler> {

    private int scroll = 0;

    public StatsScreen(StatsScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 256;
        this.backgroundHeight = 180;
    }

    @Override
    protected void init() {
        super.init();

        int x = this.x + 90;
        int y = this.y + 30;

        addDrawableChild(ButtonWidget.builder(Text.literal("+"), b -> handler.modifyStat("speed", 1))
                .dimensions(x, y, 20, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("-"), b -> handler.modifyStat("speed", -1))
                .dimensions(x + 25, y, 20, 20).build());
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.fill(x, y, x + backgroundWidth, y + backgroundHeight, 0xFF2B2B2B);

        int leftX = x + 10;
        int leftY = y + 20;

        context.drawText(textRenderer, "Stats:", leftX, leftY, 0xFFFFFF, false);

        int i = 0;
        var stats = handler.getStats();

        context.drawText(textRenderer, "Speed: " + stats.speed, leftX, leftY + 15 + (i++ * 12), 0xAAAAFF, false);
        context.drawText(textRenderer, "Defense: " + stats.defense, leftX, leftY + 15 + (i++ * 12), 0xFFAAAA, false);
        context.drawText(textRenderer, "Health: " + stats.health, leftX, leftY + 15 + (i++ * 12), 0xAAFFAA, false);
        context.drawText(textRenderer, "AtkSpeed: " + stats.attackSpeed, leftX, leftY + 15 + (i++ * 12), 0xFFFFAA, false);

        InventoryScreen.drawEntity(
                context,
                x + 200,
                y + 140,
                40,
                (float)(x + 200) - mouseX,
                (float)(y + 140 - 50) - mouseY,
                client.player
        );
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        scroll += amount * 5;
        return true;
    }
}