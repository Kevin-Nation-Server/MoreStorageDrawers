package com.rydelfox.morestoragedrawers.network;

import com.jaquadro.minecraft.storagedrawers.StorageDrawers;
import com.jaquadro.minecraft.storagedrawers.network.CountUpdateMessage;
import com.rydelfox.morestoragedrawers.block.tile.TileEntityDrawersMore;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ItemUpdateMessage {

    private int x;
    private int y;
    private int z;
    private int slot;
    private int count;

    private final boolean failed;

    public ItemUpdateMessage (BlockPos pos, int slot, int count) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.slot = slot;
        this.count = count;
        this.failed = false;
    }

    private ItemUpdateMessage (boolean failed) {
        this.failed = failed;
    }

    public static ItemUpdateMessage decode (ByteBuf buf) {
        //return new ItemUpdateMessage(buf.readBlockPos(), buf.readByte(), buf.readItem());

        try {
            int x = buf.readInt();
            int y = buf.readShort();
            int z = buf.readInt();
            int slot = buf.readByte();
            int count = buf.readInt();
            return new ItemUpdateMessage(new BlockPos(x, y, z), slot, count);
        }
        catch (IndexOutOfBoundsException e) {
            StorageDrawers.log.error("CountUpdateMessage: Unexpected end of packet.\nMessage: " + ByteBufUtil.hexDump(buf, 0, buf.writerIndex()), e);
            return new ItemUpdateMessage(true);
        }

    }

    public static void encode (ItemUpdateMessage msg, ByteBuf buf) {
        buf.writeInt(msg.x);
        buf.writeShort(msg.y);
        buf.writeInt(msg.z);
        buf.writeByte(msg.slot);
        buf.writeInt(msg.count);
    }

    public static void handle(ItemUpdateMessage msg, Supplier<NetworkEvent.Context> ctx) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handleClient(msg, ctx.get()));
    }

    @OnlyIn(Dist.CLIENT)
    private static void handleClient(ItemUpdateMessage msg, NetworkEvent.Context ctx) {
        if (!msg.failed) {
            Level world = Minecraft.getInstance().level;
            if (world != null) {
                BlockPos pos = new BlockPos(msg.x, msg.y, msg.z);
                BlockEntity tileEntity = world.getBlockEntity(pos);
                if (tileEntity instanceof TileEntityDrawersMore) {
                    ((TileEntityDrawersMore) tileEntity).clientUpdateItem(msg.slot, msg.count);
                }
            }
        }
        ctx.setPacketHandled(true);
    }
}
