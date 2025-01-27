package gregicadditions.jei;

import com.google.common.collect.Lists;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.BlockSSPMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class FusionReactor4Info extends MultiblockInfoPage {

	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.FUSION_REACTOR[3];
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder().aisle("###############", "######NCN######", "###############").aisle("######DCD######", "####CCcccCC####", "######UCU######").aisle("####CC###CC####", "###sccNCNccs###", "####CC###CC####").aisle("###J#######J###", "##wcnC###Cnce##", "###J#######J###").aisle("##C#########C##", "#Cce#######wcC#", "##C#########C##").aisle("##C#########C##", "#CcC#######CcC#", "##C#########C##").aisle("#D###########D#", "WcE#########WcE", "#U###########U#").aisle("#C###########C#", "CcC#########CcC", "#C###########C#").aisle("#D###########D#", "WcE#########WcE", "#U###########U#").aisle("##C#########C##", "#CcC#######CcC#", "##C#########C##").aisle("##C#########C##", "#Cce#######wcC#", "##C#########C##").aisle("###J#######J###", "##wcsC###Csce##", "###J#######J###").aisle("####CC###CC####", "###nccSCSccn###", "####CC###CC####").aisle("######DCD######", "####CCcccCC####", "######UCU######").aisle("###############", "######NMN######", "###############").where('M', GATileEntities.FUSION_REACTOR[3], EnumFacing.SOUTH).where('C', MetaBlocks.SSP_METAL_CASING.getState(BlockSSPMetalCasing.SSPMetalCasingType.Fusion_3)).where('J',MetaBlocks.SSP_METAL_CASING.getState(BlockSSPMetalCasing.SSPMetalCasingType.Plasma_injector)).where('c', MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.FUSION_COIL_2)).where('W', MetaTileEntities.FLUID_EXPORT_HATCH[8], EnumFacing.WEST).where('E', MetaTileEntities.FLUID_EXPORT_HATCH[9], EnumFacing.EAST).where('S', MetaTileEntities.FLUID_EXPORT_HATCH[9], EnumFacing.SOUTH).where('N', MetaTileEntities.FLUID_EXPORT_HATCH[9], EnumFacing.NORTH).where('w', MetaTileEntities.ENERGY_INPUT_HATCH[9], EnumFacing.WEST).where('e', MetaTileEntities.ENERGY_INPUT_HATCH[9], EnumFacing.EAST).where('s', MetaTileEntities.ENERGY_INPUT_HATCH[9], EnumFacing.SOUTH).where('n', MetaTileEntities.ENERGY_INPUT_HATCH[9], EnumFacing.NORTH).where('U', MetaTileEntities.FLUID_IMPORT_HATCH[9], EnumFacing.UP).where('D', MetaTileEntities.FLUID_IMPORT_HATCH[9], EnumFacing.DOWN).where('#', Blocks.AIR.getDefaultState()).build();

		return Lists.newArrayList(shapeInfo);
	}

	@Override
	public String[] getDescription() {
		return new String[] { I18n.format("gregtech.multiblock.fusion_reactor_mk4.description") };
	}

}