package com.sacredninja.sacredsbiomes.world.biomes;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

public class MountainRange extends Biome{
	public MountainRange(Builder biomeBuilder) {
		super (biomeBuilder);
		
		// Cows
		this.addSpawn(
			EntityClassification.CREATURE,
			new SpawnListEntry(EntityType.COW, 5, 3, 6) // Type, Weight, Min Group, Max Group
		);
		
		// Pigs
		this.addSpawn(
			EntityClassification.CREATURE,
			new SpawnListEntry(EntityType.PIG, 5, 3, 6) // Type, Weight, Min Group, Max Group
		);
		
		// Sheeps
		this.addSpawn(
			EntityClassification.CREATURE,
			new SpawnListEntry(EntityType.SHEEP, 15, 3, 6) // Type, Weight, Min Group, Max Group
		);
		
		// Fox
		this.addSpawn(
			EntityClassification.CREATURE,
			new SpawnListEntry(EntityType.FOX, 1, 1, 3) // Type, Weight, Min Group, Max Group
		);
		
		// Rabbit
		this.addSpawn(
			EntityClassification.CREATURE,
			new SpawnListEntry(EntityType.RABBIT, 1, 1, 4) // Type, Weight, Min Group, Max Group
		);
		
		// Wolf
		this.addSpawn(
			EntityClassification.CREATURE,
			new SpawnListEntry(EntityType.WOLF, 1, 1, 3) // Type, Weight, Min Group, Max Group
		);
		
		// Caves
		this.addCarver(
			GenerationStage.Carving.AIR,
			Biome.createCarver(
				WorldCarver.CAVE,
				new ProbabilityConfig(0.14285715f))); // Default : 0.14285715f
				
		// Ravines
		this.addCarver(
			GenerationStage.Carving.AIR, 
			Biome.createCarver(
				WorldCarver.CANYON, 
				new ProbabilityConfig(0.025f))); // Default 0.2
		
		// Monster Rooms
		this.addFeature(
			GenerationStage.Decoration.UNDERGROUND_STRUCTURES, 
			Feature.MONSTER_ROOM.withConfiguration(
				IFeatureConfig.NO_FEATURE_CONFIG
			)
			.withPlacement(
				Placement.DUNGEONS.configure(
					new ChanceConfig(1)
				)
			)
		);
		
		/*this.addFeature(
			GenerationStage.Decoration.LOCAL_MODIFICATIONS, 
			Feature.FOREST_ROCK.withConfiguration(
				new BlockBlobConfig(MOSSY_COBBLESTONE, 0))
			.withPlacement(
				Placement.FOREST_ROCK.configure(
					new FrequencyConfig(3)
				)
			)
		);*/
		
		DefaultBiomeFeatures.addSedimentDisks(this);
		//DefaultBiomeFeatures.addTaigaRocks(this);
		
		DefaultBiomeFeatures.addScatteredOakAndSpruceTrees(this);
		DefaultBiomeFeatures.addScatteredOakTrees(this);
		DefaultBiomeFeatures.addSparseGrass(this);
		
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addStoneVariants(this);
	}
}
