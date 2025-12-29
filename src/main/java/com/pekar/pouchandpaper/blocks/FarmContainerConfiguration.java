package com.pekar.pouchandpaper.blocks;

/**
 * Configuration values for farm-like containers (pouches/packs).
 *
 * @param capacityPerPack        maximum number of items a single pack/pouch can hold
 * @param itemsToExtractPerUse   how many items are extracted when a player uses the container (non-shift); use 1 when shift
 * @param itemsToAddPerUse       how many items are added when a player adds items (non-shift); use 1 when shift
 * @param packsToAddPerUse       how many packs/pouches are added when a player uses a pack item (non-shift); use 1 when shift
 */
public record FarmContainerConfiguration(int capacityPerPack, int itemsToExtractPerUse, int itemsToAddPerUse, int packsToAddPerUse)
{
}
