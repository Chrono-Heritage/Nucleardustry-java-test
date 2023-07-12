package nucd;

import nucd.content.*;
import nucd.content.nucdBlocks;
import mindustry.content.Items;
import mindustry.mod.Mod;

public class nucdLoader extends Mod{
    public nucdLoader(){
    }
    
	public void loadContent() {
		nucdItems.load();
		nucdLiquids.load();
        nucdStatusEffects.load();
		nucdUnitTypes.load();
		nucdBlocks.load();
		nucdTechTree.load();
        (Items.thorium).radioactivity = 0.7f;
	}
}