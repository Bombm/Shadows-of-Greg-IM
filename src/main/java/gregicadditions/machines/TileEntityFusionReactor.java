package gregicadditions.machines;

import static gregtech.api.multiblock.BlockPattern.RelativeDirection.BACK;
import static gregtech.api.multiblock.BlockPattern.RelativeDirection.DOWN;
import static gregtech.api.multiblock.BlockPattern.RelativeDirection.LEFT;

import java.util.List;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.client.ClientHandler;
import gregtech.api.GTValues;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.recipeproperties.FusionEUToStartProperty;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.blocks.*;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.items.IItemHandlerModifiable;

public class TileEntityFusionReactor extends RecipeMapMultiblockController {
	private final int tier;
	private EnergyContainerList inputEnergyContainers;
	private long heat = 0; // defined in TileEntityFusionReactor but serialized in FusionRecipeLogic

	public TileEntityFusionReactor(ResourceLocation metaTileEntityId, int tier) {
		super(metaTileEntityId, RecipeMaps.FUSION_RECIPES);
		this.recipeMapWorkable = new FusionRecipeLogic(this);
		this.tier = tier;
		this.reinitializeStructurePattern();
		this.energyContainer = new EnergyContainerHandler(this, Integer.MAX_VALUE, 0, 0, 0, 0) {
			@Override
			public String getName() {
				return "EnergyContainerInternal";
			}
		};
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityFusionReactor(metaTileEntityId, tier);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		FactoryBlockPattern.start();
		return FactoryBlockPattern.start(LEFT, DOWN, BACK)
				.aisle("###############", "######OCO######", "###############")
				.aisle("######ICI######", "####CCcccCC####", "######ICI######")
				.aisle("####CC###CC####", "###EccOCOccE###", "####CC###CC####")
				.aisle("###J#######J###", "##EcEC###CEcE##", "###J#######J###")
				.aisle("##C#########C##", "#CcE#######EcC#", "##C#########C##")
				.aisle("##C#########C##", "#CcC#######CcC#", "##C#########C##")
				.aisle("#I###########I#", "OcO#########OcO", "#I###########I#")
				.aisle("#C###########C#", "CcC#########CcC", "#C###########C#")
				.aisle("#I###########I#", "OcO#########OcO", "#I###########I#")
				.aisle("##C#########C##", "#CcC#######CcC#", "##C#########C##")
				.aisle("##C#########C##", "#CcG#######GcC#", "##C#########C##")
				.aisle("###J#######J###", "##GcGC###CGcG##", "###J#######J###")
				.aisle("####CC###CC####", "###GccOCOccG###", "####CC###CC####")
				.aisle("######ICI######", "####CCcccCC####", "######ICI######")
				.aisle("###############", "######OSO######", "###############")
				.where('S', selfPredicate())
				.where('C', statePredicate(getCasingState()))
				.where('J', statePredicate(getInjectorState()))
				.where('c', statePredicate(getCoilState()))
				.where('O', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.EXPORT_FLUIDS)))
				.where('E', statePredicate(getCasingState()).or(tilePredicate((state, tile) -> {
					for (int i = tier; i < GTValues.V.length; i++) {
						if (tile.metaTileEntityId.equals(MetaTileEntities.ENERGY_INPUT_HATCH[i].metaTileEntityId)) return true;
					}
					return false;
				})))
				.where('G', statePredicate(getInjectorState()).or(tilePredicate((state, tile) -> {
					for (int i = tier; i < GTValues.V.length; i++) {
						if (tile.metaTileEntityId.equals(MetaTileEntities.ENERGY_INPUT_HATCH[i].metaTileEntityId)) return true;
					}
					return false;
				})))
				.where('I', statePredicate(getCasingState()).or(abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS)))
				.where('#', (tile) -> true)
				.build();
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		switch (tier){
			case 9:
				return ClientHandler.FUSION_TEXTURE_2;
			default:
				return ClientHandler.FUSION_TEXTURE;
		}

	}

	private IBlockState getCasingState() {
		switch (tier) {
		case 6:
			return MetaBlocks.MACHINE_CASING.getState(BlockMachineCasing.MachineCasingType.LuV);
		case 7:
			return MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.FUSION_CASING);
		case 9:
			return MetaBlocks.SSP_METAL_CASING.getState(BlockSSPMetalCasing.SSPMetalCasingType.Fusion_3);
		case 8:
		default:
			return MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.FUSION_CASING_MK2);
		}
	}

	private IBlockState getInjectorState(){
		switch (tier) {
			case 6:
				return MetaBlocks.MACHINE_CASING.getState(BlockMachineCasing.MachineCasingType.LuV);
			case 7:
				return MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.FUSION_CASING);
			case 9:
				return MetaBlocks.SSP_METAL_CASING.getState(BlockSSPMetalCasing.SSPMetalCasingType.Plasma_injector);
			default:
				return MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.FUSION_CASING_MK2);
		}
	}

	private IBlockState getCoilState() {
		switch (tier) {
		case 6:
			return MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.SUPERCONDUCTOR);
		case 9:
			return MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.FUSION_COIL_2);
		case 7:
		case 8:
		default:
			return MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.FUSION_COIL);
		}
	}

	@Override
	protected void formStructure(PatternMatchContext context) {
		long energyStored = this.energyContainer.getEnergyStored();
		super.formStructure(context);
		this.initializeAbilities();
		((EnergyContainerHandler) this.energyContainer).setEnergyStored(energyStored);
	}

	private void initializeAbilities() {
		this.inputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));
		this.inputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
		this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
		this.outputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
		List<IEnergyContainer> energyInputs = getAbilities(MultiblockAbility.INPUT_ENERGY);
		this.inputEnergyContainers = new EnergyContainerList(energyInputs);
		int powTier;
		switch (tier){
			case 9: powTier = 32;
			default: powTier = (int) Math.pow(2, tier - 6);
		}
		long euCapacity = energyInputs.size() * 10000000L * powTier;
		this.energyContainer = new EnergyContainerHandler(this, euCapacity, GTValues.V[tier], 0, 0, 0) {
			@Override
			public String getName() {
				return "EnergyContainerInternal";
			}
		};
	}

	@Override
	protected void updateFormedValid() {
		if (!getWorld().isRemote) {
			if (this.inputEnergyContainers.getEnergyStored() > 0) {
				long energyAdded = this.energyContainer.addEnergy(this.inputEnergyContainers.getEnergyStored());
				if (energyAdded > 0) this.inputEnergyContainers.removeEnergy(energyAdded);
			}
			super.updateFormedValid();
		}
	}

	@Override
	protected void addDisplayText(List<ITextComponent> textList) {
		if (!this.isStructureFormed()) {
			textList.add(new TextComponentTranslation("gregtech.multiblock.invalid_structure").setStyle(new Style().setColor(TextFormatting.RED)));
		}
		if (this.isStructureFormed()) {
			if (!this.recipeMapWorkable.isWorkingEnabled()) {
				textList.add(new TextComponentTranslation("gregtech.multiblock.work_paused"));
			} else if (this.recipeMapWorkable.isActive()) {
				textList.add(new TextComponentTranslation("gregtech.multiblock.running"));
				int currentProgress;
				if (energyContainer.getEnergyCapacity() > 0) {
					currentProgress = (int) (this.recipeMapWorkable.getProgressPercent() * 100.0D);
					textList.add(new TextComponentTranslation("gregtech.multiblock.progress", currentProgress));
				} else {
					currentProgress = -this.recipeMapWorkable.getRecipeEUt();
					textList.add(new TextComponentTranslation("gregtech.multiblock.generation_eu", currentProgress));
				}
			} else {
				textList.add(new TextComponentTranslation("gregtech.multiblock.idling"));
			}

			if (this.recipeMapWorkable.isHasNotEnoughEnergy()) {
				textList.add(new TextComponentTranslation("gregtech.multiblock.not_enough_energy").setStyle(new Style().setColor(TextFormatting.RED)));
			}
		}

		textList.add(new TextComponentString("EU: " + this.energyContainer.getEnergyStored() + " / " + this.energyContainer.getEnergyCapacity()));
		textList.add(new TextComponentTranslation("gregtech.multiblock.fusion_reactor.heat", heat));
	}

	@Override
	public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
		this.getBaseTexture(null).render(renderState, translation, pipeline);
		ClientHandler.FUSION_REACTOR_OVERLAY.render(renderState, translation, pipeline, this.getFrontFacing(), this.recipeMapWorkable.isActive());
	}

	private class FusionRecipeLogic extends MultiblockRecipeLogic {
		public FusionRecipeLogic(TileEntityFusionReactor tileEntity) {
			super(tileEntity);
			this.allowOverclocking = false;
		}

		@Override
		public void updateWorkable() {
			super.updateWorkable();
			if (!isActive && heat > 0) {
				heat = heat <= 10000 ? 0 : (heat - 10000);
			}
		}

		@Override
		protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
			Recipe recipe = super.findRecipe(maxVoltage, inputs, fluidInputs);
			return (recipe != null && recipe.getRecipePropertyStorage().getRecipePropertyValue(FusionEUToStartProperty.getInstance(), 0L) <= energyContainer.getEnergyCapacity()) ? recipe : null;
		}

		@Override
		protected boolean setupAndConsumeRecipeInputs(Recipe recipe) {
			long heatDiff = recipe.getRecipePropertyStorage().getRecipePropertyValue(FusionEUToStartProperty.getInstance(), 0L) - heat;
			if (heatDiff <= 0) {
				return super.setupAndConsumeRecipeInputs(recipe);
			}
			if (energyContainer.getEnergyStored() < heatDiff || !super.setupAndConsumeRecipeInputs(recipe)) {
				return false;
			}
			energyContainer.removeEnergy(heatDiff);
			heat += heatDiff;
			return true;
		}

		@Override
		public NBTTagCompound serializeNBT() {
			NBTTagCompound tag = super.serializeNBT();
			tag.setLong("Heat", heat);
			return tag;
		}

		@Override
		public void deserializeNBT(NBTTagCompound compound) {
			super.deserializeNBT(compound);
			heat = compound.getLong("Heat");
		}
	}
}
