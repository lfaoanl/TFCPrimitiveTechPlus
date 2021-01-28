package tfcprimitivetech.items;

import com.dunk.tfc.Core.TFCTabs;
import com.dunk.tfc.Items.ItemTerra;
import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.Interfaces.ISize;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tfcprimitivetech.core.ModBlocks;
import tfcprimitivetech.core.ModDetails;

import java.util.List;

public class ItemCelluloseFibers extends Item implements ISize {
    public ItemCelluloseFibers() {
        super();
        this.maxStackSize = 64;
        this.setCreativeTab(TFCTabs.TFC_MATERIALS);
        this.hasSubtypes = false;
        this.setUnlocalizedName("ItemCelluloseFibers");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister registerer) {
        this.itemIcon = registerer.registerIcon(ModDetails.ModID + ":" + "ItemCelluloseFibers");
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void addInformation(ItemStack is, EntityPlayer player, List arraylist, boolean flag) {
        ItemTerra.addSizeInformation(is, arraylist);
    }

    @Override
    public EnumSize getSize(ItemStack is) {
        return EnumSize.TINY;
    }

    @Override
    public EnumWeight getWeight(ItemStack is) {
        return EnumWeight.LIGHT;
    }

    @Override
    public boolean canStack() {
        return true;
    }

    @Override
    public EnumItemReach getReach(ItemStack is) {
        return EnumItemReach.SHORT;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
        if (itemStack.stackSize >= 2 && player.isSneaking() && world.getBlock(x, y, z) == ModBlocks.woodenPressHalf && world.getBlockMetadata(x, y, z) == 0 && side == 1) {
            itemStack.stackSize -= 2;

            world.setBlockMetadataWithNotify(x, y, z, 1, 2);

            double posX = x + world.rand.nextFloat();
            double posZ = z + world.rand.nextFloat();

            world.spawnParticle("splash", posX, y, posZ, 0, 0, 0);
			world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "mob.slime.attack", 0.5F, Blocks.planks.stepSound.getPitch() * 0.8F);
		}

        return super.onItemUse(itemStack, player, world, x, y, z, side, px, py, pz);
    }
}
