package com.rydelfox.morestoragedrawers.block;

//import com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers;

import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawer;
import com.jaquadro.minecraft.storagedrawers.block.BlockTrim;
import com.jaquadro.minecraft.storagedrawers.block.tile.BlockEntityDrawersStandard;
import com.jaquadro.minecraft.storagedrawers.core.ModBlocks;
import com.jaquadro.minecraft.storagedrawers.util.ItemStackMatcher;
import com.rydelfox.morestoragedrawers.MoreStorageDrawers;
import com.rydelfox.morestoragedrawers.block.tile.BlockEntityDrawersMore;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class BlockMoreDrawers extends BlockStandardDrawers {

    private String matKey = null;
    private String matNamespace = MoreStorageDrawers.MOD_ID;

    public BlockMoreDrawers (int drawerCount, boolean halfDepth, int storageUnits, BlockBehaviour.Properties properties) {
        super(drawerCount, halfDepth, storageUnits, properties);
    }

    public BlockMoreDrawers (int drawerCount, boolean halfDepth, BlockBehaviour.Properties properties) {
        super(drawerCount, halfDepth, calcUnits(drawerCount, halfDepth), properties);
    }

    private static int calcUnits(int drawerCount, boolean halfDepth) {
        return halfDepth ? 4 / drawerCount : 8 / drawerCount;
    }

    public BlockMoreDrawers setMatKey (ResourceLocation material) {
        this.matNamespace = material.getNamespace();
        this.matKey = material.getPath();
        return this;
    }

    public BlockMoreDrawers setMatKey (@Nullable String matKey) {
        this.matKey = matKey;
        return this;
    }

    public String getMatKey () {
        return matKey;
    }

    public String getNameMatKey () {
        return "block." + matNamespace + ".mat." + matKey;
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

    @Override
    public boolean retrimBlock (Level world, BlockPos pos, ItemStack prototype) {
        if (retrimType() == null)
            return false;

        Block protoBlock = Block.byItem(prototype.getItem());
        if (!(protoBlock instanceof BlockTrim))
            return false;

        BlockTrim trim = (BlockTrim) protoBlock;
        if (trim.getMatKey() == null || Objects.equals(trim.getMatKey(), ""))
            return false;

        var blockList = ModBlocks.getDrawersOfTypeAndSizeAndDepth(com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers.class, getDrawerCount(), isHalfDepth())
                .filter(b -> b.getMatKey() == trim.getMatKey()).toList();

        if (blockList.size() != 1)
            return false;

        com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers targetBlock = blockList.get(0);
        BlockEntity entity = world.getBlockEntity(pos);
        if (!(entity instanceof BlockEntityDrawersStandard))
            return false;

        BlockState curState = world.getBlockState(pos);
        BlockEntityDrawersStandard curEntity = (BlockEntityDrawersStandard) entity;
        CompoundTag entityData = curEntity.saveWithoutMetadata();

        BlockState newState = targetBlock.defaultBlockState().setValue(FACING, curState.getValue(FACING));
        world.setBlockAndUpdate(pos, newState);

        BlockEntity newEnt = world.getBlockEntity(pos);
        newEnt.load(entityData);

        return true;
    }

    @Override
    public boolean repartitionBlock (Level world, BlockPos pos, ItemStack prototype) {
        if (retrimType() == null)
            return false;

        Block protoBlock = Block.byItem(prototype.getItem());
        if (!(protoBlock instanceof com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers))
            return false;

        com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers targetBlock = (com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers) protoBlock;
        if (targetBlock.isHalfDepth() != isHalfDepth())
            return false;
        if (targetBlock.getDrawerCount() == getDrawerCount())
            return false;

        BlockEntity sourceEntity = world.getBlockEntity(pos);
        if (!(sourceEntity instanceof BlockEntityDrawersStandard))
            return false;

        BlockEntityDrawersStandard sourceBE = (BlockEntityDrawersStandard) sourceEntity;
        ItemStack firstStack = sourceBE.getGroup().getDrawer(0).getStoredItemPrototype();
        int aggCount = 0;

        for (int i = 0; i < sourceBE.getGroup().getDrawerCount(); i++) {
            IDrawer drawer = sourceBE.getGroup().getDrawer(i);
            ItemStack stack = drawer.getStoredItemPrototype();

            if (firstStack.isEmpty() && !stack.isEmpty())
                firstStack = stack;

            if (!ItemStackMatcher.areItemsEqual(firstStack, stack) && !stack.isEmpty())
                return false;

            aggCount += drawer.getStoredItemCount();
        }

        // Set new block

        BlockState curState = world.getBlockState(pos);
        CompoundTag entityData = sourceEntity.saveWithoutMetadata();

        BlockState newState = targetBlock.defaultBlockState().setValue(FACING, curState.getValue(FACING));
        world.setBlockAndUpdate(pos, newState);

        BlockEntity newEnt = world.getBlockEntity(pos);
        newEnt.load(entityData);

        BlockEntityDrawersStandard targetBE = (BlockEntityDrawersStandard) newEnt;
        int drawerCount = targetBE.getGroup().getDrawerCount();
        int divCount = aggCount / drawerCount;
        int remCount = aggCount - (divCount * drawerCount);
        for (int i = 0; i < drawerCount; i++) {
            int slotCount = divCount;
            if (i < remCount)
                slotCount += 1;
            targetBE.getGroup().getDrawer(i).setStoredItem(firstStack, slotCount);
        }

        return true;
    }
}