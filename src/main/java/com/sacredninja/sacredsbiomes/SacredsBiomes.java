package com.sacredninja.sacredsbiomes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sacredninja.sacredsbiomes.setup.ClientSetup;
import com.sacredninja.sacredsbiomes.setup.Config;
import com.sacredninja.sacredsbiomes.setup.ModSetup;
import com.sacredninja.sacredsbiomes.setup.Registration;
import com.sacredninja.sacredsbiomes.init.BiomeInit;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("sacredsbiomes")
@Mod.EventBusSubscriber(modid = SacredsBiomes.MOD_ID, bus = Bus.MOD)
public class SacredsBiomes{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "sacredsbiomes";
    public static SacredsBiomes instance;
    
    public SacredsBiomes() {   
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

        Registration.init();

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
    }

    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
    	BiomeInit.registerBiomes();
    }
    
    private void setup(final FMLCommonSetupEvent event){	
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }
}
