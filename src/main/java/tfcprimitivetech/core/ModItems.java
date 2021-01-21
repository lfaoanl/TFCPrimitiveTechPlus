package tfcprimitivetech.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import tfcprimitivetech.TFCPrimitiveTech;
import tfcprimitivetech.items.*;

public class ModItems {
    // Items
    public static Item itemWoodenTwig;
    public static Item itemCelluloseFibers;
    public static Item itemWoodenPress;
    public static Item itemLeatherBelt;
    public static Item itemSharpStone;
    public static Item itemHardStone;
    public static Item itemSoftStone;
    public static Item itemSlingshot;
    public static Item itemWoodenClub;

    public static void initialise() {
        System.out.println("[" + ModDetails.ModName + "] Registering Items");

        itemWoodenTwig = new ItemWoodenTwig();
        if (TFCPrimitiveTech.instance.isPaperEnabled) {
            itemCelluloseFibers = new ItemCelluloseFibers();
            itemWoodenPress = new ItemWoodenPress();
        }
        itemLeatherBelt = new ItemLeatherBelt();
        itemWoodenClub = new ItemWoodenClub();
        if (TFCPrimitiveTech.instance.isSlingshotEnabled) {
            itemSharpStone = new ItemSharpStone();
            itemHardStone = new ItemHardStone();
            itemSoftStone = new ItemSoftStone();
            itemSlingshot = new ItemSlingshot();
        }


        registerItems();

        System.out.println("[" + ModDetails.ModName + "] Done Registering Items");
    }

    private static void registerItems() {
        GameRegistry.registerItem(itemWoodenTwig, "itemWoodenTwig");
        if (TFCPrimitiveTech.instance.isPaperEnabled) {
            GameRegistry.registerItem(itemCelluloseFibers, "itemCelluloseFibers");
            GameRegistry.registerItem(itemWoodenPress, "itemWoodenPress");
        }
        GameRegistry.registerItem(itemLeatherBelt, "itemLeatherBelt");
        GameRegistry.registerItem(itemWoodenClub, "itemWoodenClub");

        if (TFCPrimitiveTech.instance.isSlingshotEnabled) {
            GameRegistry.registerItem(itemSharpStone, "itemSharpStone");
            GameRegistry.registerItem(itemHardStone, "itemHardStone");
            GameRegistry.registerItem(itemSoftStone, "itemSoftStone");
            GameRegistry.registerItem(itemSlingshot, "itemSlingshot");
        }
//	  GameRegistry.registerItem(itemSack, "itemSack");
    }
}
