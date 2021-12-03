package com.pekar.compactmod.items;

import java.util.Set;

public class InkBottle extends ModItem
{
    @Override
    public void addRecipeNames(Set<String> recipes)
    {
        recipes.add("inkbottle");
        recipes.add("inkdye");
    }
}
