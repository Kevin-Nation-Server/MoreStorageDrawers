package com.rydelfox.morestoragedrawers.core;

import com.jaquadro.minecraft.storagedrawers.core.ModBlocks;
import com.rydelfox.morestoragedrawers.MoreStorageDrawers;
import com.rydelfox.morestoragedrawers.block.BlockDrawersExtended;
import com.rydelfox.morestoragedrawers.block.DrawerMaterial;
import com.rydelfox.morestoragedrawers.block.tile.Tiles;
import com.rydelfox.morestoragedrawers.datagen.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.rydelfox.morestoragedrawers.MoreStorageDrawers.*;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Registration {
    @SubscribeEvent
    public static void onBlockRegistry(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.BLOCKS, (helper) -> {
            logInfo("MoreStorageDrawers: Registering Blocks");
            for (DrawerMaterial material : DrawerMaterial.values()) {
                if (material.getMod() != null && material.getMod().isLoaded())
                    material.registerBlocks(event.getForgeRegistry());
            }
            Tiles.initializeTiles();
        });
    }

    @SubscribeEvent
    public static void onItemRegistry(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.ITEMS, (helper) -> {
            logInfo("MoreStorageDrawers: Registering Items");
            for (DrawerMaterial material : DrawerMaterial.values()) {
                if (material.getMod() != null && material.getMod().isLoaded())
                    material.registerItems(event.getForgeRegistry());
            }
        });
    }

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        logInfo("MoreStorageDrawers: Running Datagen");
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeServer()) {
            generator.addProvider(true, new DrawerRecipeProvider(packOutput));
            generator.addProvider(true, new ModBlockTagGenerator(packOutput, lookupProvider, helper));
            generator.addProvider(true, new DrawerLootTableProvider(packOutput));
            //generator.addProvider(new DrawerTagsProvider(generator,helper));
        }

        if (event.includeClient()) {
            generator.addProvider(true, new DrawerBlockStateProvider(packOutput, helper));
            generator.addProvider(true, new DrawerItemModelProvider(packOutput, helper));
            generator.addProvider(true, new DrawerLanguageProvider(packOutput, MoreStorageDrawers.MOD_ID, "en_us" ));
        }


        try {
            generator.run();
        } catch (IOException e) {
            logInfo("DataGenerator#run threw an exception");
            e.printStackTrace();
        }
    }


    @OnlyIn(Dist.CLIENT)
    public static void bindRenderTypes() {
        List<Block> alldrawers = new ArrayList<>();
        for (DrawerMaterial material : DrawerMaterial.values()) {
            alldrawers.addAll(material.getBlocks(false));
        }
        for (Block block : alldrawers) {
            if (block instanceof BlockDrawersExtended) {
                ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped());
            }
        }
    }
}