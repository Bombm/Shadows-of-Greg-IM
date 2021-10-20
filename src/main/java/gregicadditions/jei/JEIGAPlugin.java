package gregicadditions.jei;

<<<<<<< HEAD
=======
import gregicadditions.item.*;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
>>>>>>> acc299ca062120e0e99cf1882f721128927e4a05
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

@JEIPlugin
public class JEIGAPlugin implements IModPlugin {

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new GAMultiblockInfoCategory(registry.getJeiHelpers()));
	}

	@Override
	public void register(IModRegistry registry) {
		GAMultiblockInfoCategory.registerRecipes(registry);
		// Workaround - hide the Coke Oven blocks until we can remove them without ID shift
		registry.getJeiHelpers()
		        .getIngredientBlacklist()
		        .addIngredientToBlacklist(GAMetaBlocks.MUTLIBLOCK_CASING
			                                  .getItemVariant(GAMultiblockCasing.CasingType.COKE_OVEN_BRICKS));


		//Multiblock info page registration
		GAMultiblockInfoCategory.multiblockRecipes.values().forEach(v -> {
			MultiblockInfoPage infoPage = v.getInfoPage();
			registry.addIngredientInfo(infoPage.getController().getStackForm(),
					VanillaTypes.ITEM,
					infoPage.getDescription());
		});
	}
}