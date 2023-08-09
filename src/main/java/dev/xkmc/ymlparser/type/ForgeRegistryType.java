package dev.xkmc.ymlparser.type;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class ForgeRegistryType<R extends T, T> implements IterableType<T> {

	private final IForgeRegistry<R> registry;
	private final Lazy<List<String>> keys;
	private final String name;

	public ForgeRegistryType(Class<T> cls, IForgeRegistry<R> registry) {
		this.name = cls.getSimpleName();
		this.registry = registry;
		this.keys = Lazy.of(() -> this.registry.getKeys().stream().map(ResourceLocation::toString).toList());
	}

	@Override
	public String name() {
		return name;
	}

	@Nullable
	@Override
	public SupplierEntry<T> get(String str) {
		ResourceLocation id = new ResourceLocation(str.toLowerCase(Locale.ROOT));
		if (!registry.containsKey(id)) return null;
		return new Entry<>(this, id, registry.getValue(id));
	}

	@Override
	public Collection<String> getAllKeys() {
		return keys.get();
	}

	private record Entry<T>(ForgeRegistryType<?, T> type, ResourceLocation id, T val) implements SupplierEntry<T> {

		@Override
		public T parse() {
			return val;
		}

	}

}
