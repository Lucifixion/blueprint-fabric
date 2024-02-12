package client;

import com.teamabnormals.blueprint.core.BlueprintForge;
import com.teamabnormals.blueprint.core.endimator.Endimation;
import com.teamabnormals.blueprint.core.endimator.Endimator;
import com.teamabnormals.blueprint.core.endimator.EndimatorModelPart;
import com.teamabnormals.blueprint.core.endimator.model.EndimatorLayerDefinition;
import com.teamabnormals.blueprint.core.endimator.model.EndimatorPartDefinition;
import common.entity.EndimatedWalkingEntity;
import core.BlueprintTest;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.resources.ResourceLocation;

public final class EndimatedWalkingEntityModel<T extends EndimatedWalkingEntity> extends HierarchicalModel<T> {
	private static final Endimation WALKING = BlueprintForge.ENDIMATION_LOADER.getEndimation(new ResourceLocation(BlueprintTest.MOD_ID, "walking"));
	private final EndimatorModelPart root;
	private final Endimator endimator;

	public EndimatedWalkingEntityModel() {
		this.endimator = Endimator.compile(this.root = createBodyLayer().bakeRoot());
	}

	public static EndimatorLayerDefinition createBodyLayer() {
		EndimatorPartDefinition root = EndimatorPartDefinition.root();
		EndimatorPartDefinition main = root.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 0.0F));
		EndimatorPartDefinition base = main.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition top = base.addOrReplaceChild("top", CubeListBuilder.create().texOffs(28, 0).addBox(-0.5F, -11.0F, -0.5F, 1.0F, 3.0F, 1.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition ball = top.addOrReplaceChild("ball", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -13.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition right_leg = main.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(16, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F), PartPose.offset(-4.0F, 0.0F, 0.0F));
		EndimatorPartDefinition left_leg = main.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 0).addBox(2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
		return new EndimatorLayerDefinition(root, 32, 16);
	}

	private static float computeWalkTime(float limbSwing, float length) {
		float period = length * 5.0F;
		return (((limbSwing + period) % period) / period) * length;
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float p_102621_, float p_102622_, float p_102623_) {
		assert WALKING != null;
		float length = WALKING.getLength();
		float adjustedLimbSwingAmount = 4.0F * limbSwingAmount / length;
		if (adjustedLimbSwingAmount > 1.0F) {
			adjustedLimbSwingAmount = 1.0F;
		}
		this.endimator.apply(WALKING, computeWalkTime(limbSwing, length), adjustedLimbSwingAmount, Endimator.ResetMode.ALL);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}
