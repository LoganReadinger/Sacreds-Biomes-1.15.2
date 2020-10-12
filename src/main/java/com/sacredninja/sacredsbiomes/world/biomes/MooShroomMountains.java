package com.sacredninja.sacredsbiomes.world.biomes;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.TwoFeatureChoiceConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.HeightWithChanceConfig;
import net.minecraft.world.gen.placement.Placement;

public class MooShroomMountains extends Biome{
	public MooShroomMountains(Builder biomeBuilder) {
		super(biomeBuilder);
		
		this.addSpawn(
				EntityClassification.CREATURE,
				new SpawnListEntry(EntityType.MOOSHROOM, 50, 3, 6));
		
		this.addCarver(
				GenerationStage.Carving.AIR,
				Biome.createCarver(
						WorldCarver.CAVE,
						new ProbabilityConfig(0.14285710f))); // Default : 0.14285715f
		
		this.addFeature(
				GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_BOOLEAN_SELECTOR.withConfiguration(
						new TwoFeatureChoiceConfig(Feature.HUGE_RED_MUSHROOM
								.withConfiguration(DefaultBiomeFeatures.BIG_RED_MUSHROOM), 
								Feature.HUGE_BROWN_MUSHROOM
								.withConfiguration(DefaultBiomeFeatures.BIG_BROWN_MUSHROOM)))
				.withPlacement(
						Placement.COUNT_HEIGHTMAP
						.configure(new FrequencyConfig(5))));
	    
		this.addFeature(
				GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.RANDOM_PATCH.withConfiguration(
						DefaultBiomeFeatures.BROWN_MUSHROOM_CONFIG)
				.withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP
						.configure(new HeightWithChanceConfig(5, 0.25F))));
	    
		this.addFeature(
	    		GenerationStage.Decoration.VEGETAL_DECORATION,
	    		Feature.RANDOM_PATCH.withConfiguration(
	    				DefaultBiomeFeatures.RED_MUSHROOM_CONFIG)
	    		.withPlacement(Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE
	    				.configure(new HeightWithChanceConfig(5, 0.125F))));
	   
		DefaultBiomeFeatures.addLakes(this);
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addExtraGoldOre(this);
		DefaultBiomeFeatures.addExtraEmeraldOre(this);
	}

	
}
