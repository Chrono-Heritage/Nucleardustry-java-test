package nucd;

import nucd.content.nucdItems;
import nucd.content.nucdLiquids;
import nucd.content.nucdStatusEffects;
import nucd.content.nucdBlocks;
import mindustry.mod.Mod;

public class nucleard extends Mod{
    public nucleard(){
    }

    @Override
    public void loadContent(){
        nucdItems.load();
        nucdLiquids.load();
        nucdStatusEffects.load();
        nucdBlocks.load();
    }
}