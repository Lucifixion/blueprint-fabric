package com.teamabnormals.blueprint.common.block;

import com.teamabnormals.blueprint.core.util.BlockUtil;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import io.github.fabricators_of_create.porting_lib.tool.ToolAction;
import io.github.fabricators_of_create.porting_lib.tool.ToolActions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * A {@link RotatedPillarBlock} extension that fills its item after the latest vanilla log item.
 */
public class LogBlock extends RotatedPillarBlock {
	private final Supplier<Block> block;

	public LogBlock(Supplier<Block> strippedBlock, Properties properties) {
		super(properties);
		this.block = strippedBlock;
	}

	@Override
	@Nullable
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction action, boolean simulate) {
		if (action == ToolActions.AXE_STRIP)
			return this.block != null ? BlockUtil.transferAllBlockStates(state, this.block.get().defaultBlockState()) : null;
		return super.getToolModifiedState(state, context, action, simulate);
	}
}