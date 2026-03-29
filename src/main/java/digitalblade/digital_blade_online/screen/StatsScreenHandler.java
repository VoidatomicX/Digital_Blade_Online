package digitalblade.digital_blade_online.screen;

import digitalblade.digital_blade_online.stat.PlayerStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class StatsScreenHandler extends ScreenHandler {

    public static ScreenHandlerType<StatsScreenHandler> TYPE;

    private final PlayerStats stats;
    private final ServerPlayerEntity player;

    public StatsScreenHandler(int syncId, PlayerInventory inventory) {
        super(TYPE, syncId);

        if (!(inventory.player instanceof ServerPlayerEntity serverPlayer)) {
            throw new IllegalStateException("Not a server player");
        }

        this.player = serverPlayer;
        this.stats = PlayerStats.get(serverPlayer);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public void modifyStat(String stat, int delta) {
        if (player.experienceLevel < 2) return;

        player.addExperienceLevels(-2);

        switch (stat) {
            case "speed" -> stats.speed += delta;
            case "defense" -> stats.defense += delta;
            case "health" -> stats.health += delta;
            case "attackSpeed" -> stats.attackSpeed += delta;
        }

        PlayerStats.save(player, stats);
    }

    public static void register() {
        TYPE = Registry.register(
                Registries.SCREEN_HANDLER,
                new Identifier("digital_blade_online", "stats"),
                new ScreenHandlerType<>(
                        (syncId, inventory) -> new StatsScreenHandler(syncId, inventory),
                        FeatureFlags.VANILLA_FEATURES
                )
        );
    }
}