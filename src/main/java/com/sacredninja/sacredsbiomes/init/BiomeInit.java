package com.sacredninja.sacredsbiomes.init;

import com.sacredninja.sacredsbiomes.world.biomes.TestBiome;

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
	
	public static final RegistryObject<Biome> TEST_BIOME = BIOMES.register("test_biome",
			() -> new TestBiome(new Biome.Builder()
				.precipitation(RainType.SNOW)
				.scale(1.2f)
				.temperature(0.5f)
				.waterColor(0x03fcf8)
				.waterFogColor(0xc9fffe)
				.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(
						Blocks.SNOW.getDefaultState(),
						Blocks.DIRT.getDefaultState(),
						Blocks.ACACIA_PLANKS.getDefaultState()))
				.category(Category.PLAINS)
				.downfall(0.5f)
				.depth(0.12f)
				.parent(null)));
	
	
	public static void registerBiomes() {
		registerBiome(TEST_BIOME.get(), Type.PLAINS, Type.OVERWORLD);
	}
	
	private static void registerBiome(Biome biome, Type... types) {
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
	}
}
