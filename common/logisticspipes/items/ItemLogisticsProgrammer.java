package logisticspipes.items;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import logisticspipes.utils.string.StringUtils;

public class ItemLogisticsProgrammer extends LogisticsItem {

	public static final String RECIPE_TARGET = "LogisticsRecipeTarget";

	public ItemLogisticsProgrammer() {
		super();
		setNoRepair();
		setContainerItem(this);
	}

	@Nonnull
	@Override
	public ItemStack getContainerItem(@Nonnull ItemStack itemStack) {
		ItemStack items = super.getContainerItem(itemStack);
		items.setTagCompound(itemStack.getTagCompound());
		return items;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (!stack.isEmpty()) {
			if (stack.hasTagCompound()) {
				NBTTagCompound nbt = stack.getTagCompound();
				String target = nbt.getString(RECIPE_TARGET);
				if (!target.isEmpty()) {
					Item targetItem = REGISTRY.getObject(new ResourceLocation(target));
					if (targetItem instanceof ItemModule) {
						tooltip.add(StringUtils.translate("tooltip.programmerForModule"));
						tooltip.add(StringUtils.translate(targetItem.getUnlocalizedName() + ".name"));
					} else if (targetItem instanceof ItemUpgrade) {
						tooltip.add(StringUtils.translate("tooltip.programmerForUpgrade"));
						tooltip.add(StringUtils.translate(targetItem.getUnlocalizedName() + ".name"));
					} else if (targetItem instanceof ItemLogisticsPipe) {
						tooltip.add(StringUtils.translate("tooltip.programmerForPipe"));
						tooltip.add(StringUtils.translate(targetItem.getUnlocalizedName() + ".name"));
					} else {
						tooltip.add(StringUtils.translate("tooltip.programmerForUnknown.1"));
						tooltip.add(StringUtils.translate("tooltip.programmerForUnknown.2"));
						tooltip.add(StringUtils.translate("tooltip.programmerForUnknown.3"));
					}
				}
			} else {
				tooltip.add(StringUtils.translate("tooltip.programmerForUnknown.1"));
				tooltip.add(StringUtils.translate("tooltip.programmerForUnknown.2"));
				tooltip.add(StringUtils.translate("tooltip.programmerForUnknown.3"));
			}
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
