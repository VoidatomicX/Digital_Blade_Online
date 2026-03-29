package digitalblade.digital_blade_online.client;

import digitalblade.digital_blade_online.screen.StatsScreen;
import digitalblade.digital_blade_online.screen.StatsScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class Digital_blade_onlineClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HandledScreens.register(StatsScreenHandler.TYPE, StatsScreen::new);
    }
}
