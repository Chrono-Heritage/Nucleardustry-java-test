package nucd.content.Blocks;

import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.type.StatusEffect.*;
import mindustry.content.StatusEffects.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.campaign.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.legacy.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.power.SolarGenerator;
import mindustry.world.blocks.storage.Unloader;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.part.RegionPart;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import nucd.content.*;
import nucd.content.nucdBlocks.*;
import nucd.content.nucdItems.*;
import nucd.content.nucdLiquids.*;
import nucd.content.nucdStatusEffects.*;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static nucd.content.nucdItems.*;
import static nucd.content.nucdLiquids.*;
import static nucd.content.nucdStatusEffects.*;

public class nucdCrafting {
    public static Block 
    duAmmoConstructer, electrolyzer, enrichedUraniumCrafter, fissionFuelInfuser, isotopeSeparator, plutonium239Producer, saltExtractor, radioactiveLiquefier, radioactiveSeparator, radioactiveSmelter;
    
    public static void load() {
    /* Crafting */
    duAmmoConstructer = new GenericCrafter("du-ammo-constructer") {{
        requirements(Category.crafting, with(copper,125, graphite,45, metaglass,25, titanium,20, uraniumDepleted,10));
        researchCost = ItemStack.with(copper,1250,graphite,450, metaglass, 300,titanium,250,uraniumDepleted,150);
        itemCapacity = 60;
        health = 650;
        size = 3;
        hasPower = true;
        hasItems = true;
        craftTime = 120;
        buildCostMultiplier = 1.5f;
        outputItem = new ItemStack(uraniumDepletedAmmo,2);
        ambientSound = Vars.tree.loadSound("power/GeigerCounter");
        drawer = new DrawMulti(new DrawDefault() , new DrawRegion("-top"), new DrawFlame());

        consumePower(1.25f);
        consumeItems(with(uraniumDepleted,2, lead,6, titanium,8));
    }};
    electrolyzer = new GenericCrafter("electrolyzer") {{
        requirements(Category.crafting, with(copper,100,lead,85,graphite,55, metaglass,40, titanium,25));
        researchCost = with(copper,1750,lead,950,graphite,750,metaglass,550,titanium,350);
        itemCapacity = 60;
        liquidCapacity = 120;
        health = 650;
        size = 3;
        hasPower = true;
        hasItems = true;
        hasLiquids = true;
        craftTime = 90;
        outputLiquid = new LiquidStack(nucdLiquids.deuterium,0.1f);
        drawer = new DrawMulti( new DrawDefault() , new DrawRegion("-top"), new DrawFlame());

        consumePower(2.0f);
        consumeLiquid(nucdLiquids.heavyWater, 0.1f);
        consumeItem(salt,1);
    }};
    enrichedUraniumCrafter = new GenericCrafter("enriched-uranium-crafter") {{
        requirements(Category.crafting, with(copper,125, silicon,55, metaglass,30, titanium,20,thorium,15,uranium,15));
        researchCost = with(copper,1250, silicon,600, metaglass,450, titanium,300, thorium,200, uranium,200);
        itemCapacity = 60;
        health = 450;
        size = 2;
        hasPower = true;
        hasItems = true;
        craftTime = 120;
        outputItems = (with(uraniumEnriched,2,uraniumDepleted,1));
        ambientSound = Vars.tree.loadSound("power/GeigerCounter");
        drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault() , new DrawRegion("-top"), new DrawFlame());

        consumePower(2.0f);
        consumeItems(with(uranium235,1, uranium238,4));
    }};
    fissionFuelInfuser = new GenericCrafter("fission-fuel-infuser") {{
        requirements(Category.crafting, with(copper,100, graphite,45, metaglass,30, titanium,25,uranium,15));
        researchCost = with(copper,1000,graphite,550,metaglass,400,titanium,250,uranium,200);
        itemCapacity = 60;
        health = 450;
        size = 2;
        hasPower = true;
        hasItems = true;
        craftTime = 120;
        buildCostMultiplier = 1.25f;
        outputItem = new ItemStack(uraniumFuel,1);
        ambientSound = Vars.tree.loadSound("power/GeigerCounter");
        drawer = new DrawMulti(new DrawDefault() , new DrawRegion("-top"), new DrawFlame());

        consumePower(1.1f);
        consumeItems(with(metaglass, 2, uraniumEnriched,4));
    }};
    isotopeSeparator = new Separator("isotope-separator") {{
        requirements(Category.crafting, with(copper,150, lead,85, graphite,55, metaglass,35, titanium,25, thorium,15, uranium, 15));
        researchCost = with(copper, 2000, lead, 1500, graphite,850,graphite,600,metaglass,500,titanium,325,thorium,200,uranium,200);
        itemCapacity = 60;
        health = 650;
        size = 3;
        hasPower = true;
        hasItems = true;
        hasLiquids = false;
        craftTime = 60;
        ambientSound = Vars.tree.loadSound("power/GeigerCounter");
        drawer = new DrawMulti(new DrawRegion("-bottom"),  new DrawRegion("-spinner", 3, true), new DrawDefault(), new DrawRegion("-top"));
        results = with(
            uranium235, 1,
            uranium238, 4
        );
        consumePower(3.0f);
        consumeItem(uranium, 1);
    }};
    plutonium239Producer = new GenericCrafter("plutonium239-producer") {{
        requirements(Category.crafting, with(copper,125, graphite, 40, silicon,40, metaglass,30, titanium,20, plutonium,10));
        researchCost = with(copper,1000,graphite,600, silicon,600,metaglass,450,titanium,300,plutonium,200);
        itemCapacity = 60;
        health = 450;
        size = 2;
        hasPower = true;
        hasItems = true;
        craftTime = 180;
        buildCostMultiplier = 1.0f;
        outputItem = new ItemStack(plutonium239, 6);
        ambientSound = Vars.tree.loadSound("power/GeigerCounter");
        drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault() , new DrawRegion("-top"), new DrawFlame());

        consumePower(1.1f);
        consumeItems(with(uranium235,2, uranium238,5));
    }};
    saltExtractor = new GenericCrafter("salt-extractor"){{
        requirements(Category.crafting, with(copper, 30, lead, 25, metaglass, 25, silicon, 10));
        researchCost = with(copper,450,lead,325,metaglass,325,silicon,250);
        itemCapacity = 60;
        health = 450;
        size = 2;
        hasPower = true;
        hasItems = true;
        hasLiquids = true;
        craftTime = 120;
        outputItem = new ItemStack(salt,2);
        drawer = new DrawMulti(new DrawRegion("-bottom"),  new DrawRegion("-spinner", 5, true), new DrawDefault(), new DrawRegion("-top"));
        consumeLiquid(Liquids.water, 0.1f);
        consumePower(1.5f);
    }};
    radioactiveLiquefier = new GenericCrafter("radioactive-liquefier") {{
        requirements(Category.crafting, with(copper,100, silicon,75, metaglass,50, titanium,30,thorium,20));
        researchCost = with(copper,1250,silicon,1000,metaglass,500,titanium,350,thorium,250);
        itemCapacity = 60;
        liquidCapacity = 120;
        health = 450;
        size = 2;
        hasPower = true;
        hasItems = true;
        hasLiquids = true;
        craftTime = 60;
        buildCostMultiplier = 0.85f;
        outputLiquid = new LiquidStack(nucdLiquids.radioactiveSolution, 6f / 60f);
        ambientSound = Vars.tree.loadSound("power/GeigerCounter");
        drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault() , new DrawRegion("-top"), new DrawFlame());

        consumePower(1.1f);
        consumeItem(thorium, 2);
        consumeLiquid(Liquids.water, 0.1f);
    }};
    radioactiveSeparator = new Separator("radioactive-separator") {{
        requirements(Category.crafting, with(copper,125, silicon,55, graphite,55, metaglass,30, titanium,35, thorium,25));
        researchCost = with(copper,2500,silicon,750, graphite,750,metaglass,450, titanium,350);
        itemCapacity = 60;
        liquidCapacity = 120;
        health = 650;
        size = 3;
        hasPower = true;
        hasItems = true;
        hasLiquids = true;
        craftTime = 45;
        buildCostMultiplier = 0.85f;
        ambientSound = Vars.tree.loadSound("power/GeigerCounter");
        drawer = new DrawMulti(new DrawRegion("-bottom"),  new DrawRegion("-spinner", 3, true), new DrawDefault());
        results = with(
            thorium, 1,
            uranium, 4,
            plutonium, 4
        );
        consumePower(3.0f);
        consumeLiquid(nucdLiquids.radioactiveSolution,0.05f);
    }};
    radioactiveSmelter = new GenericCrafter("radioactive-smelter") {{
        requirements(Category.crafting, with(copper,100, silicon,45, metaglass,25, titanium,10,thorium,10));
        researchCost = with(copper, 1500, silicon,650, metaglass, 400, titanium,250,thorium,250);
        itemCapacity = 60;
        health = 650;
        size = 3;
        hasPower = true;
        hasItems = true;
        craftTime = 75;
        buildCostMultiplier = 1.15f;
        outputItem = new ItemStack(radioalloy, 2);
        ambientSound = Vars.tree.loadSound("power/GeigerCounter");
        drawer = new DrawMulti(new DrawDefault() , new DrawRegion("-top"), new DrawFlame());

        consumePower(1.75f);
        consumeItems(with(thorium, 1, uranium, 1, titanium, 3));
    }};
}
}
