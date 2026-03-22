package digitalblade.digital_blade_online.item;

import digitalblade.digital_blade_online.Digital_blade_online;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup DigitalBladeOnline = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Digital_blade_online.MOD_ID, "digital_blade_online"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.digital_blade_online"))
                    .icon(() -> new ItemStack(ModItems.Rose_Scythe)).entries((displayContext, entries) -> {
                        entries.add(ModItems.Rose_Scythe);
                        entries.add(ModItems.Dual_Z_Scythe);
                        entries.add(ModItems.One_Last_Time);

                    }).build());


    public static void registerItemGroups() {
        Digital_blade_online.LOGGER.info("Registering Item Groups for " + Digital_blade_online.MOD_ID);
    }
}