package com.rydelfox.morestoragedrawers.datagen;

import com.rydelfox.morestoragedrawers.block.DrawerMaterial;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class DrawerLanguageProvider extends LanguageProvider {

    public DrawerLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.add("itemGroup.morestoragedrawers", "More Storage Drawers");



        for (DrawerMaterial material : DrawerMaterial.values()) {
            if(material.getMod() != null && material.getMod().isLoaded()) {
                this.add("block.morestoragedrawers."+material.getNamespace()+"_"+material.getName()+"_trim", material.getEnglishName()+" Trim");
                this.add("block.morestoragedrawers."+material.getNamespace()+"_"+material.getName()+"_full_1", material.getEnglishName()+" Drawers 1x1");
                this.add("block.morestoragedrawers."+material.getNamespace()+"_"+material.getName()+"_full_2", material.getEnglishName()+" Drawers 1x2");
                this.add("block.morestoragedrawers."+material.getNamespace()+"_"+material.getName()+"_full_4", material.getEnglishName()+" Drawers 2x2");
                this.add("block.morestoragedrawers."+material.getNamespace()+"_"+material.getName()+"_half_2", material.getEnglishName()+" Half Drawers 1x2");
                this.add("block.morestoragedrawers."+material.getNamespace()+"_"+material.getName()+"_half_1", material.getEnglishName()+" Half Drawers 1x1");
                this.add("block.morestoragedrawers."+material.getNamespace()+"_"+material.getName()+"_half_4", material.getEnglishName()+" Half Drawers 2x2");
            }
        }

    }
}
