package com.moyashi.yasu.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modeltest<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("yasu", "modelquantumelytssra"), "main");
	public final ModelPart Body;
	public final ModelPart bone;

	public Modeltest(ModelPart root) {
		this.Body = root.getChild("Body");
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(40, 8).addBox(-3.0F, 0.25F, 2.0F, 6.0F, 6.75F, 3.1F, new CubeDeformation(0.0F)).texOffs(50, 50)
						.addBox(-2.0F, 0.25F, 5.0F, 4.0F, 5.75F, 1.1F, new CubeDeformation(0.0F)).texOffs(42, 60).addBox(0.75F, 7.0F, 2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(42, 60)
						.addBox(-2.75F, 7.0F, 2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(19, 24).addBox(-1.5F, -1.0F, 2.75F, 1.0F, 2.0F, 1.25F, new CubeDeformation(0.0F)).texOffs(22, 21)
						.addBox(0.75F, -1.0F, 2.75F, 1.0F, 2.0F, 1.25F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1",
				CubeListBuilder.create().texOffs(0, 47).addBox(-2.75F, 10.875F, 2.1F, 2.25F, 10.5F, 0.8F, new CubeDeformation(0.0F)).texOffs(50, 60).addBox(-2.2F, 7.075F, 3.8F, 1.5F, 1.5F, 1.7F, new CubeDeformation(0.0F)).texOffs(9, 22)
						.addBox(-2.0F, 7.375F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(52, 51).addBox(-2.75F, 13.375F, 2.5F, 2.25F, 0.5F, 0.75F, new CubeDeformation(0.0F)).texOffs(52, 51)
						.addBox(-2.75F, 15.375F, 2.5F, 2.25F, 0.5F, 0.75F, new CubeDeformation(0.0F)).texOffs(52, 51).addBox(-2.75F, 17.375F, 2.5F, 2.25F, 0.5F, 0.75F, new CubeDeformation(0.0F)).texOffs(52, 51)
						.addBox(-2.75F, 19.375F, 2.5F, 2.25F, 0.5F, 0.75F, new CubeDeformation(0.0F)).texOffs(52, 51).addBox(-2.75F, 5.875F, 2.5F, 2.25F, 5.5F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 8)
						.addBox(-3.25F, 4.875F, 2.5F, 0.5F, 16.8F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 8).addBox(-0.5F, 5.875F, 2.5F, 0.5F, 15.8F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 8)
						.addBox(-2.45F, 21.875F, 1.5F, 1.55F, 1.2F, 1.65F, new CubeDeformation(0.0F)).texOffs(40, 8).addBox(-2.75F, 21.375F, 2.5F, 2.25F, 0.5F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 8)
						.addBox(-3.25F, 5.875F, 1.5F, 3.25F, 16.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.75F, 3.625F, 3.5F, 0.0F, -0.6545F, -2.2253F));
		PartDefinition Body_r2 = Body.addOrReplaceChild("Body_r2",
				CubeListBuilder.create().texOffs(8, 44).addBox(-3.25F, -0.125F, -1.2F, 2.25F, 15.5F, 1.0F, new CubeDeformation(0.0F)).texOffs(46, 60).addBox(-3.2F, 1.225F, 0.6F, 1.5F, 1.5F, 1.7F, new CubeDeformation(0.0F)).texOffs(21, 24)
						.addBox(-2.7F, 1.425F, 0.3F, 1.0F, 1.0F, 0.4F, new CubeDeformation(0.0F)).texOffs(52, 51).addBox(-3.25F, 7.125F, -0.5F, 2.25F, 0.5F, 0.75F, new CubeDeformation(0.0F)).texOffs(52, 51)
						.addBox(-3.25F, 9.375F, -0.5F, 2.25F, 0.5F, 0.75F, new CubeDeformation(0.0F)).texOffs(52, 51).addBox(-3.25F, 11.375F, -0.5F, 2.25F, 0.5F, 0.75F, new CubeDeformation(0.0F)).texOffs(52, 51)
						.addBox(-3.25F, 13.375F, -0.5F, 2.25F, 0.5F, 0.75F, new CubeDeformation(0.0F)).texOffs(52, 51).addBox(-3.25F, -0.275F, -0.7F, 2.25F, 5.4F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 8)
						.addBox(-1.0F, -0.875F, -0.75F, 0.5F, 16.55F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 8).addBox(-3.75F, -0.225F, -0.75F, 0.5F, 15.9F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 8)
						.addBox(-2.95F, 15.875F, -1.7F, 1.55F, 1.2F, 1.65F, new CubeDeformation(0.0F)).texOffs(40, 8).addBox(-3.25F, 15.375F, -0.7F, 2.25F, 0.5F, 0.95F, new CubeDeformation(0.0F)).texOffs(40, 8)
						.addBox(-3.75F, -0.125F, -1.7F, 3.25F, 16.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.75F, 3.625F, 3.5F, 0.0F, 0.6545F, 2.2253F));
		PartDefinition silver = Body.addOrReplaceChild("silver", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition black = Body.addOrReplaceChild("black", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}

