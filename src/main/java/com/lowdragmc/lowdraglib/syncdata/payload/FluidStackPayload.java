package com.lowdragmc.lowdraglib.syncdata.payload;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.neoforged.neoforge.fluids.FluidStack;

public class FluidStackPayload extends ObjectTypedPayload<FluidStack> {

    @Override
    public void writePayload(RegistryFriendlyByteBuf buf) {
        FluidStack.STREAM_CODEC.encode(buf, payload);
    }

    @Override
    public void readPayload(RegistryFriendlyByteBuf buf) {
        payload = FluidStack.STREAM_CODEC.decode(buf);
    }

    @Override
    public Tag serializeNBT(HolderLookup.Provider provider) {
        return payload.save(provider, new CompoundTag());
    }

    @Override
    public void deserializeNBT(Tag tag, HolderLookup.Provider provider) {
        payload = FluidStack.parseOptional(provider, (CompoundTag) tag);
    }
}
