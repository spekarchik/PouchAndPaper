package com.pekar.pouchandpaper.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class Sound
{
    public void playSoundByBlock(Player player, BlockPos pos, SoundEvent soundEvent, float volume, float pitch)
    {
        playSound(player, pos, soundEvent, SoundSource.BLOCKS, volume, pitch);
    }

    public void playSoundByBlock(Player player, BlockPos pos, SoundEvent soundEvent)
    {
        playSoundByBlock(player, pos, soundEvent, 1F, 1F);
    }

    public void playSound(Player player, BlockPos pos, SoundEvent soundEvent, SoundSource soundSource, float volume, float pitch)
    {
        var level = player.level();
        if (level.isClientSide())
            level.playLocalSound(pos, soundEvent, soundSource, volume, pitch, true);
        else
            level.playSound(player, pos, soundEvent, soundSource, volume, pitch);
    }
}
