package com.rydelfox.morestoragedrawers;

import com.rydelfox.morestoragedrawers.block.DrawerMaterial;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreStorageDrawers.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register(MoreStorageDrawers.MOD_ID,
            () -> CreativeModeTab.builder().icon(() -> makeIcon())
                    .title(Component.translatable(MoreStorageDrawers.MOD_ID))
                    .displayItems((pParameters, pOutput) -> {
                        for (DrawerMaterial material : DrawerMaterial.values()) {
                            if(material.getMod() != null && material.getMod().isLoaded()) {
                                pOutput.accept(material.getTrim());
                                pOutput.accept(material.getDrawer(1, false));
                                pOutput.accept(material.getDrawer(2, false));
                                pOutput.accept(material.getDrawer(4, false));
                                pOutput.accept(material.getDrawer(1, true));
                                pOutput.accept(material.getDrawer(2, true));
                                pOutput.accept(material.getDrawer(4, true));
                            }
                        }
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final ItemStack makeIcon() {
        for (DrawerMaterial material : DrawerMaterial.values()) {
            if(material.getMod() != null && material.getMod().isLoaded()) {
                return new ItemStack(material.getDrawer(4, false));
            }
        }
        return new ItemStack(Blocks.ACACIA_LOG);
    }

}
