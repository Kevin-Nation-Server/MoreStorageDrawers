package com.rydelfox.morestoragedrawers.block;

import com.jaquadro.minecraft.storagedrawers.block.BlockTrim;
import com.rydelfox.morestoragedrawers.MoreStorageDrawers;
import com.rydelfox.morestoragedrawers.block.tile.BlockEntityDrawersMore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DrawerMaterial implements StringRepresentable {
    DEFAULT("", ID.DEFAULT, "default", "NO MATERIAL", 0, null),


    MINECRAFT_BRICK("pickaxe", ID.MINECRAFT, "brick", "Brick", 100, "brick", "brick_slab"),
    MINECRAFT_WHITE_CONCRETE("pickaxe", ID.MINECRAFT, "white_concrete", "White Concrete",                  101, "white_concrete", "white_concrete"),
    MINECRAFT_LIGHT_GRAY_CONCRETE("pickaxe", ID.MINECRAFT, "light_gray_concrete", "Light Gray Concrete",   102, "light_gray_concrete", "light_gray_concrete"),
    MINECRAFT_GRAY_CONCRETE("pickaxe", ID.MINECRAFT, "gray_concrete", "Gray Concrete",                     103, "gray_concrete", "gray_concrete"),
    MINECRAFT_BLACK_CONCRETE("pickaxe", ID.MINECRAFT, "black_concrete", "Black Concrete",                  104, "black_concrete", "black_concrete"),
    MINECRAFT_BROWN_CONCRETE("pickaxe", ID.MINECRAFT, "brown_concrete", "Brown Concrete",                  105, "brown_concrete", "brown_concrete"),
    MINECRAFT_RED_CONCRETE("pickaxe", ID.MINECRAFT, "red_concrete", "Red Concrete",                        106, "red_concrete", "red_concrete"),
    MINECRAFT_ORANGE_CONCRETE("pickaxe", ID.MINECRAFT, "orange_concrete", "Orange Concrete",               107, "orange_concrete", "orange_concrete"),
    MINECRAFT_YELLOW_CONCRETE("pickaxe", ID.MINECRAFT, "yellow_concrete", "Yellow Concrete",               108, "yellow_concrete", "yellow_concrete"),
    MINECRAFT_LIME_CONCRETE("pickaxe", ID.MINECRAFT, "lime_concrete", "Lime Concrete",                     109, "lime_concrete", "lime_concrete"),
    MINECRAFT_GREEN_CONCRETE("pickaxe", ID.MINECRAFT, "green_concrete", "Green Concrete",                  110, "green_concrete", "green_concrete"),
    MINECRAFT_CYAN_CONCRETE("pickaxe", ID.MINECRAFT, "cyan_concrete", "Cyan Concrete",                     111, "cyan_concrete", "cyan_concrete"),
    MINECRAFT_LIGHT_BLUE_CONCRETE("pickaxe", ID.MINECRAFT, "light_blue_concrete", "Light Blue Concrete",   112, "light_blue_concrete", "light_blue_concrete"),
    MINECRAFT_BLUE_CONCRETE("pickaxe", ID.MINECRAFT, "blue_concrete", "Blue Concrete",                     113, "blue_concrete", "blue_concrete"),
    MINECRAFT_PURPLE_CONCRETE("pickaxe", ID.MINECRAFT, "purple_concrete", "Purple Concrete",               114, "purple_concrete", "purple_concrete"),
    MINECRAFT_MAGENTA_CONCRETE("pickaxe", ID.MINECRAFT, "magenta_concrete", "Magenta Concrete",            115, "magenta_concrete", "magenta_concrete"),
    MINECRAFT_PINK_CONCRETE("pickaxe", ID.MINECRAFT, "pink_concrete", "Pink Concrete",                     116, "pink_concrete", "pink_concrete"),

    MINECRAFT_TERRACOTTA("pickaxe", ID.MINECRAFT, "terracotta", "Terracotta",                                    117, "terracotta", "terracotta"),
    MINECRAFT_WHITE_TERRACOTTA("pickaxe", ID.MINECRAFT, "white_terracotta", "White Terracotta",                  118, "white_terracotta", "white_terracotta"),
    MINECRAFT_LIGHT_GRAY_TERRACOTTA("pickaxe", ID.MINECRAFT, "light_gray_terracotta", "Light Gray Terracotta",   119, "light_gray_terracotta", "light_gray_terracotta"),
    MINECRAFT_GRAY_TERRACOTTA("pickaxe", ID.MINECRAFT, "gray_terracotta", "Gray Terracotta",                     120, "gray_terracotta", "gray_terracotta"),
    MINECRAFT_BLACK_TERRACOTTA("pickaxe", ID.MINECRAFT, "black_terracotta", "Black Terracotta",                  121, "black_terracotta", "black_terracotta"),
    MINECRAFT_BROWN_TERRACOTTA("pickaxe", ID.MINECRAFT, "brown_terracotta", "Brown Terracotta",                  122, "brown_terracotta", "brown_terracotta"),
    MINECRAFT_RED_TERRACOTTA("pickaxe", ID.MINECRAFT, "red_terracotta", "Red Terracotta",                        123, "red_terracotta", "red_terracotta"),
    MINECRAFT_ORANGE_TERRACOTTA("pickaxe", ID.MINECRAFT, "orange_terracotta", "Orange Terracotta",               124, "orange_terracotta", "orange_terracotta"),
    MINECRAFT_YELLOW_TERRACOTTA("pickaxe", ID.MINECRAFT, "yellow_terracotta", "Yellow Terracotta",               125, "yellow_terracotta", "yellow_terracotta"),
    MINECRAFT_LIME_TERRACOTTA("pickaxe", ID.MINECRAFT, "lime_terracotta", "Lime Terracotta",                     126, "lime_terracotta", "lime_terracotta"),
    MINECRAFT_GREEN_TERRACOTTA("pickaxe", ID.MINECRAFT, "green_terracotta", "Green Terracotta",                  127, "green_terracotta", "green_terracotta"),
    MINECRAFT_CYAN_TERRACOTTA("pickaxe", ID.MINECRAFT, "cyan_terracotta", "Cyan Terracotta",                     128, "cyan_terracotta", "cyan_terracotta"),
    MINECRAFT_LIGHT_BLUE_TERRACOTTA("pickaxe", ID.MINECRAFT, "light_blue_terracotta", "Light Blue Terracotta",   129, "light_blue_terracotta", "light_blue_terracotta"),
    MINECRAFT_BLUE_TERRACOTTA("pickaxe", ID.MINECRAFT, "blue_terracotta", "Blue Terracotta",                     130, "blue_terracotta", "blue_terracotta"),
    MINECRAFT_PURPLE_TERRACOTTA("pickaxe", ID.MINECRAFT, "purple_terracotta", "Purple Terracotta",               131, "purple_terracotta", "purple_terracotta"),
    MINECRAFT_MAGENTA_TERRACOTTA("pickaxe", ID.MINECRAFT, "magenta_terracotta", "Magenta Terracotta",            132, "magenta_terracotta", "magenta_terracotta"),
    MINECRAFT_PINK_TERRACOTTA("pickaxe", ID.MINECRAFT, "pink_terracotta", "Pink Terracotta",                     133, "pink_terracotta", "pink_terracotta"),

    CREATE_ANDESITE_CASING("pickaxe", ID.CREATE, "andesite_casing", "Andesite Casing", 200, "andesite_casing", "andesite_casing"),
    CREATE_BRASS_CASING("pickaxe", ID.CREATE, "brass_casing", "Brass Casing", 201, "brass_casing", "brass_casing"),
    CREATE_COPPER_CASING("pickaxe", ID.CREATE, "copper_casing", "Copper Casing", 202, "copper_casing", "copper_casing"),
    CREATE_RAILWAY_CASING("pickaxe", ID.CREATE, "railway_casing", "Railway Casing", 203, "railway_casing", "railway_casing"),

    //MINECRAFT_TERRACOTTA_RED(ID.MINECRAFT, "red_terracotta", "Red Terracotta", 1, "red_terracotta", "slab_terracotta_red"),
    ARSNOUVEAU_ARCHWOOD("axe", ID.ARSNOUVEAU, "archwood", "Archwood", 1, "archwood_planks", "archwood_slab"),
    //ASTRALSORCERY_INFUSED(ID.ASTRALSORCERY, "infused", "Infused", 2, "infused_wood_planks", "infused_wood_slab", 6, 11, 0),
    //BOTANIA_LIVINGWOOD("axe", ID.BOTANIA, "livingwood", "Livingwood", 3, "livingwood_planks", "livingwood_planks_slab"),
    //BOTANIA_MOSSY_LIVINGWOOD(ID.BOTANIA, "mossy_livingwood", "Mossy Livingwood", 4, "mossy_livingwood_planks"),
    //BOTANIA_DREAMWOOD(ID.BOTANIA, "dreamwood", "Dreamwood", 5, "dreamwood_planks", "dreamwood_planks_slab"),
    //BOTANIA_MOSSY_DREAMWOOD(ID.BOTANIA, "mossy_dreamwood", "Mossy Dreamwood", 6, "mossy_dreamwood_planks"),
    //BOTANIA_SHIMMERWOOD(ID.BOTANIA, "shimmerwood", "Shimmerwood", 7, "shimmerwood_planks", "shimmerwood_planks_slab"),
    //EIDOLON_POLISHED(ID.EIDOLON, "polished", "Polished", 8, "polished_planks", "polished_planks_slab", 4, 5, 0),
    //HEXBLADES_DARK_POLISHED(ID.HEXBLADES, "dark_polished", "Dark Polished", 9, "dark_polished_planks", "dark_polished_planks_slab", 4, 5, 0),
    //NATURESAURA_ANCIENT(ID.NATURESAURA, "ancient", "Ancient", 10, "ancient_planks", "ancient_slab"),
    TWILIGHTFOREST_TOWER("axe", ID.TWILIGHTFOREST, "tower", "Towerwood", 11, "towerwood", "cacked_towerwood", 25, 20, 0),
    TWILIGHTFOREST_TWILIGHT("axe", ID.TWILIGHTFOREST, "twilight", "Twilight Oak", 12, "twilight_oak_planks", "twilight_oak_slab"),
    TWILIGHTFOREST_CANOPY("axe", ID.TWILIGHTFOREST, "canopy", "Canopy", 13, "canopy_planks", "canopy_slab"),
    TWILIGHTFOREST_MANGROVE("axe", ID.TWILIGHTFOREST, "mangrove", "Mangrove", 14, "mangrove_planks", "mangrove_slab"),
    TWILIGHTFOREST_DARK("axe", ID.TWILIGHTFOREST, "dark", "Darkwood", 15, "dark_planks", "dark_slab"),
    TWILIGHTFOREST_TIME("axe", ID.TWILIGHTFOREST, "time", "Timewood", 16, "time_planks", "time_slab"),
    TWILIGHTFOREST_TRANS("axe", ID.TWILIGHTFOREST, "trans", "Transwood", 17, "transformation_planks", "transformation_slab"),
    TWILIGHTFOREST_MINE("axe", ID.TWILIGHTFOREST, "mine", "Minewood", 18, "mining_planks", "mining_slab"),
    TWILIGHTFOREST_SORT("axe", ID.TWILIGHTFOREST, "sort", "Sortingwood", 19, "sorting_planks", "sorting_slab"),
    BIOMESOPLENTY_FIR("axe", ID.BIOMESOPLENTY, "fir", "Fir", 20, "fir_planks", "fir_slab"),
    BIOMESOPLENTY_REDWOOD("axe", ID.BIOMESOPLENTY, "redwood", "Redwood", 21, "redwood_planks", "redwood_slab"),
    BIOMESOPLENTY_CHERRY("axe", ID.BIOMESOPLENTY, "cherry", "Cherry", 22, "cherry_planks", "cherry_slab"),
    BIOMESOPLENTY_MAHOGANY("axe", ID.BIOMESOPLENTY, "mahogany", "Mahogany", 23, "mahogany_planks", "mahogany_slab"),
    BIOMESOPLENTY_JACARANDA("axe", ID.BIOMESOPLENTY, "jacaranda", "Jacaranda", 24, "jacaranda_planks", "jacaranda_slab"),
    BIOMESOPLENTY_PALM("axe", ID.BIOMESOPLENTY, "palm", "Palm", 25, "palm_planks", "palm_slab"),
    BIOMESOPLENTY_WILLOW("axe", ID.BIOMESOPLENTY, "willow", "Willow", 26, "willow_planks", "willow_slab"),
    BIOMESOPLENTY_DEAD("axe", ID.BIOMESOPLENTY, "dead", "Deadwood", 27, "dead_planks", "dead_slab"),
    BIOMESOPLENTY_MAGIC("axe", ID.BIOMESOPLENTY, "magic", "Magic Wood", 28, "magic_planks", "magic_slab"),
    BIOMESOPLENTY_UMBRAN("axe", ID.BIOMESOPLENTY, "umbran", "Umbran", 29, "umbran_planks", "umbran_slab"),
    BIOMESOPLENTY_HELLBARK("axe", ID.BIOMESOPLENTY, "hellbark", "Hellbark", 30, "hellbark_planks", "hellbark_slab"),
    //BETTERENDFORGE_MOSSY_GLOWSHRROM(ID.BETTERENDFORGE, "mossy_glowshroom", "Mossy Glowshroom", 31, "mossy_glowshroom_planks", "mossy_glowshroom_slab"),
    //BETTERENDFORGE_LACUGROVE(ID.BETTERENDFORGE, "lacugrove", "Lacugrove", 32, "lacugrove_planks", "lacugrove_slab"),
    //BETTERENDFORGE_END_LOTUS(ID.BETTERENDFORGE, "end_lotus", "End Lotus", 33, "end_lotus_planks", "end_lotus_slab"),
    //BETTERENDFORGE_PYTHADENDRON(ID.BETTERENDFORGE, "pythadendron", "Pythadendron", 34, "pythadendron_planks", "pythadendron_slab"),
    //BETTERENDFORGE_DRAGON_TREE(ID.BETTERENDFORGE, "dragon_tree", "Dragon Tree", 35, "dragon_tree_planks", "dragon_tree_slab"),
    //BETTERENDFORGE_TENANEA(ID.BETTERENDFORGE, "tenanea", "Tenanea", 36, "tenanea_planks", "tenanea_slab"),
    //BETTERENDFORGE_HELIX_TREE(ID.BETTERENDFORGE, "helix_tree", "Helix Tree", 37, "helix_tree_planks", "helix_tree_slab"),
    //BETTERENDFORGE_UMBRELLA_TREE(ID.BETTERENDFORGE, "umbrella_tree", "Umbrella Tree", 38, "umbrella_tree_planks", "umbrella_tree_slab"),
    //BETTERENDFORGE_JELLYSHROOM(ID.BETTERENDFORGE, "jellyshroom", "Jellyshroom", 39, "jellyshroom_planks", "jellyshroom_slab"),
    //BETTERENDFORGE_LUCERNIA(ID.BETTERENDFORGE, "lucernia", "Lucernia", 40, "lucernia_planks", "lucernia_slab"),
    //DESOLATION_CHARRED(ID.DESOLATION, "charred", "Charred", 41, "charred_planks", "charred_slab", 1, 10, 0),
    //BIOMESYOULLGO_ASPEN(ID.BIOMESYOULLGO, "aspen", "Aspen", 42, "aspen_planks", "aspen_slab"),
    //BIOMESYOULLGO_BAOBAB(ID.BIOMESYOULLGO, "baobab", "Baobab", 43, "baobab_planks", "baobab_slab"),
    //BIOMESYOULLGO_BLUE_ENCHANTED(ID.BIOMESYOULLGO, "blue_enchanted", "Blue Enchanted", 44, "blue_enchanted_planks", "blue_enchanted_slab"),
    //BIOMESYOULLGO_BULBIS(ID.BIOMESYOULLGO, "bulbis", "Bulbis", 45, "bulbis_planks", "bulbis_slab"),
    //BIOMESYOULLGO_CHERRY(ID.BIOMESYOULLGO, "cherry", "Cherry", 46, "cherry_planks", "cherry_slab"),
    //BIOMESYOULLGO_CIKA(ID.BIOMESYOULLGO, "cika", "Cika", 47, "cika_planks", "cika_slab"),
    //BIOMESYOULLGO_CYPRESS(ID.BIOMESYOULLGO, "cypress", "Cypress", 48, "cypress_planks", "cypress_slab"),
    //BIOMESYOULLGO_EBONY(ID.BIOMESYOULLGO, "ebony", "Ebony", 49, "ebony_planks", "ebony_slab"),
    //BIOMESYOULLGO_ETHER(ID.BIOMESYOULLGO, "ether", "Ether", 50, "ether_planks", "ether_slab"),
    //BIOMESYOULLGO_FIR(ID.BIOMESYOULLGO, "fir", "Fir", 51, "fir_planks", "fir_slab"),
    //BIOMESYOULLGO_GREEN_ENCHANTED(ID.BIOMESYOULLGO, "green_enchanted", "Green Enchanted", 52, "green_enchanted_planks", "green_enchanted_slab"),
    //BIOMESYOULLGO_HOLLY(ID.BIOMESYOULLGO, "holly", "Holly", 53, "holly_planks", "holly_slab"),
    //BIOMESYOULLGO_IMPARIUS(ID.BIOMESYOULLGO, "imparius", "Imparius", 54, "imparius_planks", "imparius_slab"),
    //BIOMESYOULLGO_JACARANDA(ID.BIOMESYOULLGO, "jacaranda", "Jacaranda", 53, "jacaranda_planks", "jacaranda_slab"),
    //BIOMESYOULLGO_LAMENT(ID.BIOMESYOULLGO, "lament", "Lament", 54, "lament_planks", "lament_slab"),
    //BIOMESYOULLGO_MAHOGANY(ID.BIOMESYOULLGO, "mahogany", "Mahogany", 55, "mahogany_planks", "mahogany_slabs"),
    //BIOMESYOULLGO_MANGROVE(ID.BIOMESYOULLGO, "mangrove", "Mangrove", 56, "mangrove_planks", "mangrove_slab"),
    //BIOMESYOULLGO_MAPLE(ID.BIOMESYOULLGO, "maple", "Maple", 57, "maple_planks", "maple_slab"),
    //BIOMESYOULLGO_NIGHTSHADE(ID.BIOMESYOULLGO, "nightshade", "Nightshade", 58, "nightshade_planks", "nightshade_slab"),
    //BIOMESYOULLGO_PALM(ID.BIOMESYOULLGO, "palm", "Palm", 59, "palm_planks", "palm_slab"),
    //BIOMESYOULLGO_PINE(ID.BIOMESYOULLGO, "pine", "Pine", 60, "pine_planks", "pine_slab"),
    //BIOMESYOULLGO_RAINBOW_EUCALYPTUS(ID.BIOMESYOULLGO, "rainbow_eucalyptus", "Rainbow Eucalyptus", 61, "rainbow_eucalyptus_planks", "rainbow_eucalyptus_slab"),
    //BIOMESYOULLGO_REDWOOD(ID.BIOMESYOULLGO, "redwood", "Redwood", 62, "redwood_planks", "redwood_slab"),
    //BIOMESYOULLGO_SKYRIS(ID.BIOMESYOULLGO, "skyris", "Skyris", 63, "skyris_planks", "skyris_slab"),
    //BIOMESYOULLGO_WILLOW(ID.BIOMESYOULLGO, "willow", "Willow", 64, "willow_planks", "willow_slab"),
    //BIOMESYOULLGO_WITCH_HAZEL(ID.BIOMESYOULLGO, "witch_hazel", "Witch Hazel", 65, "witch_hazel_planks", "witch_hazel_slab"),
    //BIOMESYOULLGO_ZELKOVA(ID.BIOMESYOULLGO, "zelkova", "Zelkova", 66, "zelkova_planks", "zelkova_slab"),
    //BIOMESYOULLGO_SYTHIAN(ID.BIOMESYOULLGO, "sythian", "Sythian", 67, "sythian_planks", "sythian_slab"),
    //BIOMESYOULLGO_EMBUR(ID.BIOMESYOULLGO, "embur", "Embur", 68, "embur_planks", "embur_slab"),
    //OUTEREND_AZURE(ID.OUTEREND, "azure", "Azure", 69, "azure_planks", "azure_slab"),
    //WILDNATURE_ORANGE_BIND(ID.WILDNATURE, "orange_bind", "Orange Bind", 70, "orange_bind_wood_planks", "orange_bind_wood_slab"),
    //WILDNATURE_END_WOOD(ID.WILDNATURE, "end_wood", "End Wood", 71, "end_wood_planks", "end_wood_slab"),
    //WILDNATURE_DEATH_WOOD(ID.WILDNATURE, "death_wood", "Death Wood", 72, "death_wood_planks", "death_wood_slab"),
    //WILDNATURE_SAKURA(ID.WILDNATURE, "sakura", "Sakura", 73, "sakura_planks", "sakura_slab"),
    //WILDNATURE_LAVENDER(ID.WILDNATURE, "lavender", "Lavender", 74, "sakura_slab", "lavender_slab"),
    //WILDNATURE_REDWOOD(ID.WILDNATURE, "redwood", "Redwood", 75, "redwood_planks", "redwood_slab");
    ;

    private static final Map<ResourceLocation, DrawerMaterial> RESOURCE_LOOKUP;
    private static final Map<Integer, DrawerMaterial> INDEX_LOOKUP;

    private final String namespace;
    private final String name;
    private final String englishName;
    public final ResourceLocation resource;
    private final ResourceLocation plankResource;
    private final ResourceLocation slabResource;
    private final int index;
    private final int hardness;
    private final int blastResistance;
    private final int light;

    public final String minableWith;

    private RegistryObject<Block> regBlockTrim = null;
    private RegistryObject<Block> regFullOne = null;
    private RegistryObject<Block> regFullTwo = null;
    private RegistryObject<Block> regFullFour = null;
    private RegistryObject<Block> regHalfOne = null;
    private RegistryObject<Block> regHalfTwo = null;
    private RegistryObject<Block> regHalfFour = null;

    private Block blockTrim = null;
    private Block blockFullOne = null;
    private Block blockFullTwo = null;
    private Block blockFullFour = null;
    private Block blockHalfOne = null;
    private Block blockHalfTwo = null;
    private Block blockHalfFour = null;

    private Item itemTrim = null;
    private Item itemFullOne = null;
    private Item itemFullTwo = null;
    private Item itemFullFour = null;
    private Item itemHalfOne = null;
    private Item itemHalfTwo = null;
    private Item itemHalfFour = null;

    private BlockEntityDrawersMore tileFullOne = null;
    private BlockEntityDrawersMore tileFullTwo = null;
    private BlockEntityDrawersMore tileFullFour = null;
    private BlockEntityDrawersMore tileHalfOne = null;
    private BlockEntityDrawersMore tileHalfTwo = null;
    private BlockEntityDrawersMore tileHalfFour = null;

    DrawerMaterial(String minableWith, String namespace, String name, String englishName, int index, String blockId) {
        this(minableWith, namespace, name, englishName, index, blockId, null);
    }

    DrawerMaterial(String minableWith, String namespace, String name, String englishName, int index, String plankId, String slabId) {
        this(minableWith, namespace, name, englishName, index, plankId, slabId, 5, 5, 0);
    }

    DrawerMaterial(String minableWith, String namespace, String name, String englishName, int index, String plankId, String slabId, int hardness, int blastResistance, int light) {
        this.namespace = namespace;
        this.name = name;
        this.englishName = englishName;
        this.plankResource = plankId != null ? new ResourceLocation(namespace, plankId) : null;
        this.slabResource = slabId != null ? new ResourceLocation(namespace, slabId) : null;
        this.resource = new ResourceLocation(namespace, name);
        this.index = index;
        this.hardness = hardness;
        this.blastResistance = blastResistance;
        this.light = light;
        this.minableWith = minableWith;
    }

    @Nonnull
    public String getNamespace() {
        return resource.getNamespace();
    }

    @Nonnull
    public String getPath() {
        return resource.getPath();
    }

    @Override
    @Nonnull
    public String getSerializedName() {
        return resource.toString();
    }

    public EnumMod getMod() {
        return EnumMod.byId(namespace);
    }

    @Nonnull
    public ResourceLocation getResource() {
        return resource;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public String prefix() {
        return namespace + "_" + name;
    }

    public ResourceLocation getPlankResource() {
        return plankResource;
    }

    public ResourceLocation getSlabResource() {
        return slabResource;
    }

    public int getIndex() {
        return index;
    }

    public int getGroupIndex() {
        return index / 16;
    }

    public int getGroupMeta() {
        return index % 16;
    }

    public int getHardness() {
        return hardness;
    }

    public int getBlastResistance() {
        return blastResistance;
    }

    public int getLight() {
        return light;
    }

    public String getEnglishName() {
        return englishName;
    }

    public BlockMoreDrawers getDrawer(int slots, boolean halfDepth) {
        if (halfDepth) {
            switch (slots) {
                case 4:
                    return (BlockMoreDrawers) blockHalfFour;
                case 2:
                    return (BlockMoreDrawers) blockHalfTwo;
                default:
                    return (BlockMoreDrawers) blockHalfOne;
            }
        } else {
            switch (slots) {
                case 4:
                    return (BlockMoreDrawers) blockFullFour;
                case 2:
                    return (BlockMoreDrawers) blockFullTwo;
                default:
                    return (BlockMoreDrawers) blockFullOne;
            }
        }
    }

    public BlockTrim getTrim() {
        return (BlockTrim) blockTrim;
    }

    public List<Block> getBlocks() {
        return getBlocks(true);
    }

    public List<Block> getBlocks(boolean includeTrim) {
        List<Block> blocks = new ArrayList<>();
        blocks.add(blockFullOne);
        blocks.add(blockFullTwo);
        blocks.add(blockFullFour);
        blocks.add(blockHalfOne);
        blocks.add(blockHalfTwo);
        blocks.add(blockHalfFour);
        if (includeTrim)
            blocks.add(blockTrim);
        return blocks;
    }

    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.add(itemFullOne);
        items.add(itemFullTwo);
        items.add(itemFullFour);
        items.add(itemHalfOne);
        items.add(itemHalfTwo);
        items.add(itemHalfFour);
        items.add(itemTrim);
        return items;
    }

    public void registerBlocks(IForgeRegistry<Block> registry) {
        if (this == DEFAULT)
            return;
        if (this.blockTrim != null)
            throw new IllegalStateException(this.getEnglishName() + " blocks have already been registered!");
        BlockBehaviour.Properties properties = BlockBehaviour.Properties
            .copy(Blocks.OAK_WOOD)
            .strength(hardness, blastResistance)
            .lightLevel((p1) -> light)
            .isSuffocating((p1, p2, p3) -> false)
            .isRedstoneConductor((p1, p2, p3) -> false);
        MoreStorageDrawers.logInfo("Registering blocks for " + englishName);
        this.blockTrim = new BlockTrim(properties);
        this.blockFullOne = new BlockMoreDrawers(1, false, properties);
        this.blockFullTwo = new BlockMoreDrawers(2, false, properties);
        this.blockFullFour = new BlockMoreDrawers(4, false, properties);
        this.blockHalfOne = new BlockMoreDrawers(1, true, properties);
        this.blockHalfTwo = new BlockMoreDrawers(2, true, properties);
        this.blockHalfFour = new BlockMoreDrawers(4, true, properties);

        registry.register(namespace + "_" + name + "_trim", blockTrim);
        registry.register(namespace + "_" + name + "_full_1", blockFullOne);
        registry.register(namespace + "_" + name + "_full_2", blockFullTwo);
        registry.register(namespace + "_" + name + "_full_4", blockFullFour);
        registry.register(namespace + "_" + name + "_half_1", blockHalfOne);
        registry.register(namespace + "_" + name + "_half_2", blockHalfTwo);
        registry.register(namespace + "_" + name + "_half_4", blockHalfFour);
    }

    public void registerItems(IForgeRegistry<Item> registry) {
        if (this == DEFAULT)
            return;
        if (this.itemTrim != null)
            throw new IllegalStateException(this.getEnglishName() + " items have already been registered!");
        if (this.blockTrim == null)
            throw new IllegalStateException("Blocks must be registered before registering items!");

        this.itemTrim = new BlockItem(this.blockTrim, new Item.Properties());
        this.itemFullOne = new BlockItem(this.blockFullOne, new Item.Properties());
        this.itemFullTwo = new BlockItem(this.blockFullTwo, new Item.Properties());
        this.itemFullFour = new BlockItem(this.blockFullFour, new Item.Properties());
        this.itemHalfOne = new BlockItem(this.blockHalfOne, new Item.Properties());
        this.itemHalfTwo = new BlockItem(this.blockHalfTwo, new Item.Properties());
        this.itemHalfFour = new BlockItem(this.blockHalfFour, new Item.Properties());

        registry.register(namespace + "_" + name + "_trim", itemTrim);
        registry.register(namespace + "_" + name + "_full_1", itemFullOne);
        registry.register(namespace + "_" + name + "_full_2", itemFullTwo);
        registry.register(namespace + "_" + name + "_full_4", itemFullFour);
        registry.register(namespace + "_" + name + "_half_1", itemHalfOne);
        registry.register(namespace + "_" + name + "_half_2", itemHalfTwo);
        registry.register(namespace + "_" + name + "_half_4", itemHalfFour);
    }

    public RegistryObject<Block> getRegisteredTrim() {
        return regBlockTrim;
    }

    @Nonnull
    public static DrawerMaterial byResource(String resource) {
        DrawerMaterial variant = RESOURCE_LOOKUP.get(new ResourceLocation(resource));
        return variant != null ? variant : DEFAULT;
    }

    @Nonnull
    public static DrawerMaterial byGroupMeta(int group, int meta) {
        DrawerMaterial varient = INDEX_LOOKUP.get(group * 16 + meta);
        return varient != null ? varient : DEFAULT;
    }

    static {
        RESOURCE_LOOKUP = new HashMap<>();
        INDEX_LOOKUP = new HashMap<>();

        for (DrawerMaterial varient : values()) {
            RESOURCE_LOOKUP.put(varient.getResource(), varient);
            INDEX_LOOKUP.put(varient.getIndex(), varient);
        }
    }

    private static class ID {
        public static final String DEFAULT = "none";
        public static final String MINECRAFT = "minecraft";
        public static final String CREATE = "create";
        public static final String ARSNOUVEAU = "ars_nouveau";
        public static final String ASTRALSORCERY = "astralsorcery";
        public static final String BOTANIA = "botania";
        public static final String EIDOLON = "eidolon";
        public static final String HEXBLADES = "hexblades";
        public static final String NATURESAURA = "naturesaura";
        public static final String TWILIGHTFOREST = "twilightforest";
        public static final String BIOMESOPLENTY = "biomesoplenty";
        public static final String BETTERENDFORGE = "betterendforge";
        public static final String DESOLATION = "desolation";
        public static final String BIOMESYOULLGO = "byg";
        public static final String OUTEREND = "outer_end";
        public static final String TRAVERSE = "traverse";
        public static final String WILDNATURE = "wild_nature";
    }
}