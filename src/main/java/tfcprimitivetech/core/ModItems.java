package tfcprimitivetech.core;

import com.dunk.tfc.Items.ItemTerra;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import tfcprimitivetech.items.ItemWoodenTwig;
import tfcprimitivetech.items.WoodenBucket_BasePotashLiquor;
import tfcprimitivetech.TFCPrimitiveTech;
import tfcprimitivetech.items.ItemCelluloseFibers;
import tfcprimitivetech.items.ItemWoodenPress;
import tfcprimitivetech.items.ItemLeatherBelt;
//import tfcprimitivetech.items.ItemSack;
import tfcprimitivetech.items.ItemSharpStone;
import tfcprimitivetech.items.ItemHardStone;
import tfcprimitivetech.items.ItemSoftStone;
import tfcprimitivetech.items.ItemSlingshot;
import tfcprimitivetech.items.ItemWoodenClub;
import tfcprimitivetech.items.ItemClayBrick;

public class ModItems 
{
	// Items
	public static Item itemWoodenTwig;
	public static Item woodenBucket_BasePotashLiquor;
	public static Item itemCelluloseFibers;
	public static Item itemWoodenPress;
	public static Item itemLeatherBelt;
	public static Item itemSharpStone;
	public static Item itemHardStone;
	public static Item itemSoftStone;	
	public static Item itemSlingshot;
	public static Item itemWoodenClub;	
	public static Item itemClayBrick;	
//	public static Item itemSack;	
	
	public static void initialise()
	{
		System.out.println("[" + ModDetails.ModName + "] Registering Items");
		
		itemWoodenTwig = new ItemWoodenTwig();
		woodenBucket_BasePotashLiquor = new WoodenBucket_BasePotashLiquor();
        if (TFCPrimitiveTech.instance.isPaperEnabled)
        {
        	itemCelluloseFibers = new ItemCelluloseFibers();
        	itemWoodenPress = new ItemWoodenPress();
        }
		itemLeatherBelt = new ItemLeatherBelt();
		itemWoodenClub = new ItemWoodenClub();
		itemClayBrick = new ItemClayBrick().setUnlocalizedName("Brick");
        if (TFCPrimitiveTech.instance.isSlingshotEnabled)
        {
        	itemSharpStone = new ItemSharpStone();
        	itemHardStone = new ItemHardStone();
        	itemSoftStone = new ItemSoftStone();
        	itemSlingshot = new ItemSlingshot();
        }


		registerItems();
		        
		System.out.println("[" + ModDetails.ModName + "] Done Registering Items");
	}
	
	private static void registerItems()
	{
	  GameRegistry.registerItem(itemWoodenTwig, "itemWoodenTwig");
	  GameRegistry.registerItem(woodenBucket_BasePotashLiquor, "woodenBucket_BasePotashLiquor");
      if (TFCPrimitiveTech.instance.isPaperEnabled)
      { 
    	  GameRegistry.registerItem(itemCelluloseFibers, "itemCelluloseFibers");
    	  GameRegistry.registerItem(itemWoodenPress, "itemWoodenPress");
	  }
	  GameRegistry.registerItem(itemLeatherBelt, "itemLeatherBelt");
	  GameRegistry.registerItem(itemWoodenClub, "itemWoodenClub");
	  GameRegistry.registerItem(itemClayBrick, "itemClayBrick");
      if (TFCPrimitiveTech.instance.isSlingshotEnabled)
      {
    	  GameRegistry.registerItem(itemSharpStone, "itemSharpStone");
    	  GameRegistry.registerItem(itemHardStone, "itemHardStone");
    	  GameRegistry.registerItem(itemSoftStone, "itemSoftStone");
    	  GameRegistry.registerItem(itemSlingshot, "itemSlingshot");
      }
//	  GameRegistry.registerItem(itemSack, "itemSack");
	}
}
