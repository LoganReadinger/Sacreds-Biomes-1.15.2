package com.sacredninja.sacredsbiomes.commands;

import com.sacredninja.sacredsbiomes.dimension.ModDimensions;
import com.sacredninja.sacredsbiomes.tools.TeleportationTools;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

public class DimensionTeleportCommand implements Command<CommandSource> {

    private static final DimensionTeleportCommand CMD = new DimensionTeleportCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("perlinnoise")
                .requires(cs -> cs.hasPermissionLevel(0))
                .executes(CMD);
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().asPlayer();
        int x = player.getPosition().getX();
        int z = player.getPosition().getZ();
        if (player.dimension.equals(ModDimensions.DIMENSION_TYPE)) {
            TeleportationTools.teleport(player, DimensionType.OVERWORLD, new BlockPos(x, 200, z));
        } else {
            TeleportationTools.teleport(player, ModDimensions.DIMENSION_TYPE, new BlockPos(x, 200, z));
        }
        return 0;
    }
}