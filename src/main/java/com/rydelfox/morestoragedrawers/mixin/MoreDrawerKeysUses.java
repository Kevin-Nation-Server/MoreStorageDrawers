package com.rydelfox.morestoragedrawers.mixin;

import com.jaquadro.minecraft.storagedrawers.item.ItemKey;
import com.jaquadro.minecraft.storagedrawers.util.WorldUtils;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemKey.class)
public class MoreDrawerKeysUses {
    @Inject(method = "useOn", at = @At("HEAD"))
    protected void injectMoreBlocks(UseOnContext context, CallbackInfoReturnable info) {
        BlockEntity blockEntity = WorldUtils.getBlockEntity(context.getLevel(), context.getClickedPos(), BlockEntity.class);
//System.out.println("########################### MIXIN MIXIN MIXIN MIXIN MIXIN MIXIN MIXIN MIXIN MIXIN MIXIN MIXIN");
    }
}
