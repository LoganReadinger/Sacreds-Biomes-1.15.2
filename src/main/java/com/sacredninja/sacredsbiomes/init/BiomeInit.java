package com.sacredninja.sacredsbiomes.init;

import com.sacredninja.sacredsbiomes.world.biomes.MooShroomMountains;
import com.sacredninja.sacredsbiomes.world.biomes.MountainRange;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, "sacredsbiomes");
	
	//Mooshroom Mountains
	public static final RegistryObject<Biome> MOOSHROOM_MOUNTAINS = BIOMES.register("mooshroom_mountains",
			() -> new MooShroomMountains(new Biome.Builder()
				.precipitation(RainType.RAIN)
				.depth(0.2f) // Height of world building level
				.scale(1f) // 1
				.downfall(0.5f)
				.temperature(0.7f) // Default : 0.5
				.waterColor(0x571294)
				.waterFogColor(0x342342)
				.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(
					Blocks.MYCELIUM.getDefaultState(),
					Blocks.DIRT.getDefaultState(),
					Blocks.SAND.getDefaultState()))
				.category(Category.MUSHROOM)
				.parent(null)));
	
	//Mountain Range
	public static final RegistryObject<Biome> MOUNTAIN_RANGE = BIOMES.register("mountain_range",
			() -> new MountainRange(new Biome.Builder()
					.precipitation(RainType.SNOW)
					.depth(0.3f) // Height of world building level
					.scale(0.60f)
					.downfall(0.5f)
					.temperature(0.7f)
					.waterColor(0x111111)
					.waterFogColor(0x111111)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(
						Blocks.GRASS_BLOCK.getDefaultState(),
						Blocks.DIRT.getDefaultState(),
						Blocks.SAND.getDefaultState()))
					.category(Category.EXTREME_HILLS)
					.parent(null)));
	
	public static void registerBiomes() {
		registerBiome(MOOSHROOM_MOUNTAINS.get(), Type.MUSHROOM, Type.MAGICAL, Type.OVERWORLD);
		registerBiome(MOUNTAIN_RANGE.get(), Type.MOUNTAIN, Type.HILLS, Type.COLD, Type.OVERWORLD);
	}
	
	private static void registerBiome(Biome biome, Type... types) {
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 100));
		BiomeManager.addSpawnBiome(biome);
	}
}
