package com.sacredninja.sacredsbiomes.setup;

import com.sacredninja.sacredsbiomes.dimension.CustomDimensionFactory;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sacredninja.sacredsbiomes.SacredsBiomes.MOD_ID;

public class Registration {

    private static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, MOD_ID);

    public static void init() {
        DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<CustomDimensionFactory> DIMENSION = DIMENSIONS.register("dimension", CustomDimensionFactory::new);

}
