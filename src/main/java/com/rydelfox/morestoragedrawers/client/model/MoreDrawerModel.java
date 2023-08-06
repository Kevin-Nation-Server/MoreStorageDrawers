package com.rydelfox.morestoragedrawers.client.model;

import com.google.common.collect.Lists;
import com.jaquadro.minecraft.storagedrawers.StorageDrawers;
import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawerAttributes;
import com.jaquadro.minecraft.storagedrawers.api.storage.attribute.LockAttribute;
import com.jaquadro.minecraft.storagedrawers.block.BlockCompDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockDrawers;
import com.jaquadro.minecraft.storagedrawers.block.tile.BlockEntityDrawers;
import com.jaquadro.minecraft.storagedrawers.client.model.BasicDrawerModel;
import com.mojang.datafixers.util.Either;
import com.rydelfox.morestoragedrawers.MoreStorageDrawers;
import com.rydelfox.morestoragedrawers.block.BlockDrawersExtended;
import com.rydelfox.morestoragedrawers.block.BlockMoreDrawers;
import com.rydelfox.morestoragedrawers.block.DrawerMaterial;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ChunkRenderTypeSet;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.ModelEvent.BakingCompleted;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.IDynamicBakedModel;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.io.IOUtils;
import org.joml.Vector3f;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

public class MoreDrawerModel {

    private static final Map<Direction, BakedModel> lockOverlaysFull = new HashMap<>();
    private static final Map<Direction, BakedModel> lockOverlaysHalf = new HashMap<>();
    private static final Map<Direction, BakedModel> voidOverlaysFull = new HashMap<>();
    private static final Map<Direction, BakedModel> voidOverlaysHalf = new HashMap<>();
    private static final Map<Direction, BakedModel> shroudOverlaysFull = new HashMap<>();
    private static final Map<Direction, BakedModel> shroudOverlaysHalf = new HashMap<>();
    private static final Map<Direction, BakedModel> indicator1Full = new HashMap<>();
    private static final Map<Direction, BakedModel> indicator1Half = new HashMap<>();
    private static final Map<Direction, BakedModel> indicator2Full = new HashMap<>();
    private static final Map<Direction, BakedModel> indicator2Half = new HashMap<>();
    private static final Map<Direction, BakedModel> indicator4Full = new HashMap<>();
    private static final Map<Direction, BakedModel> indicator4Half = new HashMap<>();
    private static final Map<Direction, BakedModel> indicatorComp = new HashMap<>();

    private static boolean geometryDataLoaded = false;

    @Mod.EventBusSubscriber(modid = MoreStorageDrawers.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class Register {
        @SubscribeEvent
        public static void registerTextures(TextureStitchEvent event) {
            MoreStorageDrawers.logInfo("MoreStorageDrawers: Registering Textures");
         /*   loadUnbakedModel(event, new ResourceLocation(StorageDrawers.MOD_ID, "models/block/full_drawers_lock.json"));
            loadUnbakedModel(event, new ResourceLocation(StorageDrawers.MOD_ID, "models/block/full_drawers_void.json"));
            loadUnbakedModel(event, new ResourceLocation(StorageDrawers.MOD_ID, "models/block/full_drawers_shroud.json"));
            loadUnbakedModel(event, new ResourceLocation(StorageDrawers.MOD_ID, "models/block/compdrawers_indicator.json"));
            loadUnbakedModel(event, new ResourceLocation(StorageDrawers.MOD_ID, "models/block/full_drawers_indicator_1.json"));
            loadUnbakedModel(event, new ResourceLocation(StorageDrawers.MOD_ID, "models/block/full_drawers_indicator_2.json"));
            loadUnbakedModel(event, new ResourceLocation(StorageDrawers.MOD_ID, "models/block/full_drawers_indicator_4.json"));
*/
            loadGeometryData();
        }

        private static void loadGeometryData() {
            if (geometryDataLoaded)
                return;
            geometryDataLoaded = true;
            MoreStorageDrawers.logInfo("x1155 MoreStorageDrawers: loadGeometryData ");
            List<BlockMoreDrawers> fullDrawers1 = new ArrayList<>();
            List<BlockMoreDrawers> fullDrawers2 = new ArrayList<>();
            List<BlockMoreDrawers> fullDrawers4 = new ArrayList<>();
            List<BlockMoreDrawers> halfDrawers1 = new ArrayList<>();
            List<BlockMoreDrawers> halfDrawers2 = new ArrayList<>();
            List<BlockMoreDrawers> halfDrawers4 = new ArrayList<>();
            for (DrawerMaterial material : DrawerMaterial.values()) {
                MoreStorageDrawers.logInfo("x1155 MoreStorageDrawers: DrawerMaterial.values() ");
                fullDrawers1.add(material.getDrawer(1, false));
                fullDrawers1.add(material.getDrawer(1, true));
                fullDrawers2.add(material.getDrawer(2, false));
                fullDrawers2.add(material.getDrawer(2, true));
                fullDrawers4.add(material.getDrawer(4, false));
                fullDrawers4.add(material.getDrawer(4, true));
            }

            MoreStorageDrawers.logInfo("x1155 MoreStorageDrawers: Populating Geometry");
            populateGeometryData(new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/full_drawers_icon_area_1.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/full_drawers_count_area_1.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/full_drawers_ind_area_1.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/full_drawers_indbase_area_1.json"),
                fullDrawers1.stream().toArray(BlockMoreDrawers[]::new));
            populateGeometryData(new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/full_drawers_icon_area_2.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/full_drawers_count_area_2.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/full_drawers_ind_area_2.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/full_drawers_indbase_area_2.json"),
                fullDrawers2.stream().toArray(BlockDrawersExtended[]::new));
            populateGeometryData(new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/full_drawers_icon_area_4.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/full_drawers_count_area_4.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/full_drawers_ind_area_4.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/full_drawers_indbase_area_4.json"),
                fullDrawers4.stream().toArray(BlockDrawersExtended[]::new));
            populateGeometryData(new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/half_drawers_icon_area_1.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/half_drawers_count_area_1.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/half_drawers_ind_area_1.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/half_drawers_indbase_area_1.json"),
                halfDrawers1.stream().toArray(BlockDrawersExtended[]::new));
            populateGeometryData(new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/half_drawers_icon_area_2.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/half_drawers_count_area_2.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/half_drawers_ind_area_2.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/half_drawers_indbase_area_2.json"),
                halfDrawers2.stream().toArray(BlockDrawersExtended[]::new));
            populateGeometryData(new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/half_drawers_icon_area_4.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/half_drawers_count_area_4.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/half_drawers_ind_area_4.json"),
                new ResourceLocation(StorageDrawers.MOD_ID, "models/block/geometry/half_drawers_indbase_area_4.json"),
                halfDrawers4.stream().toArray(BlockDrawersExtended[]::new));
        }

        private static void populateGeometryData(ResourceLocation locationIcon,
                                                 ResourceLocation locationCount,
                                                 ResourceLocation locationInd,
                                                 ResourceLocation locationIndBase,
                                                 BlockDrawersExtended... blocks) {
            BlockModel slotInfo = getBlockModel(locationIcon);
            BlockModel countInfo = getBlockModel(locationCount);
            BlockModel indInfo = getBlockModel(locationInd);
            BlockModel indBaseInfo = getBlockModel(locationIndBase);
            for (BlockDrawersExtended block : blocks) {
                if (block == null)
                    continue;

                for (int i = 0; i < block.getDrawerCount(); i++) {
                    Vector3f from = slotInfo.getElements().get(i).from;
                    Vector3f to = slotInfo.getElements().get(i).to;
                    block.labelGeometry[i] = new AABB(from.x(), from.y(), from.z(), to.x(), to.y(), to.z());
                }
                for (int i = 0; i < block.getDrawerCount(); i++) {
                    Vector3f from = countInfo.getElements().get(i).from;
                    Vector3f to = countInfo.getElements().get(i).to;
                    block.countGeometry[i] = new AABB(from.x(), from.y(), from.z(), to.x(), to.y(), to.z());
                }
                for (int i = 0; i < block.getDrawerCount(); i++) {
                    Vector3f from = indInfo.getElements().get(i).from;
                    Vector3f to = indInfo.getElements().get(i).to;
                    block.indGeometry[i] = new AABB(from.x(), from.y(), from.z(), to.x(), to.y(), to.z());
                }
                for (int i = 0; i < block.getDrawerCount(); i++) {
                    Vector3f from = indBaseInfo.getElements().get(i).from;
                    Vector3f to = indBaseInfo.getElements().get(i).to;
                    block.indBaseGeometry[i] = new AABB(from.x(), from.y(), from.x(), to.z(), to.y(), to.z());
                }
            }
        }

     /*   private static void loadUnbakedModel(TextureStitchEvent.Pre event, ResourceLocation resource) {
            BlockModel unbakedModel = getBlockModel(resource);

            for (Either<Material, String> x : unbakedModel.textureMap.values()) {
                x.ifLeft((value) -> {
                    if (value.atlasLocation().equals(event.getAtlas().location()))
                        event.addSprite(value.texture());
                });
            }
        }*/

        private static BlockModel getBlockModel (ResourceLocation location) {
            Resource iresource = null;
            Reader reader = null;
            try {
                iresource = Minecraft.getInstance().getResourceManager().getResourceOrThrow(location);
                reader = new InputStreamReader(iresource.open(), StandardCharsets.UTF_8);
                return BlockModel.fromStream(reader);
            } catch (IOException e) {
                return null;
            } finally {
                IOUtils.closeQuietly(reader);
            }
        }

        @SubscribeEvent
        public static void registerModels(ModelEvent.ModifyBakingResult event) {
            MoreStorageDrawers.logInfo("MoreStorageDrawers: Registering Models");
            for (int i = 0; i < 4; i++) {
                Direction dir = Direction.from2DDataValue(i);
                BlockModelRotation rot = BlockModelRotation.by(0, (int) dir.toYRot() + 180);
                Function<Material, TextureAtlasSprite> texGet = Material::sprite;

                lockOverlaysFull.put(dir, event.getModels().get(new ModelResourceLocation(StorageDrawers.rl("meta_locked"), getVariant(dir, false))));
                lockOverlaysHalf.put(dir, event.getModels().get(new ModelResourceLocation(StorageDrawers.rl("meta_locked"), getVariant(dir, true))));
                voidOverlaysFull.put(dir, event.getModels().get(new ModelResourceLocation(StorageDrawers.rl("meta_void"), getVariant(dir, false))));
                voidOverlaysHalf.put(dir, event.getModels().get(new ModelResourceLocation(StorageDrawers.rl("meta_void"), getVariant(dir, true))));
                shroudOverlaysFull.put(dir, event.getModels().get(new ModelResourceLocation(StorageDrawers.rl("meta_shroud"), getVariant(dir, false))));
                shroudOverlaysHalf.put(dir, event.getModels().get(new ModelResourceLocation(StorageDrawers.rl("meta_shroud"), getVariant(dir, true))));
                indicator1Full.put(dir, event.getModels().get(new ModelResourceLocation(StorageDrawers.rl("meta_indicator"), getVariant(dir, false, 1))));
                indicator1Half.put(dir, event.getModels().get(new ModelResourceLocation(StorageDrawers.rl("meta_indicator"), getVariant(dir, true, 1))));
                indicator2Full.put(dir, event.getModels().get(new ModelResourceLocation(StorageDrawers.rl("meta_indicator"), getVariant(dir, false, 2))));
                indicator2Half.put(dir, event.getModels().get(new ModelResourceLocation(StorageDrawers.rl("meta_indicator"), getVariant(dir, true, 2))));
                indicator4Full.put(dir, event.getModels().get(new ModelResourceLocation(StorageDrawers.rl("meta_indicator"), getVariant(dir, false, 4))));
                indicator4Half.put(dir, event.getModels().get(new ModelResourceLocation(StorageDrawers.rl("meta_indicator"), getVariant(dir, true, 4))));
                indicatorComp.put(dir, event.getModels().get(new ModelResourceLocation(StorageDrawers.rl("meta_comp_indicator"), getVariant(dir, false))));

            }

            for (DrawerMaterial material : DrawerMaterial.values()) {
                if (material.getMod() != null && material.getMod().isLoaded()) {
                    replaceBlock(event, material.getDrawer(1, false));
                    replaceBlock(event, material.getDrawer(2, false));
                    replaceBlock(event, material.getDrawer(3, false));
                    replaceBlock(event, material.getDrawer(1, true));
                    replaceBlock(event, material.getDrawer(2, true));
                    replaceBlock(event, material.getDrawer(3, true));
                }
            }
        }
        static String getVariant(Direction dir, boolean half) {
            return "facing=" + dir.getName() + ",half=" + half;
        }

        static String getVariant(Direction dir, boolean half, int slots) {
            return "facing=" + dir.getName() + ",half=" + half + ",slots=" + slots;
        }

        public static void replaceBlock(ModelEvent.ModifyBakingResult event, BlockDrawers block) {
            BakedModel missing = event.getModels().get(ModelBakery.MISSING_MODEL_LOCATION);
            for (BlockState state : block.getStateDefinition().getPossibleStates()) {
                ModelResourceLocation modelResource = BlockModelShaper.stateToModelLocation(state);
                BakedModel parentModel = event.getModels().get(modelResource);
                if (parentModel == null) {
                    StorageDrawers.log.warn("Got back null model from ModelBakeEvent.ModelManager for resource " + modelResource.toString());
                    continue;
                } else if (parentModel == missing)
                    continue;

                if (block.isHalfDepth())
                    event.getModels().put(modelResource, new Model2.HalfModel(parentModel));
                else
                    event.getModels().put(modelResource, new Model2.FullModel(parentModel));
            }
        }

        public static abstract class Model2 implements IDynamicBakedModel {
            protected final BakedModel mainModel;
            protected final Map<Direction, BakedModel> lockOverlay;
            protected final Map<Direction, BakedModel> voidOverlay;
            protected final Map<Direction, BakedModel> shroudOverlay;
            protected final Map<Direction, BakedModel> indicator1Overlay;
            protected final Map<Direction, BakedModel> indicator2Overlay;
            protected final Map<Direction, BakedModel> indicator4Overlay;
            protected final Map<Direction, BakedModel> indicatorCompOverlay;

            public static class FullModel extends Model2 {
                FullModel(BakedModel mainModel) {
                    super(mainModel, lockOverlaysFull, voidOverlaysFull, shroudOverlaysFull, indicator1Full, indicator2Full, indicator4Full, indicatorComp);
                }
            }

            public static class HalfModel extends Model2 {
                HalfModel(BakedModel mainModel) {
                    super(mainModel, lockOverlaysHalf, voidOverlaysHalf, shroudOverlaysHalf, indicator1Half, indicator2Half, indicator4Half, indicatorComp);
                }
            }

            private Model2(BakedModel mainModel,
                           Map<Direction, BakedModel> lockOverlay,
                           Map<Direction, BakedModel> voidOverlay,
                           Map<Direction, BakedModel> shroudOverlay,
                           Map<Direction, BakedModel> indicator1Overlay,
                           Map<Direction, BakedModel> indicator2Overlay,
                           Map<Direction, BakedModel> indicator4Overlay,
                           Map<Direction, BakedModel> indicatorComp) {
                this.mainModel = mainModel;
                this.lockOverlay = lockOverlay;
                this.voidOverlay = voidOverlay;
                this.shroudOverlay = shroudOverlay;
                this.indicator1Overlay = indicator1Overlay;
                this.indicator2Overlay = indicator2Overlay;
                this.indicator4Overlay = indicator4Overlay;
                this.indicatorCompOverlay = indicatorComp;
            }

            @Override
            public boolean usesBlockLight() {
                return mainModel.usesBlockLight();
            }

            @NotNull
            @Override
            public List<BakedQuad> getQuads (@Nullable BlockState state,
                                             @Nullable Direction side,
                                             @NotNull RandomSource rand,
                                             @NotNull ModelData extraData,
                                             @Nullable RenderType type) {
                List<BakedQuad> mainQuads;
                if (state != null) {
                    ChunkRenderTypeSet renderTypes = mainModel.getRenderTypes(state, rand, extraData);
                    if (type == null || renderTypes.contains(type)) {
                        mainQuads = mainModel.getQuads(state, side, rand, extraData, type);
                    } else {
                        mainQuads = Collections.emptyList();
                    }
                } else {
                    // NB: getting here for item renders (state == null) implies that the caller has not
                    // respected #getRenderPasses, since if they had this method wouldn't be called.
                    // If that's the case, then we might as well return the main quads that they're looking
                    // for anyway.
                    return mainModel.getQuads(null, side, rand, extraData, type);
                }

                if (!extraData.has(BlockEntityDrawers.ATTRIBUTES)) {
                    // Nothing to render.
                    return mainQuads;
                }

                if (!(type == null || type == RenderType.cutoutMipped())) {
                    // Don't render in the wrong layer.
                    return mainQuads;
                }

                List<BakedQuad> quads = new ArrayList<>(mainQuads);
                IDrawerAttributes attr = extraData.get(BlockEntityDrawers.ATTRIBUTES);
                Direction dir = state.getValue(BlockDrawers.FACING);

                if (attr.isItemLocked(LockAttribute.LOCK_EMPTY) || attr.isItemLocked(LockAttribute.LOCK_POPULATED)) {
                    BakedModel model = lockOverlay.get(dir);
                    if (model != null)
                        quads.addAll(model.getQuads(state, side, rand, extraData, type));
                }
                if (attr.isVoid()) {
                    BakedModel model = voidOverlay.get(dir);
                    if (model != null)
                        quads.addAll(model.getQuads(state, side, rand, extraData, type));
                }
                if (attr.isConcealed()) {
                    BakedModel model = shroudOverlay.get(dir);
                    if (model != null)
                        quads.addAll(model.getQuads(state, side, rand, extraData, type));
                }
                if (attr.hasFillLevel()) {
                    Block block = state.getBlock();
                    if (block instanceof BlockCompDrawers) {
                        BakedModel model = indicatorCompOverlay.get(dir);
                        if (model != null)
                            quads.addAll((model.getQuads(state, side, rand, extraData, type)));
                    }
                    else if (block instanceof BlockDrawers) {
                        int count = ((BlockDrawers) block).getDrawerCount();
                        if (count == 1) {
                            BakedModel model = indicator1Overlay.get(dir);
                            if (model != null)
                                quads.addAll((model.getQuads(state, side, rand, extraData, type)));
                        }
                        else if (count == 2) {
                            BakedModel model = indicator2Overlay.get(dir);
                            if (model != null)
                                quads.addAll((model.getQuads(state, side, rand, extraData, type)));
                        }
                        else if (count == 4) {
                            BakedModel model = indicator4Overlay.get(dir);
                            if (model != null)
                                quads.addAll((model.getQuads(state, side, rand, extraData, type)));
                        }
                    }
                }
                return quads;
            }

            @Override
            public boolean useAmbientOcclusion() {
                return mainModel.useAmbientOcclusion();
            }

            @Override
            public boolean isGui3d() {
                return mainModel.isGui3d();
            }

            @Override
            public boolean isCustomRenderer() {
                return mainModel.isCustomRenderer();
            }

            @Override
            public TextureAtlasSprite getParticleIcon() {
                return mainModel.getParticleIcon();
            }

            @Override
            public ItemOverrides getOverrides() {
                return mainModel.getOverrides();
            }
        }
    }
}