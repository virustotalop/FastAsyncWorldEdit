/*
 * WorldEdit, a Minecraft world manipulation toolkit
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldEdit team and contributors
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.sk89q.worldedit.command.composition;

import com.google.common.base.Function;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandLocals;
import com.sk89q.worldedit.command.argument.NumberArg;
import com.sk89q.worldedit.command.argument.PointGeneratorArg;
import com.sk89q.worldedit.function.EditContext;
import com.sk89q.worldedit.function.RegionFunction;
import com.sk89q.worldedit.function.factory.Scatter;
import com.sk89q.worldedit.util.command.argument.CommandArgs;
import com.sk89q.worldedit.util.command.composition.SimpleCommand;

public class ScatterCommand extends SimpleCommand<Scatter> {

    private final NumberArg densityCommand = addParameter(new NumberArg("density", "0-100", "20"));
    private final PointGeneratorArg pointGeneratorArg = addParameter(new PointGeneratorArg());

    @Override
    public Scatter call(CommandArgs args, CommandLocals locals) throws CommandException {
        double density = densityCommand.call(args, locals).doubleValue() / 100.0;
        Function<EditContext, ? extends RegionFunction> function = pointGeneratorArg.call(args, locals);
        return new Scatter(function, density);
    }

    @Override
    public String getDescription() {
        return "Scatters a function over an area";
    }

    @Override
    protected boolean testPermission0(CommandLocals locals) {
        return true;
    }

}
