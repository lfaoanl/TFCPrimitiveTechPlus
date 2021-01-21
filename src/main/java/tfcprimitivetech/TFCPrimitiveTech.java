package tfcprimitivetech;

import com.dunk.tfc.Core.Recipes;
import com.dunk.tfc.TerraFirmaCraft;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import tfcprimitivetech.core.*;
import tfcprimitivetech.core.player.ModPlayerTracker;
import tfcprimitivetech.handlers.ChunkEventHandler;
import tfcprimitivetech.handlers.CraftingHandler;
import tfcprimitivetech.handlers.network.InitClientWorldPacket;

import java.io.File;

@Mod(modid = ModDetails.ModID, name = ModDetails.ModName, version = ModDetails.ModVersion, dependencies = ModDetails.ModDependencies)
public class TFCPrimitiveTech {
    public static ToolMaterial woodenClubMaterial;
    public boolean isPaperEnabled;
    public boolean isSlingshotEnabled;

    @Instance(ModDetails.ModID)
    public static TFCPrimitiveTech instance;

    @SidedProxy(clientSide = ModDetails.CLIENT_PROXY_CLASS, serverSide = ModDetails.SERVER_PROXY_CLASS)
    public static ModCommonProxy proxy;

    public File getMinecraftDirectory() {
        return proxy.getMinecraftDirectory();
    }

    @EventHandler
    public void preInitialize(FMLPreInitializationEvent e) {
        instance = this;

        // Load our settings
        proxy.loadOptions();

        proxy.registerTickHandler();

        ModBlocks.initialise();

        // Register Key Bindings(Client only)
        proxy.registerKeys();
        // Register KeyBinding Handler (Client only)
        proxy.registerKeyBindingHandler();
        // Register Handler (Client only)
        proxy.registerHandlers();
        // Register Tile Entities
        proxy.registerTileEntities(true);
        // Register Sound Handler (Client only)
        proxy.registerSoundHandler();

        woodenClubMaterial = EnumHelper.addToolMaterial("woodenClub", 1, 120, 2, 80, 5);

        ModItems.initialise();


        // Register Gui Handler
        proxy.registerGuiHandler();
//		proxy.registerWailaHandler();
    }

    @EventHandler
    public void initialize(FMLInitializationEvent e) {
        // Register packets in the TFC PacketPipeline
        TerraFirmaCraft.PACKET_PIPELINE.registerPacket(InitClientWorldPacket.class);

        // Register the player tracker
        FMLCommonHandler.instance().bus().register(new ModPlayerTracker());

        // Register the tool classes
        proxy.registerToolClasses();

        // Register Crafting Handler
        FMLCommonHandler.instance().bus().register(new CraftingHandler());

        // Register the Chunk Load/Save Handler
        MinecraftForge.EVENT_BUS.register(new ChunkEventHandler());

        // Register all the render stuff for the client
        proxy.registerRenderInformation();

        ModRecipes.initialise();

        // Register WAILA classes
        proxy.registerWailaClasses();
        proxy.hideNEIItems();
    }

    @EventHandler
    public void postInitialize(FMLPostInitializationEvent e) {
        if (TFCPrimitiveTech.instance.isPaperEnabled) {
            Recipes.removeRecipe(new ItemStack(Items.paper, 3));
        }
    }
}
