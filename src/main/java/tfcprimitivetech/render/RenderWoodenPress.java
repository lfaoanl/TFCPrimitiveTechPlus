package tfcprimitivetech.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import tfcprimitivetech.core.Bound;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderWoodenPress implements ISimpleBlockRenderingHandler
{
    public static final double VoxelSizeScaled = 0.0625;// 1/16
    
    public static float[] bounds = new float[]{(float) VoxelSizeScaled, 0, (float)VoxelSizeScaled, 1 - (float)VoxelSizeScaled, 8 * (float)VoxelSizeScaled, 1 - (float)VoxelSizeScaled};
    protected Bound _bound = new Bound(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        setBound(_bound, renderer);
        renderInvBlock(block, renderer, metadata);
    }
    
    
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        setBound(_bound, renderer);
        renderer.renderStandardBlock(block, x, y, z);

        return true;
    }
    
    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }
    
    @Override
    public int getRenderId()
    {
        return 0;
    }
    
    protected static void renderInvBlock(Block block, RenderBlocks renderer, int meta)
    {
        Tessellator var14 = Tessellator.instance;
        
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        // Bottom - 0
        var14.startDrawingQuads();
        var14.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, meta));
        var14.draw();

        // Top
        var14.startDrawingQuads();
        var14.setNormal(0.0F, 0.56F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, meta));
        var14.draw();

        // West - 2
        var14.startDrawingQuads();
        var14.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, meta));
        var14.draw();

        // North - 3
        var14.startDrawingQuads();
        var14.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, meta));
        var14.draw();

        // East - 4
        var14.startDrawingQuads();
        var14.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, meta));
        var14.draw();

        // South - 5
        var14.startDrawingQuads();
        var14.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, meta));
        var14.draw();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
    
    protected static void setBound(Bound bound, RenderBlocks renderer)
    {
        renderer.setRenderBounds(bound.MinX, bound.MinY, bound.MinZ, bound.MaxX, bound.MaxY, bound.MaxZ);
    }


}
