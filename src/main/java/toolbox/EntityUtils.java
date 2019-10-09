package toolbox;

import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

import java.util.Objects;

import static toolbox.Helper.getLocalPlayer;
import static toolbox.Helper.getWorld;

public class EntityUtils {
    /**
     * If the entity is a player
     */
    public static boolean isPlayer(Entity entity) {
        return entity instanceof EntityPlayer;
    }

    @Nullable
    public static Entity getRidingEntity() {
        if (getLocalPlayer() != null) {
            return getLocalPlayer().getRidingEntity();
        } else {
            return null;
        }
    }

    public static boolean isDrivenByPlayer(Entity entityIn) {
        return getLocalPlayer() != null && entityIn != null && entityIn == getRidingEntity();
    }

    public static boolean isLocalPlayer(Entity entity) {
        return Objects.equals(getLocalPlayer(), entity);
    }

    public static boolean isAboveWater(Entity entity) {
        return isAboveWater(entity, false);
    }

    public static boolean isAboveWater(Entity entity, boolean packet) {
        if (entity == null) {
            return false;
        }

        double y = entity.posY - (packet ? 0.03 : (EntityUtils.isPlayer(entity) ? 0.2 : 0.5)); // increasing this seems to flag more in NCP but needs to be increased
        // so the player lands on solid water

        for (int x = MathHelper.floor(entity.posX); x < MathHelper.ceil(entity.posX); x++) {
            for (int z = MathHelper.floor(entity.posZ); z < MathHelper.ceil(entity.posZ); z++) {
                BlockPos pos = new BlockPos(x, MathHelper.floor(y), z);

                if (getWorld().getBlockState(pos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }

        return false;
    }

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
