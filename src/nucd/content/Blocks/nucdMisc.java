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

public class nucdMisc {
    public static Block
    nucdustrySym,nuclearOverdriver, coreNuclear;

    public static void load() {
        nucdustrySym = new Wall("Nucleardustry") {{
            requirements(Category.defense, with(thorium, 1));
            health = 10;
            size = 2;
            placeablePlayer = false;
        }};
        coreNuclear = new CoreBlock("core-nuclear"){{
            requirements(Category.effect, with(copper,7500, lead, 7500, graphite, 4500, silicon,4500, thorium, 3500, uranium,2500));
            researchCost = with(copper,22500, lead,17500,graphite,12500,silicon,12500, thorium,7500, uranium,7500);
            unitType = nucdUnitTypes.neutron;
            health = 12000;
            itemCapacity = 20000;
            size = 7;
            unitCapModifier = 32;
        }};
        nuclearOverdriver = new OverdriveProjector("nuclear-overdriver"){{
            requirements(Category.effect, with(copper, 200, lead, 150, titanium, 100, silicon, 100, uranium, 50, plutonium, 50 ));
            itemCapacity = 60;
            liquidCapacity = 120;
            size = 3;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            phaseColor = Color.valueOf("46C44E");
            baseColor = Color.valueOf("46C44E");
            range = 240f;
            speedBoost = 3.0f;
            hasBoost = false;
            useTime = 300f;

            consume(new ConsumeItemRadioactive());
            consumeLiquid(nucdLiquids.radioactiveSolution, 0.1f);
            consumePower(6f);
        }};
    }
}
