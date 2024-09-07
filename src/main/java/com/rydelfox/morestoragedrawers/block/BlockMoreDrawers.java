package com.rydelfox.morestoragedrawers.block;

//import com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers;
import com.rydelfox.morestoragedrawers.block.tile.BlockEntityDrawersMore;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class BlockMoreDrawers extends BlockStandardDrawers {
    public BlockMoreDrawers (int drawerCount, boolean halfDepth, int storageUnits, BlockBehaviour.Properties properties) {
        super(drawerCount, halfDepth, storageUnits, properties);
    }

    public BlockMoreDrawers (int drawerCount, boolean halfDepth, BlockBehaviour.Properties properties) {
        super(drawerCount, halfDepth, calcUnits(drawerCount, halfDepth), properties);
    }

    private static int calcUnits(int drawerCount, boolean halfDepth) {
        return halfDepth ? 4 / drawerCount : 8 / drawerCount;
    }

    @Override
    protected int getFaceSlot (Direction correctSide, @NotNull Vec3 normalizedHit) {
        if (!hitWithinArea(correctSide, normalizedHit, .0625f, .9375f))
            return super.getFaceSlot(correctSide, normalizedHit);

        if (getDrawerCount() == 1)
            return 0;

        boolean hitTop = hitWithinY(normalizedHit, .5f, 1f);
        if (getDrawerCount() == 2)
            return hitTop ? 0 : 1;

        if (getDrawerCount() == 4) {
            if (hitWithinX(correctSide, normalizedHit, 0, .5f))
                return hitTop ? 0 : 2;
            else
                return hitTop ? 1 : 3;
        }

        return super.getFaceSlot(correctSide, normalizedHit);
    }
    @Override
    public BlockEntityDrawersMore newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityDrawersMore.createEntity(getDrawerCount(), pos, state);
    }
}