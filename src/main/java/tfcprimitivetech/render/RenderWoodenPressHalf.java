package tfcprimitivetech.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import tfcprimitivetech.core.Bound;

public class RenderWoodenPressHalf extends RenderWoodenPress {

    public static float[] bounds = new float[]{(float) VoxelSizeScaled, 0, (float)VoxelSizeScaled, 1 - (float)VoxelSizeScaled, 4.44f * (float)VoxelSizeScaled, 1 - (float)VoxelSizeScaled};
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
}
