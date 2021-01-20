package tfcprimitivetech.entities;

import com.dunk.tfc.api.Enums.EnumDamageType;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import tfcprimitivetech.core.ModItems;

public class EntityProjectileSharpStone extends EntityProjectileStone 
{
	
	public Item pickupItem = ModItems.itemSharpStone;	
	
	public EntityProjectileSharpStone(World par1World)
	{
		super(par1World);
	}

	public EntityProjectileSharpStone(World par1World, double i, double j, double k)
	{
		super(par1World, i , j, k);
	}

	public EntityProjectileSharpStone(World world, EntityLivingBase shooter, EntityLivingBase victim, float force, float forceVariation)
	{
		super(world, shooter, victim, force * 1.1F, forceVariation);
	}

	public EntityProjectileSharpStone(World par1World, EntityLivingBase shooter, float force)
	{
		super(par1World, shooter, force * 1.1F);
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player)
	{
		if (!this.worldObj.isRemote)
		{
			NBTTagCompound nbt = new NBTTagCompound();
			this.writeToNBT(nbt);

			boolean inground = nbt.hasKey("inGround") && nbt.getByte("inGround") == 1;
			if(inground)
			{
				boolean flag = this.canBePickedUp == 1 || (this.canBePickedUp == 2 && player.capabilities.isCreativeMode);

				EntityItem ei = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(this.pickupItem, 1, 0));

				if (this.canBePickedUp == 1)
				{
					EntityItemPickupEvent event = new EntityItemPickupEvent(player, ei);

					if (MinecraftForge.EVENT_BUS.post(event))
						return;
				}

				ItemStack itemstack = ei.getEntityItem();
				if (itemstack.stackSize <= 0)
					flag = true;
				else if (this.canBePickedUp == 1 && !player.inventory.addItemStackToInventory(itemstack))
					flag = false;

				if (flag)
				{
					this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
					player.onItemPickup(this, 1);
					this.setDead();
				}
			}
		}
	}
	
	
	@Override
	public EnumDamageType getDamageType(EntityLivingBase e)
	{
		return EnumDamageType.SLASHING;
	}
}

