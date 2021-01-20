package tfcprimitivetech.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.dunk.tfc.Items.ItemTerra;
import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.Interfaces.ISize;

public class ItemBlockWoodenPressWet extends ItemBlock implements ISize
{
    private static String[] _names = { "Wet", "Dry" };
    
    public ItemBlockWoodenPressWet(Block block)
    {
        super(block);
        
        setHasSubtypes(true);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        int meta = itemstack.getItemDamage();
        
        if(meta < 0 || meta >= _names.length)
            meta = 0;
        
        return getUnlocalizedName() + "." + _names[meta];
    }
    
    @Override
    public void addInformation(ItemStack is, EntityPlayer player, List arraylist, boolean flag)
    {
        int meta = is.getItemDamage();
        String tip = (meta == 0 ? "Place exposed to sun" : "Break to achieve paper");
        arraylist.add(tip);
    	ItemTerra.addSizeInformation(is, arraylist);
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
}
