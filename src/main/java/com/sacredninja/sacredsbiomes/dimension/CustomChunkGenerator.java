package com.sacredninja.sacredsbiomes.dimension;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;

public class CustomChunkGenerator extends ChunkGenerator<CustomChunkGenerator.Config> {

    public CustomChunkGenerator(IWorld world, BiomeProvider provider) {
        super(world, provider, Config.createDefault());
    }

    @Override
    public void generateSurface(WorldGenRegion region, IChunk chunk) {
        BlockState bedrock = Blocks.BEDROCK.getDefaultState();
        BlockState grass = Blocks.GRASS_BLOCK.getDefaultState();
        BlockState dirt = Blocks.DIRT.getDefaultState();
        BlockState stone = Blocks.STONE.getDefaultState();
        ChunkPos chunkpos = chunk.getPos();
        BlockPos.Mutable pos = new BlockPos.Mutable();
        
        PerlinNoiseAlgorithm perlin = new PerlinNoiseAlgorithm();
        double frequency = 90; // size of unit cube
        double amplitude = 100; // relative height modifier
        int height = 0;
        int x;
        int z;
        
        // y=0 for bedrock layer
        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
                chunk.setBlockState(pos.setPos(x, 0, z), bedrock, false);
            }
        }

        // y=height for stone layers
        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
                double realx = Math.abs(chunkpos.x) * 16 + x;
                double realz = Math.abs(chunkpos.z) * 16 + z;
                
                // Perlin for Height + minHeight blocks 
                height = (int) (amplitude * (perlin.perlinHalf(realx / frequency, 2, realz / frequency)));
                
                // Cosine terrain generation
                // height = (int) (65 + Math.cos(realx / 20.0f)*10 + Math.cos(realz / 20.0f)*10);

                // Sine terrain generation
                // height = (int) (65 + Math.sin(realx / 20.0f)*10 + Math.sin(realz / 20.0f)*10);
                
                // Tangent terrain generation
                // height = (int) (65 + Math.tan(realx / 20.0f)*10 + Math.tan(realz / 20.0f)*10);
                
                // Top layer grass, 3 layers under dirt, the rest stone
                for (int y = 1 ; y < height ; y++) {
                	
                	if (y == height-1) {
                    	chunk.setBlockState(pos.setPos(x, y, z), grass, false);
                	}else if (y == height-2) {
                    	chunk.setBlockState(pos.setPos(x, y, z), dirt, false);
                	}else if (y == height-3){
                    	chunk.setBlockState(pos.setPos(x, y, z), dirt, false);
                	}else if (y == height-4){
                    	chunk.setBlockState(pos.setPos(x, y, z), dirt, false);
                	}else {
                        chunk.setBlockState(pos.setPos(x, y, z), stone, false);                		
                	}                    
                }
            }
        }

    }

    @Override
    public void makeBase(IWorld worldIn, IChunk chunkIn) {

    }

    @Override
    public int func_222529_a(int p_222529_1_, int p_222529_2_, Heightmap.Type heightmapType) {
        return 0;
    }

    @Override
    public int getGroundHeight() {
        return world.getSeaLevel()+1;
    }

    public static class Config extends GenerationSettings {

        public static Config createDefault() {
            Config config = new Config();
            config.setDefaultBlock(Blocks.DIAMOND_BLOCK.getDefaultState());
            config.setDefaultFluid(Blocks.LAVA.getDefaultState());
            return config;
        }

    }
}