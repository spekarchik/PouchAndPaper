package com.pekar.compactmod;

import com.pekar.compactmod.blocks.BlockRegistry;
import com.pekar.compactmod.items.ItemRegistry;
import com.pekar.compactmod.tab.MainTab;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CompactMod.MODID)
public class CompactMod
{
    // Directly reference a log4j logger.
//    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "compactmod";

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);


    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> COMPACT_MOD_TAB = new MainTab().createTab();
//    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
//            //.withTabsBefore(CreativeModeTabs.COMBAT)
//            .icon(() -> ItemRegistry.PAPER_STACK.get().getDefaultInstance())
//            .displayItems((parameters, output) -> {
//                output.accept(ItemRegistry.PAPER_STACK.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
//            }).build());


    public CompactMod()
    {
        initialyzeRegistry();

        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        //modEventBus.addListener(this::addCreative);

        // Register the setup method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void initialyzeRegistry()
    {
        BlockRegistry.initStatic();
        ItemRegistry.initStatic();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // some preinit code
//        LOGGER.info("HELLO FROM PREINIT");
//        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        // TODO: register key bindings
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    //@SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // do something when the server starts
//        LOGGER.info("HELLO from server starting");
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
//            event.accept(ItemRegistry.INK_BOTTLE);
    }
}
