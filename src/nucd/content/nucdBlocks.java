package nucd.content;

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
import nucd.content.*;
import nucd.content.nucdItems.*;
import nucd.content.nucdLiquids.*;
import nucd.content.nucdStatusEffects.*;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;
import static mindustry.type.StatusEffect.*;

public class nucdBlocks {

    public static Block 
    /* Symbols */
    nucdustrySym,
    /* Crafting */
    duAmmoConstructer, enrichedUraniumCrafter, fissionFuelInfuser, isotopeSeparator, plutonium239Producer,  saltExtractor, radioactiveLiquefier, radioactiveSeparator, radioactiveSmelter,
    electrolyzer,
    /* Environment */
    oreUranium, orePlutonium,
    /* Defence */
    radioalloyWall,radioalloyWallLarge,
    /* Distribution */
    nuclearMassDriver,
    /* Misc */
    nuclearOverdriver, 
    /* Power */
    fissionReactor, fusionReactor, nuclearFurnace, pressurizedHeavyWaterReactor,
    /* Production */
    heavyWaterExtractor, nuclearDrill, nuclearPump,
    /* Turret */
    depletedUraniumRifle, nuclearLaserEmitter, nuclearWeaponLauncher
    ;
    public static void load() {
        /* Symbols */
        nucdustrySym = new Wall("Nucleardustry") {{
            requirements(Category.defense, with(Items.thorium, 1));
            health = 10;
            size = 2;
            placeablePlayer = false;
        }};
        /* Crafting */
        duAmmoConstructer = new GenericCrafter("du-ammo-constructer") {{
            requirements(Category.crafting, with(Items.copper,125, Items.graphite,45, Items.metaglass,25, Items.titanium,20,nucdItems.uraniumDepleted,10));
            itemCapacity = 60;
            health = 650;
            size = 3;
            hasPower = true;
            hasItems = true;
            craftTime = 120;
            buildCostMultiplier = 1.5f;
            outputItem = new ItemStack(nucdItems.uraniumDepletedAmmo,2);
            ambientSound = Vars.tree.loadSound("power/GeigerCounter");
            drawer = new DrawMulti(new DrawDefault() , new DrawRegion("-top"), new DrawFlame());

            consumePower(1.25f);
            consumeItems(with(nucdItems.uraniumDepleted,2, Items.lead,6, Items.titanium,8));
        }};
        electrolyzer = new GenericCrafter("electrolyzer") {{
            requirements(Category.crafting, with(Items.copper,100, Items.graphite,55, Items.metaglass,40, Items.titanium,25));
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
            consumeItem(nucdItems.salt,1);
        }};
        enrichedUraniumCrafter = new GenericCrafter("enriched-uranium-crafter") {{
            requirements(Category.crafting, with(Items.copper,125, Items.silicon,55, Items.metaglass,30, Items.titanium,20,Items.thorium,15));
            itemCapacity = 60;
            health = 450;
            size = 2;
            hasPower = true;
            hasItems = true;
            craftTime = 120;
            outputItems = (with(nucdItems.uraniumEnriched,2,nucdItems.uraniumDepleted,1));
            ambientSound = Vars.tree.loadSound("power/GeigerCounter");
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault() , new DrawRegion("-top"), new DrawFlame());

            consumePower(2.0f);
            consumeItems(with(nucdItems.uranium235,1, nucdItems.uranium238,4));
        }};
        fissionFuelInfuser = new GenericCrafter("fission-fuel-infuser") {{
            requirements(Category.crafting, with(Items.copper,100, Items.graphite,45, Items.metaglass,30, Items.titanium,25,nucdItems.uranium,15));
            itemCapacity = 60;
            health = 450;
            size = 2;
            hasPower = true;
            hasItems = true;
            craftTime = 120;
            buildCostMultiplier = 1.25f;
            outputItem = new ItemStack(nucdItems.uraniumFuel,1);
            ambientSound = Vars.tree.loadSound("power/GeigerCounter");
            drawer = new DrawMulti(new DrawDefault() , new DrawRegion("-top"), new DrawFlame());

            consumePower(1.1f);
            consumeItems(with(Items.metaglass, 2, nucdItems.uraniumEnriched,4));
        }};
        isotopeSeparator = new Separator("isotope-separator") {{
            requirements(Category.crafting, with(Items.copper,150, Items.lead,85, Items.graphite,55, Items.metaglass,35, Items.titanium,25, Items.thorium,15, nucdItems.uranium, 15));
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
                nucdItems.uranium235, 1,
                nucdItems.uranium238, 4
            );
            consumePower(3.0f);
            consumeItem(nucdItems.uranium, 1);
        }};
        plutonium239Producer = new GenericCrafter("plutonium239-producer") {{
            requirements(Category.crafting, with(Items.copper,125, Items.graphite, 40, Items.silicon,40, Items.metaglass,30, Items.titanium,20, nucdItems.plutonium,10));
            itemCapacity = 60;
            health = 450;
            size = 2;
            hasPower = true;
            hasItems = true;
            craftTime = 180;
            buildCostMultiplier = 1.0f;
            outputItem = new ItemStack(nucdItems.plutonium239, 6);
            ambientSound = Vars.tree.loadSound("power/GeigerCounter");
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault() , new DrawRegion("-top"), new DrawFlame());

            consumePower(1.1f);
            consumeItems(with(nucdItems.uranium235,2, nucdItems.uranium238,5));
        }};
        saltExtractor = new GenericCrafter("salt-extractor"){{
            requirements(Category.crafting, with(Items.copper, 30, Items.lead, 25, Items.metaglass, 25, Items.silicon, 10));
            itemCapacity = 60;
            health = 450;
            size = 2;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            craftTime = 120;
            outputItem = new ItemStack(nucdItems.salt,2);
            drawer = new DrawMulti(new DrawRegion("-bottom"),  new DrawRegion("-spinner", 5, true), new DrawDefault(), new DrawRegion("-top"));
            consumeLiquid(Liquids.water, 0.1f);
            consumePower(1.5f);
        }};
        radioactiveLiquefier = new GenericCrafter("radioactive-liquefier") {{
            requirements(Category.crafting, with(Items.copper,100, Items.silicon,75, Items.metaglass,50, Items.titanium,30,Items.thorium,20));
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
            consumeItem(Items.thorium, 2);
            consumeLiquid(Liquids.water, 0.1f);
        }};
        radioactiveSeparator = new Separator("radioactive-separator") {{
            requirements(Category.crafting, with(Items.copper,125, Items.silicon,55, Items.graphite,55, Items.metaglass,30, Items.titanium,35, Items.thorium,25));
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
                Items.thorium, 1,
                nucdItems.uranium, 4,
                nucdItems.plutonium, 4
            );
            consumePower(3.0f);
            consumeLiquid(nucdLiquids.radioactiveSolution,0.05f);
        }};
        radioactiveSmelter = new GenericCrafter("radioactive-smelter") {{
            requirements(Category.crafting, with(Items.copper,100, Items.silicon,45, Items.metaglass,25, Items.titanium,10,Items.thorium,10));
            itemCapacity = 60;
            health = 650;
            size = 3;
            hasPower = true;
            hasItems = true;
            craftTime = 75;
            buildCostMultiplier = 1.15f;
            outputItem = new ItemStack(nucdItems.radioalloy, 2);
            ambientSound = Vars.tree.loadSound("power/GeigerCounter");
            drawer = new DrawMulti(new DrawDefault() , new DrawRegion("-top"), new DrawFlame());

            consumePower(1.75f);
            consumeItems(with(Items.thorium, 1, nucdItems.uranium, 1, Items.titanium, 3));
        }};

        /* Defence */
        radioalloyWall = new Wall("radioalloy-wall"){{
            requirements(Category.defense, with(nucdItems.radioalloy, 10, Items.thorium, 4));
            health = 1250;
            lightningChance = 0.1f;
            lightningDamage = 40;
            lightningLength = 10;
            lightningColor = Color.valueOf("B5C103");
            absorbLasers = true;
            buildCostMultiplier = 1.25f;
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
        }};

        /* Distribution */
        nuclearMassDriver = new MassDriver("nuclear-mass-driver"){{
            requirements(Category.distribution, with(Items.copper, 300, Items.graphite, 200, Items.silicon, 125, nucdItems.uranium, 55));
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

        /*Environment */
        oreUranium = new OreBlock(nucdItems.uranium){{
            playerUnmineable = true;
            oreDefault = true;
            variants = 3;
            oreThreshold = 0.891f;
            oreScale = 23.4f;
        }};
        orePlutonium = new OreBlock(nucdItems.plutonium){{
            playerUnmineable = true;
            oreDefault = true;
            variants = 3;
            oreThreshold = 0.91f;
            oreScale = 20.4f;
        }}; 

        /* Misc */
        nuclearOverdriver = new OverdriveProjector("nuclear-overdriver"){{
            requirements(Category.effect, with(Items.copper, 200, Items.lead, 150, Items.titanium, 100, Items.silicon, 100, nucdItems.uranium, 50, nucdItems.plutonium, 50 ));
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
            
        /* Power */
        fissionReactor = new NuclearReactor("fission-reactor"){{
            requirements(Category.power, with(Items.copper, 400, Items.lead, 350, Items.metaglass, 275, Items.silicon, 200, Items.thorium, 150, nucdItems.uranium, 150));
            ambientSound = Vars.tree.loadSound("power/FissionReactor");
            ambientSoundVolume = 0.25f;
            buildCostMultiplier = 0.5f;
            fuelItem = nucdItems.uraniumFuel;
            itemCapacity = 1;
            size = 5;
            health = 1600;
            itemDuration = 1800;
            powerProduction = 100f;
            heating = 0.05f;
            explosionRadius = 30;
            explosionDamage = 5192;
            explodeSound = Vars.tree.loadSound("power/ReactorExplode");
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault() , new DrawRegion("-top"));

            consumeItem(nucdItems.uraniumFuel,1);
            consumeLiquid(Liquids.cryofluid, heating / coolantPower).update(false);
        }};
        fusionReactor = new ImpactReactor("fusion-reactor"){{
            requirements(Category.power, with(Items.copper, 500, Items.lead, 450, Items.silicon, 350, Items.graphite, 350, Items.titanium, 350, Items.metaglass, 250, Items.thorium, 150));
            liquidCapacity = 120;
            health = 1600;
            size = 4;
            hasPower = true;
            hasLiquids = true;
            itemDuration = 180;
            buildCostMultiplier = 0.35f;
            warmupSpeed = 0.1f;
            ambientSound = Sounds.pulse;
            ambientSoundVolume = 0.2f;
            powerProduction = 440.0f;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawPlasma(), new DrawDefault() );

            consumePower(200f);
            consumeLiquids(LiquidStack.with(nucdLiquids.deuterium, 3f / 60f, nucdLiquids.tritium, 3f / 60f));
        }};
        nuclearFurnace = new ConsumeGenerator("nuclear-furnace"){{
            requirements(Category.power, with(Items.copper, 65, Items.lead, 45, Items.silicon, 25, Items.titanium, 15, nucdItems.uranium,10));
            health = 450;
            size = 2;
            buildCostMultiplier = 1.15f;
            powerProduction = 6.5f;
            itemDuration = 1080f;
            ambientSound = Vars.tree.loadSound("power/GeigerCounter");
            ambientSoundVolume = 0.1f;

            consume(new ConsumeItemRadioactive());

            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-top"), new DrawWarmupRegion());
        }};
        pressurizedHeavyWaterReactor = new ConsumeGenerator("pressurized-heavy-water-reactor"){{
            requirements(Category.power, with(Items.copper, 150, Items.lead, 75, Items.silicon, 45, Items.metaglass, 45, Items.titanium, 25, nucdItems.uranium,15));
            itemCapacity = 60;
            liquidCapacity = 120;
            health = 750;
            size = 3;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            buildCostMultiplier = 0.7f;
            powerProduction = 3.5f;
            itemDuration = 150f;
            ambientSound = Vars.tree.loadSound("power/GeigerCounter");
            ambientSoundVolume = 0.1f;
            outputLiquid = new LiquidStack(nucdLiquids.tritium, 0.1f);

            consume(new ConsumeItemRadioactive());
            consumeLiquid(nucdLiquids.heavyWater,0.1f);

            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-top"), new DrawFlame());
        }};

        /* Production */
        heavyWaterExtractor = new SolidPump("heavy-water-extractor"){{
            requirements(Category.production, with(Items.copper, 40, Items.metaglass, 25, Items.graphite, 10, Items.titanium, 10));
            result = nucdLiquids.heavyWater;
            pumpAmount = 0.11f;
            size = 2;
            liquidCapacity = 60f;
            rotateSpeed = 1.4f;

            consumePower(1.75f);
        }};
        nuclearDrill = new Drill("nuclear-drill"){{
            requirements(Category.production, with(Items.copper, 125, Items.silicon, 75, Items.titanium, 50, nucdItems.uranium, 35));
            itemCapacity = 60;
            liquidCapacity = 120;
            health = 850;
            size = 4;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            drillTime = 220;
            tier = 5;
            buildCostMultiplier = 0.8f;
            liquidBoostIntensity = 1.75f;
            drawMineItem = false;
            liquidBoostIntensity = 1.8f;

            consumePower(3f);
            consumeLiquid(Liquids.water, 0.1f).boost();
        }};
        nuclearPump = new Pump("nuclear-pump"){{
            requirements(Category.liquid, with(Items.copper, 75, Items.metaglass, 50, Items.silicon, 30, Items.titanium, 30, nucdItems.uranium, 20));
            itemCapacity = 60;
            liquidCapacity = 120;
            health = 650;
            size = 3;
            buildCostMultiplier = 0.75f;
            hasPower = true;
            hasLiquids = true;
            pumpAmount = 0.4f;
            consumeTime = 180;

            consumePower(3f);
            consume(new ConsumeItemRadioactive());
        }};

         /* Turrets */
         depletedUraniumRifle = new ItemTurret("depleted-uranium-rifle"){{
            requirements(Category.turret, with(Items.copper, 45, Items.lead, 35, Items.metaglass, 20, nucdItems.uranium, 10));
            health = 450;
            size = 2;
            reload = 5f;
            maxAmmo = 100;
            range = 196;
            rotateSpeed = 10f;
            inaccuracy = 2f;
            recoil = 5f;
            shootSound = Sounds.shootAlt;
            shootCone = 30f;
            shoot = new ShootSpread(1,0);
            ammo(
                Items.lead,  new BasicBulletType(30f, 20){{
                    width = 7f;
                    height = 15f;
                    reloadMultiplier = 1.15f;
                    knockback = 0.5f;
                    lifetime = 60f;
                    ammoMultiplier = 4;
                }},
                Items.metaglass, new BasicBulletType(30f, 30){{
                    width = 10f;
                    height = 18f;
                    ammoMultiplier = 4;
                    lifetime = 60f;
                    fragBullets = 12;
                    fragBullet = new BasicBulletType(30f, 30){{
                        width = 5f;
                        height = 12f;
                        shrinkY = 1f;
                        lifetime = 20f;
                        backColor = Pal.gray;
                        frontColor = Color.white;
                        despawnEffect = Fx.none;
                        collidesGround = true;
                    }};
                }},
                nucdItems.uraniumDepleted, new BasicBulletType(30f, 40){{
                    width = 7f;
                    height = 15f;
                    ammoMultiplier = 6;
                    lifetime = 60f;
                }},
                nucdItems.uraniumDepletedAmmo, new BasicBulletType(30f, 50){{
                    width = 7f;
                    height = 15f;
                    ammoMultiplier = 8;
                    lifetime = 60f;
                    pierceArmor = true;
                    status = StatusEffects.burning;
                    statusDuration = 300;
                }}
            );
            limitRange();
        }};
        nuclearLaserEmitter = new ItemTurret("nuclear-laser-emitter"){{
            requirements(Category.turret, with(Items.copper, 55, Items.lead, 55, Items.graphite, 35, Items.thorium, 20, nucdItems.uranium, 20, nucdItems.plutonium,20));
            health = 450;
            size = 2;
            reload = 80f;
            maxAmmo = 20;
            range = 256;
            rotateSpeed = 10f;
            recoil = 5f;
            shootSound = Sounds.laser;
            shootCone = 30f;
            shoot = new ShootSpread(1,0);
            ammo(
                Items.thorium,  new LaserBulletType(150){{
                    colors = new Color[]{Color.valueOf("CB8EBF"), Color.valueOf("F8A3C7"), Color.white};
                    length = 342;
                    width = 17;
                    ammoMultiplier = 1.0f;
                    knockback = 0.5f;
                    status = nucdStatusEffects.radiation;
                    statusDuration = 600;
                    pierceCap = 2;
                }},
                nucdItems.uranium, new LaserBulletType(200){{
                    colors = new Color[]{Color.valueOf("218A21"), Color.valueOf("32CD32"), Color.white};
                    length = 342;
                    width = 17;
                    ammoMultiplier = 2.0f;
                    knockback = 0.75f;
                    status = nucdStatusEffects.radiation;
                    statusDuration = 600;
                    pierceCap = 3;
                }},
                nucdItems.plutonium, new LaserBulletType(250){{
                    colors = new Color[]{Color.valueOf("218A21"), Color.valueOf("32CD32"), Color.white};
                    length = 342;
                    width = 17;
                    ammoMultiplier = 2.0f;
                    knockback = 1.0f;
                    status = nucdStatusEffects.radiation;
                    statusDuration = 600;
                    pierceCap = 3;
                }}
            );
        }};
    };
};