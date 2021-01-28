package tfcprimitivetech.items;

import com.dunk.tfc.Items.ItemTerra;
import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.Interfaces.ISize;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tfcprimitivetech.core.ModBlocks;
import tfcprimitivetech.core.ModDetails;

import java.util.List;

public class ItemBlockWoodenPress extends ItemBlock implements ISize
{

    public ItemBlockWoodenPress(Block block)
    {
        super(block);
        
        setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister registerer)
    {
        this.itemIcon = registerer.registerIcon(ModDetails.ModID+":"+"ItemWoodenPress");
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        int meta = itemstack.getItemDamage();

        if (meta == 0) {
            return getUnlocalizedName();
        }

        return getUnlocalizedName() + "Paper";
    }
    
    @Override
    public int getItemStackLimit(ItemStack is)
    {
        return this.getSize(null).stackSize * getWeight(null).multiplier;
    }
    
    @Override
    public boolean canStack()
    {
        return true;
    }
    
    @Override
    public int getMetadata(int i)
    {
        return i;
    }
    
    
    @Override
    public EnumSize getSize(ItemStack is)
    {
        return EnumSize.SMALL;
    }

    @Override
    public EnumWeight getWeight(ItemStack is)
    {
        return EnumWeight.HEAVY;
    }
    
    @Override
    public EnumItemReach getReach(ItemStack is)
    {
        return EnumItemReach.SHORT;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {

        if (player.isSneaking() &&
                world.getBlock(x, y, z) == ModBlocks.woodenPressHalf &&
                world.getBlockMetadata(x, y, z) == 1 &&
                side == 1) {
            itemStack.stackSize--;
            world.setBlock(x, y, z, ModBlocks.WoodenPressWet, 0, 2);
            world.playSoundEffect(x + 0.5f, y + 0.5f, z + 0.5f, "dig.wood", 0.8f, world.rand.nextFloat() * 0.1f + 0.9f);
            return true;
        }

        return super.onItemUse(itemStack, player, world, x, y, z, side, px, py, pz);
    }
}
