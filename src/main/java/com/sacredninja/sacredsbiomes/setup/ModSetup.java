package com.sacredninja.sacredsbiomes.setup;

import com.sacredninja.sacredsbiomes.SacredsBiomes;
import com.sacredninja.sacredsbiomes.commands.CommandRegistry;
import com.sacredninja.sacredsbiomes.dimension.ModDimensions;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = SacredsBiomes.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {


    public static void init(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public static void serverLoad(FMLServerStartingEvent event) {
        CommandRegistry.register(event.getCommandDispatcher());
    }

    @SubscribeEvent
    public static void onDimensionRegistry(RegisterDimensionsEvent event) {
        ModDimensions.DIMENSION_TYPE = DimensionManager.registerOrGetDimension(ModDimensions.DIMENSION_ID, Registration.DIMENSION.get(), null, true);
    }

}
