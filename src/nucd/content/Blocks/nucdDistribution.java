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

public class nucdDistribution {
    public static Block 
    nuclearMassDriver;

    public static void load() {
        nuclearMassDriver = new MassDriver("nuclear-mass-driver"){{
            requirements(Category.distribution, with(copper, 300, graphite, 200, silicon, 125, uranium, 55));
            researchCost = with(copper,1500,graphite,750,silicon,550,uranium,400);
            itemCapacity = 240;
            liquidCapacity = 120;
            health = 750;
            size = 3;
            buildCostMultiplier = 0.4f;
            hasPower = true;
            hasItems = true;
            range = 880;
            rotateSpeed = 20;
            bulletSpeed = 11.0f;
            bulletLifetime = 300;
            shake = 1.0f;
            minDistribute = 1;

            consumePower(6.0f);
        }};
    }
}
