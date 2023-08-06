package com.rydelfox.morestoragedrawers.datagen;

import com.rydelfox.morestoragedrawers.block.DrawerMaterial;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class DrawerLootTableProvider extends BaseLootTables {

    public DrawerLootTableProvider(PackOutput dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        for(DrawerMaterial material : DrawerMaterial.values()) {
            if (material.getMod() != null && material.getMod().isLoaded())
                lootTables.put(material.getTrim(), createStandardTable(material.getName()+"_trim", material.getTrim()));
        }
    }
}
