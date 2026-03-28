package digitalblade.digital_blade_online.item;

import digitalblade.digital_blade_online.Digital_blade_online;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item Rose_Scythe = registerItem("rose_scythe",
            new SwordItem(ToolMaterials.NETHERITE,4,-2.4f, new FabricItemSettings()));

    public static final Item Dual_Z_Scythe = registerItem("dual_z_scythe",
            new SwordItem(ToolMaterials.NETHERITE,4,-2f, new FabricItemSettings()));

    public static final Item The_World_Divider = registerItem("the_world_divider",
            new SwordItem(ToolMaterials.NETHERITE,5,-2.5f, new FabricItemSettings()));

    public static final Item The_Lost_Truth = registerItem("the_lost_truth",
            new SwordItem(ToolMaterials.NETHERITE,4,-2f, new FabricItemSettings()));

    public static final Item Rose_Hammer = registerItem("rose_hammer",
            new SwordItem(ToolMaterials.NETHERITE,6,-3f, new FabricItemSettings()));

    public static final Item Bone_Cracker = registerItem("bone_cracker",
            new SwordItem(ToolMaterials.NETHERITE,6,-3f, new FabricItemSettings()));

    public static final Item One_Last_Time = registerItem("one_last_time",
            new SwordItem(ToolMaterials.NETHERITE,-1,-1f, new FabricItemSettings()));

    public static final Item Truth_Omen = registerItem("truth_omen",
            new SwordItem(ToolMaterials.NETHERITE, 1,-1f, new FabricItemSettings()));

    public static final Item Karmatic_Omen = registerItem("karmatic_omen",
            new SwordItem(ToolMaterials.NETHERITE, 1,-1f, new FabricItemSettings()));

    public static final Item Copper_Chakram = registerItem("copper_chakram",
            new SwordItem(ToolMaterials.NETHERITE,1,-1.5f, new FabricItemSettings()));

    public static final Item Rose_Rapier = registerItem("rose_rapier",
            new SwordItem(ToolMaterials.NETHERITE,1,-1.5f, new FabricItemSettings()));

    public static final Item Asriels_Rapier = registerItem("asriels_rapier",
            new SwordItem(ToolMaterials.NETHERITE,1,-1f, new FabricItemSettings()));




    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Digital_blade_online.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Digital_blade_online.LOGGER.info("Registering Mod Items for " + Digital_blade_online.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}