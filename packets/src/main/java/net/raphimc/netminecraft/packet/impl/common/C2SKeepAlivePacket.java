/*
 * This file is part of NetMinecraft - https://github.com/RaphiMC/NetMinecraft
 * Copyright (C) 2022-2024 RK_01/RaphiMC and contributors
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.raphimc.netminecraft.packet.impl.common;

import io.netty.buffer.ByteBuf;
import net.raphimc.netminecraft.constants.MCVersion;
import net.raphimc.netminecraft.packet.Packet;
import net.raphimc.netminecraft.packet.PacketTypes;

public abstract class C2SKeepAlivePacket implements Packet {

    public long keepAliveId;

    public C2SKeepAlivePacket() {
    }

    public C2SKeepAlivePacket(final long keepAliveId) {
        this.keepAliveId = keepAliveId;
    }

    @Override
    public void read(final ByteBuf byteBuf, final int protocolVersion) {
        if (protocolVersion >= MCVersion.v1_12_2) {
            this.keepAliveId = byteBuf.readLong();
        } else if (protocolVersion >= MCVersion.v1_8) {
            this.keepAliveId = PacketTypes.readVarInt(byteBuf);
        } else {
            this.keepAliveId = byteBuf.readInt();
        }
    }

    @Override
    public void write(final ByteBuf byteBuf, final int protocolVersion) {
        byteBuf.writeLong(this.keepAliveId);
    }

}
