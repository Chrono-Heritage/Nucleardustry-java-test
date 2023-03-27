package nucd.content;

import arc.graphics.*;
import mindustry.type.*;

public class nucdLiquids {

    public static Liquid
        deuterium, heavyWater, radioactiveSolution, tritium;

    public static void load(){
        deuterium = new Liquid("deuterium", Color.valueOf("1B3DA0")){{
            gas = true;
            viscosity = 0.65f;
        }};
        heavyWater = new Liquid("heavy-water", Color.valueOf("1B3DA0")){{
            coolant = false;
        }};
        radioactiveSolution = new Liquid("radioactive-solution", Color.valueOf("B5C103")){{
            explosiveness = 0.75f;
            temperature = 0.4f;
            viscosity = 0.4f;
            heatCapacity = 0.1f;
            coolant = false;
        }};
        tritium = new Liquid("tritium", Color.valueOf("617590")){{
            gas = true;
            viscosity = 0.65f;
        }};

    };
};