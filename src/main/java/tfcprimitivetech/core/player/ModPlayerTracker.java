package tfcprimitivetech.core.player;

import tfcprimitivetech.handlers.network.InitClientWorldPacket;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.item.ItemTossEvent;

import com.dunk.tfc.TerraFirmaCraft;
import com.dunk.tfc.Handlers.Network.AbstractPacket;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerDisconnectionFromClientEvent;

public class ModPlayerTracker 
{
    @SubscribeEvent 
    public void onPlayerLoggedIn(PlayerLoggedInEvent e) 
    { 
        AbstractPacket packet = new InitClientWorldPacket(); 
        TerraFirmaCraft.PACKET_PIPELINE.sendTo(packet, (EntityPlayerMP)e.player); 
    } 

    @SubscribeEvent 
    public void onClientConnect(ClientConnectedToServerEvent e) 
    { 
    } 

    @SubscribeEvent 
    public void onClientDisconnect(ServerDisconnectionFromClientEvent e) 
    { 
    } 

    @SubscribeEvent 
    public void onPlayerRespawn(PlayerRespawnEvent e) 
    { 
    } 

    @SubscribeEvent 
    public void notifyPickup(ItemPickupEvent e) 
    { 
    } 

    @SubscribeEvent 
    public void onPlayerTossEvent(ItemTossEvent e) 
    { 
    } 
}
