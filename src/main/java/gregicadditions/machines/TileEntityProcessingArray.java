package gregicadditions.machines;

import gregicadditions.*;
import gregicadditions.recipes.*;
import gregtech.api.*;
import gregtech.api.block.machines.*;
import gregtech.api.capability.*;
import gregtech.api.capability.impl.*;
import gregtech.api.gui.*;
import gregtech.api.metatileentity.*;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.multiblock.*;
import gregtech.api.recipes.*;
import gregtech.api.recipes.Recipe.*;
import gregtech.api.recipes.builders.*;
import gregtech.api.render.*;
import gregtech.api.util.*;
import gregtech.common.blocks.BlockMetalCasing.*;
import gregtech.common.blocks.*;
import gregtech.common.metatileentities.electric.*;
import it.unimi.dsi.fastutil.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.block.state.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.util.text.*;
import net.minecraftforge.fluids.*;
import net.minecraftforge.items.*;

import java.util.Arrays;
import java.util.*;
import java.util.function.Function;
import java.util.function.*;
import java.util.stream.*;

import static gregtech.api.gui.widgets.AdvancedTextWidget.*;
import static gregtech.api.util.Predicates.*;

public class TileEntityProcessingArray extends RecipeMapMultiblockController {

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
		MultiblockAbility.IMPORT_ITEMS,
		MultiblockAbility.EXPORT_ITEMS,
		MultiblockAbility.IMPORT_FLUIDS,
		MultiblockAbility.EXPORT_FLUIDS,
		MultiblockAbility.INPUT_ENERGY,
		GACapabilities.PA_MACHINE_CONTAINER
	};

	protected boolean isDistinctInputBusMode = false;

	public TileEntityProcessingArray(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, GARecipeMaps.PROCESSING_ARRAY_RECIPES);
		ProcessingArrayWorkable recipeLogic = new ProcessingArrayWorkable(this);
		recipeLogic.initReflection();
		this.recipeMapWorkable = recipeLogic;

	}

	@Override
	protected BlockPattern createStructurePattern() {

		return FactoryBlockPattern.start()
<<<<<<< HEAD
				.aisle("XXX", "XXX", "XXX")
				.aisle("XXX", "X#X", "XXX")
				.aisle("XXX", "XSX", "XXX")
				.setAmountAtLeast('L', 12)
				.where('L', statePredicate(getCasingState()))
				.where('S', selfPredicate())
				.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('#', isAirPredicate()).build();

=======
		                          .aisle("XXX", "XXX", "XXX")
		                          .aisle("XXX", "X#X", "XXX")
		                          .aisle("XXX", "XSX", "XXX")
		                          .setAmountAtLeast('L', 12)
		                          .setAmountLimit('M', 1, 1)
		                          .where('M', abilityPartPredicate(GACapabilities.PA_MACHINE_CONTAINER))
		                          .where('L', statePredicate(getCasingState()))
		                          .where('S', selfPredicate())
		                          .where('X',
		                                 statePredicate(getCasingState())
			                                 .or(abilityPartPredicate(ALLOWED_ABILITIES)))
		                          .where('#', isAirPredicate()).build();
>>>>>>> acc299ca062120e0e99cf1882f721128927e4a05
	}

	public IBlockState getCasingState() {
		return MetaBlocks.METAL_CASING.getState(MetalCasingType.TUNGSTENSTEEL_ROBUST);
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart arg0) {
		return Textures.ROBUST_TUNGSTENSTEEL_CASING;
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityProcessingArray(metaTileEntityId);
	}

<<<<<<< HEAD
	protected class ProcessingArrayWorkable extends MultiblockRecipeLogic {

		int machineTierVoltage = 0;
		int numberOfMachines = 0;
		int numberOfOperations = 0;
		ItemStack machineItemStack = null;
		String machineName = "";
		Field isActiveField = null;
		Field wasActiveAndNeedsUpdateField = null;
		Field hasNotEnoughEnergyField = null;
		// Method setActiveMethod = null;

		public RecipeMap<?> getRecipeMaps(String machineName) {
			switch (machineName) {
			case "macerator":
				return RecipeMaps.MACERATOR_RECIPES;
			case "cluster_mill":
				return GARecipeMaps.CLUSTER_MILL_RECIPES;
			case "lathe":
				return RecipeMaps.LATHE_RECIPES;
			case "extractor":
				return RecipeMaps.EXTRACTOR_RECIPES;
			case "fluid_extractor":
				return RecipeMaps.FLUID_EXTRACTION_RECIPES;
			case "alloy_smelter":
				return RecipeMaps.ALLOY_SMELTER_RECIPES;
			case "ore_washer":
				return RecipeMaps.ORE_WASHER_RECIPES;
			case "thermal_centrifuge":
				return RecipeMaps.THERMAL_CENTRIFUGE_RECIPES;
			case "centrifuge":
				return RecipeMaps.CENTRIFUGE_RECIPES;
			case "electrolyzer":
				return RecipeMaps.ELECTROLYZER_RECIPES;
			case "electric_furnace":
				return RecipeMaps.FURNACE_RECIPES;
			case "bender":
				return RecipeMaps.BENDER_RECIPES;
			case "arc_furnace":
				return RecipeMaps.ARC_FURNACE_RECIPES;
			case "autoclave":
				return RecipeMaps.AUTOCLAVE_RECIPES;
			case "assembler":
				return RecipeMaps.ASSEMBLER_RECIPES;
			case "brewery":
				return RecipeMaps.BREWING_RECIPES;
			case "canner":
				return RecipeMaps.CANNER_RECIPES;
			case "chemical_bath":
				return RecipeMaps.CHEMICAL_BATH_RECIPES;
			case "chemical_reactor":
				return RecipeMaps.CHEMICAL_RECIPES;
			case "compressor":
				return RecipeMaps.COMPRESSOR_RECIPES;
			case "cutter":
				return RecipeMaps.CUTTER_RECIPES;
			case "distillery":
				return RecipeMaps.DISTILLATION_RECIPES;
			case "electromagnetic_separator":
				return RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES;
			case "fermenter":
				return RecipeMaps.FERMENTING_RECIPES;
			case "fluid_canner":
				return RecipeMaps.FLUID_CANNER_RECIPES;
			case "fluid_heater":
				return RecipeMaps.FLUID_HEATER_RECIPES;
			case "fluid_solidifier":
				return RecipeMaps.FLUID_SOLIDFICATION_RECIPES;
			case "forge_hammer":
				return RecipeMaps.FORGE_HAMMER_RECIPES;
			case "forming_press":
				return RecipeMaps.FORMING_PRESS_RECIPES;
			case "microwave":
				return RecipeMaps.MICROWAVE_RECIPES;
			case "mixer":
				return RecipeMaps.MIXER_RECIPES;
			case "packer":
				return RecipeMaps.PACKER_RECIPES;
			case "unpacker":
				return RecipeMaps.UNPACKER_RECIPES;
			case "plasma_arc_furnace":
				return RecipeMaps.PLASMA_ARC_FURNACE_RECIPES;
			case "polarizer":
				return RecipeMaps.POLARIZER_RECIPES;
			case "laser_engraver":
				return RecipeMaps.LASER_ENGRAVER_RECIPES;
			case "wiremill":
				return RecipeMaps.WIREMILL_RECIPES;
			case "mass_fab":
				return GARecipeMaps.MASS_FAB_RECIPES;
			case "replicator":
				return GARecipeMaps.REPLICATOR_RECIPES;
			case "sifter":
				return RecipeMaps.SIFTER_RECIPES;
			case "extruder":
					return RecipeMaps.EXTRUDER_RECIPES;
			default:
				return null;
			}

		}

		public void initReflection() {
			isActiveField = ObfuscationReflectionHelper.findField(AbstractRecipeLogic.class, "isActive");
			wasActiveAndNeedsUpdateField = ObfuscationReflectionHelper.findField(AbstractRecipeLogic.class, "wasActiveAndNeedsUpdate");
			// setActiveMethod =
			// ObfuscationReflectionHelper.findMethod(AbstractRecipeLogic.class,
			// "setActive", null, Boolean.class);
			hasNotEnoughEnergyField = ObfuscationReflectionHelper.findField(AbstractRecipeLogic.class, "hasNotEnoughEnergy");
		}

=======
	protected MultiblockRecipeLogic getWorkable() {
		return recipeMapWorkable;
	}

	@Override
	protected void addDisplayText(List<ITextComponent> textList) {
		super.addDisplayText(textList);

		ITextComponent buttonText = new TextComponentTranslation("gtadditions.multiblock.processing_array.distinct");
		buttonText.appendText(" ");
		ITextComponent button = withButton((isDistinctInputBusMode ?
			new TextComponentTranslation("gtadditions.multiblock.processing_array.distinct.yes") :
			new TextComponentTranslation("gtadditions.multiblock.processing_array.distinct.no")), "distinct");
		withHoverTextTranslate(button, "gtadditions.multiblock.processing_array.distinct.info");
		buttonText.appendSibling(button);
		textList.add(buttonText);
		textList.add(new TextComponentTranslation("gtadditions.multiblock.processing_array.distinct2", isDistinctInputBusMode ?
			new TextComponentTranslation("gtadditions.multiblock.processing_array.distinct.yes") :
			new TextComponentTranslation("gtadditions.multiblock.processing_array.distinct.no")));
		if(this.recipeMapWorkable.isActive()) {
			textList.add(new TextComponentTranslation("gtadditions.multiblock.processing_array.locked"));
		}
	}

	@Override
	protected void handleDisplayClick(String componentData, Widget.ClickData clickData) {
		super.handleDisplayClick(componentData, clickData);
		isDistinctInputBusMode = !isDistinctInputBusMode;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setBoolean("Distinct", isDistinctInputBusMode);
		return data;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		isDistinctInputBusMode = data.getBoolean("Distinct");
	}

	@Override
	public void writeInitialSyncData(PacketBuffer buf) {
		super.writeInitialSyncData(buf);
		buf.writeBoolean(isDistinctInputBusMode);
	}

	@Override
	public void receiveInitialSyncData(PacketBuffer buf) {
		super.receiveInitialSyncData(buf);
		this.isDistinctInputBusMode = buf.readBoolean();
	}

	@Override
	public void invalidateStructure() {
		super.invalidateStructure();
		((ProcessingArrayWorkable) this.recipeMapWorkable).invalidate();
	}

	protected static class ProcessingArrayWorkable extends MultiblockRecipeLogic {
		/** The voltage this machine operates at */
		long machineVoltage;
		/** The GTValues.V tier ordinal for the machine's tier */
		int machineTier;
		int numberOfMachines = 0;
		int numberOfOperations = 0;
		ItemStack machineItemStack = null;
		ItemStack oldMachineStack = null;
		RecipeMap<?> recipeMap = null;
		// Fields used for distinct mode
		protected int lastRecipeIndex = 0;
		protected ItemStack[][] lastItemInputsMatrix;

>>>>>>> acc299ca062120e0e99cf1882f721128927e4a05
		public ProcessingArrayWorkable(RecipeMapMultiblockController tileEntity) {
			super(tileEntity);
		}

<<<<<<< HEAD
		@Override
		protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {

			String machineName = findMachine(inputs, fluidInputs);
			RecipeMap<?> recipeM = getRecipeMaps(machineName);

			if (recipeM == null) { return null; }

			Recipe r = recipeM.findRecipe(machineTierVoltage, inputs, fluidInputs, this.getMinTankCapacity(this.getOutputTank()));

			if (r == null) { return null; }

			int minMultiplier = Integer.MAX_VALUE;
=======
		/*
		  Overridden solely to update the machine stack and the recipe map at an early point.
		  Recipe multiplication will come at a later time.
		*/
		@Override
		protected Recipe findRecipe(long maxVoltage,
		                            IItemHandlerModifiable inputs,
		                            IMultipleTankHandler fluidInputs) {

			//Update the machine stack and recipe map
			findMachineStack();

			// Avoid crashing during load, when GTCE initializes its multiblock previews
			if(machineItemStack.isEmpty() || this.recipeMap == null) {
				return null;
			}

			return this.recipeMap.findRecipe(Math.min(this.machineVoltage, maxVoltage),
			                                 inputs,
			                                 fluidInputs,
			                                 this.getMinTankCapacity(this.getOutputTank()));
		}


		protected Recipe multiplyRecipe(IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs, Recipe recipe, ItemStack machineStack, RecipeMap<?> rmap) {
			//Check if passed a null recipemap or machine stack
			if(rmap == null || machineStack == null) {
				return null;
			}

			//Find the number of machines
			this.numberOfMachines = Math.min(GAConfig.processingArray.processingArrayMachineLimit, machineStack.getCount());

			Set<ItemStack> ingredientStacks = findAllItemsInInputs(inputs);
			Map<String, Integer> fluidStacks = findAllFluidsInInputs(fluidInputs);

			int itemMultiplier = getMinRatioItem(ingredientStacks, recipe, this.numberOfMachines);
			int fluidMultiplier = getMinRatioFluid(fluidStacks, recipe, this.numberOfMachines);
>>>>>>> acc299ca062120e0e99cf1882f721128927e4a05

			Set<ItemStack> countIngredients = new HashSet<>();
			if (r.getInputs().size() != 0) {

				this.findIngredients(countIngredients, inputs);
				minMultiplier = Math.min(minMultiplier, this.getMinRatioItem(countIngredients, r, inputs, this.numberOfMachines));

			}

			Map<String, Integer> countFluid = new HashMap<>();
			if (r.getFluidInputs().size() != 0) {

				this.findFluid(countFluid, fluidInputs);
				minMultiplier = Math.min(minMultiplier, this.getMinRatioFluid(countFluid, r, this.numberOfMachines));
			}

			if (minMultiplier == Integer.MAX_VALUE) {
				GTLog.logger.error("Cannot calculate ratio of items for processing array");
				return null;
			}

			this.numberOfOperations = minMultiplier;

			List<CountableIngredient> newRecipeInputs = new ArrayList<>();
			List<FluidStack> newFluidInputs = new ArrayList<>();
			List<ItemStack> outputI = new ArrayList<>();
			List<FluidStack> outputF = new ArrayList<>();
<<<<<<< HEAD
			this.multiplyInputsAndOutputs(newRecipeInputs, newFluidInputs, outputI, outputF, r, numberOfOperations);

			RecipeBuilder<?> newRecipe = recipeM.recipeBuilder().inputsIngredients(newRecipeInputs).fluidInputs(newFluidInputs).outputs(outputI).fluidOutputs(outputF).EUt(r.getEUt()).duration(r.getDuration());

			copyChancedItemOutputs(newRecipe, r, numberOfOperations);
			newRecipe.notConsumable(this.machineItemStack);

			return (Recipe) newRecipe.build().getResult();
		}

		protected void copyChancedItemOutputs(RecipeBuilder<?> newRecipe, Recipe oldRecipe, int numberOfOperations) {
			for (ChanceEntry s : oldRecipe.getChancedOutputs()) {
				int chance = s.getChance();
				ItemStack itemStack = s.getItemStack().copy();
				int boost = s.getBoostPerTier();
=======
			this.multiplyInputsAndOutputs(newRecipeInputs,
			                              newFluidInputs,
			                              outputI,
			                              outputF,
			                              recipe,
			                              minMultiplier);

			RecipeBuilder<?> newRecipe = rmap.recipeBuilder()
			                                 .inputsIngredients(newRecipeInputs)
			                                 .fluidInputs(newFluidInputs)
			                                 .outputs(outputI)
			                                 .fluidOutputs(outputF)
			                                 .EUt(recipe.getEUt())
			                                 .duration(recipe.getDuration());

			//Don't allow MV or LV macerators to have chanced outputs, because they do not have the slots for chanced
			MetaTileEntity mte = MachineItemBlock.getMetaTileEntity(machineStack);
			if(!(mte instanceof MetaTileEntityMacerator && this.machineTier < GTValues.HV))
				copyChancedItemOutputs(newRecipe, recipe, minMultiplier);

			this.numberOfOperations = minMultiplier;
			return newRecipe.build().getResult();
		}

		protected static void copyChancedItemOutputs(RecipeBuilder<?> newRecipe,
		                                             Recipe oldRecipe,
		                                             int numberOfOperations) {
			for(ChanceEntry entry : oldRecipe.getChancedOutputs()) {
				int chance = entry.getChance();
				ItemStack itemStack = entry.getItemStack().copy();
				int boost = entry.getBoostPerTier();
>>>>>>> acc299ca062120e0e99cf1882f721128927e4a05
				itemStack.setCount(itemStack.getCount() * numberOfOperations);

				newRecipe.chancedOutput(itemStack, chance, boost);

			}
		}

<<<<<<< HEAD
		protected void findIngredients(Set<ItemStack> countIngredients, IItemHandlerModifiable inputs) {

			for (int slot = 0; slot < inputs.getSlots(); slot++) {
				ItemStack wholeItemStack = inputs.getStackInSlot(slot);
				String name = wholeItemStack.getItem().getUnlocalizedNameInefficiently(wholeItemStack);

				// skip empty slots
				if (name.equals("tile.air")) {
					continue;
				}

				boolean found = false;
				for (ItemStack i : countIngredients) {
					if (ItemStack.areItemsEqual(i, wholeItemStack)) {
						i.setCount(i.getCount() + wholeItemStack.getCount());
						found = true;
						break;
					}

				}
				if (!found) {
					countIngredients.add(wholeItemStack.copy());
				}

			}
		}

		protected int getMinRatioItem(Set<ItemStack> countIngredients, Recipe r, IItemHandlerModifiable inputs, int numberOfMachines) {
=======
		protected List<IItemHandlerModifiable> getInputBuses() {
			RecipeMapMultiblockController controller = (RecipeMapMultiblockController) metaTileEntity;
			return controller.getAbilities(MultiblockAbility.IMPORT_ITEMS);
		}

		protected static Set<ItemStack> findAllItemsInInputs(IItemHandlerModifiable inputs) {
			Hash.Strategy<ItemStack> strategy = ItemStackHashStrategy.comparingAllButCount();
			final Supplier<Map<ItemStack, Integer>> mapSupplier =
				() -> new Object2IntOpenCustomHashMap<>(strategy);

			final Set<ItemStack> result = new ObjectOpenCustomHashSet<>(strategy);

			StreamUtils.streamFrom(inputs)
			           // keep only non-empty item stacks
			           .filter(not(ItemStack::isEmpty))
			           // Track the number of identical items
			           .collect(Collectors.toMap(Function.identity(),
			                                     ItemStack::getCount,
			                                     Math::addExact,
			                                     mapSupplier))
			           // Create a single stack of the combined count for each item
			           .entrySet().stream()
			           .map(entry -> {
				           ItemStack combined = entry.getKey().copy();
				           combined.setCount(entry.getValue());
				           return combined;
			           })
			           .forEach(result::add);
			return result;
		}

		protected int getMinRatioItem(Set<ItemStack> countIngredients,
		                              Recipe recipe,
		                              int numberOfMachines) {
>>>>>>> acc299ca062120e0e99cf1882f721128927e4a05

			int minMultiplier = Integer.MAX_VALUE;
			for (CountableIngredient ci : r.getInputs()) {

				if (ci.getCount() == 0) {
					continue;
				}

				for (ItemStack wholeItemStack : countIngredients) {

					if (ci.getIngredient().apply(wholeItemStack)) {
						int ratio = Math.min(numberOfMachines, wholeItemStack.getCount() / ci.getCount());
						if (ratio < minMultiplier) {
							minMultiplier = ratio;
						}
						break;
					}

				}
			}
			return minMultiplier;
		}

<<<<<<< HEAD
		protected void findFluid(Map<String, Integer> countFluid, IMultipleTankHandler fluidInputs) {
=======
		protected static Map<String, Integer> findAllFluidsInInputs(IMultipleTankHandler fluidInputs) {
>>>>>>> acc299ca062120e0e99cf1882f721128927e4a05

			for (IFluidTank tank : fluidInputs) {

				if (tank.getFluid() != null) {

					String name = tank.getFluid().getUnlocalizedName();

					if (countFluid.containsKey(name)) {
						int existingValue = countFluid.get(name);
						countFluid.put(name, existingValue + tank.getFluidAmount());

					} else {
						countFluid.put(name, tank.getFluidAmount());
					}
				}
			}

<<<<<<< HEAD
		}
=======
		protected int getMinRatioFluid(Map<String, Integer> countFluid,
		                               Recipe recipe,
		                               int numberOfMachines) {
>>>>>>> acc299ca062120e0e99cf1882f721128927e4a05

		protected int getMinRatioFluid(Map<String, Integer> countFluid, Recipe r, int numberOfMachines) {
			int minMultiplier = Integer.MAX_VALUE;
			for (FluidStack fs : r.getFluidInputs()) {
				String name = fs.getFluid().getUnlocalizedName();
				int ratio = Math.min(numberOfMachines, countFluid.get(name) / fs.amount);

				if (ratio < minMultiplier) {
					minMultiplier = ratio;
				}

			}
			return minMultiplier;
		}

<<<<<<< HEAD
		protected void multiplyInputsAndOutputs(List<CountableIngredient> newRecipeInputs, List<FluidStack> newFluidInputs, List<ItemStack> outputI, List<FluidStack> outputF, Recipe r, int numberOfOperations) {
			for (CountableIngredient ci : r.getInputs()) {
				CountableIngredient newIngredient = new CountableIngredient(ci.getIngredient(), ci.getCount() * numberOfOperations);
				newRecipeInputs.add(newIngredient);
			}
			for (FluidStack fs : r.getFluidInputs()) {
				FluidStack newFluid = new FluidStack(fs.getFluid(), fs.amount * numberOfOperations);
				newFluidInputs.add(newFluid);
			}
			for (ItemStack s : r.getOutputs()) {
				int num = s.getCount() * numberOfOperations;
				ItemStack itemCopy = s.copy();
				itemCopy.setCount(num);
				outputI.add(itemCopy);
			}
			for (FluidStack f : r.getFluidOutputs()) {
				int fluidNum = f.amount * numberOfOperations;
				FluidStack fluidCopy = f.copy();
				fluidCopy.amount = fluidNum;
				outputF.add(fluidCopy);
			}
		}

		protected String findMachine(IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {

			for (int slot = 0; slot < inputs.getSlots(); slot++) {
				// find tileentity
				ItemStack wholeItemStack = inputs.getStackInSlot(slot);
				String unlocalizedName = wholeItemStack.getItem().getUnlocalizedNameInefficiently(wholeItemStack);
				if (unlocalizedName.contains("gregtech.machine") || unlocalizedName.contains("gtadditions.machine")) {
					this.numberOfMachines = Math.min(16, wholeItemStack.getCount());
					String trimmedName = "";
					String voltage = unlocalizedName.substring(unlocalizedName.lastIndexOf(".") + 1);
					trimmedName = unlocalizedName.substring(0, unlocalizedName.lastIndexOf("."));

					//Checks if the tile entity is actually a machine
					if(!GAEnums.voltageMap.containsKey(voltage)) {
						continue;
					}
					this.machineName = trimmedName.substring(trimmedName.lastIndexOf(".") + 1);
					this.machineTierVoltage = GAEnums.voltageMap.get(voltage);
					this.machineItemStack = wholeItemStack;
					break;

=======
		protected static ItemStack copyItemStackWithCount(ItemStack itemStack, int count) {
			ItemStack itemCopy = itemStack.copy();
			itemCopy.setCount(count);
			return itemCopy;
		}

		protected static FluidStack copyFluidStackWithAmount(FluidStack fluidStack, int count) {
			FluidStack fluidCopy = fluidStack.copy();
			fluidCopy.amount = count;
			return fluidCopy;
		}

		protected void multiplyInputsAndOutputs(List<CountableIngredient> newRecipeInputs,
		                                        List<FluidStack> newFluidInputs,
		                                        List<ItemStack> outputItems,
		                                        List<FluidStack> outputFluids,
		                                        Recipe recipe,
		                                        int numberOfOperations) {

			recipe.getInputs().forEach(ci ->
				                           newRecipeInputs.add(new CountableIngredient(ci.getIngredient(),
				                                                                       ci.getCount() * numberOfOperations)));

			recipe.getFluidInputs().forEach(fluidStack ->
				                                newFluidInputs.add(new FluidStack(fluidStack.getFluid(),
				                                                                  fluidStack.amount * numberOfOperations)));

			recipe.getOutputs().forEach(itemStack ->
				                            outputItems.add(copyItemStackWithCount(itemStack,
				                                                                   itemStack.getCount() * numberOfOperations)));

			recipe.getFluidOutputs().forEach(fluidStack ->
				                                 outputFluids.add(copyFluidStackWithAmount(fluidStack,
				                                                                           fluidStack.amount * numberOfOperations)));
		}

		public void invalidate() {
			this.lastRecipeIndex = 0;
		}

		//Finds the Recipe Map of the passed Machine Stack and checks if it is a valid Recipe Map
		public static RecipeMap<?> findRecipeMapAndCheckValid(ItemStack machineStack) {

			if(machineStack == null || machineStack.isEmpty()) {
				return null;
			}

			String unlocalizedName = machineStack.getItem().getUnlocalizedNameInefficiently(machineStack);
			String recipeMapName = findRecipeMapName(unlocalizedName);


			//Check the machine against the Config blacklist
			if(!findMachineInBlacklist(recipeMapName)) {

				RecipeMap<?> rmap = RecipeMap.getByName(recipeMapName);

				if(rmap == null) {
					return null;
				}

				RecipeBuilder<?> rbuilder = rmap.recipeBuilder();

				//Find the RecipeMap of the MTE and ensure that the Processing Array only works on SimpleRecipeBuilders
				//For some reason GTCE has specialized recipe maps for some machines, when it does not need them
				if (rbuilder instanceof SimpleRecipeBuilder ||
					rbuilder instanceof IntCircuitRecipeBuilder ||
					rbuilder instanceof ArcFurnaceRecipeBuilder ||
					rbuilder instanceof CutterRecipeBuilder ||
					rbuilder instanceof UniversalDistillationRecipeBuilder) {

					return rmap;
>>>>>>> acc299ca062120e0e99cf1882f721128927e4a05
				}
			}
			return null;
		}

		protected static String findRecipeMapName(String unlocalizedName) {

			String trimmedName = unlocalizedName.substring(0, unlocalizedName.lastIndexOf("."));
			trimmedName = trimmedName.substring(trimmedName.lastIndexOf(".") + 1);

			//Catch some cases where the machine's name is not the same as its recipe map's name
			switch (trimmedName) {
				case "cutter":
					trimmedName = "cutting_saw";
					break;
				case "electric_furnace":
					trimmedName = "furnace";
					break;
				case "ore_washer":
					trimmedName = "orewasher";
					break;
				case "brewery":
					trimmedName = "brewer";
			}

			return trimmedName;
		}

		protected static boolean findMachineInBlacklist(String unlocalizedName) {

			String[] blacklist = GAConfig.processingArray.machineBlackList;

			return Arrays.asList(blacklist).contains(unlocalizedName);
		}

		public void findMachineStack() {

			RecipeMapMultiblockController controller = (RecipeMapMultiblockController) this.metaTileEntity;

			//The Processing Array is limited to 1 Machine Interface per multiblock, and only has 1 slot
			ItemStack machine = controller.getAbilities(GACapabilities.PA_MACHINE_CONTAINER).get(0).getStackInSlot(0);

			RecipeMap<?> rmap = findRecipeMapAndCheckValid(machine);

			MetaTileEntity mte = MachineItemBlock.getMetaTileEntity(machine);

			//Find the voltage tier of the machine.
			this.machineTier = mte instanceof ITieredMetaTileEntity
				? ((ITieredMetaTileEntity) mte).getTier()
				: 0;

			this.machineVoltage = GTValues.V[this.machineTier];

			this.machineItemStack = machine;
			this.recipeMap = rmap;
		}

		/**
		 * Sets up and consumes recipe inputs, considering all inputs. Used when Distinct Bus Mode is disabled.
		 * @param recipe    the recipe to prepare to run
		 * @return {@code true} if the recipe was successfully set up and ingredients consumed, or
		 *         {@code false} if the recipe could not be configured and no work was done.
		 * @see #setupAndConsumeRecipeInputs(Recipe recipe, IItemHandlerModifiable importInventory)
		 */
		@Override
		protected boolean setupAndConsumeRecipeInputs(Recipe recipe) {
			// use all input buses
			return setupAndConsumeRecipeInputs(recipe, getInputInventory());
		}

		/**
		 * Sets up and consumes recipe inputs targeting a predetermined input bus. Used for Distinct Bus mode.
		 * @param recipe    the recipe to prepare to run
		 * @param index     the index of the predetermined index bus
		 * @return {@code true} if the recipe was successfully set up and ingredients consumed, or
		 *         {@code false} if the recipe could not be configured and no work was done.
		 * @see #setupAndConsumeRecipeInputs(Recipe recipe, IItemHandlerModifiable importInventory)
		 */
		protected boolean setupAndConsumeRecipeInputs(Recipe recipe, int index) {
			// use the specified input bus
			return setupAndConsumeRecipeInputs(recipe, getInputBuses().get(index));
		}

		/**
		 * If possible, consumes the ingredients for a recipe from the target inventory in preparation for starting the
		 * craft.
		 *
		 * @param recipe          the recipe to prepare to run
		 * @param importInventory the inventory to check for ingredients
		 * @return {@code true} if the recipe was successfully set up and ingredients consumed, or
		 *         {@code false} if the recipe could not be configured and no work was done.
		 */
		protected boolean setupAndConsumeRecipeInputs(Recipe recipe, IItemHandlerModifiable importInventory) {
			IItemHandlerModifiable exportInventory = getOutputInventory();
			IMultipleTankHandler importFluids = getInputTank();
			IMultipleTankHandler exportFluids = getOutputTank();

<<<<<<< HEAD
			/*
			 * this.numberOfOperations = 0;
			 *
			 * for (int i = 0; i < numberOfMachines; i++) { if
			 * (MetaTileEntity.addItemsToItemHandler(exportInventory, true,
			 * recipe.getAllItemOutputs(exportInventory.getSlots())) &&
			 * MetaTileEntity.addFluidsToFluidHandler(exportFluids, true,
			 * recipe.getFluidOutputs())) {
			 *
			 *
			 * if (recipe.matches(true, importInventory, importFluids)) {
			 * numberOfOperations++; } else { break; } } }
			 */

			int[] resultOverclock = calculateOverclock(recipe.getEUt(), machineTierVoltage, recipe.getDuration());
			int totalEUt = resultOverclock[0] * resultOverclock[1] * this.numberOfOperations;

			boolean enoughPower = totalEUt >= 0 ? getEnergyStored() >= (totalEUt > getEnergyCapacity() / 2 ? resultOverclock[0] : totalEUt) : getEnergyStored() - resultOverclock[0] * this.numberOfOperations <= getEnergyCapacity();

			if (!enoughPower) { return false; }

			return MetaTileEntity.addItemsToItemHandler(exportInventory, true, recipe.getAllItemOutputs(exportInventory.getSlots())) && MetaTileEntity.addFluidsToFluidHandler(exportFluids, true, recipe.getFluidOutputs()) && recipe.matches(true, importInventory, importFluids);
=======
			if(!haveEnoughPowerToProceed(recipe, machineVoltage, this.numberOfOperations))
				return false;

			return MetaTileEntity.addItemsToItemHandler(exportInventory,
			                                            true,
			                                            recipe.getAllItemOutputs(exportInventory.getSlots())) &&
				MetaTileEntity.addFluidsToFluidHandler(exportFluids, true, recipe.getFluidOutputs()) &&
				recipe.matches(true, importInventory, importFluids);
		}

		/**
		 * Determines if there is sufficient energy buffer to proceed with running a parallelized recipe overclocked to
		 * a given tier.
		 *
		 * @param recipe        the Recipe to perform
		 * @param voltageTier   the voltage tier to overclock to in the computations
		 * @param numOperations the number of times this recipe is to be multiplied by
		 * @return {@code true} if there is enough energy to proceed, {@code false} otherwise.
		 */
		protected boolean haveEnoughPowerToProceed(Recipe recipe, long voltageTier, int numOperations) {
			//Format: EU/t, duration
			int[] resultOverclock = calculateOverclock(recipe.getEUt(), voltageTier, recipe.getDuration());
			int totalEU = resultOverclock[0] * resultOverclock[1] * numOperations;
			int EUt = resultOverclock[0] * numOperations;

			boolean enoughPower;
			if(totalEU >= 0) {
				int capacity;
				if(totalEU > getEnergyCapacity() / 2)
					capacity = EUt;
				else
					capacity = totalEU;
				enoughPower = getEnergyStored() >= capacity;
			} else {
				int power = EUt * numOperations;
				enoughPower = getEnergyStored() - (long) power <= getEnergyCapacity();
			}

			return enoughPower;
		}

		/**
		 * Will check if the previous machine stack and the current machine stack are different
		 * @param newMachineStack - The current machine stack
		 * @return - true if the machine stacks are not equal, false if they are equal
		 */
		protected boolean didMachinesChange(ItemStack newMachineStack) {
			if(newMachineStack == null || this.oldMachineStack == null)
				return newMachineStack != this.oldMachineStack;

			return !ItemStack.areItemStacksEqual(this.oldMachineStack, newMachineStack);
>>>>>>> acc299ca062120e0e99cf1882f721128927e4a05
		}

		@Override
		protected void trySearchNewRecipe() {
			if(metaTileEntity instanceof TileEntityProcessingArray && ((TileEntityProcessingArray) metaTileEntity).isDistinctInputBusMode) {
				trySearchNewRecipeDistinct();
			}
			else {
				trySearchNewRecipeCombined();
			}
		}

		private void trySearchNewRecipeCombined() {
			long maxVoltage = getMaxVoltage();
			Recipe currentRecipe;
			Recipe multipliedRecipe = null;
			IItemHandlerModifiable importInventory = getInputInventory();
			IMultipleTankHandler importFluids = getInputTank();

			//Update the stored machine stack and recipe map variables
			findMachineStack();

			boolean dirty = checkRecipeInputsDirty(importInventory, importFluids);
<<<<<<< HEAD
			if (dirty || forceRecipeRecheck) {
				this.forceRecipeRecheck = false;
=======
			if(dirty || forceRecipeRecheck) {
				//Check if the machine that the PA is operating on has changed
				if(didMachinesChange(machineItemStack)) {
					previousRecipe = null;
					oldMachineStack = null;
				}
			}
>>>>>>> acc299ca062120e0e99cf1882f721128927e4a05

			if(previousRecipe != null &&
				previousRecipe.matches(false, importInventory, importFluids)) {
				currentRecipe = previousRecipe;
			}
			else {
				//If the previous recipe was null, or does not match the current recipe, search for a new recipe
				currentRecipe = findRecipe(maxVoltage, importInventory, importFluids);
<<<<<<< HEAD
				if (currentRecipe != null) {
					this.previousRecipe = currentRecipe;
				}
			} else if (previousRecipe != null && previousRecipe.matches(false, importInventory, importFluids)) {
				// if previous recipe still matches inputs, try to use it
=======
				oldMachineStack = null;

				//Update the previous recipe
				if(currentRecipe != null) {
					this.previousRecipe = currentRecipe;
				}

				this.forceRecipeRecheck = false;
			}

			if(currentRecipe != null) {
				multipliedRecipe = multiplyRecipe(importInventory, importFluids, currentRecipe, machineItemStack, recipeMap);
			}

			//Attempts to run the current recipe, if it is not null
			if(multipliedRecipe != null && setupAndConsumeRecipeInputs(multipliedRecipe)) {
				oldMachineStack = machineItemStack;
				setupRecipe(multipliedRecipe);
			}
		}

		// ------------------------------ Distinct Bus Logic -----------------------------------------------
		// Distinct bus logic thanks to dan

		private void trySearchNewRecipeDistinct() {
			long maxVoltage = getMaxVoltage();
			Recipe currentRecipe = null;
			Recipe multipliedRecipe = null;
			List<IItemHandlerModifiable> importInventory = getInputBuses();
			IMultipleTankHandler importFluids = getInputTank();
			RecipeMapMultiblockController controller = (RecipeMapMultiblockController) this.metaTileEntity;
			IItemHandlerModifiable machineBus = controller.getAbilities(GACapabilities.PA_MACHINE_CONTAINER).get(0);

			//Update the stored machine stack and recipe map variables
			findMachineStack();

			// Cache the old fluid inputs
			final FluidStack[] cachedLastFluids =
				(lastFluidInputs == null) ? null : Arrays.copyOf(this.lastFluidInputs, lastFluidInputs.length);

			//Check to see if the machine stack has changed first
			//TODO Could this machine bus specific check be used in the combined code?
			boolean machineDirty = checkRecipeInputsDirty(machineBus, importFluids);
			if(machineDirty || forceRecipeRecheck) {
				//Check if the machine that the PA is operating on has changed
				//TODO Is this check needed if machineDirty is true?
				if(didMachinesChange(machineItemStack)) {
					previousRecipe = null;
					oldMachineStack = null;
				}
			}

			//Check if the previous recipe is null, to avoid having to iterate the distinct inputs
			if(previousRecipe != null && previousRecipe.matches(false, importInventory.get(lastRecipeIndex), importFluids)) {
>>>>>>> acc299ca062120e0e99cf1882f721128927e4a05
				currentRecipe = previousRecipe;
				multipliedRecipe = multiplyRecipe(importInventory.get(lastRecipeIndex), importFluids, currentRecipe, machineItemStack, recipeMap);
				if(setupAndConsumeRecipeInputs(multipliedRecipe, lastRecipeIndex)) {
					setupRecipe(multipliedRecipe);
					oldMachineStack = machineItemStack;
					return;
				}
			}

			//If the machine stack changed, or the previous recipe is null, check for a new recipe
			oldMachineStack = null;
			for (int i = 0; i < importInventory.size(); i++) {
				IItemHandlerModifiable bus = importInventory.get(i);
				// Restore the cached fluid inputs before each per-bus dirty check to prevent false negatives
				lastFluidInputs =
					(cachedLastFluids == null) ? null : Arrays.copyOf(cachedLastFluids, cachedLastFluids.length);
				boolean dirty = checkRecipeInputsDirty(bus, importFluids, i);
				if (dirty || forceRecipeRecheck) {
					this.forceRecipeRecheck = false;
					currentRecipe = findRecipe(maxVoltage, bus, importFluids);
					if(currentRecipe != null) {
						this.previousRecipe = currentRecipe;
					}
				}
				if(currentRecipe != null) {
					multipliedRecipe = multiplyRecipe(bus, importFluids, currentRecipe, machineItemStack, recipeMap);
				}

				if(multipliedRecipe != null && setupAndConsumeRecipeInputs(multipliedRecipe, i)) {
					lastRecipeIndex = i;
					setupRecipe(multipliedRecipe);
					oldMachineStack = machineItemStack;
					break;
				}
			}
<<<<<<< HEAD
			if (currentRecipe != null && setupAndConsumeRecipeInputs(currentRecipe)) {
				setupRecipe(currentRecipe);
			}

=======
>>>>>>> acc299ca062120e0e99cf1882f721128927e4a05
		}

		// Replacing this for optimization reasons
		protected boolean checkRecipeInputsDirty(IItemHandler inputs, IMultipleTankHandler fluidInputs, int index) {
			boolean shouldRecheckRecipe = false;

			if (lastItemInputsMatrix == null || lastItemInputsMatrix.length != getInputBuses().size()) {
				lastItemInputsMatrix = new ItemStack[getInputBuses().size()][];
			}
			if (lastItemInputsMatrix[index] == null || lastItemInputsMatrix[index].length != inputs.getSlots()) {
				this.lastItemInputsMatrix[index] = new ItemStack[inputs.getSlots()];
				Arrays.fill(lastItemInputsMatrix[index], ItemStack.EMPTY);
			}
			if (lastFluidInputs == null || lastFluidInputs.length != fluidInputs.getTanks()) {
				this.lastFluidInputs = new FluidStack[fluidInputs.getTanks()];
			}
			for (int i = 0; i < lastItemInputsMatrix[index].length; i++) {
				ItemStack currentStack = inputs.getStackInSlot(i);
				ItemStack lastStack = lastItemInputsMatrix[index][i];
				if (!areItemStacksEqual(currentStack, lastStack)) {
					this.lastItemInputsMatrix[index][i] = currentStack.isEmpty() ? ItemStack.EMPTY : currentStack.copy();
					shouldRecheckRecipe = true;
				} else if (currentStack.getCount() != lastStack.getCount()) {
					lastStack.setCount(currentStack.getCount());
					shouldRecheckRecipe = true;
				}
			}
			for (int i = 0; i < lastFluidInputs.length; i++) {
				FluidStack currentStack = fluidInputs.getTankAt(i).getFluid();
				FluidStack lastStack = lastFluidInputs[i];
				if ((currentStack == null && lastStack != null) ||
					(currentStack != null && !currentStack.isFluidEqual(lastStack))) {
					this.lastFluidInputs[i] = currentStack == null ? null : currentStack.copy();
					shouldRecheckRecipe = true;
				} else if (currentStack != null && lastStack != null &&
					currentStack.amount != lastStack.amount) {
					lastStack.amount = currentStack.amount;
					shouldRecheckRecipe = true;
				}
			}
			return shouldRecheckRecipe;
		}

		// ------------------------------- End Distinct Bus Logic ------------------------------------------------

		@Override
		protected void setupRecipe(Recipe recipe) {
			int[] resultOverclock = calculateOverclock(recipe.getEUt(), machineVoltage, recipe.getDuration());
			this.progressTime = 1;
			setMaxProgress(resultOverclock[1]);
			this.recipeEUt = resultOverclock[0] * this.numberOfOperations;
			this.fluidOutputs = GTUtility.copyFluidList(recipe.getFluidOutputs());
<<<<<<< HEAD
			int tier = getMachineTierForRecipe(recipe);
			this.itemOutputs = GTUtility.copyStackList(recipe.getResultItemOutputs(getOutputInventory().getSlots(), random, tier));
			try {
				if (this.wasActiveAndNeedsUpdateField.getBoolean(this)) {
					this.wasActiveAndNeedsUpdateField.set(this, false);
				} else {

					setActive(true);
					// this.setActiveMethod.invoke(this,true);
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

=======
			int tier = Math.min(getMachineTierForRecipe(recipe), machineTier);
			this.itemOutputs = GTUtility.copyStackList(recipe.getResultItemOutputs(getOutputInventory().getSlots(),
			                                                                       random,
			                                                                       tier));

			if(this.wasActiveAndNeedsUpdate)
				this.wasActiveAndNeedsUpdate = false;
			else
				setActive(true);
>>>>>>> acc299ca062120e0e99cf1882f721128927e4a05
		}

		// hack over the internal AbstractRecipeLogic setActive method
		protected void setActive(boolean active) {
			ObfuscationReflectionHelper.setPrivateValue(AbstractRecipeLogic.class, recipeMapWorkable, active, "isActive");
			metaTileEntity.markDirty();
			if (!metaTileEntity.getWorld().isRemote) {
				writeCustomData(1, buf -> buf.writeBoolean(active));
			}
		}

	}
}
