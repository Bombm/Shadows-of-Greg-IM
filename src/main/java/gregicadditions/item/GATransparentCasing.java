package gregicadditions.item;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GATransparentCasing extends VariantBlock<GATransparentCasing.CasingType> {

	public GATransparentCasing() {
		super(Material.IRON);
		setTranslationKey("ga_transparent_casing");
		setHardness(5.0f);
		setResistance(5000.0f);
		setSoundType(SoundType.GLASS);
		setHarvestLevel("wrench", 2);
		setDefaultState(getState(CasingType.REINFORCED_GLASS));
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
		return false;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("deprecation")
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("deprecation")
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();

		return block != this && super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}

	public enum CasingType implements IStringSerializable {

		REINFORCED_GLASS("reinforced_glass"),
		BACTERIAL_REFLECTIVE_GLASS("bacterial_reflective_glass"),
		BATTERY_ALLOY_GLASS("battery_alloy_glass"),
		PALLADIUM_GLASS("palladium_glass"),
		TITANIUM_GLASS("titanium_glass"),
		TUNGSTEN_GLASS("tungsten_glass"),
		IRIDIUM_GLASS("iridium_glass"),
		OSMIUM_GLASS("osmium_glass"),
		LANTHANUM_GLASS("lanthanum_glass");


		private final String name;

		CasingType(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}

	}
}
