//package naenaenz.quandary.entity.renderer;
//
//import naenaenz.quandary.ClientMain;
//import naenaenz.quandary.entity.DubEntity;
//import naenaenz.quandary.entity.client.DubClient;
//import naenaenz.quandary.entity.model.DubEntityModel;
//import net.minecraft.client.render.entity.EntityRendererFactory;
//import net.minecraft.client.render.entity.MobEntityRenderer;
//import net.minecraft.util.Identifier;
//
//public class DubEntityRenderer extends MobEntityRenderer<DubEntity, DubEntityModel> {
//    public DubEntityRenderer(EntityRendererFactory.Context context) {
//        super(context, new DubEntityModel(context.getPart(DubClient.MODEL_DUB_LAYER)), 0.5f);
//    }
//
//    @Override
//    public Identifier getTexture(DubEntity entity) {
//        return new Identifier("quandary", "textures/entity/dub/dub.png");
//    }
//
//}
