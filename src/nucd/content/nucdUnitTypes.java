package nucd.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.ai.*;
import mindustry.ai.types.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.content.Fx;
import mindustry.content.Fx.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;
import nucd.content.nucdItems.*;
import nucd.content.nucdLiquids.*;
import nucd.content.nucdStatusEffects.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

public class nucdUnitTypes {
    public static UnitType
    /* Core Unit */
    neutron,
    /* Normal Units */
    nuclearTank
    ;
    


    public static void load(){
        neutron = new UnitType("neutron"){{
            constructor = UnitEntity::create;
            aiController = BuilderAI::new;
            isEnemy = false;
            flying = true;
            mineSpeed = 12.0f;
            mineTier = 3;
            buildSpeed = 1.75f;
            drag = 0.1f;
            speed = 4f;
            rotateSpeed = 30f;
            accel = 0.3f;
            itemCapacity = 120;
            health = 300f;
            engineOffset = 9f;
            hitSize = 12f;

            weapons.add(new Weapon("basic-nuclear-laser"){{
                shoot = new ShootSpread(){{
                    shots = 1;
                    spread = 0f;
                }};
                reload = 60f;
                x = 0;
                y = 5;
                mirror = false;
                rotate = false;
                continuous = true;
                shootSound = Sounds.laser;
                loopSound = Sounds.beam;
                bullet = new ContinuousLaserBulletType(20){{
                    width = 3f;
                    length = 80f;
                    lifetime = 180f;
                    status = nucdStatusEffects.radiation;
                    colors = new Color[]{Color.valueOf("218A21"), Color.valueOf("32CD32"), Color.white};
                    collidesTeam = false;
                    buildingDamageMultiplier = 0.01f;
                }};
            }});
            loopSound = Sounds.none;
        }};
        nuclearTank = new TankUnitType("nuclear-tank"){{
            constructor = UnitEntity::create;
            hitSize = 46f;
            treadPullOffset = 1;
            speed = 0.75f;
            health = 25000;
            armor = 30f;
            crushDamage = 25f / 5f;
            rotateSpeed = 1.5f;

            float xo = 231f/2f, yo = 231f/2f;
            treadRects = new Rect[]{new Rect(27 - xo, 152 - yo, 56, 73), new Rect(24 - xo, 51 - 9 - yo, 29, 17), new Rect(59 - xo, 18 - 9 - yo, 39, 19)};

            weapons.add(new Weapon("chrono-nucleardustry-main-cannon"){{
                shootSound = Sounds.largeCannon;
                layerOffset = 0.1f;
                reload = 100f;
                shootY = 32.5f;
                shake = 5f;
                recoil = 5f;
                rotate = true;
                rotateSpeed = 0.6f;
                mirror = false;
                x = 0f;
                y = -2f;
                shadow = 50f;
                heatColor = Color.valueOf("f9350f");
                shootWarmupSpeed = 0.06f;
                cooldownTime = 110f;
                heatColor = Color.valueOf("f9350f");
                minWarmup = 0.9f;

                parts.addAll(
                new RegionPart("-glow"){{
                    color = Color.red;
                    blending = Blending.additive;
                    outline = mirror = false;
                }},
                new RegionPart("-sides"){{
                    progress = PartProgress.warmup;
                    mirror = true;
                    under = true;
                    moveX = 0.75f;
                    moveY = 0.75f;
                    moveRot = 82f;
                    x = 37 / 4f;
                    y = 8 / 4f;
                }},
                new RegionPart("-sinks"){{
                    progress = PartProgress.warmup;
                    mirror = true;
                    under = true;
                    heatColor = new Color(1f, 0.1f, 0.1f);
                    moveX = 17f / 4f;
                    moveY = -15f / 4f;
                    x = 32 / 4f;
                    y = -34 / 4f;
                }},
                new RegionPart("-sinks-heat"){{
                    blending = Blending.additive;
                    progress = PartProgress.warmup;
                    mirror = true;
                    outline = false;
                    colorTo = new Color(1f, 0f, 0f, 0.5f);
                    color = colorTo.cpy().a(0f);
                    moveX = 17f / 4f;
                    moveY = -15f / 4f;
                    x = 32 / 4f;
                    y = -34 / 4f;
                }}
                );

                for(int i = 1; i <= 3; i++){
                    int fi = i;
                    parts.add(new RegionPart("-blade"){{
                        progress = PartProgress.warmup.delay((3 - fi) * 0.3f).blend(PartProgress.reload, 0.3f);
                        heatProgress = PartProgress.heat.add(0.3f).min(PartProgress.warmup);
                        heatColor = new Color(1f, 0.1f, 0.1f);
                        mirror = true;
                        under = true;
                        moveRot = -40f * fi;
                        moveX = 3f;
                        layerOffset = -0.002f;

                        x = 11 / 4f;
                    }});
                }

                bullet = new BasicBulletType(8f, 360f){{
                    sprite = "missile-large";
                    width = 12f;
                    height = 20f;
                    lifetime = 35f;
                    hitSize = 6f;

                    smokeEffect = Fx.shootSmokeTitan;
                    pierceCap = 3;
                    pierce = true;
                    pierceBuilding = true;
                    hitColor = backColor = trailColor = Color.valueOf("feb380");
                    frontColor = Color.white;
                    trailWidth = 4f;
                    trailLength = 9;
                    hitEffect = despawnEffect = Fx.massiveExplosion;

                    shootEffect = new ExplosionEffect(){{
                        lifetime = 40f;
                        waveStroke = 4f;
                        waveColor = sparkColor = trailColor;
                        waveRad = 15f;
                        smokeSize = 5f;
                        smokes = 8;
                        smokeSizeBase = 0f;
                        smokeColor = trailColor;
                        sparks = 8;
                        sparkRad = 40f;
                        sparkLen = 4f;
                        sparkStroke = 3f;
                    }};
                }};
            }});
            weapons.add(new Weapon("chrono-nucleardustry-secondary-machine-turret"){{
                shoot = new ShootSpread(){{
                    shots = 1;
                    spread = 1f;
                }};
                reload = 6f;
                x = 15;
                y = 0;
                mirror = true;
                rotate = true;
                rotateSpeed = 3f; 
                bullet = new BasicBulletType(25f,24){{
                    width = 8f;
                    height = 17f;
                    lifetime = 10f;
                    status = nucdStatusEffects.radiation;
                    frontColor = Color.valueOf("249624");
                    backColor = Color.valueOf("218A21");
                    lightColor = Color.valueOf("32CD32");
                    collidesTeam = false;
                    pierceArmor = true;
                }};
            }});
            parts.add(new RegionPart("-glow"){{
                color = Color.red;
                blending = Blending.additive;
                layer = -1f;
                outline = false;
            }});
        }};
    }
}
            
