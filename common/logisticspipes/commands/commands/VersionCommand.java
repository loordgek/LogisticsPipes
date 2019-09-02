package logisticspipes.commands.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;

import net.minecraftforge.fml.common.Loader;

import logisticspipes.LPConstants;
import logisticspipes.LogisticsPipes;
import logisticspipes.commands.abstracts.ICommandHandler;
import logisticspipes.ticks.VersionChecker;

public class VersionCommand implements ICommandHandler {

	@Override
	public String[] getNames() {
		return new String[] { "version", "v" };
	}

	@Override
	public boolean isCommandUsableBy(ICommandSender sender) {
		return true;
	}

	@Override
	public String[] getDescription() {
		return new String[] { "Display the used LP version", "and shows, if an update is available" };
	}

	@Override
	public void executeCommand(ICommandSender sender, String[] args) {
		sender.sendMessage(new TextComponentString(String.format("LogisticsPipes %s for Minecraft %s.", Loader.instance().getIndexedModList().get(LPConstants.LP_MOD_ID).getVersion(), LPConstants.MCVersion)));

		VersionChecker versionChecker = LogisticsPipes.versionChecker;
		sender.sendMessage(new TextComponentString(versionChecker.getVersionCheckerStatus()));

		if (versionChecker.isVersionCheckDone() && versionChecker.getVersionInfo().isNewVersionAvailable()) {
			sender.sendMessage(new TextComponentString("Use \"/logisticspipes changelog\" to see a changelog."));
		}
	}
}
