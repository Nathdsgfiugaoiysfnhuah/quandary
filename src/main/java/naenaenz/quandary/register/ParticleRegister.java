package naenaenz.quandary.register;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ParticleRegister {
    public static final DefaultParticleType FLOWER_PETAL_PARTICLE = FabricParticleTypes.simple();

    public static void register()
    {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("quandary", "flower_petal_particle"), FLOWER_PETAL_PARTICLE);
    }

    public static void clientRegister()
    {
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(new Identifier("quandary", "particle/flower_petal_particle"));
        }));

        /* Registers our particle client-side.
         * First argument is our particle's instance, created previously on ExampleMod.
         * Second argument is the particle's factory. The factory controls how the particle behaves.
         * In this example, we'll use FlameParticle's Factory.*/
        ParticleFactoryRegistry.getInstance().register(FLOWER_PETAL_PARTICLE, FlameParticle.Factory::new);

    }
}
