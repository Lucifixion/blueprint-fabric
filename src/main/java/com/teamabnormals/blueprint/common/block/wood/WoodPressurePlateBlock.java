package com.teamabnormals.blueprint.common.block.wood;

import com.teamabnormals.blueprint.common.block.BlueprintPressurePlateBlock;
import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * A {@link BlueprintPressurePlateBlock} extension that fills its item after the latest vanilla wooden pressure plate item.
 */
public class WoodPressurePlateBlock extends BlueprintPressurePlateBlock {
	private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(() -> Items.MANGROVE_PRESSURE_PLATE);

	public WoodPressurePlateBlock(Sensitivity sensitivityIn, Properties propertiesIn) {
		super(sensitivityIn, propertiesIn);
	}

	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this.asItem(),group, items);
	}
}
