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

import mindustry.world.Block;

public class nucdDefence {
    public static Block
    radioalloyWall,radioalloyWallLarge;

    public static void load() {
        radioalloyWall = new Wall("radioalloy-wall"){{
            requirements(Category.defense, with(radioalloy, 10, thorium, 4));
            health = 1250;
            lightningChance = 0.1f;
            lightningDamage = 40;
            lightningLength = 10;
            lightningColor = Color.valueOf("B5C103");
            absorbLasers = true;
            buildCostMultiplier = 1.25f;
            researchCostMultiplier = 150f;
        }};

        radioalloyWallLarge = new Wall("radioalloy-wall-large"){{
            requirements(Category.defense, ItemStack.mult(radioalloyWall.requirements, 4));
            health = 5000;
            size = 2;
            lightningChance = 0.25f;
            lightningDamage = 75;
            lightningLength = 20;
            lightningColor = Color.valueOf("B5C103");
            absorbLasers = true;
            buildCostMultiplier = 2.0f;
            researchCostMultiplier = 150f;
        }};

    }
}
