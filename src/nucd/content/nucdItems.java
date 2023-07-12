package nucd.content;

import arc.graphics.*;
import mindustry.type.*;

public class nucdItems {

    public static Item
         plutonium, plutonium239, radioalloy, salt, uranium, uranium235, uranium238, uraniumEnriched, uraniumDepleted, uraniumDepletedAmmo, uraniumFuel, sucker;
    public static void load() {
        uranium  = new Item("uranium", Color.valueOf("32CD32")){{
            radioactivity = 0.75f;
            hardness = 4;
        }};       
        plutonium = new Item("plutonium", Color.valueOf("FF0000")){{
            radioactivity = 0.9f;
            hardness = 4;
        }};
        plutonium239 = new Item("plutonium239", Color.valueOf("FF0000")){{
            radioactivity = 0.9f;
        }};
        radioalloy = new Item("radioalloy", Color.valueOf("B5C103")){{
        }};
        salt = new Item("salt", Color.valueOf("FFFFFF")){{
        }};
        uranium235 = new Item("uranium235", Color.valueOf("32CD32")){{
            radioactivity = 1.0f;
            explosiveness = 0.5f;
        }};
        uranium238 = new Item("uranium238", Color.valueOf("32CD32")){{
            radioactivity = 0.9f;
        }};
        uraniumEnriched = new Item("uranium-enriched", Color.valueOf("29FF29")){{
            radioactivity = 1.5f;
        }};
        uraniumDepleted = new Item("uranium-depleted", Color.valueOf("218A21")){{
            radioactivity = 0.25f;
        }};
        uraniumDepletedAmmo = new Item("uranium-depleted-ammo", Color.valueOf("218A21")){{
            explosiveness = 0.2f;
            flammability = 0.75f;
        }};
        uraniumFuel = new Item("uranium-fuel", Color.valueOf("29FF29")){{
            explosiveness = 0.1f;
            radioactivity = 1.15f;
        }};
    };
};