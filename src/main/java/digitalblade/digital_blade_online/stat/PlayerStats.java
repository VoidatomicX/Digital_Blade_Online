package digitalblade.digital_blade_online.stat;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerStats {

    public int speed = 0;
    public int defense = 0;
    public int health = 0;
    public int attackSpeed = 0;

    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("Speed", speed);
        nbt.putInt("Defense", defense);
        nbt.putInt("Health", health);
        nbt.putInt("AttackSpeed", attackSpeed);
    }

    public void readNbt(NbtCompound nbt) {
        speed = nbt.getInt("Speed");
        defense = nbt.getInt("Defense");
        health = nbt.getInt("Health");
        attackSpeed = nbt.getInt("AttackSpeed");
    }

    public static PlayerStats get(ServerPlayerEntity player) {
        NbtCompound nbt = player.writeNbt(new NbtCompound());
        PlayerStats stats = new PlayerStats();

        if (nbt.contains("DBO_STATS")) {
            stats.readNbt(nbt.getCompound("DBO_STATS"));
        }

        return stats;
    }

    public static void save(ServerPlayerEntity player, PlayerStats stats) {
        NbtCompound nbt = player.writeNbt(new NbtCompound());
        NbtCompound tag = new NbtCompound();

        stats.writeNbt(tag);
        nbt.put("DBO_STATS", tag);

        player.readNbt(nbt);
    }
}