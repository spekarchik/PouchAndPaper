package com.pekar.pouchandpaper.blocks.entity;

import com.pekar.pouchandpaper.blocks.BlockRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static com.pekar.pouchandpaper.Main.MODID;
import static com.pekar.pouchandpaper.utils.Resources.createResourceLocation;

public class BlockEntityRegistry
{
    public static final BlockEntityType<BurntPaperBlockEntity> BURNT_PAPER_BLOCK_ENTITY =
            register("burnt_paper_block_entity", BurntPaperBlockEntity::new, BlockRegistry.BURNT_PAPER_BLOCK);

    public static final BlockEntityType<PouchOfWheatSeedsBlockEntity> POUCH_OF_WHEAT_BLOCK_ENTITY =
            register("pouch_of_wheat_block_entity", PouchOfWheatSeedsBlockEntity::new, BlockRegistry.POUCH_OF_WHEAT);

    public static final BlockEntityType<PouchOfBeetrootSeedsBlockEntity> POUCH_OF_BEET_BLOCK_ENTITY =
            register("pouch_of_beet_block_entity", PouchOfBeetrootSeedsBlockEntity::new, BlockRegistry.POUCH_OF_BEET);

    public static final BlockEntityType<PouchOfPumpkinSeedsBlockEntity> POUCH_OF_PUMPKIN_BLOCK_ENTITY =
            register("pouch_of_pumpkin_block_entity", PouchOfPumpkinSeedsBlockEntity::new, BlockRegistry.POUCH_OF_PUMPKIN);

    public static final BlockEntityType<PouchOfWatermelonSeedsBlockEntity> POUCH_OF_WATERMELON_BLOCK_ENTITY =
            register("pouch_of_watermelon_block_entity", PouchOfWatermelonSeedsBlockEntity::new, BlockRegistry.POUCH_OF_WATERMELON);

    public static final BlockEntityType<PouchOfCocoaBeansBlockEntity> POUCH_OF_COCOA_BLOCK_ENTITY =
            register("pouch_of_cocoa_block_entity", PouchOfCocoaBeansBlockEntity::new, BlockRegistry.POUCH_OF_COCOA);

    public static final BlockEntityType<PotatoSackBlockEntity> SACK_OF_POTATO_BLOCK_ENTITY =
            register("sack_of_potato_block_entity", PotatoSackBlockEntity::new, BlockRegistry.SACK_OF_POTATO);

    public static final BlockEntityType<BeetrootSackBlockEntity> SACK_OF_BEETROOT_BLOCK_ENTITY =
            register("sack_of_beetroot_block_entity", BeetrootSackBlockEntity::new, BlockRegistry.SACK_OF_BEETROOT);

    public static final BlockEntityType<CarrotSackBlockEntity> SACK_OF_CARROT_BLOCK_ENTITY =
            register("sack_of_carrot_block_entity", CarrotSackBlockEntity::new, BlockRegistry.SACK_OF_CARROT);

    public static final BlockEntityType<PackOfFeathersBlockEntity> PACK_OF_FEATHERS_BLOCK_ENTITY =
            register("pack_of_feathers_block_entity", PackOfFeathersBlockEntity::new, BlockRegistry.PACK_OF_FEATHERS);

    public static void initStatic()
    {
        // just to initialize static members
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder.Factory<T> factory, Block block)
    {
        var id = createResourceLocation(MODID, name);
        var blockEntityType = FabricBlockEntityTypeBuilder.create(factory, block).build();
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, blockEntityType);
    }
}
