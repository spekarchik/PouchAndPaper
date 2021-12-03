package com.pekar.compactmod.items;

import java.util.Set;

public class PaperStack extends ModItem
{
    @Override
    public void addRecipeNames(Set<String> recipes)
    {
        recipes.add("paperstack");
        recipes.add("paper");
    }
}
