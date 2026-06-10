package com.pekar.pouchandpaper.tooltip;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class TooltipLine implements ITooltipLine
{
    private final Tooltip tooltip;
    private final MutableComponent component;
    private final boolean ignoreEmptyLines;

    TooltipLine(Tooltip tooltip, String descriptionRoot, boolean ignoreEmptyLines)
    {
        this.tooltip = tooltip;
        this.component = Component.translatable(descriptionRoot).withStyle(ChatFormatting.GRAY);
        this.ignoreEmptyLines = ignoreEmptyLines;
    }

    TooltipLine(Tooltip tooltip)
    {
        this.tooltip = tooltip;
        this.component = Component.empty();
        this.ignoreEmptyLines = false;
    }

    @Override
    public ITooltipLine styledAs(TextStyle style, boolean applyStyle)
    {
        if (applyStyle)
        {
            switch (style)
            {
                case Header -> component.withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.WHITE);
                case Subheader -> component.withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GRAY);
                case Notice -> component.withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY);
                case ImportantNotice -> component.withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.WHITE);
                case DarkGray -> component.withStyle(ChatFormatting.DARK_GRAY);
                default -> component.withStyle(ChatFormatting.RESET).withStyle(ChatFormatting.GRAY);
            }
        }
        return this;
    }

    @Override
    public final ITooltipLine asHeader()
    {
        return styledAs(TextStyle.Header);
    }

    @Override
    public final ITooltipLine asSubHeader()
    {
        return styledAs(TextStyle.Subheader);
    }

    @Override
    public final ITooltipLine asNotice()
    {
        return styledAs(TextStyle.Notice);
    }

    @Override
    public final ITooltipLine asImportantNotice()
    {
        return styledAs(TextStyle.ImportantNotice);
    }

    @Override
    public final ITooltipLine asDarkGrey()
    {
        return styledAs(TextStyle.DarkGray);
    }

    @Override
    public final ITooltipLine withFormatting(ChatFormatting formatting, boolean applyFormatting)
    {
        if (applyFormatting)
            component.withStyle(formatting);

        return this;
    }

    @Override
    public final void apply()
    {
        if (isEmpty() && ignoreEmptyLines) return;
        tooltip.apply(this);
    }

    final Component getComponent()
    {
        return component;
    }

    private boolean isEmpty()
    {
        return component.getString().isEmpty();
    }

    private ITooltipLine styledAs(TextStyle style)
    {
        return styledAs(style, true);
    }
}
