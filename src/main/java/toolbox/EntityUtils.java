package toolbox;

import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import static toolbox.Helper.getWorld;

public class EntityUtils {
    public static boolean isInWater(Entity entity) {
        if (entity == null) {
            return false;
        }

        double y = entity.posY + 0.01;
        for (int x = MathHelper.floor(entity.posX); x < MathHelper.ceil(entity.posX); x++) {
            for (int z = MathHelper.floor(entity.posZ); z < MathHelper.ceil(entity.posZ); z++) {
                BlockPos pos = new BlockPos(x, (int) y, z);

                if (getWorld().getBlockState(pos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }

        return false;
    }
}
