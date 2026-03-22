package digitalblade.digital_blade_online;

import digitalblade.digital_blade_online.item.ModItemGroups;
import digitalblade.digital_blade_online.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Digital_blade_online implements ModInitializer {
    public static final String MOD_ID = "digital_blade_online";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
    }
}