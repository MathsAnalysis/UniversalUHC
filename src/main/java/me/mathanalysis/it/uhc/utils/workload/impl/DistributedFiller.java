package me.mathanalysis.it.uhc.utils.workload.impl;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import me.mathanalysis.it.uhc.utils.cuboid.Cuboid;
import me.mathanalysis.it.uhc.utils.workload.BlockLoadRunnable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.Vector;

/**
 * This implementation does not change a Block instantly.
 * It defines a Workload (PlacableBlock in this case) and simply passes it
 * to the BlockLoadRunnable. We then forget about it because the runnable
 * will decide when there is enough CPU time left for this Workload to be
 * computed.
 */
@AllArgsConstructor
public class DistributedFiller implements VolumeFiller {

  private final BlockLoadRunnable blockLoadRunnable;

  @Override
  public void fill(Location cornerA, Location cornerB, Material material) {
    Preconditions.checkArgument(cornerA.getWorld() == cornerB.getWorld() && cornerA.getWorld() != null);
    Cuboid cuboid = new Cuboid(cornerA, cornerB);
    Vector max = cuboid.getMax();
    Vector min = cuboid.getMin();

    World world = cornerA.getWorld();

    for (int x = min.getBlockX(); x <= max.getBlockX(); x++) {
      for (int y = min.getBlockY(); y <= max.getBlockY(); y++) {
        for (int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
          PlacableBlock placableBlock = new PlacableBlock(world.getUID(), x, y, z, material);
          this.blockLoadRunnable.addWorkload(placableBlock);
        }
      }
    }
  }

}
