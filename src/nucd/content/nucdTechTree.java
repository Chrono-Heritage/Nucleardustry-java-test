package nucd.content;

import arc.func.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;
import mindustry.world.Block;
import nucd.content.Blocks.nucdCrafting;

import static mindustry.content.Blocks.*;
import static mindustry.content.Items.*;
import static mindustry.content.TechTree.*;
import static mindustry.content.UnitTypes.*;
import static nucd.content.nucdItems.*;
import static nucd.content.nucdLiquids.*;
import static nucd.content.Blocks.nucdCrafting.*;
import static nucd.content.Blocks.nucdDefence.*;
import static nucd.content.Blocks.nucdDistribution.*;
import static nucd.content.Blocks.nucdMisc.*;
import static nucd.content.Blocks.nucdPower.*;
import static nucd.content.Blocks.nucdProduction.*;
import static nucd.content.Blocks.nucdTurrets.*;
import static nucd.content.Blocks.nucdUnits.*;

public class nucdTechTree {

public static void load(){
    nodeRoot("Nucleardustry", nucdustrySym, false, () -> {
        node(radioactiveSolution, ItemStack.with(), () -> {
            node(plutonium, () -> {
                node(plutonium239);
            });
            node(radioalloy);
                node(uranium235, () -> {
                    node(uranium238, () -> {
                        node(uraniumEnriched, () -> {
                            node(uraniumFuel);
                            node(uraniumDepleted, () -> {
                                node(uraniumDepletedAmmo);
                            });
                        });
                    });
                });
            });
        node(coreNuclear, () -> {});
        node(radioactiveLiquefier, () ->{
            node(radioactiveSeparator, () -> {
                node(radioactiveSmelter,() -> {});
                node(isotopeSeparator,() -> {
                    node(plutonium239Producer,() -> {});
                    node(enrichedUraniumCrafter, () -> {
                        node(fissionFuelInfuser,() -> {});
                        node(duAmmoConstructer,() -> {});
                });
                });
            });
        });
        node(nuclearFurnace, () -> {
            node(fissionReactor, () ->{});
            node(pressurizedHeavyWaterReactor, () -> {
                node(fusionReactor,() ->{});
        });
    });
        node(heavyWaterExtractor,() -> {
            node(saltExtractor,() -> {
                node(nucdCrafting.electrolyzer,() ->{});
                node(heavyWater,() -> {
                    node(nucdItems.salt);
                    node(deuterium,() -> {
                        node(tritium);
                    });
                });
            });
        });
        node(depletedUraniumRifle,() -> {
            node(nuclearLaserEmitter,() ->{});
        });
        node(radioalloyWall,() -> {
            node(radioalloyWallLarge);
        });
        node(nuclearMassDriver,() -> {});
        node(nuclearOverdriver,() -> {});
        node(nuclearDrill,() -> {});
        node(nuclearPump,() -> {});
    });
    node(nuclearUnitFactory);
};
}

