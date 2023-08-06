package com.rydelfox.morestoragedrawers.block;

import com.jaquadro.minecraft.storagedrawers.block.BlockDrawers;
import net.minecraft.world.level.block.Block;

public abstract class BlockDrawersExtended extends BlockDrawers {
    public BlockDrawersExtended(int drawerCount, boolean halfDepth, int storageUnits, Block.Properties properties) {
        super(drawerCount, halfDepth, storageUnits, properties);
    }
}