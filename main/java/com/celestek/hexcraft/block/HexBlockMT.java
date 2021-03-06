package com.celestek.hexcraft.block;

import com.celestek.hexcraft.init.HexBlocks;
import com.celestek.hexcraft.init.HexConfig;
import com.celestek.hexcraft.util.HexUtils;
import com.celestek.hexcraft.util.TankAnalyzer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * @author CoffeePirate     <celestek@openmailbox.org>
 * @version 0.8.2
 */

public class HexBlockMT extends HexBlock {

    /**
     * Constructor for the block.
     *
     * @param material Default material to use.
     */
    public HexBlockMT(Material material) {
        super(material);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if ((block instanceof HexBlockMT
                || block == HexBlocks.blockTemperedHexoriumGlass
                || block == HexBlocks.blockHexoriumCoatedStone
                || block == HexBlocks.blockTankValve)
                && HexUtils.getMetaBit(HexBlocks.META_STRUCTURE_IS_PART, world, x, y, z)) {

            if (HexConfig.cfgTankDebug)
                System.out.println("[Tank Block] (" + x + ", " + y + ", " + z + "): Neighbour tank block destroyed, analyzing!");

            /* DO ANALYSIS */
            TankAnalyzer analyzer = new TankAnalyzer();
            analyzer.analyzeTank(world, x, y, z);
        }
    }
}
