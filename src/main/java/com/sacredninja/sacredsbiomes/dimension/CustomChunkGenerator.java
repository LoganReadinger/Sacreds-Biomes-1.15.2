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
        double frequency = 128;
        double amplitude = 2;
        
        
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
                int realx = chunkpos.x * 16 + x;
                int realz = chunkpos.z * 16 + z;
                
                
                // Perlin terrain generation
                int height = (int) (65 + (perlin.perlinHalf(realx / frequency, 0.5, realz / frequency) * amplitude));
                
                // Cosine terrain generation
                //int height = (int) (65 + Math.cos(realx / 20.0f)*10 + Math.cos(realz / 20.0f)*10);
                
                // place stone based on y value
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