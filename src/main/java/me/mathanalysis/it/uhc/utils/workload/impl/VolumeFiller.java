package me.mathanalysis.it.uhc.utils.workload.impl;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * We are defining out problem with an interface.
 * Our task is it to fill a volume defined by two corners with blocks.
 */
public interface VolumeFiller {

  void fill(Location cornerA, Location cornerB, Material material);

}
