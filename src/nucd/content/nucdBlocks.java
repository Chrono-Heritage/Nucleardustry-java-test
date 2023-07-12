package nucd.content;

import nucd.content.Blocks.nucdCrafting;
import nucd.content.Blocks.nucdDefence;
import nucd.content.Blocks.nucdDistribution;
import nucd.content.Blocks.nucdMisc;
import nucd.content.Blocks.nucdPower;
import nucd.content.Blocks.nucdProduction;
import nucd.content.Blocks.nucdTurrets;
import nucd.content.Blocks.nucdUnits;


public class nucdBlocks{
    public static void load() {
        nucdCrafting.load();
        nucdDefence.load();
        nucdDistribution.load();
        nucdMisc.load();
        nucdPower.load();
        nucdProduction.load();
        nucdTurrets.load();
        nucdUnits.load();
    }
};