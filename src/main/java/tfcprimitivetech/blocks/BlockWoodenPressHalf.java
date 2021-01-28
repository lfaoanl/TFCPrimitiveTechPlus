package tfcprimitivetech.blocks;

import com.dunk.tfc.Blocks.BlockTerraContainer;
import com.dunk.tfc.Core.TFCTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tfcprimitivetech.core.ModBlocks;
import tfcprimitivetech.core.ModDetails;
import tfcprimitivetech.core.ModItems;
import tfcprimitivetech.render.RenderWoodenPress;
import tfcprimitivetech.render.RenderWoodenPressHalf;
import tfcprimitivetech.tileentities.TileEntityWoodenPressWet;

import java.util.ArrayList;
import java.util.List;

public class BlockWoodenPressHalf extends BlockTerraContainer
{
    @SideOnly(Side.CLIENT)
    private IIcon _topIcon;

    @SideOnly(Side.CLIENT)
    private IIcon _sideIcon;

    @SideOnly(Side.CLIENT)
    private IIcon _sideIconPulp;


    @SideOnly(Side.CLIENT)
    private IIcon _topIconPulp;

    public BlockWoodenPressHalf()
    {
        super(Material.wood);
        
        this.setHardness(0.3f);
        this.setResistance(10.0f);
        this.setCreativeTab(TFCTabs.TFC_TOOLS);

        float[] bounds = RenderWoodenPressHalf.bounds;
        this.setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
        
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();

        drops.add(new ItemStack(ModBlocks.woodenPressHalf, 1, 0));

        if (hasPaper(metadata)) {
            drops.add(new ItemStack(ModItems.itemCelluloseFibers, 2, 0));
        }

        return drops;
    }    

 
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int meta)
    {
        return hasPaper(meta) ? BlockWoodenPressWet.WoodenPress_ColorWet : BlockWoodenPressWet.WoodenPress_ColorDry;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        
        return getRenderColor(meta);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List list)
    {
        list.add(new ItemStack(this, 1, 0));
        list.add(new ItemStack(this, 1, 1));
    }

    @Override
    public int damageDropped(int i)
    {
        return i;
    }

 
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        IIcon icon;
        switch (side) {
            case 0:
                icon = _topIcon;
                break;
            case 1:
                icon = hasPaper(metadata) ? _topIconPulp : _topIcon;
                break;
            default:
                icon = hasPaper(metadata) ? _sideIconPulp : _sideIcon;
                break;
        }
        return icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        String baseName = ModDetails.ModID +  ":";

        _topIcon = register.registerIcon(baseName + "blockWoodenPressTop");
        _sideIcon = register.registerIcon(baseName + "blockWoodenPressSideHalf");
        _topIconPulp = register.registerIcon(baseName + "blockWoodenPressTopPulp");
        _sideIconPulp = register.registerIcon(baseName + "blockWoodenPressSidePulp");
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
    {
        return true;
    }


    private boolean hasPaper(int meta) {
        return meta == 1;
    }

    @Override
    public int getRenderType()
    {
        return ModBlocks.halfWoodenPressRenderId;
    }


    private boolean canUse(EntityPlayer player, Item item, int amount) {
        ItemStack itemInUse = player.getHeldItem();
        return player.isSneaking() && itemInUse.getItem() == item && itemInUse.stackSize >= amount;
    }
}
