package com.pekar.pouchandpaper.events;

import net.neoforged.bus.api.IEventBus;

import static net.neoforged.neoforge.common.NeoForge.EVENT_BUS;

public class EventRegistry
{
    public static void registerEvents()
    {
        register(new PlayerInteractionEvents());
    }

    public static void registerEventsOnModBus(IEventBus modEventBus)
    {
    }

    private static void register(IEventHandler eventHandler)
    {
        EVENT_BUS.register(eventHandler);
    }

    private static void register(IEventBus modEventBus, IEventHandler eventHandler)
    {
        modEventBus.register(eventHandler);
    }
}
