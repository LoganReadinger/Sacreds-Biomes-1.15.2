package com.sacredninja.sacredsbiomes.commands;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.sacredninja.sacredsbiomes.SacredsBiomes;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class CommandRegistry {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralCommandNode<CommandSource> command = dispatcher.register(
                Commands.literal(SacredsBiomes.MOD_ID)
                        .then(DimensionTeleportCommand.register(dispatcher)));

        dispatcher.register(Commands.literal("sacredsbiomes").redirect(command));
    }

}