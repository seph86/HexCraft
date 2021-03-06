package com.celestek.hexcraft.init;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * @author Thorinair   <celestek@openmailbox.org>
 * @version 0.8.2
 */

public class HexGenOre extends WorldGenerator {

    // Used later for generation.
    private Block block;
    private int blockmeta;
    private Block target;
    private int sizeMin;
    private int sizeMax;

    /**
     * Constructor for the vein generator class.
     * @param sizeMin Minimum ore blocks.
     * @param sizeMax Maximum ore blocks.
     */
    public HexGenOre(Block block, Block replace, int sizeMin, int sizeMax) {
        this(block, replace);

        // Load the constructor parameters.
        this.sizeMin = sizeMin;
        this.sizeMax = sizeMax;
    }

    public HexGenOre(Block block, Block target) {
        this(block, 0, target);
    }

    public HexGenOre(Block block, int meta, Block target) {
        this.block = block;
        this.blockmeta = meta;
        this.target = target;
    }

    /**
     * Method used for the generation of ore veins.
     */
    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        // Prepare the random vein size variable. If min and max values are same, use only one value, otherwise use random.
        int genSize;
        if(sizeMin == sizeMax)
            genSize = sizeMax;
        else
            genSize = sizeMin + random.nextInt(sizeMax - sizeMin + 1);

        // System.out.println("sizeMin: " + sizeMin + "sizeMax: " + sizeMax + " Generating size: " + genSize);

        // Generate the first vein block.
        if (world.getBlock(x, y, z).isReplaceableOreGen(world, x, y, z, target))
            world.setBlock(x, y, z, block, blockmeta, 2);

        // Loop the count of additional blocks.
        for(int i = 0; i < genSize - 1; i++) {
            // Create a random direction, then perform a move. If the new block is air, move 2 blocks backwards.
            int genDirection = random.nextInt(6);
            switch (genDirection) {
                case 0:
                    x++;
                    if (world.blockExists(x, y, z))
                        if (world.isAirBlock(x, y, z))
                            x = x - 2;
                    break;
                case 1:
                    x--;
                    if (world.blockExists(x, y, z))
                        if (world.isAirBlock(x, y, z))
                        x = x + 2;
                    break;
                case 2:
                    y++;
                    if (world.blockExists(x, y, z))
                        if (world.isAirBlock(x, y, z))
                        y = y - 2;
                    break;
                case 3:
                    y--;
                    if (world.blockExists(x, y, z))
                        if (world.isAirBlock(x, y, z))
                        y = y + 2;
                    break;
                case 4:
                    z++;
                    if (world.blockExists(x, y, z))
                        if (world.isAirBlock(x, y, z))
                        z = z - 2;
                    break;
                case 5:
                    z--;
                    if (world.blockExists(x, y, z))
                        if (world.isAirBlock(x, y, z))
                        z = z - 2;
                    break;
            }
            // Generate a vein block.
            if (world.blockExists(x, y, z))
                if (world.getBlock(x, y, z).isReplaceableOreGen(world, x, y, z, target))
                    world.setBlock(x, y, z, block, blockmeta, 2);
        }
        return true;
    }
}
