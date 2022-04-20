package naenaenz.quandary.entity.client;

import naenaenz.quandary.entity.DubEntity;
import naenaenz.quandary.entity.model.DubEntityModel;
import naenaenz.quandary.entity.renderer.DubEntityRenderer;
import naenaenz.quandary.register.EntityRegister;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class DubClient {
    public static final EntityModelLayer MODEL_DUB_LAYER = new EntityModelLayer(new Identifier("quandary", "dub"), "main");

    public static void register() {
        /*
         * Registers our Cube Entity's renderer, which provides a model and texture for the entity.
         *
         * Entity Renderers can also manipulate the model before it renders based on entity context (EndermanEntityRenderer#render).
         */
        EntityRendererRegistry.register(EntityRegister.DUB, (context) -> {
            return new DubEntityRenderer(context);
        });

        EntityModelLayerRegistry.registerModelLayer(MODEL_DUB_LAYER, DubEntityModel::getTexturedModelData);
    }

}
