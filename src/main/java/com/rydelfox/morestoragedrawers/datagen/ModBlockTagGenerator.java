package com.rydelfox.morestoragedrawers.datagen;

import com.rydelfox.morestoragedrawers.MoreStorageDrawers;
import com.rydelfox.morestoragedrawers.block.DrawerMaterial;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator  extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MoreStorageDrawers.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        for (DrawerMaterial material : DrawerMaterial.values()) {
            if(material.getMod() != null && material.getMod().isLoaded()) {
                if(material.minableWith =="axe") {
                    this.tag(BlockTags.MINEABLE_WITH_AXE)
                            .add(
                                    material.getTrim(),
                                    material.getDrawer(1, false),
                                    material.getDrawer(2, false),
                                    material.getDrawer(4, false),
                                    material.getDrawer(1, true),
                                    material.getDrawer(2, true),
                                    material.getDrawer(4, true));
                } else if(material.minableWith =="pickaxe") {
                    this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                            .add(
                                    material.getTrim(),
                                    material.getDrawer(1, false),
                                    material.getDrawer(2, false),
                                    material.getDrawer(4, false),
                                    material.getDrawer(1, true),
                                    material.getDrawer(2, true),
                                    material.getDrawer(4, true));
                }
            }
        }
    }
}
