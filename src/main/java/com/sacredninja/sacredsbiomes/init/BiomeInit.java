package com.sacredninja.sacredsbiomes.init;

import com.sacredninja.sacredsbiomes.world.biomes.MooShroomMountains;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, "sacredsbiomes");
	
	public static final RegistryObject<Biome> MOOSHROOM_MOUNTAINS = BIOMES.register("mooshroom_mountains",
			() -> new MooShroomMountains(new Biome.Builder()
				.precipitation(RainType.RAIN)
				.scale(1f) // 1
				.temperature(0.7f) // Default : 0.5
				.waterColor(0x571294)
				.waterFogColor(0x342342)
				.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(
					Blocks.MYCELIUM.getDefaultState(),
					Blocks.DIRT.getDefaultState(),
					Blocks.SAND.getDefaultState()))
				.category(Category.MUSHROOM)
				.downfall(0.5f)
				.depth(0.2f)
				.parent(null)));
	
	
	public static void registerBiomes() {
		registerBiome(MOOSHROOM_MOUNTAINS.get(), Type.MUSHROOM, Type.MAGICAL, Type.OVERWORLD);
	}
	
	private static void registerBiome(Biome biome, Type... types) {
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 100));
		BiomeManager.addSpawnBiome(biome);
	}
}
